package com.rao.constant.system;

import com.rao.constant.permission.user.SystemCodeConstant;
import com.rao.constant.permission.user.UserCodeConstant;
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
     * 权限组
     */
    ADMIN_PERMISSION_GROUP("权限组", SystemCodeConstant.ADMIN_PERMISSION_GROUP, "权限组"),

    /**
     * 新增权限
     */
    ADMIN_PERMISSION_ADD("新增权限", SystemCodeConstant.ADMIN_PERMISSION_ADD, "新增权限"),

    /**
     * 删除权限
     */
    ADMIN_PERMISSION_DELETE("删除权限", SystemCodeConstant.ADMIN_PERMISSION_DELETE, "删除权限"),

    /**
     * 修改权限
     */
    ADMIN_PERMISSION_UPDATE("修改权限", SystemCodeConstant.ADMIN_PERMISSION_UPDATE, "修改权限"),

    /**
     * 权限列表
     */
    ADMIN_PERMISSION_LIST("权限列表", SystemCodeConstant.ADMIN_PERMISSION_LIST, "权限列表"),

    /**
     * 角色组
     */
    ADMIN_ROLE_GROUP("角色组", SystemCodeConstant.ADMIN_ROLE_GROUP, "角色组"),

    /**
     * 新增角色
     */
    ADMIN_ROLE_ADD("新增角色", SystemCodeConstant.ADMIN_ROLE_ADD, "新增角色"),

    /**
     * 角色删除
     */
    ADMIN_ROLE_DELETE("角色删除", SystemCodeConstant.ADMIN_ROLE_DELETE, "角色删除"),

    /**
     * 角色修改
     */
    ADMIN_ROLE_UPDATE("角色修改", SystemCodeConstant.ADMIN_ROLE_UPDATE, "角色修改"),

    /**
     * 角色列表
     */
    ADMIN_ROLE_LIST("角色列表", SystemCodeConstant.ADMIN_ROLE_LIST, "角色列表"),

    /**
     * 角色列表(全部)
     */
    ADMIN_ROLE_LIST_ALL("角色列表(全部)", SystemCodeConstant.ADMIN_ROLE_LIST_ALL, "角色列表(全部)"),

    /**
     * 角色详情
     */
    ADMIN_ROLE_DETAIL("角色详情", SystemCodeConstant.ADMIN_ROLE_DETAIL, "角色详情"),

    /**
     * 系统用户组
     */
    ADMIN_SYSTEM_USER_GROUP("系统用户组", UserCodeConstant.ADMIN_SYSTEM_USER_GROUP, "系统用户组"),

    /**
     * 系统用户新增
     */
    ADMIN_SYSTEM_USER_ADD("系统用户新增", UserCodeConstant.ADMIN_SYSTEM_USER_ADD, "系统用户新增"),

    /**
     * 系统用户删除
     */
    ADMIN_SYSTEM_USER_DELETE("系统用户删除", UserCodeConstant.ADMIN_SYSTEM_USER_DELETE, "系统用户删除"),

    /**
     * 系统用户修改
     */
    ADMIN_SYSTEM_USER_UPDATE("系统用户修改", UserCodeConstant.ADMIN_SYSTEM_USER_UPDATE, "系统用户修改"),

    /**
     * 系统用户状态修改
     */
    ADMIN_SYSTEM_USER_UPDATE_STATUS("系统用户状态修改", UserCodeConstant.ADMIN_SYSTEM_USER_UPDATE_STATUS, "系统用户状态修改"),

    /**
     * 系统用户列表
     */
    ADMIN_SYSTEM_USER_LIST("系统用户列表", UserCodeConstant.ADMIN_SYSTEM_USER_LIST, "系统用户列表"),

    /**
     * 系统用户角色列表
     */
    ADMIN_SYSTEM_USER_ROLE_LIST("系统用户角色列表", UserCodeConstant.ADMIN_SYSTEM_USER_ROLE_LIST, "系统用户角色列表"),

    /**
     * 系统用户详情
     */
    ADMIN_SYSTEM_USER_DETAIL("系统用户详情", UserCodeConstant.ADMIN_SYSTEM_USER_DETAIL, "系统用户详情"),

    /**
     * 修改密码
     */
    ADMIN_SYSTEM_USER_RESET_PASSWORD("修改密码", UserCodeConstant.ADMIN_SYSTEM_USER_RESET_PASSWORD, "修改密码"),

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
