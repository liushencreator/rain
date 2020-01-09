package com.rao.constant.user;

import com.rao.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author raojing
 * @date 2019/12/7 23:27
 */
@AllArgsConstructor
public enum OperationTypeEnum {

    /**
     * 登录
     */
    LOG_IN("logIn"),

    /**
     * 登出
     */
    LOG_OUT("logOut"),
    ;

    /**
     * 用户类型
     */
    @Getter
    private String value;
    
    public static OperationTypeEnum ofValue(String value){
        for (OperationTypeEnum userTypeEnum : values()) {
            if(userTypeEnum.getValue().equals(value)){
                return userTypeEnum;
            }
        }
        throw BusinessException.operate("没有匹配的日志操作类型");
    }
    
}
