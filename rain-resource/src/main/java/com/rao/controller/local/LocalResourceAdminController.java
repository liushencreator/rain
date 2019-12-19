package com.rao.controller.local;

import com.rao.pojo.entity.resource.ResourceLocationsConfig;
import com.rao.service.resource.ResourceLocationsConfigService;
import com.rao.util.page.PageParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rao.util.result.PageResult;
import com.rao.util.result.ResultMessage;

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
    public ResultMessage<PageResult<ResourceLocationsConfig>> configList(PageParam pageParam){
        List<ResourceLocationsConfig> locationsConfigList = resourceLocationsConfigService.listConfigByPage(pageParam.getPageNumber(), pageParam.getPageSize());
        Long totalSize = resourceLocationsConfigService.count();
        PageResult<ResourceLocationsConfig> pageResult = PageResult.build(totalSize, locationsConfigList);
        return ResultMessage.success(pageResult);
    }
}
