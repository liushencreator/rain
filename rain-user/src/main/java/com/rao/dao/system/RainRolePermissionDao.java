package com.rao.dao.system;

import com.rao.pojo.entity.system.RainRolePermission;
import java.util.List;
import java.util.Map;

/**
 * DAO - RainRolePermission(角色权限关系表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface RainRolePermissionDao {

    RainRolePermission find(Long id);

    List<RainRolePermission> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(RainRolePermission video);

    Integer update(RainRolePermission video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<RainRolePermission> findByParams(Map<String, Object> var1);

    List<RainRolePermission> findByPage(Map<String, Object> var1);

    /**
     * insertSelective
     * @param rainRolePermission
     * @return
     */
    Integer insertSelective(RainRolePermission rainRolePermission);

}
