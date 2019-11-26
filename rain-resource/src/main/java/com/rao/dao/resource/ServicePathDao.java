package com.rao.dao.resource;

import com.rao.pojo.entity.resource.ServicePath;

import java.util.List;
import java.util.Map;

/**
 * DAO - ServicePath(服务地址)
 * 
 * @author zijing
 * @version 2.0
 */
public interface ServicePathDao {

    ServicePath find(Long id);

    List<ServicePath> findAll();

    Integer count(Map<String, Object> var1);

    Integer insert(ServicePath video);

    Integer update(ServicePath video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<ServicePath> findByParams(Map<String, Object> var1);

//    List<ServicePath> findByPage(Map<String, Object> var1);

}
