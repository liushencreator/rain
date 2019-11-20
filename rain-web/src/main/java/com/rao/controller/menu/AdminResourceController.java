package com.rao.controller.menu;

import com.rao.config.LocalOssConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.dto.resource.UpdateResourceDTO;
import pojo.entity.resource.ResourceLocationsConfig;
import pojo.entity.resource.ResourceVideo;
import pojo.entity.resource.ServicePath;
import pojo.vo.resource.ResourceVideoVO;
import service.resource.ResourceLocationsConfigService;
import service.resource.ResourceVideoService;
import service.resource.ServicePathService;
import util.common.Paramap;
import util.result.ResultMessage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源管理后台 controller
 * Created by Lenovo on 2018/9/9.
 */
@Controller
@RequestMapping("/admin/resource")
public class AdminResourceController {

    @Autowired
    private ServicePathService servicePathService;
    @Autowired
    private ResourceVideoService resourceVideoService;
    @Autowired
    private ResourceLocationsConfigService resourceLocationsConfigService;
    @Autowired
    private LocalOssConfig localOssConfig;


    @RequestMapping("/index")
    public String admin() {
        return "/resource/admin/index";
    }


    /**
     * 服务列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/serviceList")
    public String serviceList(Model model) {
        List<ServicePath> servicePaths = servicePathService.findAll();
        model.addAttribute("servicePaths", servicePaths);
        return "/resource/admin/serviceList";
    }

    /**
     * 根据服务id获取资源列表
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
        List<ResourceVideo> resourceList = resourceVideoService.findByPage(paramap, pageNumber, pageSize);

        List<ResourceVideoVO> resourceVideoVOS = resourceList.stream().map(item -> {
            ResourceVideoVO resourceVideoVO = new ResourceVideoVO();
            BeanUtils.copyProperties(item, resourceVideoVO);
            resourceVideoVO.setVideoImageUrl(localOssConfig.getFullPath(item.getVideoImage()));
            return resourceVideoVO;
        }).collect(Collectors.toList());

        model.addAttribute("resourceList", resourceVideoVOS);
        return "/resource/admin/resource_list";
    }

    /**
     * 修改资源信息
     *
     * @param updateResourceDTO
     * @return
     */
    @ResponseBody
    @PostMapping("/update_resource")
    public ResultMessage updateResource(UpdateResourceDTO updateResourceDTO){
        resourceVideoService.updateResource(updateResourceDTO);
        return ResultMessage.success();
    }

    /**
     * 服务详情
     *
     * @return
     */
    @RequestMapping("/viewService")
    public String viewService(Long id, Model model) {
        ServicePath path = servicePathService.find(id);
        ResourceLocationsConfig config = resourceLocationsConfigService.find(path.getConfigId());
        model.addAttribute("path", path);
        model.addAttribute("config", config);
        return "/resource/admin/serviceView";
    }


    /**
     * 添加或修改服务页面
     *
     * @return
     */
    @RequestMapping("/forwordService")
    public String addService(@RequestParam(required = false) Long id, Model model) {
        List<ResourceLocationsConfig> configList = resourceLocationsConfigService.findAll();
        model.addAttribute("configList", configList);
        if (id != null) {
            ServicePath path = servicePathService.find(id);
            model.addAttribute("path", path);
        }
        return "/resource/admin/forwordService";
    }


    /**
     * 保存服务
     *
     * @return
     */
    @RequestMapping("/saveService")
    public String saveService(ServicePath path) {
        servicePathService.saveService(path);
        return "redirect:serviceList.html";
    }


    /**
     * 删除服务
     *
     * @return
     */
    @RequestMapping("/deleteService")
    public String deleteService(Long id) {
//        servicePathService.delete(id);
//        resourceVideoService.deleteByServiceId(id);
        return "redirect:serviceList.html";
    }


    /**
     * 初始化资源表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/init")
    public ResultMessage initResourceData(@RequestParam Long id) {
        servicePathService.initResourceData(id);
        return ResultMessage.success();
    }


}
