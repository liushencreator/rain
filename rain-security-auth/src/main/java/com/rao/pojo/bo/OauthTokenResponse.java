package com.rao.pojo.bo;

import lombok.Data;

/**
 * 获取token 返回值
 * @author raojing
 * @date 2019/12/2 15:50
 */
@Data
public class OauthTokenResponse {

    /**
     * access_token
     */
    private String access_token;

    /**
     * token_type
     */
    private String token_type;

    /**
     * refresh_token
     */
    private String refresh_token;

    /**
     * expires_in
     */
    private String expires_in;

    /**
     * scope
     */
    private String scope;
    
}
