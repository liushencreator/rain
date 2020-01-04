package com.rao.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录成功视图模型
 * @author raojing
 * @date 2020/1/5 0:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginSuccessVO {

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * refreshToken
     */
    private String refreshToken;

    /**
     * 有效期
     */
    private String expire;

}
