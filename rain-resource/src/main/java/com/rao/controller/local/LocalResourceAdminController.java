package com.rao.controller.local;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.entity.resource.ResourceLocationsConfig;
import service.resource.ResourceLocationsConfigService;
import util.result.ResultMessage;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author raojing
 * @date 2019/11/20 20:31
 */
@RestController
@RequestMapping("/admin/local")
public class LocalResourceAdminController {

    @Resource
    private ResourceLocationsConfigService resourceLocationsConfigService;

    @GetMapping("/list")
    public ResultMessage configList(@RequestParam(defaultValue = "1") Integer pageNumber,
                                    @RequestParam(defaultValue = "10") Integer pageSize){
        List<ResourceLocationsConfig> locationsConfigList = resourceLocationsConfigService.listConfigByPage(pageNumber, pageSize);
        int totalSize = resourceLocationsConfigService.count();
        return ResultMessage.success().page(totalSize, locationsConfigList);
    }
}
