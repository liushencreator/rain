package com.rao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DefaultSuccessMsgEnum
 *
 * @author raojing
 * @date 2019/11/15 12:37
 */
@AllArgsConstructor
public enum DefaultSuccessMsgEnum {

    /**
     * 成功 
     */
    SUCCESS(200,"操作成功"),

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
