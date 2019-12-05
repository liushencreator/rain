package com.rao.pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity - 角色
 * 
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RainRole {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	private Long id;
	
	/** 角色名称 */
	private String roleName;

	/** 角色标识 */
	private String roleCode;
	
	/** 角色描述 */
	private String roleDesc;
	
	/** 平台类型 1-管理后台 2-普通用户 */
	private Integer platformType;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;
	
}
