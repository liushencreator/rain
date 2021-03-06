package com.rao.service.impl;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.rao.cache.key.MessageCacheKey;
import com.rao.client.MemberWalletClient;
import com.rao.constant.common.DateFormatEnum;
import com.rao.constant.common.StateConstants;
import com.rao.constant.server.ServiceInstanceConstant;
import com.rao.constant.sms.SmsOperationTypeEnum;
import com.rao.constant.user.UserCommonConstant;
import com.rao.constant.user.UserTypeEnum;
import com.rao.dao.RainMemberDao;
import com.rao.dao.RainSystemUserDao;
import com.rao.dto.WxUserInfo;
import com.rao.exception.BusinessException;
import com.rao.pojo.bo.OauthTokenResponse;
import com.rao.pojo.dto.PasswordLoginDTO;
import com.rao.pojo.dto.RefreshTokenDTO;
import com.rao.pojo.dto.SmsCodeLoginDTO;
import com.rao.pojo.dto.WxLoginDTO;
import com.rao.pojo.entity.RainMember;
import com.rao.pojo.entity.RainSystemUser;
import com.rao.pojo.vo.LoginSuccessVO;
import com.rao.service.LoginService;
import com.rao.util.cache.RedisTemplateUtils;
import com.rao.util.common.RandomUtils;
import com.rao.util.common.TwiterIdUtil;
import com.rao.util.wx.WxAppletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 登录 service 实现
 * @author raojing
 * @date 2019/12/2 14:53
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;
    @Resource
    private RainMemberDao rainMemberDao;
    @Resource
    private RedisTemplateUtils redisTemplateUtils;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private MemberWalletClient memberWalletClient;

    @Override
    public LoginSuccessVO loginAdmin(PasswordLoginDTO passwordLoginDTO) {
        // 认证
        String userName = passwordLoginDTO.getUsername();
        RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(userName);
        if(systemUser == null){
            throw BusinessException.operate("账号不存在");
        }else{
            if(!systemUser.getStatus().equals(StateConstants.STATE_ENABLE)){
                throw BusinessException.operate("账号不可用");
            }
            if(!passwordEncoder.matches(passwordLoginDTO.getPassword(), systemUser.getPassword())){
                throw BusinessException.operate("密码错误，请重新输入");
            }
        }
        // 获取 access_token
        return requestAccessToken(buildLoginParam(UserTypeEnum.ADMIN.getValue(), userName, passwordLoginDTO.getPassword(), true));
    }

    @Override
    public LoginSuccessVO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        Integer accountType = refreshTokenDTO.getAccountType();
        String refreshToken = refreshTokenDTO.getRefreshToken();
        String type = accountType == 1 ? UserTypeEnum.ADMIN.getValue() : UserTypeEnum.C_USER.getValue();
        return requestAccessToken(buildRefreshTokenParam(type, refreshToken, refreshTokenDTO.getLoginType().equals(UserCommonConstant.PWD_LOGIN)));
    }

    @Override
    public LoginSuccessVO smsCodeLoginSystemUser(SmsCodeLoginDTO smsCodeLoginDTO) {
        String phone = smsCodeLoginDTO.getPhone();
        String smsCode = smsCodeLoginDTO.getSmsCode();
        // 通过手机号码查询用户信息
        RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(phone);
        if(systemUser == null || !systemUser.getStatus().equals(StateConstants.STATE_ENABLE)){
            throw BusinessException.operate("账号不存在或不可用");
        }
        String smsCacheKey = MessageCacheKey.smsCacheKey(SmsOperationTypeEnum.LOGIN, UserTypeEnum.ADMIN.getValue(), phone);
        if(!redisTemplateUtils.isExists(smsCacheKey)){
            throw BusinessException.operate("验证码已过期，请重新获取");
        }
        // 校验验证码
        String smsCacheCode = redisTemplateUtils.get(smsCacheKey);
        if(!smsCacheCode.equals(smsCode)){
            throw BusinessException.operate("验证码不正确");
        }
        // 获取 access_token
        LoginSuccessVO loginSuccessVO = requestAccessToken(buildLoginParam(UserTypeEnum.ADMIN.getValue(), phone, "", false));
        return loginSuccessVO;
    }

    @Override
    public LoginSuccessVO wxLoginCUser(WxLoginDTO wxLoginDTO) {
        JSONObject sessionKeyOropenid = WxAppletUtils.getSessionKeyOropenid(wxLoginDTO.getCode());
        log.info("获取微信登录信息:{}", sessionKeyOropenid.toJSONString());
        String openId = sessionKeyOropenid.getString("openid");
        String sessionKey = sessionKeyOropenid.getString("session_key");
        if(StringUtils.isBlank(openId)|| StringUtils.isBlank(sessionKey)){
            log.error("openId:{}, sessionKey:{}", openId, sessionKey);
            throw BusinessException.operate("参数无效");
        }
        
        // 获取微信用户信息
        WxUserInfo userInfo = WxAppletUtils.getUserInfo(wxLoginDTO.getEncryptedData(), sessionKey, wxLoginDTO.getIv());
        log.info("解密微信用户授权信息:{}", userInfo);

        // 通过openID查询用户信息
        RainMember rainMember = rainMemberDao.findByWxOpenId(openId);
        if(rainMember == null){
            // 注册用户
            this.registerMember(openId, userInfo);
        }

        if(!rainMember.getStatus().equals(StateConstants.STATE_ENABLE)){
            throw BusinessException.operate("账号不可用");
        }
        
        return null;
    }

    /**
     * 初始化会员账号
     * @param wxOpenId
     * @param wxUserInfo
     * @return
     */
    private RainMember registerMember(String wxOpenId, WxUserInfo wxUserInfo){
        String userName = RandomUtils.randomStringWithTime(6, DateFormatEnum.FORMAT_CONNECT_EXTEND.getFormatString());
        Long memberId = TwiterIdUtil.getTwiterId();
        Date now = new Date();
        RainMember rainMember = new RainMember();
        rainMember.setId(memberId);
        rainMember.setUserName(userName);
        rainMember.setPhone(wxUserInfo.getPhoneNumber());
        // 初始密码为 "" 的加密串
        rainMember.setPassword(UserCommonConstant.DEFAULT_PWD);
        rainMember.setNickname(userName);
        rainMember.setWxOpenid(wxOpenId);
        rainMember.setWxNickname(wxUserInfo.getNickName());
        rainMember.setEmail("");
        rainMember.setAvatar(wxUserInfo.getAvatarUrl());
        rainMember.setGender(wxUserInfo.getGender().equals("男") ? 1 : 2);
        rainMember.setStatus(StateConstants.STATE_ENABLE);
        rainMember.setPersonalSignature("");
        rainMember.setBirthday(now);
        rainMember.setCreateTime(now);
        rainMember.setUpdateTime(now);
        rainMemberDao.insert(rainMember);

        memberWalletClient.init();
        return rainMember;
    }

    /**
     * 获取 access_token
     * @param requestParam
     * @return
     */
    private LoginSuccessVO requestAccessToken(MultiValueMap<String, String> requestParam){
        ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceInstanceConstant.RAIN_AUTH);
        if(serviceInstance == null){
            throw BusinessException.operate("当前系统不稳定，请检查注册中心状态");
        }

        String url = serviceInstance.getUri() + "/auth/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(requestParam, headers);
        try{
            OauthTokenResponse response = restTemplate.postForObject(url, request, OauthTokenResponse.class);
            return LoginSuccessVO.builder()
                    .accessToken(response.getAccess_token())
                    .refreshToken(response.getRefresh_token())
                    .expire(response.getExpires_in())
                    .build();
        }catch (Exception e){
            log.info("授权失败:{}", e.getMessage());
            throw BusinessException.operate("账号不可用");
        }
    }

    /**
     * 构建登录请求参数
     * @param accountType
     * @param userName
     * @param password
     * @param pwdLogin
     * @return
     */
    private MultiValueMap<String, String> buildLoginParam(String accountType, String userName, String password, boolean pwdLogin){
        MultiValueMap<String, String> param= new LinkedMultiValueMap<String, String>();
        param.add("username", userName);
        param.add("password", password);
        param.add("grant_type", "password");
        param.add("client_id", "client");
        param.add("client_secret", "secret");
        // 刷新token的用户类型
        param.add("account_type", accountType);
        // 是否密码登录
        param.add("pwdLogin", pwdLogin ? "true" : "false");
        return param;
    }

    /**
     * 构建刷新token的请求参数
     * @param accountType
     * @param refreshToken
     * @return
     */
    private MultiValueMap<String, String> buildRefreshTokenParam(String accountType, String refreshToken, boolean pwdLogin){
        MultiValueMap<String, String> param= new LinkedMultiValueMap<String, String>();
        param.add("refresh_token", refreshToken);
        param.add("grant_type", "refresh_token");
        param.add("client_id", "client");
        param.add("client_secret", "secret");
        // 刷新token的用户类型
        param.add("account_type", accountType);
        // 是否密码登录
        param.add("pwdLogin", pwdLogin ? "true" : "false");
        return param;
    }

}
