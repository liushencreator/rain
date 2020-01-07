package com.rao.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 刷新token 数据传输模型
 * @author raojing
 * @date 2020/1/7 10:01
 */
@Data
public class RefreshTokenDTO {

    /**
     * 账号类型
     */
    @NotNull(message = "用户类型不能为空") @Range(min = 1, max = 2, message = "用户类型非法")
    private Integer accountType;

    /**
     * refreshToken
     */
    @NotBlank(message = "refreshToken 不能为空")
    private String refreshToken;
    
}
