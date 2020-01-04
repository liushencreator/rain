package com.rao.pojo.dto;

import lombok.Data;

/**
 * 短信验证码登录
 * @author raojing
 * @date 2020/1/5 2:29
 */
@Data
public class SmsCodeLoginDTO {

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 手机验证码
     */
    private String smsCode;

}
