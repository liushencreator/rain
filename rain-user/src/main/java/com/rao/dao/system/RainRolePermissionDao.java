package com.rao.dao.system;

import com.rao.pojo.entity.system.RainRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainRolePermission(角色权限关系表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface RainRolePermissionDao {

    List<RainRolePermission> findAll();

    Integer count(Map<String, Object> var1);

    Integer insert(RainRolePermission video);

    List<RainRolePermission> findByParams(Map<String, Object> var1);

    /**
     * 保存角色权限关系
     * @param rolePermissions
     * @return
     */
    Integer batchSaveRelation(@Param("rolePermissions") List<RainRolePermission> rolePermissions);

}
