package com.rao.component.listener;

import com.rao.dao.SystemUserLoginLogoutLogDao;
import com.rao.pojo.bo.SystemUserLoginLogoutLogBO;
import com.rao.pojo.entity.SystemUserLoginLogoutLog;
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
public class LoginLogoutListener implements RocketMQListener<SystemUserLoginLogoutLogBO> {

    @Resource
    private SystemUserLoginLogoutLogDao systemUserLoginLogoutLogDao;

    @Override
    public void onMessage(SystemUserLoginLogoutLogBO systemUserLoginLogoutLogBO) {
        SystemUserLoginLogoutLog systemUserLoginLogoutLog = CopyUtil.transToO(systemUserLoginLogoutLogBO, SystemUserLoginLogoutLog.class);
        systemUserLoginLogoutLog.setId(TwiterIdUtil.getTwiterId());
        systemUserLoginLogoutLog.setCreateTime(new Date());
        systemUserLoginLogoutLogDao.insertSelective(systemUserLoginLogoutLog);
    }
}
