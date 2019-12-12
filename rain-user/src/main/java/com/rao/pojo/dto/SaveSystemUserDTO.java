package com.rao.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 保存系统用户 数据传输模型
 * @author raojing
 * @date 2019/12/12 14:18
 */
@Data
public class SaveSystemUserDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    
}
