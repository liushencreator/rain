package com.rao.dao.system;

import com.rao.mapper.RainBaseDao;
import com.rao.pojo.entity.system.RainUserRole;
import java.util.List;
import java.util.Map;

/**
 * DAO - RainUserRole(用户角色关系表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface RainUserRoleDao extends RainBaseDao<RainUserRole> {

    void batchSaveRelation(List<RainUserRole> userRoleList);
}
