package com.rao.controller;

import com.rao.annotation.BeanValid;
import com.rao.pojo.dto.*;
import com.rao.pojo.vo.LoginSuccessVO;
import com.rao.service.LoginService;
import com.rao.service.UserService;
import com.rao.util.result.ResultMessage;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private UserService userService;
    @Resource
    private TokenStore tokenStore;

    /**
     * 后台用户账号密码登录
     * @param passwordLoginDTO
     * @return
     */
    @PostMapping(value = "/login/system_user")
    public ResultMessage<LoginSuccessVO> loginSystemUser(@BeanValid @RequestBody PasswordLoginDTO passwordLoginDTO) {
        LoginSuccessVO loginSuccessVO = loginService.loginAdmin(passwordLoginDTO);
        return ResultMessage.success(loginSuccessVO).message("登录成功");
    }

    /**
     * 后台用户短信验证码登录
     * @param smsCodeLoginDTO
     * @return
     */
    @PostMapping(value = "/login/sms_code/system_user")
    public ResultMessage<LoginSuccessVO> smsCodeLoginSystemUser(@BeanValid @RequestBody SmsCodeLoginDTO smsCodeLoginDTO){
        LoginSuccessVO loginSuccessVO = loginService.smsCodeLoginSystemUser(smsCodeLoginDTO);
        return ResultMessage.success(loginSuccessVO).message("登录成功");
    }

    /**
     * C端用户账号密码登录
     * @return
     */
    @PostMapping(value = "/login/c_user")
    public ResultMessage<LoginSuccessVO> loginCUser(){
        return ResultMessage.fail().message("暂未实现");
    }

    /**
     * C端用户登录微信第三方登录
     * @param wxLoginDTO
     * @return
     */
    @PostMapping(value = "/login/wx/c_user")
    public ResultMessage wxLoginCUser(@BeanValid @RequestBody WxLoginDTO wxLoginDTO){
        LoginSuccessVO loginSuccessVO = loginService.wxLoginCUser(wxLoginDTO);
        return ResultMessage.success(loginSuccessVO).message("登录成功");
    }

    /**
     * 刷新token
     * @param refreshTokenDTO
     * @return
     */
    @PostMapping(value = "/refresh_token")
    public ResultMessage<LoginSuccessVO> refreshToken(@BeanValid @RequestBody RefreshTokenDTO refreshTokenDTO){
        LoginSuccessVO loginSuccessVO = loginService.refreshToken(refreshTokenDTO);
        return ResultMessage.success(loginSuccessVO).message("令牌刷新成功");
    }

    /**
     * 检查账号（验证码发送前校验）
     * @param smsSendDTO
     * @return
     */
    @PostMapping(value = "/check_account")
    public ResultMessage checkAccount(@BeanValid @RequestBody SmsSendDTO smsSendDTO){
        userService.checkAccount(smsSendDTO);
        return ResultMessage.success();
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
