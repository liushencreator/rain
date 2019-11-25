package com.rao.controller.admin;

import com.rao.service.resource.ResourceNetworkLinkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.vo.resource.NetworkLinkVO;
import util.result.ResultMessage;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网链 controller
 * @author raojing
 * @date 2019/11/14 21:43
 */
@RestController
@RequestMapping("/web/network_link")
public class WebResourceNetworkLinkController {

    @Resource
    private ResourceNetworkLinkService resourceNetworkLinkService;

    /**
     * 网链列表
     * @param pageNumber
     * @param pageSize
     * @param searchKeyWord
     * @return
     */
    @PostMapping("/list")
    public ResultMessage list(@RequestParam(defaultValue = "1") Integer pageNumber,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(value = "searchKeyWord", required = false) String searchKeyWord){
        List<NetworkLinkVO> networkLinkVOList = resourceNetworkLinkService.listNetworkLink(pageNumber, pageSize, searchKeyWord);
        int totalSize = resourceNetworkLinkService.count(searchKeyWord);
        int totalPages = totalSize / pageSize;
        return ResultMessage.success()
                .add("list", networkLinkVOList)
                .add("totalPages", totalSize % pageSize == 0 ? totalPages : totalPages + 1);
    }

}
