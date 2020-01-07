package com.rao.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 短信验证码发送
 * @author raojing
 * @date 2020/1/6 15:48
 */
@Data
public class SmsSendDTO {

    /**
     * 账号类型
     */
    @NotBlank(message = "账号类型不能为空")
    private String accountType;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    /**
     * 发送类型
     */
    @NotNull(message = "发送类型不能为空")
    @Range(min = 1, max = 4, message = "发送类型非法")
    private Integer type;
    
}
