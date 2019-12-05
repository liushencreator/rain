package com.rao.dao.client;

import com.rao.pojo.entity.client.OauthClientDetails;
import java.util.List;
import java.util.Map;

/**
 * DAO - OauthClientDetails(客户端信息)
 * 
 * @author zijing
 * @version 2.0
 */
public interface OauthClientDetailsDao {

    OauthClientDetails find(Long id);

    List<OauthClientDetails> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(OauthClientDetails video);

    Integer update(OauthClientDetails video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<OauthClientDetails> findByParams(Map<String, Object> var1);

    List<OauthClientDetails> findByPage(Map<String, Object> var1);

    /**
     * insertSelective
     * @param oauthClientDetails
     * @return
     */
    Integer insertSelective(OauthClientDetails oauthClientDetails);

}
