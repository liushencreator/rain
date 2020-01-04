package com.rao.service.impl;

import com.rao.constant.user.UserTypeEnum;
import com.rao.dao.RainSystemUserDao;
import com.rao.pojo.bo.LoginUserBO;
import com.rao.pojo.entity.RainSystemUser;
import com.rao.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户 service 实现
 * @author raojing
 * @date 2019/12/7 23:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;

    @Override
    public LoginUserBO findByUserNameOrPhoneAndUserType(String userName, String type) {
        LoginUserBO loginUser = null;
        if(UserTypeEnum.ADMIN.getValue().equals(type)){
            // 系统管理员用户
            RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(userName);
            loginUser = new LoginUserBO();
            BeanUtils.copyProperties(systemUser, loginUser);
        }else if(UserTypeEnum.C_USER.getValue().equals(type)){
            // C 端用户(暂无)

        }
        return loginUser;
    }

}
