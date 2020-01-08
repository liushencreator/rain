package com.rao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常信息 枚举
 * @author raojing
 * @date 2019/11/15 12:37
 */
@AllArgsConstructor
public enum ErrorMsgEnum {

    /**
     * 失败
     */
    FAIL(500, "操作失败"),

    /**
     * 无权限访问
     */
    ACCESS_DENIED(403, "无权限访问"),
    
    ;

    /**
     * 编号 
     */
    @Getter
    private Integer code;

    /**
     * 信息 
     */
    @Getter
    private String msg;

}
