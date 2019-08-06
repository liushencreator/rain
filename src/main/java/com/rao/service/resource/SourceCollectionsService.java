package com.rao.service.resource;


import com.rao.bean.resource.SourceCollections;

import java.util.List;
import java.util.Map;

/**
 * SERVICE - SourceCollections(收藏)
 * 
 * @author zijing
 * @version 2.0
 */
public interface SourceCollectionsService {

    /**
     * 保存
     */
    Integer save(SourceCollections param);

    /**
     * 根据主键删除
     */
    Integer delete(Long pk);

    /**
     * 根据条件查询实体对象数量
     */
    Integer count(Map<String, Object> params);

    /**
     * 根据条件查询数据
     */
    SourceCollections find(String propertyName, Object propertyValue);

    /**
     * 分页查询
     */
    List<SourceCollections> findByPage(Map<String, Object> params, Integer pageNumber, Integer pageSize);

    /**
     * 矫正收藏信息
     */
    void adjustCollection();
}
