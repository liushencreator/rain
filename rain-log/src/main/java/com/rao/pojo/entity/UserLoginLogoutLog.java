package com.rao.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统用户登录登出日志
 * @author raojing
 * @date 2019/12/29 23:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_login_logout_log")
public class UserLoginLogoutLog {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 类型 1-登录 2-登出
     */
    @Column(name = "type")
    private Integer type;

    /**
     * ip
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 国家
     */
    @Column(name = "country")
    private String country;

    /**
     * 省份
     */
    @Column(name = "province")
    private String province;

    /**
     * 城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 区县
     */
    @Column(name = "area")
    private String area;

    /**
     * 互联网服务提供商
     */
    @Column(name = "isp")
    private String isp;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 用户类型
     */
    @Column(name = "user_type")
    private String userType;

}
