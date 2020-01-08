package com.rao.pojo.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "RAIN_MEMBER")
public class RainMember {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 电话号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 微信openID
     */
    @Column(name = "wx_openid")
    private String wxOpenid;

    /**
     * 微信昵称
     */
    @Column(name = "wx_nickname")
    private String wxNickname;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 性别 1-男 2-女
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 个性签名
     */
    @Column(name = "personal_signature")
    private String personalSignature;

    /**
     * 出生日期
     */
    @Column(name = "birthday")
    private Date birthday;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}
