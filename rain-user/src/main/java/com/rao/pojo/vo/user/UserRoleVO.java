package com.rao.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @ClassName : RoleVO  //类名
 * @Description : 角色的视图  //描述
 * @Author : xujianjie  //作者
 * @Date: 2019-12-17 10:49  //时间
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleVO {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;
}
