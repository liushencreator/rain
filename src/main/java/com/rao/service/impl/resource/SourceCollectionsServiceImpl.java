package com.rao.service.impl.resource;

import com.rao.Utils.Paramap;
import com.rao.bean.resource.ResourceVideo;
import com.rao.bean.resource.SourceCollections;
import com.rao.constants.CollectionConstant;
import com.rao.dao.resource.ResourceVideoDao;
import com.rao.dao.resource.SourceCollectionsDao;
import com.rao.service.resource.SourceCollectionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * SERVICE - SourceCollections(本地视频)
 *
 * @author zijing
 * @version 2.0
 */
@Service
public class SourceCollectionsServiceImpl implements SourceCollectionsService {

    @Resource
    private SourceCollectionsDao sourceCollectionsDao;
    @Resource
    private ResourceVideoDao resourceVideoDao;


    @Override
    public Integer save(SourceCollections param) {
        return sourceCollectionsDao.insert(param);
    }

    @Override
    public Integer delete(Long pk) {
        return sourceCollectionsDao.delete(pk);
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return sourceCollectionsDao.count(params);
    }

    @Override
    public SourceCollections find(String propertyName, Object propertyValue) {
        List<SourceCollections> byParams = sourceCollectionsDao.findByParams(Paramap.create().put(propertyName, propertyValue));
        if (CollectionUtils.isEmpty(byParams)) {
            return null;
        }
        return byParams.get(0);
    }

    @Override
    public List<SourceCollections> findByPage(Map<String, Object> params, Integer pageNumber, Integer pageSize) {
        params.put("pageBegin", (pageNumber - 1) * pageSize);
        params.put("pageSize", pageSize);
        return sourceCollectionsDao.findByPage(params);
    }

    @Override
    public void adjustCollection() {
        sourceCollectionsDao.adjustCollection();
    }

    @Override
    public List<ResourceVideo> listByPage(Integer pageNumber, Integer pageSize) {
        // 构建分页和筛选条件
        Paramap paramap = Paramap.create();
        paramap.put("pageBegin", (pageNumber - 1) * pageSize);
        paramap.put("pageSize", pageSize);
        paramap.put("collectionType", CollectionConstant.COLLECTION_TYPE_VIDEO);

        List<SourceCollections> collectionsList = sourceCollectionsDao.findByPage(paramap);
        List<ResourceVideo> page = new ArrayList<>();
        if(CollectionUtils.isEmpty(collectionsList)){
            return page;
        }

        // 查询资源信息
        List<Long> resourceIds = collectionsList.stream().map(SourceCollections::getResourceId).collect(Collectors.toList());
        Map<Long, ResourceVideo> resourceIdToResourceMap = resourceVideoDao.listByResoueceIds(resourceIds)
                .stream().collect(Collectors.toMap(ResourceVideo::getId, Function.identity()));


        for (SourceCollections collections : collectionsList) {
            ResourceVideo video = new ResourceVideo();
            video.setId(collections.getResourceId());
            video.setVideoName(collections.getCollectionName());
            video.setVideoPath(collections.getCollectionPath());
            video.setCreateTime(collections.getCreateTime());
            page.add(video);
        }

        // 构建返回参数
        return collectionsList.stream().map(item -> {
            ResourceVideo resourceVideo = resourceIdToResourceMap.get(item.getResourceId());
            ResourceVideo resourceVO = new ResourceVideo();
            BeanUtils.copyProperties(resourceVideo, resourceVO);
            resourceVO.setVideoName(item.getCollectionName());
            resourceVO.setCreateTime(item.getCreateTime());
            return resourceVO;
        }).collect(Collectors.toList());
    }
}