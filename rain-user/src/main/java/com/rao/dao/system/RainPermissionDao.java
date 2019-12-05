package com.rao.dao.system;

import com.rao.pojo.entity.system.RainPermission;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainPermission(权限)
 * 
 * @author zijing
 * @version 2.0
 */
public interface RainPermissionDao {

    RainPermission find(Long id);

    List<RainPermission> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(RainPermission video);

    Integer update(RainPermission video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<RainPermission> findByParams(Map<String, Object> var1);

    List<RainPermission> findByPage(Map<String, Object> var1);

    /**
     * insertSelective
     * @param rainPermission
     * @return
     */
    Integer insertSelective(RainPermission rainPermission);

}
