package com.rao.pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RainUserRole {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	private Long id;
	
	/** 用户id */
	private Long userId;
	
	/** 角色id */
	private Long roleId;
	
}
