package com.rao.controller.menu;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.result.ResultMessage;

/**
 * @author raojing
 * @date 2019/11/20 20:31
 */
@RestController
@RequestMapping("/admin/local")
public class LocalResourceAdminController {
    
    @PostMapping("/test_local")
    public ResultMessage testLocal(){
        
        return ResultMessage.success().add("info", "resource_server");
    }
    
}
