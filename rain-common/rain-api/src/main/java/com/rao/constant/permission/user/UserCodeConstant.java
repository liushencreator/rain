package com.rao.constant.permission.user;

import com.rao.annotation.PermissionDesc;

/**
 * 用户服务-user-权限标识
 * @author raojing
 * @date 2019/12/17 20:17
 */
public interface UserCodeConstant {

    /********************************* 系统用户管理相关 *********************************/
    /**
     * 系统用户组
     */
    @PermissionDesc(desc = "系统用户组")
    String ADMIN_SYSTEM_USER_GROUP = "admin:system:user:group";
    
    /**
     * 系统用户新增
     */
    @PermissionDesc(desc = "系统用户新增")
    String ADMIN_SYSTEM_USER_ADD = "admin:system:user:add";

    /**
     * 系统用户删除
     */
    @PermissionDesc(desc = "系统用户删除")
    String ADMIN_SYSTEM_USER_DELETE = "admin:system:user:delete";

    /**
     * 系统用户修改
     */
    @PermissionDesc(desc = "系统用户修改")
    String ADMIN_SYSTEM_USER_UPDATE = "admin:system:user:update";

    /**
     * 系统用户状态修改
     */
    @PermissionDesc(desc = "系统用户状态修改")
    String ADMIN_SYSTEM_USER_UPDATE_STATUS = "admin:system:user:update:status";

    /**
     * 系统用户列表
     */
    @PermissionDesc(desc = "系统用户列表")
    String ADMIN_SYSTEM_USER_LIST = "admin:system:user:list";

    /**
     * 系统用户角色列表
     */
    @PermissionDesc(desc = "系统用户角色列表")
    String ADMIN_SYSTEM_USER_ROLE_LIST = "admin:system:user:role:list";

    /**
     * 系统用户详情
     */
    @PermissionDesc(desc = "系统用户详情")
    String ADMIN_SYSTEM_USER_DETAIL = "admin:system:user:detail";

    /**
     * 修改密码
     */
    @PermissionDesc(desc = "修改密码")
    String ADMIN_SYSTEM_USER_RESET_PASSWORD = "admin:system:user:reset:password";
    /********************************* 系统用户管理相关 *********************************/    
}
