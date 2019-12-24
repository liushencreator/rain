package com.rao.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author : hudelin
 * @className :RePasswordVO
 * @description : 修改密码
 * @date: 2019-12-24 10:30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RePasswordDTO {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "确认密码不能为空")
    private String rePassword;

}
