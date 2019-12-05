package com.rao.pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 角色权限关系表
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RainRolePermission {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	private Long id;
	
	/** 角色id */
	private Long roleId;
	
	/** 权限id */
	private Long permissionId;
	
}
