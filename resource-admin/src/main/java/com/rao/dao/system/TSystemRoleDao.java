package com.rao.dao.system;

import pojo.entity.system.TSystemRole;

import java.util.List;
import java.util.Map;

/**
 * DAO - TSystemRole(系统角色表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface TSystemRoleDao {

    TSystemRole find(Long id);

    List<TSystemRole> findAll();

    Integer count(Map<String, Object> var1);

    Integer insert(TSystemRole video);

    Integer update(TSystemRole video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<TSystemRole> findByParams(Map<String, Object> var1);

    List<TSystemRole> findByPage(Map<String, Object> var1);

}
