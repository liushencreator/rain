package com.rao.service.impl;

import com.rao.cache.key.MessageCacheKey;
import com.rao.constant.sms.SmsOperationTypeEnum;
import com.rao.exception.BusinessException;
import com.rao.pojo.dto.SmsSendDTO;
import com.rao.service.SmsCodeService;
import com.rao.util.cache.RedisTemplateUtils;
import com.rao.util.common.CacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信验证码
 * @author raojing
 * @date 2020/1/6 15:45
 */
@Service
public class SmsCodeServiceImpl implements SmsCodeService {
    
    @Autowired
    private RedisTemplateUtils redisTemplateUtils;
    
    @Override
    public String sendCode(SmsSendDTO smsSendDTO) {
        /**
         * 用户在点发送短信验证码之前，需要通过手机号码做检验账号是否存在，是否锁定等操作
         * 此处不做账户的校验，直接发送短信
         */
        SmsOperationTypeEnum operationType = SmsOperationTypeEnum.ofType(smsSendDTO.getType());
        String cacheKey = MessageCacheKey.smsCacheKey(operationType, smsSendDTO.getAccountType(), smsSendDTO.getPhone());
        this.checkOperation(cacheKey);
        
        // todo 发送短信
        // String msgCode = RandomUtils.randomNumberString(6);
        // todo 需要删除
        String msgCode = "123456";
        // 设置两分钟的过期时间
        redisTemplateUtils.set(cacheKey, msgCode, CacheConstant.TIME_TWO);
        return msgCode;
    }

    /**
     * 检查是否可以操作
     * @param cacheKey
     */
    private void checkOperation(String cacheKey){
        if(redisTemplateUtils.isExists(cacheKey)){
            int expireTime = redisTemplateUtils.getExpire(cacheKey).intValue();
            if(expireTime > CacheConstant.TIME_ONE){
                // 限制短信验证码的发送频率，如果失效时间大于1分钟，提示短信发送太快
                throw BusinessException.operate("短信发送太快了");
            }
        }
    }

}
