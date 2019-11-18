package service.system;

import pojo.dto.system.MenuConfigDTO;
import pojo.entity.system.RainSystemMenu;
import pojo.vo.system.FirstLevelMenuVO;
import pojo.vo.system.MenuTreeVO;
import pojo.vo.system.MenuVO;

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
}
