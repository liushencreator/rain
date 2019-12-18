package com.rao.dao.system;

import com.rao.mapper.RainBaseDao;
import com.rao.pojo.entity.system.RainRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainRolePermission(角色权限关系表)
 * 
 * @author raojing
 * @version 2.0
 */
public interface RainRolePermissionDao extends RainBaseDao<RainRolePermission> {

    /**
     * 保存角色权限关系
     * @param rolePermissions
     * @return
     */
    Integer batchSaveRelation(@Param("rolePermissions") List<RainRolePermission> rolePermissions);

    /**
     * 删除角色关联的所有
     * @param roleId
     * @param permissionIds
     */
    void deleteByRoleIdAndPermissions(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);
}
