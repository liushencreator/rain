package com.rao.pojo.vo.user;

import lombok.Data;

/**
 * 当前登录用户 数据传输模型
 * @author raojing
 * @date 2019/11/26 16:32
 */
@Data
public class SystemUserVO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;
    
}
