package com.rao.service.impl.resource;

import com.rao.config.LocalOssConfig;
import com.rao.dao.resource.ResourceVideoDao;
import com.rao.dao.resource.SourceCollectionsDao;
import com.rao.pojo.entity.resource.ResourceVideo;
import com.rao.pojo.entity.resource.SourceCollections;
import com.rao.service.resource.SourceCollectionsService;
import constant.common.DateFormatEnum;
import constant.resource.CollectionConstant;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.rao.pojo.vo.resource.ResourceVideoVO;
import util.common.PageParamsUtil;
import util.common.Paramap;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    @Resource
    private LocalOssConfig localOssConfig;


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
    public List<ResourceVideoVO> listByPage(Integer pageNumber, Integer pageSize) {
        // 构建分页和筛选条件
        Paramap paramap = PageParamsUtil.buildPageParamAndSort(pageNumber, pageSize, "create_time desc");
        paramap.put("collectionType", CollectionConstant.COLLECTION_TYPE_VIDEO);

        List<SourceCollections> collectionsList = sourceCollectionsDao.findByPage(paramap);
        if(CollectionUtils.isEmpty(collectionsList)){
            return new ArrayList<>();
        }

        // 查询资源信息
        List<Long> resourceIds = collectionsList.stream().map(SourceCollections::getResourceId).collect(Collectors.toList());
        Map<Long, ResourceVideo> resourceIdToResourceMap = resourceVideoDao.listByResoueceIds(resourceIds)
                .stream().collect(Collectors.toMap(ResourceVideo::getId, Function.identity()));

        // 构建返回参数
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormatEnum.FORMAT_SYMBOL_EXTEND.getFormatString());
        return collectionsList.stream().map(item -> {
            ResourceVideo resourceVideo = resourceIdToResourceMap.get(item.getResourceId());
            return ResourceVideoVO.builder()
                    .id(resourceVideo.getId())
                    .videoName(item.getCollectionName())
                    .videoDescribe(resourceVideo.getVideoDescribe())
                    .videoImage(resourceVideo.getVideoImage())
                    .videoImageUrl(localOssConfig.getFullPath(resourceVideo.getVideoImage()))
                    .videoSize(resourceVideo.getVideoSize())
                    .createTime(dateFormat.format(item.getCreateTime()))
                    .build();
        }).collect(Collectors.toList());
    }
}