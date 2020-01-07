package com.rao.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : hudelin
 * @className :SystemUserLoginLogoutLogBO
 * @description :系统用户登录登出日志
 * @date: 2020-01-07 11:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserLoginLogoutLogBO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 类型 1-登录 2-登出
     */
    private String type;

    /**
     * ip
     */
    private String ip;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String area;

    /**
     * 互联网服务提供商
     */
    private String isp;

    /**
     * 创建时间
     */
    private Date createTime;
}
