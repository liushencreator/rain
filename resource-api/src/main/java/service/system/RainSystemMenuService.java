package service.system;

import pojo.vo.common.MenuVO;

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

}
