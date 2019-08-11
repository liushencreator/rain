package com.rao.controller.resource;

import com.rao.util.common.CheckAgentUtil;
import com.rao.util.common.Paramap;
import com.rao.util.file.DownLoadUtil;
import com.rao.util.result.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.entity.resource.ResourceVideo;
import pojo.entity.resource.ServicePath;
import pojo.vo.resource.ResourceVideoVO;
import service.resource.ResourceVideoService;
import service.resource.ServicePathService;
import service.resource.SourceCollectionsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 资源 controller
 * Created by Lenovo on 2018/8/16.
 */
@Slf4j
@Controller
@RequestMapping("/web/resource")
public class WebResourceController {

    @Resource
    private ResourceVideoService resourceVideoService;
    @Resource
    private ServicePathService servicePathService;
    @Resource
    private SourceCollectionsService sourceCollectionsService;

    /**
     * 根据终端类型返回对应视图
     *
     * @param userAgent 请求头的终端类型
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestHeader("User-Agent") String userAgent) {
        boolean isMobile = CheckAgentUtil.checkAgentIsMobile(userAgent);
        if (isMobile) {
            return "/resource/mobile/index";
        }
        return "/resource/web/index";
    }

    /**
     * 受欢迎的资源列表
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @PostMapping("/list_favourite")
    public ResultMessage listFavourite(@RequestParam(defaultValue = "1") Integer pageNumber,
                                       @RequestParam(defaultValue = "10") Integer pageSize){
        List<ResourceVideoVO> resourceVideoVOList = resourceVideoService.listFavourite(pageNumber, pageSize);
        Integer totalSize = resourceVideoService.count();
        return ResultMessage.success()
                .add("list", resourceVideoVOList)
                .add("totalPages", totalSize / pageSize);
    }

    /**
     * 服务路径列表
     *
     * @param model
     * @return
     */
    @GetMapping("/resourceIndex")
    public String resourceIndex(Model model) {
        List<ServicePath> servicePaths = servicePathService.findAll();
        model.addAttribute("servicePaths", servicePaths);
        return "/resource/web/resourceIndex";
    }


    /**
     * 根据路径资源列表
     *
     * @param serviceId
     * @param model
     * @return
     */
    @GetMapping("/resourceList")
    public String resourceList(@RequestParam Long serviceId,
                               @RequestParam(defaultValue = "1") Integer pageNumber,
                               @RequestParam(defaultValue = "100") Integer pageSize,
                               Model model) {
        Paramap paramap = Paramap.create();
        paramap.put("serviceId", serviceId);
        List<ResourceVideo> page = resourceVideoService.findByPage(paramap, pageNumber, pageSize);
        model.addAttribute("page", page);
        return "/resource/web/resourceList";
    }


    /**
     * 资源详情
     *
     * @param userAgent
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/resourceDetail")
    public String resourceDetail(@RequestHeader("User-Agent") String userAgent,
                                 @RequestParam Long id,
                                 Model model) {
        ResourceVideo video = resourceVideoService.resourceDetail(id);
        Integer count = sourceCollectionsService.count(Paramap.create().put("resourceId", id));
        model.addAttribute("video", video);
        model.addAttribute("hasCollection", count > 0 ? 1 : 2);

        boolean isMobile = CheckAgentUtil.checkAgentIsMobile(userAgent);
        if (isMobile) {
            return "/resource/mobile/collection/collection_detail";
        }
        return "/resource/web/resourceDetail";
    }


    /**
     * 资源下载
     */
    @RequestMapping("/download")
    public String downloadFile(Long resourceId, HttpServletResponse response) throws Exception {
        ResourceVideo video = resourceVideoService.find(resourceId);//获取资源信息
        if (video == null) {
            return null;
        }
        ServicePath servicePath = servicePathService.find(video.getServiceId());//获取文件地址信息
        String fileName = video.getVideoName();
        String dataAddress = servicePath.getPathDir() + "/" + fileName;

        File file = new File(dataAddress);//设置文件路径
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            DownLoadUtil.downLoad(response, file, fileName);
        }
        return null;
    }

}
