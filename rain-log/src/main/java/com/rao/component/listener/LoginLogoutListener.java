package com.rao.component.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author : hudelin
 * @className :LoginLogoutListener
 * @description : 登录登出监听器
 * @date: 2019-12-31 11:03
 */
@Service
@RocketMQMessageListener(topic = "", consumerGroup = "GroupA")
public class LoginLogoutListener implements RocketMQListener {


    @Override
    public void onMessage(Object o) {

    }
}
