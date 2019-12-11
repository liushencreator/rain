package com.rao.constant.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色枚举
 * @author raojing
 * @date 2019/12/11 19:41
 */
@AllArgsConstructor
public enum RoleEnum {

    /**
     * 系统超级管理员角色
     */
    SYSTEM_SUPER_MANAGER_ROLE("超级管理员", "super:system:user", "最高角色"),

    /**
     * 普通用户角色
     */
    C_USER_ROLE("普通用户", "c:user", "普通用户角色"),
    ;

    /**
     * 角色名称
     */
    @Getter
    private String roleName;

    /**
     * 角色标识
     */
    @Getter
    private String roleCode;

    /**
     * 角色描述
     */
    @Getter
    private String roleDesc;
    
}
