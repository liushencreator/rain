package com.rao.service.resource;

import pojo.entity.resource.ResourceLocationsConfig;

import java.util.List;

/**
 * SERVICE - ResourceLocationsConfig(本地映射路径配置)
 * 
 * @author zijing
 * @version 2.0
 */
public interface ResourceLocationsConfigService {

    /**
     * 分页查询资源配置
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<ResourceLocationsConfig> listConfigByPage(Integer pageNumber, Integer pageSize);

    /**
     * 查询记录数
     * @return
     */
    int count();
}
