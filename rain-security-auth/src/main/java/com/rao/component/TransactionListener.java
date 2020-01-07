package com.rao.component;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author : hudelin
 * @className :TransanctionListener
 * @description :
 * @date: 2020-01-06 16:25
 */
@RocketMQTransactionListener(txProducerGroup="test")
public class TransactionListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // ... local transaction process, return bollback, commit or unknown
        System.out.println("executeLocalTransaction");
        System.out.println(msg);
        System.out.println(arg);
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // ... check transaction status and return bollback, commit or unknown

        System.out.println("checkLocalTransaction");
        System.out.println(msg);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
