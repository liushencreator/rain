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

    List<RainUserRole> findAll();

    Integer count(Map<String, Object> var1);

    Integer insert(RainUserRole video);

    List<RainUserRole> findByParams(Map<String, Object> var1);

}
