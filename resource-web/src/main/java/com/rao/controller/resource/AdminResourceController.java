package com.rao.controller.resource;

import com.rao.util.result.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.entity.resource.ResourceLocationsConfig;
import pojo.entity.resource.ServicePath;
import service.resource.ResourceLocationsConfigService;
import service.resource.ServicePathService;

import java.util.List;

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
    private ResourceLocationsConfigService resourceLocationsConfigService;


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
