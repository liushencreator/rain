package com.rao.pojo.bo;

import lombok.Data;

/**
 * 登录用户 业务模型
 * @author raojing
 * @date 2019/12/7 23:34
 */
@Data
public class LoginUserBO {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 1-启用
     */
    private Integer status;

}
