package com.rao.pojo.vo.system;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.rao.pojo.bo.RolePermissionBO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 角色详情 视图模型
 * @author raojing
 * @date 2019/12/12 14:46
 */
@Data
public class RoleDetailVO {

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String roleCode;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 权限列表
     */
    List<RolePermissionBO> permissionList;

}
