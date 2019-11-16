package com.rao.controller.system;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.entity.system.RainSystemMenu;
import pojo.vo.system.FirstLevelMenuVO;
import pojo.vo.system.MenuTreeVO;
import pojo.vo.system.MenuVO;
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
     * 获取菜单树-后台左侧菜单栏
     * @return
     */
    @PostMapping("/menu_tree")
    public ResultMessage menuTree(){
        List<MenuVO> menuVOList = rainSystemMenuService.menuTree();
        return ResultMessage.success().add("menu_tree", menuVOList);
    }

    /**
     * 获取菜单树-菜单配置
     * @return
     */
    @PostMapping("/menu_tree_config")
    public ResultMessage menuTreeConfig(){
        List<MenuTreeVO> menuTreeVOList = rainSystemMenuService.menuTreeConfig();
        return ResultMessage.success().add("menu_tree", menuTreeVOList);
    }

    /**
     * 获取菜单配置
     * @param id
     * @return
     */
    @PostMapping("/get_menu_config")
    public ResultMessage getMenuConfig(@RequestParam Long id){
        RainSystemMenu systemMenu = rainSystemMenuService.getMenuConfig(id);
        return ResultMessage.success().add("menu_config", systemMenu);
    }

    /**
     * 获取一级菜单
     * @return
     */
    @PostMapping("/first_level_menu")
    public ResultMessage firstLevelMenu(){
        List<FirstLevelMenuVO> firstLevelMenuVOList = rainSystemMenuService.listFirstLevelMenu();
        return ResultMessage.success().add("first_level_menu", firstLevelMenuVOList);
    }


}
