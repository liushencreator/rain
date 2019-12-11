package com.rao.dao.user;

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
public interface RainSystemUserDao {

    RainSystemUser find(Long id);

    List<RainSystemUser> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(RainSystemUser rainSystemUser);

    Integer update(RainSystemUser rainSystemUser);

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

    /**
     * 通过用户名或手机号码查询用户信息
     * @param account
     * @return
     */
    RainSystemUser findByUserNameOrPhone(@Param("account") String account);
    
}
