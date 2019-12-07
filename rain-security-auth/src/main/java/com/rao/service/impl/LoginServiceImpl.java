package com.rao.service.impl;

import com.rao.constant.user.UserTypeEnum;
import com.rao.exception.BusinessException;
import com.rao.pojo.bo.OauthTokenResponse;
import com.rao.pojo.dto.LoginDTO;
import com.rao.service.LoginService;
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

import javax.annotation.Resource;

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
        return requestAccessToken(userName, loginDTO.getPassword());
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
