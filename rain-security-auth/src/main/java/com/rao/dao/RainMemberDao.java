package com.rao.dao;

import com.rao.pojo.entity.RainMember;
import com.rao.pojo.entity.RainSystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO - RainMember(用户表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface RainMemberDao {

    RainMember find(Long id);

    List<RainMember> findAll();

    Long count(Map<String, Object> var1);

    Long insert(RainMember video);

    Integer update(RainMember video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<RainMember> findByParams(Map<String, Object> var1);

    List<RainMember> findByPage(Map<String, Object> var1);

    /**
     * insertSelective
     * @param rainMember
     * @return
     */
    Integer insertSelective(RainMember rainMember);

    /**
     * 通过微信openID查询用户信息
     * @param wxOpenId
     * @return
     */
    RainMember findByWxOpenId(@Param("wxOpenid") String wxOpenId);
    
}
