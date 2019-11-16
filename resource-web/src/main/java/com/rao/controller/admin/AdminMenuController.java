package com.rao.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.result.ResultMessage;

/**
 * 后台菜单 controller
 * @author raojing
 * @date 2019/11/15 12:37
 */
@RestController
@RequestMapping("/admin/menu")
public class AdminMenuController {

    /**
     * 获取菜单树
     * @return
     */
    @PostMapping("/menu_tree")
    public ResultMessage menuTree(){
        
        return ResultMessage.success().add("menu_tree", null);
    }
    
}
