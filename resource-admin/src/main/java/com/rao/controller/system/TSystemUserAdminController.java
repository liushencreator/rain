package com.rao.controller.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.entity.system.TSystemUser;
import service.system.TSystemUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户 controller
 *
 * @author raojing
 * @date 2019/8/12 23:23
 */
@RestController
@RequestMapping("/admin/user")
public class TSystemUserAdminController {

    @Resource
    private TSystemUserService tSystemUserService;

    @GetMapping("/list")
    public List<TSystemUser> list(){
        return tSystemUserService.findAll();
    }

}
