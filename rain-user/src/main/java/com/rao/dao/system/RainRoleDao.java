package com.rao.dao.system;

import com.rao.pojo.entity.system.RainRole;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainRole(角色)
 * 
 * @author raojing
 * @version 2.0
 */
public interface RainRoleDao {

    RainRole find(Long id);

    List<RainRole> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(RainRole video);

    Integer update(RainRole video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<RainRole> findByParams(Map<String, Object> var1);

    List<RainRole> findByPage(Map<String, Object> var1);

    /**
     * insertSelective
     * @param rainRole
     * @return
     */
    Long insertSelective(RainRole rainRole);

}
