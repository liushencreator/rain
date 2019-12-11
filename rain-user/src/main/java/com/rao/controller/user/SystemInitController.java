package com.rao.controller.user;

import com.rao.annotation.SimpleValid;
import com.rao.service.user.SystemInitService;
import com.rao.util.result.ResultMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * 系统用户管理 controller
 * @author raojing
 * @date 2019/12/8 23:30
 */
@RestController
@RequestMapping("/system")
public class SystemInitController {
    
    @Resource
    private SystemInitService systemInitService;

    /**
     * 系统初始化
     * @return
     */
    @PostMapping("/init")
    public ResultMessage systemInit(@SimpleValid @NotBlank(message = "手机号码不能为空") @RequestParam String phone){
        systemInitService.systemInit(phone);
        return ResultMessage.success().message("系统初始化成功");
    }

}
