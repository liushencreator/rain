package com.rao.pojo.dto;

import lombok.Data;

/**
 * 微信第三方登录
 * @author raojing
 * @date 2020/1/5 2:33
 */
@Data
public class WxLoginDTO {

    /**
     * code
     */
    private String code;

    /**
     * encryptedData
     */
    private String encryptedData;

    /**
     * iv
     */
    private String iv;

}
