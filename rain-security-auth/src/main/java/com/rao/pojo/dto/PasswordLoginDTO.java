package com.rao.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 密码登录
 * @author raojing
 * @date 2019/12/2 14:18
 */
@Data
public class PasswordLoginDTO {

    /**
     * 用户名
      */    
    @NotBlank(message = "账号不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    
}
