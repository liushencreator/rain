package com.rao.constant.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限枚举
 * @author raojing
 * @date 2019/12/11 19:40
 */
@AllArgsConstructor
public enum PermissionEnum {

    /**
     * 系统超级管理员权限
     */
    SYSTEM_SUPER_MANAGER_PERMISSION("超级管理员组", "super:system:user", "最高权限"),

    /**
     * 普通用户权限
     */
    C_USER_PERMISSION("普通用户组", "c:user", "普通用户权限"),
    ;

    /**
     * 权限名称
     */
    @Getter
    private String permissionName;

    /**
     * 权限码
     */
    @Getter
    private String permissionCode;

    /**
     * 权限描述
     */
    @Getter
    private String permissionDesc;
    
}
