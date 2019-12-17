package com.rao.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

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

    /**
     * 电话
     */
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "手机号码不正确")
    private String phone;

    /**
     * 角色id
     */
    @NotEmpty
    private List<Long> roleId;
    
}
