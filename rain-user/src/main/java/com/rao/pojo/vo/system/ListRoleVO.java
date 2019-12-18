package com.rao.pojo.vo.system;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hudelin
 * @className :ListRoleVO
 * @description : 角色（新增用户展示）
 * @date: 2019-12-18 09:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRoleVO {

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;
}
