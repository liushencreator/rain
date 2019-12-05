package com.rao.dao;

import com.rao.pojo.bo.UserPermissionBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色 dao
 * @author raojing
 * @date 2019/12/5 20:55
 */
public interface UserPermissionDao {

    /**
     * 通过用户查询权限信息
     * @param userId
     * @return
     */
    List<UserPermissionBO> listPermissionByUserId(@Param("userId") Long userId);
    
}
