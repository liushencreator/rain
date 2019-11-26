package com.rao.dao.resource;

import com.rao.pojo.entity.resource.ResourceNetworkLink;

import java.util.List;
import java.util.Map;

/**
 * DAO - ResourceNetworkLink(网链表)
 * 
 * @author raojing
 * @version 2.0
 */
public interface ResourceNetworkLinkDao {

    ResourceNetworkLink find(Long id);

    List<ResourceNetworkLink> findAll();

    Integer count(Map<String, Object> var1);

    Long insert(ResourceNetworkLink video);

    Integer update(ResourceNetworkLink video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<ResourceNetworkLink> findByParams(Map<String, Object> var1);

    List<ResourceNetworkLink> findByPage(Map<String, Object> var1);

    /**
     * insertSelective
     * @param resourceNetworkLink
     * @return
     */
    Long insertSelective(ResourceNetworkLink resourceNetworkLink);

}
