package com.rao.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 保存角色 数据传输模型
 * @author raojing
 * @date 2019/12/8 15:20
 */
@Data
public class SaveRoleDTO {

    /**
     * 角色名称
     */
    @NotBlank
    private String roleName;

    /**
     * 角色标识
     */
    @NotBlank
    private String roleCode;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 权限
     */
    @NotEmpty
    private List<Long> permissions;

}
