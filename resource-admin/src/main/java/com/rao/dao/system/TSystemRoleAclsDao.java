package com.rao.dao.system;

import pojo.entity.system.TSystemRoleAcls;

import java.util.List;
import java.util.Map;

/**
 * DAO - TSystemRoleAcls(系统角色权限表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface TSystemRoleAclsDao {

    TSystemRoleAcls find(Long id);

    List<TSystemRoleAcls> findAll();

    Integer count(Map<String, Object> var1);

    Integer insert(TSystemRoleAcls video);

    Integer update(TSystemRoleAcls video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<TSystemRoleAcls> findByParams(Map<String, Object> var1);

    List<TSystemRoleAcls> findByPage(Map<String, Object> var1);
    
}
