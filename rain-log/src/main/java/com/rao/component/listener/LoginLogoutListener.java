package com.rao.component.listener;

import com.rao.dao.UserLoginLogoutLogDao;
import com.rao.pojo.bo.UserLoginLogoutLogBO;
import com.rao.pojo.entity.UserLoginLogoutLog;
import com.rao.util.CopyUtil;
import com.rao.util.common.TwiterIdUtil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author : hudelin
 * @className :LoginLogoutListener
 * @description : 登录登出监听器
 * @date: 2019-12-31 11:03
 */
@Service
@RocketMQMessageListener(topic = "LoginLogout", consumerGroup = "LoginLogoutGroup")
public class LoginLogoutListener implements RocketMQListener<UserLoginLogoutLogBO> {

    @Resource
    private UserLoginLogoutLogDao userLoginLogoutLogDao;

    @Override
    public void onMessage(UserLoginLogoutLogBO userLoginLogoutLogBO) {
        UserLoginLogoutLog userLoginLogoutLog = CopyUtil.transToO(userLoginLogoutLogBO, UserLoginLogoutLog.class);
        userLoginLogoutLog.setId(TwiterIdUtil.getTwiterId());
        userLoginLogoutLog.setCreateTime(new Date());
        userLoginLogoutLogDao.insertSelective(userLoginLogoutLog);
        System.out.println(userLoginLogoutLog);
    }
}
