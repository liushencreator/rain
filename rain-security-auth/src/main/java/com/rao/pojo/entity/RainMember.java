package com.rao.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity - 用户表
 *
 * @author zijing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RainMember {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 微信openID
     */
    private String wxOpenid;

    /**
     * 微信昵称
     */
    private String wxNickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别 1-男 2-女
     */
    private Integer gender;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 个性签名
     */
    private String personalSignature;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
