package com.rao.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 保存权限 数据传输模型
 * @author raojing
 * @date 2019/12/8 14:12
 */
@Data
public class SavePermissionDTO {

    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    private String permissionName;

    /**
     * 权限码
     */
    @NotBlank(message = "权限标识不能为空")
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;

    /**
     * 父id
     */
    private Long parentId;

}
