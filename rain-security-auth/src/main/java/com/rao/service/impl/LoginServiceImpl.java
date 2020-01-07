package com.rao.service.impl;

import com.rao.component.LoginLogoutProducer;
import com.rao.constant.user.UserTypeEnum;
import com.rao.dto.IpInfo;
import com.rao.exception.BusinessException;
import com.rao.pojo.bo.OauthTokenResponse;
import com.rao.pojo.bo.SystemUserLoginLogoutLogBO;
import com.rao.pojo.dto.LoginDTO;
import com.rao.service.LoginService;
import com.rao.util.CopyUtil;
import com.rao.util.common.UserAgentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author raojing
 * @date 2019/12/2 14:53
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoginLogoutProducer loginLogoutProducer;

    @Override
    public String loginAdmin(LoginDTO loginDTO) {
        // 认证
        String userName = UserTypeEnum.ADMIN.getValue() + ":" + loginDTO.getUsername();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if(userDetails == null){
            throw BusinessException.operate("用户不存在");
        }else if(!passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword())){
            throw BusinessException.operate("密码错误，请重试");
        }
        // 获取 access_token
        String accessToken = requestAccessToken(userName, loginDTO.getPassword());

        //发送登录日志
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        IpInfo ipInfo = UserAgentUtils.getIpInfo(UserAgentUtils.getIpAddr(request));
        SystemUserLoginLogoutLogBO systemUserLoginLogoutLogBO = CopyUtil.transToO(ipInfo, SystemUserLoginLogoutLogBO.class);
        loginLogoutProducer.sendMsg(systemUserLoginLogoutLogBO);

        return accessToken;
    }

    /**
     * 获取 access_token
     * @param userName
     * @param password
     * @return
     */
    private String requestAccessToken(String userName, String password){
        String url = "http://localhost:8086/auth/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> param= new LinkedMultiValueMap<String, String>();
        param.add("username", userName);
        param.add("password", password);
        param.add("grant_type", "password");
        param.add("client_id", "client");
        param.add("client_secret", "secret");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, headers);

        try{
            OauthTokenResponse response = restTemplate.postForObject(url, request, OauthTokenResponse.class);
            return response.getAccess_token();
        }catch (Exception e){
            log.info("授权失败:{}", e.getMessage());
            throw BusinessException.operate("账号不可用");
        }
    }

}
