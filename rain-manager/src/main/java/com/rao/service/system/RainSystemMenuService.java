package com.rao.service.system;

import com.rao.pojo.entity.system.RainSystemMenu;
import com.rao.pojo.dto.system.MenuConfigDTO;
import com.rao.pojo.vo.system.FirstLevelMenuVO;
import com.rao.pojo.vo.system.MenuTreeVO;
import com.rao.pojo.vo.system.MenuVO;

import java.util.List;

/**
 * 系统菜单 service
 * @author raojing
 * @date 2019/11/16 0:09
 */
public interface RainSystemMenuService {

    /**
     * 菜单树
     * @return
     */
    List<MenuVO> menuTree();

    /**
     * 菜单树
     * @return
     */
    List<MenuTreeVO> menuTreeConfig();

    /**
     * 根据 id 获取菜单配置
     * @param id
     * @return
     */
    RainSystemMenu getMenuConfig(Long id);

    /**
     * 获取一级菜单列表
     * @return
     */
    List<FirstLevelMenuVO> listFirstLevelMenu();

    /**
     * 保存菜单配置信息
     * @param menuConfigDTO
     */
    void saveConfig(MenuConfigDTO menuConfigDTO);

    /**
     * 删除菜单
     * @param id
     */
    void delMenu(Long id);
}
