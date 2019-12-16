package com.rao.dao.user;

import com.rao.mapper.RainBaseDao;
import com.rao.pojo.entity.user.RainSystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainSystemUser(系统用户表)
 * 
 * @author raojing
 * @version 2.0
 */
public interface RainSystemUserDao extends RainBaseDao<RainSystemUser> {

    /**
     * 通过用户名或手机号码查询用户信息
     * @param account
     * @return
     */
    RainSystemUser findByUserNameOrPhone(@Param("account") String account);
    
}
