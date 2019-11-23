package com.rao.dao.resource;

import org.apache.ibatis.annotations.Param;
import pojo.entity.resource.ResourceVideo;

import java.util.List;
import java.util.Map;

/**
 * DAO - ResourceVideo(本地视频)
 * 
 * @author zijing
 * @version 2.0
 */
public interface ResourceVideoDao{

    ResourceVideo find(Long id);

    List<ResourceVideo> findAll();

    Integer count(Map<String, Object> var1);

    Integer insert(ResourceVideo video);

    Integer update(ResourceVideo video);

    Integer delete(Long id);

    Integer deleteAll(@Param("ids") List<Long> ids);

    Integer deleteByServiceId(Long serviceId);

    List<ResourceVideo> findByParams(Map<String, Object> var1);

    List<ResourceVideo> findByPage(Map<String, Object> var1);

    /**
     * 批量保存
     * @param resourceVideos
     * @return
     */
    Integer batchInsert(@Param("list") List<ResourceVideo> resourceVideos);

    /**
     * 根据资源id查询资源列表
     * @param resourceIds
     * @return
     */
    List<ResourceVideo> listByResoueceIds(@Param("list") List<Long> resourceIds);

    /**
     * 增加资源统计数量
     * @param paramap
     * @return
     */
    Integer increaseStatisticsNumber(Map<String, Object> paramap);
}
