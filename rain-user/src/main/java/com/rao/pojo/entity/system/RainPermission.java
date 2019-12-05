package com.rao.pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RainPermission {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	private Long id;
	
	/** 权限名称 */
	private String permissionName;
	
	/** 权限码 */
	private String permissionCode;
	
	/** 权限描述 */
	private String permissionDesc;
	
	/** 父id */
	private Long parentId;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;
	
}
