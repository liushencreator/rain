package com.rao.controller.user;

import com.rao.util.result.ResultMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户管理 controller
 * @author raojing
 * @date 2019/12/8 23:30
 */
@RestController
@RequestMapping("/manager/system_user")
public class SystemUserManagerController {

    /**
     * 初始化超级管理员，只能初始化一次
     * @return
     */
    @PostMapping("/init_super_user")
    public ResultMessage initSuperUser(){
        
        return ResultMessage.success().message("超级管理员初始化成功");
    }

}
