package service.resource;


import pojo.dto.resource.UpdateResourceDTO;
import pojo.entity.resource.ResourceVideo;
import pojo.vo.resource.ResourceVideoVO;

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
    List<ResourceVideo> findByPage(Map<String, Object> params, Integer pageNumber, Integer pageSize);

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

    /**
     * 修改资源信息
     * @param updateResourceDTO
     * @return
     */
    Integer updateResource(UpdateResourceDTO updateResourceDTO);
}
