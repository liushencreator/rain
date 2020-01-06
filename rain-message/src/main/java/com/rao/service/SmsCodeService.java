package com.rao.service;

import com.rao.pojo.dto.SmsSendDTO;

/**
 * 短信验证码
 * @author raojing
 * @date 2020/1/6 15:45
 */
public interface SmsCodeService {

    /**
     * 发送短信验证码
     * @param smsSendDTO
     * @return
     */
    String sendCode(SmsSendDTO smsSendDTO);
    
}
