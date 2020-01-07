package com.rao.controller;

import com.rao.component.LoginLogoutProducer;
import com.rao.pojo.dto.LoginDTO;
import com.rao.service.LoginService;
import com.rao.util.result.ResultMessage;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录管理
 * @author raojing
 * @date 2019/12/2 14:14
 */
@RestController
public class UserLoginController {

    @Resource
    private LoginService loginService;
    @Resource
    private TokenStore tokenStore;
    @Resource
    private LoginLogoutProducer loginLogoutProducer;

    /**
     * 后台用户登录
     * @param loginDTO
     * @return
     */
    @PostMapping(value = "/login/system_user")
    public ResultMessage<String> loginSystemUser(@RequestBody LoginDTO loginDTO) {
        String accessToken = loginService.loginAdmin(loginDTO);
        loginLogoutProducer.sendMsg();
        return ResultMessage.success(accessToken).message("登录成功");
    }

    /**
     * B 端用户登录
     * @return
     */
    @PostMapping(value = "/login/b_user")
    public ResultMessage<String> loginBUser(){
        return ResultMessage.fail().message("暂未实现");
    }

    /**
     * C 端用户登录
     * @return
     */
    @PostMapping(value = "/login/c_user")
    public ResultMessage<String> loginCUser(){
        return ResultMessage.fail().message("暂未实现");
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @PostMapping(value = "/user/logout")
    public ResultMessage logout(HttpServletRequest request){
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return ResultMessage.success().message("用户注销成功");
    }
    
}
