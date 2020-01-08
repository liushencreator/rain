package com.rao.dto;

import lombok.Data;

/**
 * Created by zj on 2018/10/26.
 */
@Data
public class WxUserInfo {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户绑定的手机号（国外手机号会有区号）
     */
    private String phoneNumber;

    /**
     * 没有区号的手机号
     */
    private String purePhoneNumber;

    /**
     * 区号
     */
    private String countryCode;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private String gender;
}
