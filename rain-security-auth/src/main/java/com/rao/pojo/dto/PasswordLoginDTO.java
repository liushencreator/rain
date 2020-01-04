package com.rao.pojo.dto;

import lombok.Data;

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
    private String username;

    /**
     * 密码
     */
    private String password;
    
}
