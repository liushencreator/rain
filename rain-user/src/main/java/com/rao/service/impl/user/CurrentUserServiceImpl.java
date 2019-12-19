package com.rao.service.impl.user;

import com.rao.dao.user.RainSystemUserDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.entity.user.RainSystemUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.rao.service.user.CurrentUserService;
import com.rao.pojo.vo.user.CurrentSystemUserVO;

import javax.annotation.Resource;

/**
 * 系统用户 service 实现
 * @author raojing
 * @date 2019/11/25 23:19
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;

    @Override
    public CurrentSystemUserVO findById(Long id) {
        RainSystemUser systemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if(systemUser != null){
            CurrentSystemUserVO systemUserVO = new CurrentSystemUserVO();
            BeanUtils.copyProperties(systemUser, systemUserVO);
            return systemUserVO;
        }else{
            throw BusinessException.operate("用户不存在");
        }
    }

}
