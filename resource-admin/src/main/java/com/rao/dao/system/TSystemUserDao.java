package com.rao.dao.system;

import pojo.entity.system.TSystemUser;

import java.util.List;
import java.util.Map;

/**
 * DAO - TSystemUser(系统管理员表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface TSystemUserDao {

    TSystemUser find(Long id);

    List<TSystemUser> findAll();

    Integer count(Map<String, Object> var1);

    Integer insert(TSystemUser video);

    Integer update(TSystemUser video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<TSystemUser> findByParams(Map<String, Object> var1);

    List<TSystemUser> findByPage(Map<String, Object> var1);
    
}
