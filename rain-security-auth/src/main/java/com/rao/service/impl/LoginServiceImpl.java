package com.rao.service.impl;

import com.rao.pojo.bo.OauthTokenResponse;
import com.rao.pojo.dto.LoginDTO;
import com.rao.service.LoginService;
import exception.BusinessException;
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
 * @author raojing
 * @date 2019/12/2 14:53
 */
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword())) {
            throw BusinessException.operate("密码错误，请重试");
        }

        String url = "http://localhost:8085/auth/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> param= new LinkedMultiValueMap<String, String>();
        param.add("username", loginDTO.getUsername());
        param.add("password", loginDTO.getPassword());
        param.add("grant_type", "password");
        param.add("client_id", "client");
        param.add("client_secret", "secret");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, headers);
        
        try{
            OauthTokenResponse response = restTemplate.postForObject(url, request, OauthTokenResponse.class);
            return response.getAccess_token();
        }catch (Exception e){
            throw BusinessException.operate("授权失败");
        }
    }
}
