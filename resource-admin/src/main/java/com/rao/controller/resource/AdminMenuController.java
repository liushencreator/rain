package com.rao.controller.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.vo.common.MenuVO;
import service.system.RainSystemMenuService;
import util.result.ResultMessage;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台菜单 controller
 * @author raojing
 * @date 2019/11/15 12:37
 */
@RestController
@RequestMapping("/admin/menu")
public class AdminMenuController {

    @Resource
    private RainSystemMenuService rainSystemMenuService;

    /**
     * 获取菜单树
     * @return
     */
    @PostMapping("/menu_tree")
    public ResultMessage menuTree(){
        List<MenuVO> menuVOList = rainSystemMenuService.menuTree();
        return ResultMessage.success().add("menu_tree", menuVOList);
    }
    
}
