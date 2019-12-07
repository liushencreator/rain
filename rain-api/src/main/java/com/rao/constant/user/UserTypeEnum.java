package com.rao.constant.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author raojing
 * @date 2019/12/7 23:27
 */
@AllArgsConstructor
public enum UserTypeEnum {

    /**
     * 系统用户
     */
    ADMIN("admin"),

    /**
     * B 端用户
     */
    B_USER("b_user"),

    /**
     * C 端用户
     */
    C_USER("c_user"),
    ;

    /**
     * 用户类型
     */
    @Getter
    private String value;

}
