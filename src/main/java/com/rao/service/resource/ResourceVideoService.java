package com.rao.service.resource;


import com.rao.bean.resource.ResourceVideo;
import com.rao.pojo.vo.ResourceVideoVO;

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
     * 查询实体对象数量
     */
    Integer count();

    /**
     * 根据主键查询数据
     */
    ResourceVideo find(Long pk);

    /**
     * 分页查询
     */
    List<ResourceVideo> findByPage(Map<String, Object> params,Integer pageNumber,Integer pageSize);

    /**
     * 分页查询受欢迎列表（根据点赞次数和播放次数排序）
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<ResourceVideoVO> listFavourite(Integer pageNumber, Integer pageSize);

    /**
     * 资源详情 （增加）
     * @param id
     * @return
     */
    ResourceVideo resourceDetail(Long id);
}
