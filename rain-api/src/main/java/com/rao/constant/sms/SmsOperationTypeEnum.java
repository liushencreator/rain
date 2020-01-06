package com.rao.constant.sms;

import com.rao.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 短信操作类型
 * @author raojing
 * @date 2020/1/6 15:58
 */
@AllArgsConstructor
public enum SmsOperationTypeEnum {

    /**
     * 登录
     */
    LOGIN(1, "登录"),

    /**
     * 注册
     */
    REGISTER(2, "注册"),

    /**
     * 重置密码
     */
    RESET_PWD(3, "重置密码"),

    /**
     * 验证身份
     */
    VERIFY_IDENTITY(4, "验证身份"),
    ;

    /**
     * 类型
     */
    @Getter
    private Integer type;

    /**
     * 描述
     */
    @Getter
    private String desc;
    
    public static SmsOperationTypeEnum ofType(Integer type){
        for (SmsOperationTypeEnum item : values()) {
            if(item.getType().equals(type)){
                return item;
            }
        }
        throw BusinessException.operate("没有匹配的操作类型");
    }
    
}
