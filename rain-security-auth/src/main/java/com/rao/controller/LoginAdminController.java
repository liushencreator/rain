package com.rao.controller;

import com.rao.pojo.dto.LoginDTO;
import com.rao.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.result.ResultMessage;

import javax.annotation.Resource;

/**
 * 登录管理
 * @author raojing
 * @date 2019/12/2 14:14
 */
@RestController
@RequestMapping(value = "/admin/user")
public class LoginAdminController {

    @Resource
    private LoginService loginService;

    /**
     * 后台用户登录
     * @param loginDTO
     * @return
     */
    @PostMapping(value = "login")
    public ResultMessage login(@RequestBody LoginDTO loginDTO) {
        String accessToken = loginService.loginAdmin(loginDTO);
        return ResultMessage.success().addMessage("登录成功").add("access_token", accessToken);
    }
    
}
