package com.rao.component;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : hudelin
 * @className :LogInLogoutProducer
 * @description : 登录登出生产者
 * @date: 2019-12-31 10:59
 */
@Component
public class LoginLogoutProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMsg() {

        for(int i=0;i<10;i++){
            Message<?> a=MessageBuilder.withPayload(i).build();
            rocketMQTemplate.sendMessageInTransaction("test","topic", a,null);
        }



    }

}
