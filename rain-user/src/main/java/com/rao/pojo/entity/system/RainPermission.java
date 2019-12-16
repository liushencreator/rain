package com.rao.pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity - 权限
 *
 * @author zijing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rain_permission")
public class RainPermission {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 权限名称
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 权限码
     */
    @Column(name = "permission_code")
    private String permissionCode;

    /**
     * 权限描述
     */
    @Column(name = "permission_desc")
    private String permissionDesc;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}
