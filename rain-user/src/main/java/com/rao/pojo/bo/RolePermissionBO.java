package com.rao.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hudelin
 * @className :RolePermissionBO
 * @description : 角色权限
 * @date: 2019-12-17 11:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionBO {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限码
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;
}
