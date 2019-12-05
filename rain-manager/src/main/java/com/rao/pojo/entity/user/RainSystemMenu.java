package com.rao.pojo.entity.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Entity - 系统菜单
 * 
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RainSystemMenu implements Serializable {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@JsonSerialize(using= ToStringSerializer.class)
	private Long id;
	
	/** 菜单名称 */
	private String menuName;
	
	/** 菜单链接 */
	private String menuUrl;
	
	/** 菜单权限 */
	private String menuPermission;
	
	/** 菜单图标 */
	private String menuIcon;
	
	/** 排序 */
	private Integer sort;
	
	/** 父菜单id(一级菜单为0) */
	@JsonSerialize(using= ToStringSerializer.class)
	private Long parentId;
	
	/** 状态 1-启用 2-禁用 */
	private Integer status;
	
	/** 创建时间 */
	private java.util.Date createTime;
	
	/** 修改时间 */
	private java.util.Date updateTime;

	/** 排序规则 */
	private String orderByRule;
	
}
