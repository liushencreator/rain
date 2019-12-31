package com.rao.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;




/**
 * 当前用户信息
 * @author raojing
 * @date 2019/12/6 22:19
 */

@Data
public class CurrentUserInfo {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 权限
     */
    @ApiModelProperty(value = "权限")
    private List<LinkedHashMap> authorities;

}
