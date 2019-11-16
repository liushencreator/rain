package com.rao.service.impl.system;

import com.rao.dao.system.RainSystemMenuDao;
import org.springframework.stereotype.Service;
import pojo.entity.system.RainSystemMenu;
import pojo.vo.common.MenuVO;
import service.system.RainSystemMenuService;
import util.common.Paramap;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单 service 实现
 * @author raojing
 * @date 2019/11/16 0:10
 */
@Service
public class RainSystemMenuServiceImpl implements RainSystemMenuService {

    @Resource
    private RainSystemMenuDao rainSystemMenuDao;

    @Override
    public List<MenuVO> menuTree() {
        Paramap paramap = Paramap.create();
        List<RainSystemMenu> systemMenuList = rainSystemMenuDao.findByParams(paramap);

        List<MenuVO> menuVOList = new ArrayList<>();
        for (RainSystemMenu systemMenu : systemMenuList) {
            MenuVO menuVO = MenuVO.build(systemMenu);
            if(systemMenu.getParentId() <= 0){
                menuVO.setMenuVOList(getChildList(systemMenuList, systemMenu.getId()));
                menuVOList.add(menuVO);
            }
        }
        return menuVOList;
    }

    /**
     * 构建子菜单
     * @param systemMenuList
     * @param id
     * @return
     */
    private List<MenuVO> getChildList(List<RainSystemMenu> systemMenuList, Long id) {
        List<MenuVO> menuVOList = new ArrayList<>();
        for (RainSystemMenu systemMenu : systemMenuList) {
            if(systemMenu.getParentId().equals(id)){
                MenuVO menuVO = MenuVO.build(systemMenu);
                menuVO.setMenuVOList(getChildList(systemMenuList, systemMenu.getId()));
                menuVOList.add(menuVO);
            }
        }
        return menuVOList;
    }


}
