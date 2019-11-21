package com.rao.dao.admin;

import pojo.entity.system.RainSystemMenu;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainSystemMenu(系统菜单)
 * 
 * @author raojing
 * @version 2.0
 */
public interface RainSystemMenuDao {

    RainSystemMenu find(Long id);

    List<RainSystemMenu> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(RainSystemMenu video);

    Integer update(RainSystemMenu video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<RainSystemMenu> findByParams(Map<String, Object> var1);

    List<RainSystemMenu> findByPage(Map<String, Object> var1);

    /**
     * insertSelective
     * @param rainSystemMenu
     * @return
     */
    Long insertSelective(RainSystemMenu rainSystemMenu);

}
