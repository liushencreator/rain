package com.rao.constant.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 超级管理员初始化
 * @author raojing
 * @date 2019/12/11 19:45
 */
@AllArgsConstructor
public enum SuperAdminInitEnum {

    /**
     * 系统超级管理员
     */
    SYSTEM_SUPER_MANAGER("admin", "123456"),
    ;

    /**
     * 初始化用户名
     */
    @Getter
    private String initUserName;

    /**
     * 初始化密码
     */
    @Getter
    private String initPassword;    
    
}
