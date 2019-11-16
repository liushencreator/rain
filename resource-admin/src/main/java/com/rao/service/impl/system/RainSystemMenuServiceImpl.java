package com.rao.service.impl.system;

import com.rao.dao.system.RainSystemMenuDao;
import org.springframework.stereotype.Service;
import pojo.entity.system.RainSystemMenu;
import pojo.vo.system.FirstLevelMenuVO;
import pojo.vo.system.MenuTreeVO;
import pojo.vo.system.MenuVO;
import service.system.RainSystemMenuService;
import util.common.Paramap;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        paramap.put("status", 1);
        List<RainSystemMenu> systemMenuList = rainSystemMenuDao.findByParams(paramap);

        List<MenuVO> menuVOList = new ArrayList<>();
        for (RainSystemMenu systemMenu : systemMenuList) {
            MenuVO menuVO = MenuVO.build(systemMenu);
            if(systemMenu.getParentId() <= 0){
                menuVO.setMenuVOList(MenuVO.buildChildList(systemMenuList, systemMenu.getId()));
                menuVOList.add(menuVO);
            }
        }
        return menuVOList;
    }

    @Override
    public List<MenuTreeVO> menuTreeConfig() {
        Paramap paramap = Paramap.create();
        paramap.put("status", 1);
        List<RainSystemMenu> systemMenuList = rainSystemMenuDao.findByParams(paramap);

        List<MenuTreeVO> menuTreeVOList = new ArrayList<>();
        for (RainSystemMenu systemMenu : systemMenuList) {
            MenuTreeVO menuTreeVO = MenuTreeVO.build(systemMenu);
            if(systemMenu.getParentId() <= 0){
                menuTreeVO.setChildren(MenuTreeVO.buildChildList(systemMenuList, systemMenu.getId()));
                menuTreeVOList.add(menuTreeVO);
            }
        }
        return menuTreeVOList;
    }

    @Override
    public RainSystemMenu getMenuConfig(Long id) {
        return rainSystemMenuDao.find(id);
    }

    @Override
    public List<FirstLevelMenuVO> listFirstLevelMenu() {
        Paramap paramap = Paramap.create();
        paramap.put("status", 1);
        paramap.put("parentId", 0);
        List<RainSystemMenu> systemMenuList = rainSystemMenuDao.findByParams(paramap);
        return systemMenuList.stream().map(item -> {
            return FirstLevelMenuVO.builder()
                    .id(item.getId())
                    .menuName(item.getMenuName())
                    .build();
        }).collect(Collectors.toList());
    }

}
