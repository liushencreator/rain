package com.rao.constant.permission.user;

import com.rao.annotation.PermissionDesc;

/**
 * 用户服务-system-权限标识
 * @author raojing
 * @date 2019/12/17 19:59
 */
public interface SystemCodeConstant {

    /********************************* 权限相关 *********************************/
    /**
     * 权限组
     */
    @PermissionDesc(desc = "权限组")
    String ADMIN_PERMISSION_GROUP = "admin:permission:group";
    
    /**
     * 新增权限
     */
    @PermissionDesc(desc = "新增权限")
    String ADMIN_PERMISSION_ADD = "admin:permission:add";

    /**
     * 删除权限
     */
    @PermissionDesc(desc = "删除权限")
    String ADMIN_PERMISSION_DELETE = "admin:permission:delete";
    
    /**
     * 修改权限
     */
    @PermissionDesc(desc = "修改权限")
    String ADMIN_PERMISSION_UPDATE = "admin:permission:update";
    
    /**
     * 权限列表
     */
    @PermissionDesc(desc = "权限列表")
    String ADMIN_PERMISSION_LIST = "admin:permission:list";
    /********************************* 权限相关 *********************************/

    /********************************* 角色相关 *********************************/
    /**
     * 角色组
      */    
    @PermissionDesc(desc = "角色组")
    String ADMIN_ROLE_GROUP = "admin:role:group";
    
    /**
     * 新增角色
     */
    @PermissionDesc(desc = "新增角色")
    String ADMIN_ROLE_ADD = "admin:role:add";
    
    /**
     * 角色删除
     */
    @PermissionDesc(desc = "角色删除")
    String ADMIN_ROLE_DELETE = "admin:role:delete";

    /**
     * 角色修改
     */
    @PermissionDesc(desc = "角色修改")
    String ADMIN_ROLE_UPDATE = "admin:role:update";
    
    /**
     * 角色列表
     */
    @PermissionDesc(desc = "角色列表")
    String ADMIN_ROLE_LIST = "admin:role:list";

    /**
     * 角色列表(全部)
     */
    @PermissionDesc(desc = "角色列表(全部)")
    String ADMIN_ROLE_LIST_ALL = "admin:role:list:all";

    /**
     * 角色详情
     */
    @PermissionDesc(desc = "角色详情")
    String ADMIN_ROLE_DETAIL = "admin:role:detail";
    /********************************* 角色相关 *********************************/   
    
}
