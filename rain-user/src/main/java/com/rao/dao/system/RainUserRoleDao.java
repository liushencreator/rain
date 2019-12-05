package com.rao.dao.system;

import com.rao.pojo.entity.system.RainUserRole;
import java.util.List;
import java.util.Map;

/**
 * DAO - RainUserRole(用户角色关系表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface RainUserRoleDao {

    RainUserRole find(Long id);

    List<RainUserRole> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(RainUserRole video);

    Integer update(RainUserRole video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<RainUserRole> findByParams(Map<String, Object> var1);

    List<RainUserRole> findByPage(Map<String, Object> var1);

    /**
     * insertSelective
     * @param rainUserRole
     * @return
     */
    Integer insertSelective(RainUserRole rainUserRole);

}
