package com.rao.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.entity.resource.ResourceLocationsConfig;
import service.resource.ResourceLocationsConfigService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 本地映射文件配置 controller
 * @author raojing
 * @date 2019/8/4 12:28
 */
@Controller
@RequestMapping("/admin/resource_locations_config")
public class AdminResourceLocationsConfigController {

    @Resource
    private ResourceLocationsConfigService resourceLocationsConfigService;

    /**
     * 配置列表
     * @param model
     * @return
     */
    @GetMapping("/config_list")
    public String configList(Model model){
        List<ResourceLocationsConfig> configList = resourceLocationsConfigService.findAll();
        model.addAttribute("configList", configList);
        return "/resource/config/config_list";
    }

    /**
     * 服务配置页面
     * @param id 服务id
     * @param model
     * @return
     */
    @GetMapping("/config_forword")
    public String configForword(@RequestParam(required = false)Integer id, Model model){
        if(id != null){
            ResourceLocationsConfig config = resourceLocationsConfigService.find(id);
            model.addAttribute("config", config);
        }
        return "/resource/config/config_forword";
    }

    /**
     * 保存配置
     * @param config
     * @return
     */
    @PostMapping("/config_save")
    public String configSave(ResourceLocationsConfig config){
        System.out.println("配置信息:" + config);
        resourceLocationsConfigService.saveOrUpdate(config);
        return "redirect:config_list.html";
    }

    /**
     * 配置详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/config_detail")
    public String configDetail(@RequestParam Integer id, Model model){
        ResourceLocationsConfig config = resourceLocationsConfigService.find(id);
        model.addAttribute("config", config);
        return "/resource/config/config_detail";
    }


}
