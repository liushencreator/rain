package com.rao.pojo.vo.system;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 权限 视图模型
 * @author raojing
 * @date 2019/12/8 22:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVO {

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限码
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;

    /**
     * 子权限
     */
    private List<PermissionVO> children;

}
