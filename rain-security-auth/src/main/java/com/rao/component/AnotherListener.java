package com.rao.component;

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
@RocketMQMessageListener(topic = "topic", consumerGroup = "GroupA")
public class AnotherListener implements RocketMQListener {


    @Override
    public void onMessage(Object o) {
        System.out.println("AnotherListener"+o);
    }
}
