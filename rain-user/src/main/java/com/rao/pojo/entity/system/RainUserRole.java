package com.rao.pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Entity - 用户角色关系表
 *
 * @author zijing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rain_user_role")
public class RainUserRole {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

}
