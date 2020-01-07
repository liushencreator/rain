package com.rao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rao.constant.server.ServiceInstanceConstant;
import com.rao.constant.user.UserTypeEnum;
import com.rao.dto.WxUserInfo;
import com.rao.exception.BusinessException;
import com.rao.pojo.bo.OauthTokenResponse;
import com.rao.pojo.dto.PasswordLoginDTO;
import com.rao.pojo.dto.RefreshTokenDTO;
import com.rao.pojo.dto.SmsCodeLoginDTO;
import com.rao.pojo.dto.WxLoginDTO;
import com.rao.pojo.vo.LoginSuccessVO;
import com.rao.service.LoginService;
import com.rao.util.wx.WxAppletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private final static int OPERATION_LOGIN = 1;
    private final static int OPERATION_REFRESH_TOKEN = 2;

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Override
    public LoginSuccessVO loginAdmin(PasswordLoginDTO passwordLoginDTO) {
        // 认证
        String userName = UserTypeEnum.ADMIN.getValue() + ":" + passwordLoginDTO.getUsername();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if(userDetails == null){
            throw BusinessException.operate("用户不存在");
        }else if(!passwordEncoder.matches(passwordLoginDTO.getPassword(), userDetails.getPassword())){
            throw BusinessException.operate("密码错误，请重试");
        }
        // 获取 access_token
        return requestAccessToken(buildLoginParam(userName, passwordLoginDTO.getPassword()), OPERATION_LOGIN);
    }

    @Override
    public LoginSuccessVO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        Integer accountType = refreshTokenDTO.getAccountType();
        String refreshToken = refreshTokenDTO.getRefreshToken();
        String type = accountType == 1 ? UserTypeEnum.ADMIN.getValue() : UserTypeEnum.C_USER.getValue();
        return requestAccessToken(buildRefreshTokenParam(type, refreshToken), OPERATION_REFRESH_TOKEN);
    }

    @Override
    public LoginSuccessVO smsCodeLoginSystemUser(SmsCodeLoginDTO smsCodeLoginDTO) {

        return null;
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
     * @param operationType
     * @return
     */
    private LoginSuccessVO requestAccessToken(MultiValueMap<String, String> requestParam, Integer operationType){
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
     * @param userName
     * @param password
     * @return
     */
    private MultiValueMap<String, String> buildLoginParam(String userName, String password){
        MultiValueMap<String, String> param= new LinkedMultiValueMap<String, String>();
        param.add("username", userName);
        param.add("password", password);
        param.add("grant_type", "password");
        param.add("client_id", "client");
        param.add("client_secret", "secret");
        return param;
    }

    /**
     * 构建刷新token的请求参数
     * @param accountType
     * @param refreshToken
     * @return
     */
    private MultiValueMap<String, String> buildRefreshTokenParam(String accountType, String refreshToken){
        MultiValueMap<String, String> param= new LinkedMultiValueMap<String, String>();
        param.add("refresh_token", refreshToken);
        param.add("grant_type", "refresh_token");
        param.add("client_id", "client");
        param.add("client_secret", "secret");
        // 刷新token的用户类型
        param.add("account_type", accountType);
        return param;
    }

}
