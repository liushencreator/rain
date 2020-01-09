package com.rao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rao.cache.key.MessageCacheKey;
import com.rao.component.LoginLogoutProducer;
import com.rao.constant.common.StateConstants;
import com.rao.constant.server.ServiceInstanceConstant;
import com.rao.constant.sms.SmsOperationTypeEnum;
import com.rao.constant.user.UserCommonConstant;
import com.rao.constant.user.UserTypeEnum;
import com.rao.dao.RainSystemUserDao;
import com.rao.dto.WxUserInfo;
import com.rao.exception.BusinessException;
import com.rao.pojo.bo.OauthTokenResponse;
import com.rao.pojo.bo.UserLoginLogoutLogBO;
import com.rao.pojo.dto.PasswordLoginDTO;
import com.rao.pojo.dto.RefreshTokenDTO;
import com.rao.pojo.dto.SmsCodeLoginDTO;
import com.rao.pojo.dto.WxLoginDTO;
import com.rao.pojo.entity.RainSystemUser;
import com.rao.pojo.vo.LoginSuccessVO;
import com.rao.service.LoginService;
import com.rao.util.CopyUtil;
import com.rao.util.cache.RedisTemplateUtils;
import com.rao.util.wx.WxAppletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    private RedisTemplateUtils redisTemplateUtils;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private TokenStore tokenStore;
    @Resource
    private LoginLogoutProducer loginLogoutProducer;

    @Override
    public LoginSuccessVO loginAdmin(PasswordLoginDTO passwordLoginDTO) {
        // 认证
        String userName = passwordLoginDTO.getUsername();
        RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(userName);
        if (systemUser == null) {
            throw BusinessException.operate("账号不存在");
        } else {
            if (!systemUser.getStatus().equals(StateConstants.STATE_ENABLE)) {
                throw BusinessException.operate("账号不可用");
            }
            if (!passwordEncoder.matches(passwordLoginDTO.getPassword(), systemUser.getPassword())) {
                throw BusinessException.operate("密码错误，请重新输入");
            }
        }
        // 获取 access_token
        LoginSuccessVO loginSuccessVO = requestAccessToken(buildLoginParam(UserTypeEnum.ADMIN.getValue(), userName, passwordLoginDTO.getPassword(), true));
        //发送登录日志
        UserLoginLogoutLogBO userLoginLogoutLogBO= CopyUtil.transToO(systemUser,UserLoginLogoutLogBO.class);
        userLoginLogoutLogBO.setUserType(UserTypeEnum.ADMIN.getValue());
        loginLogoutProducer.LoginSendLogMsg(userLoginLogoutLogBO);
        return loginSuccessVO;
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
    public void logout() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        //发送登出日志
        loginLogoutProducer.sendLogMsg();
    }

    @Override
    public LoginSuccessVO wxLoginCUser(WxLoginDTO wxLoginDTO) {
        JSONObject sessionKeyOropenid = WxAppletUtils.getSessionKeyOropenid(wxLoginDTO.getCode());
        String openId = sessionKeyOropenid.getString("openid");
        String sessionKey = sessionKeyOropenid.getString("session_key");
        if(StringUtils.isBlank(openId)||StringUtils.isBlank(sessionKey)){
            throw BusinessException.operate("参数无效");
        }

        // 获取微信用户信息
        WxUserInfo userInfo = WxAppletUtils.getUserInfo(wxLoginDTO.getEncryptedData(), sessionKey, wxLoginDTO.getIv());

        return null;
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
