package com.rao.dao.resource;

import com.rao.bean.resource.SourceCollections;

import java.util.List;
import java.util.Map;

/**
 * DAO - SourceCollections(本地视频)
 * 
 * @author zijing
 * @version 2.0
 */
public interface SourceCollectionsDao {

    SourceCollections find(Long id);

    List<SourceCollections> findAll();

    Integer count(Map<String, Object> var1);

    Integer insert(SourceCollections video);

    Integer update(SourceCollections video);

    Integer delete(Long id);

    Integer deleteAll(Map<String, Object> var1);

    List<SourceCollections> findByParams(Map<String, Object> var1);

    List<SourceCollections> findByPage(Map<String, Object> var1);

    /**
     * 矫正收藏信息
     * @return
     */
    Integer adjustCollection();
}
