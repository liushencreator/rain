package com.rao.controller.admin;

import com.rao.annotation.BeanValid;
import com.rao.annotation.SimpleValid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pojo.dto.system.MenuConfigDTO;
import pojo.entity.system.RainSystemMenu;
import pojo.vo.system.FirstLevelMenuVO;
import pojo.vo.system.MenuTreeVO;
import pojo.vo.system.MenuVO;
import service.system.RainSystemMenuService;
import util.result.ResultMessage;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 后台菜单 controller
 * @author raojing
 * @date 2019/11/15 12:37
 */
@Slf4j
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

    /**
     * 保存菜单配置（修改或新增）
     * @param menuConfigDTO
     * @return
     */
    @PostMapping("/save_config")
    public ResultMessage saveConfig(@BeanValid @RequestBody MenuConfigDTO menuConfigDTO){
        try{
            rainSystemMenuService.saveConfig(menuConfigDTO);
        }catch (Exception e){
            return ResultMessage.fail();
        }
        return ResultMessage.success().addMessage("保存配置成功");
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @PostMapping("/del_menu")
    public ResultMessage delMenu(@SimpleValid @NotNull @RequestParam Long id){
        rainSystemMenuService.delMenu(id);
        return ResultMessage.success().addMessage("菜单删除成功");
    }

}
