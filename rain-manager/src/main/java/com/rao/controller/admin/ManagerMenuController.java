package com.rao.controller.admin;

import com.rao.client.resource.ResourceAdminClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.result.ResultMessage;

import javax.annotation.Resource;

/**
 * @author raojing
 * @date 2019/11/20 20:46
 */
@RestController
@RequestMapping("/admin/menu")
public class ManagerMenuController {
    
    @Resource
    private ResourceAdminClient resourceAdminClient;
    
    @PostMapping("test_rpc")
    public ResultMessage testRpc(){
        ResultMessage resultMessage = resourceAdminClient.testLocal();
        return resultMessage;
    }
    
}
