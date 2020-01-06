package com.rao.cache.key;

import com.rao.constant.sms.SmsOperationTypeEnum;

/**
 * 消息模块缓存健
 * @author raojing
 * @date 2020/1/6 17:28
 */
public class MessageCacheKey {

    /**
     * 构建短信缓存健
     * @param operationType
     * @param phone
     * @return
     */
    public static String smsCacheKey(SmsOperationTypeEnum operationType, String phone){
        return "sms:" + operationType.getType() + ":" + phone;
    }
    
}
