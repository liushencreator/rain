package com.rao.service.impl.resource;

import com.rao.Utils.Paramap;
import com.rao.Utils.TwiterIdUtil;
import com.rao.Utils.pathUtils.PathUtils;
import com.rao.bean.resource.ResourceLocationsConfig;
import com.rao.bean.resource.ResourceVideo;
import com.rao.bean.resource.ServicePath;
import com.rao.dao.resource.ResourceLocationsConfigDao;
import com.rao.dao.resource.ResourceVideoDao;
import com.rao.dao.resource.ServicePathDao;
import com.rao.service.resource.ServicePathService;
import com.rao.vo.ResourceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * SERVICE - ServicePath(服务地址)
 *
 * @author zijing
 * @version 2.0
 */
@Slf4j
@Service
public class ServicePathServiceImpl implements ServicePathService {

    @Resource
    private ServicePathDao servicePathDao;
    @Resource
    private ResourceVideoDao resourceVideoDao;
    @Resource
    private ResourceLocationsConfigDao resourceLocationsConfigDao;

    @Override
    public Integer delete(Long pk) {
        return servicePathDao.delete(pk);
    }

    @Override
    public ServicePath find(Long pk) {
        return servicePathDao.find(pk);
    }

    @Override
    public List<ServicePath> findAll() {
        return servicePathDao.findAll();
    }

    @Override
    public Integer saveService(ServicePath path) {
        Date now = new Date();
        path.setUpdateTime(now);
        if (path.getId() != null) {
            return servicePathDao.update(path);
        } else {
            path.setId(TwiterIdUtil.getTwiterId());
            path.setCreateTime(now);
            return servicePathDao.insert(path);
        }
    }

    @Override
    public void initResourceData(Long id) {
        // 1.通过当前系统，根据配置查询出服务路径
        ServicePath path = servicePathDao.find(id);
        ResourceLocationsConfig config = resourceLocationsConfigDao.find(path.getConfigId());
        String os = System.getProperty("os.name");
        log.info("当前的系统为:{}", os);
        String basePath = os.toLowerCase().startsWith("win") ? config.getWdLocationPath() : config.getLmLocationPath();
        log.info("资源文件所在的路径:{}", basePath);

        // 2.查询当前服务是否初始化有资源文件
        Paramap paramap = Paramap.create();
        List<ResourceVideo> resourceVideoList = resourceVideoDao.findByParams(paramap.put("serviceId", id));

        // 3.通过服务路径查询所有资源文件
        PathUtils pathUtils = new PathUtils(new ArrayList<>(), basePath);
        List<ResourceVO> pathResults = pathUtils.pathResult(new File(basePath));

        // 4.过滤掉已经初始化的资源文件， insert
        // 5.找出已经初始化的资源文件但在该路径中不存在的资源， delete
        Map<String, ResourceVO> pathToResourceDiskMap = pathResults.stream().collect(Collectors.toMap(ResourceVO::getPath, Function.identity()));
        Map<String, ResourceVideo> pathToResoureDbMap = resourceVideoList.stream().collect(Collectors.toMap(ResourceVideo::getVideoPath, Function.identity()));
        List<String> pathDiskList = pathResults.stream().map(ResourceVO::getPath).collect(Collectors.toList());
        List<String> pathDbList = resourceVideoList.stream().map(ResourceVideo::getVideoPath).collect(Collectors.toList());
        // 求差集得到需要删除的资源地址和新增的资源地址
        List<String> addPathList = pathDiskList.stream().filter(item -> !pathDbList.contains(item)).collect(Collectors.toList());
        List<String> delPathList = pathDbList.stream().filter(item -> !pathDiskList.contains(item)).collect(Collectors.toList());

        // 过滤掉已经初始化的资源文件，新增资源
        List<ResourceVO> addResourceVOs = addPathList.stream().map(item -> {
            return pathToResourceDiskMap.get(item);
        }).collect(Collectors.toList());
        List<ResourceVideo> resourceVideos = new ArrayList<>();
        for (ResourceVO resourceVo : addResourceVOs) {
            String pathResult = resourceVo.getPath();
            ResourceVideo video = new ResourceVideo();
            video.setId(TwiterIdUtil.getTwiterId());
            video.setVideoName(pathResult);
            video.setVideoPath(pathResult);
            video.setVideoSize(resourceVo.getFileSize());
            video.setVideoDescribe("");
            video.setBroadcastNumber(0);
            video.setPraiseNumber(0);
            video.setServiceId(path.getId());
            video.setCreateTime(resourceVo.getLastModifyTime());
            video.setUpdateTime(new Date());
            resourceVideos.add(video);
        }
        if(!CollectionUtils.isEmpty(resourceVideos)){
            log.info("新增的资源文件:{}", resourceVideos);
            resourceVideoDao.batchInsert(resourceVideos);
        }

        // 找出已经初始化的资源文件但在该路径中不存在的资源，删除资源
        List<Long> delResourceVideoIdList = delPathList.stream().map(item -> {
            return pathToResoureDbMap.get(item);
        }).map(ResourceVideo::getId).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(delResourceVideoIdList)){
            // 删除路径中不存在的资源
            log.info("删除的资源文件:{}", delResourceVideoIdList);
            resourceVideoDao.deleteAll(delResourceVideoIdList);
        }
    }

}
