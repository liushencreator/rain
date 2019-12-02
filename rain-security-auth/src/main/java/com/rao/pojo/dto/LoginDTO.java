package com.rao.pojo.dto;

import lombok.Data;

/**
 * 登录参数
 * @author raojing
 * @date 2019/12/2 14:18
 */
@Data
public class LoginDTO {

    /**
     * 用户名
      */    
    private String username;

    /**
     * 密码
     */
    private String password;
    
}
