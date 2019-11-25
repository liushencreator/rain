package com.rao.dao.admin;

import pojo.entity.system.RainSystemUser;
import pojo.entity.system.RainSystemUser;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainSystemUser(系统用户表)
 * 
 * @author raojing
 * @version 2.0
 */
public interface RainSystemUserDao {

    RainSystemUser find(Long id);

    List<RainSystemUser> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(RainSystemUser video);

    Integer update(RainSystemUser video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<RainSystemUser> findByParams(Map<String, Object> var1);

    List<RainSystemUser> findByPage(Map<String, Object> var1);
    
    /**
     * insertSelective
     * @param rainSystemUser
     * @return
     */
    Integer insertSelective(RainSystemUser rainSystemUser);

}
