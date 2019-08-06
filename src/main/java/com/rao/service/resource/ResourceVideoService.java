package com.rao.service.resource;


import com.rao.bean.resource.ResourceVideo;

import java.util.List;
import java.util.Map;

/**
 * SERVICE - ResourceVideo(本地视频)
 * 
 * @author zijing
 * @version 2.0
 */
public interface ResourceVideoService {

    /**
     * 保存
     */
    abstract Integer save(ResourceVideo param);

    /**
     * 更新
     */
    abstract Integer update(ResourceVideo param);

    /**
     * 根据主键删除
     */
    abstract Integer delete(Long pk);

    /**
     * 按serviceID删除资源
     * @param serviceId
     * @return
     */
    abstract Integer deleteByServiceId(Long serviceId);

    /**
     * 查询实体对象数量
     */
    abstract Integer count();

    /**
     * 根据条件查询实体对象数量
     */
    abstract Integer count(Map<String, Object> params);

    /**
     * 根据主键查询数据
     */
    abstract ResourceVideo find(Long pk);

    /**
     * 查询所有数据
     */
    abstract List<ResourceVideo> findAll();

    /**
     * 根据条件查询数据
     */
    abstract ResourceVideo find(String propertyName, Object propertyValue);

    /**
     * 根据属性名和属性值获取实体对象集合.
     */
    public List<ResourceVideo> findList(String propertyName, Object propertyValue);

    /**
     * 根据条件查询数据
     */
    abstract List<ResourceVideo> findList(Map<String, Object> params);

    /**
     * 分页查询
     */
    List<ResourceVideo> findByPage(Map<String, Object> params,Integer pageNumber,Integer pageSize);

}
