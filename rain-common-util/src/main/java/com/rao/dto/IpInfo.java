package com.rao.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * IP 地址信息
 * @author raojing
 * @date 2019/12/29 17:39
 */
@Data
public class IpInfo implements Serializable {

    private static final long serialVersionUID = -6246313701294144575L;

    private String ip;

    /**
     * 国家
     */
    private String country;

    /**
     * 区县
     */
    private String area;

    /**
     * 省份
     */
    private String region;

    /**
     * 城市
     */
    private String city;

    /**
     * 县
     */
    private String county;

    /**
     * 互联网服务提供商
     */
    private String isp;

    private String country_id;

    private String area_id;

    private String region_id;

    private String city_id;

    private String county_id;

    private String isp_id;

}
