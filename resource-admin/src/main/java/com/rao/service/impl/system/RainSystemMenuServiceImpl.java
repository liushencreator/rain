package com.rao.service.impl.system;

import com.rao.dao.system.RainSystemMenuDao;
import pojo.vo.common.MenuVO;
import service.system.RainSystemMenuService;
import util.common.Paramap;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单 service 实现
 * @author raojing
 * @date 2019/11/16 0:10
 */
public class RainSystemMenuServiceImpl implements RainSystemMenuService {

    @Resource
    private RainSystemMenuDao rainSystemMenuDao;

    @Override
    public List<MenuVO> menuTree() {
//        Paramap paramap = Paramap.
//        rainSystemMenuDao.findByParams();

        return null;
    }

}
