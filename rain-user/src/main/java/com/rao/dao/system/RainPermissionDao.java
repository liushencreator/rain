package com.rao.dao.system;

import com.rao.mapper.RainBaseDao;
import com.rao.pojo.entity.system.RainPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainPermission(权限)
 * 
 * @author zijing
 * @version 2.0
 */
public interface RainPermissionDao extends RainBaseDao<RainPermission> {

    /**
     * 根据id查询权限列表
     * @param permissions
     * @return
     */
    List<RainPermission> listByPermissionIds(@Param("permissions") List<Long> permissions);
}
