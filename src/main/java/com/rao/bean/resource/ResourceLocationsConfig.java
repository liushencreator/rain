package com.rao.bean.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 本地映射路径配置
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceLocationsConfig {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	private Integer id;

	/* 配置名称 */
	private String configName;
	
	/** linux或Mac-本地地址 */
	private String lmLocationPath;
	
	/** windows-本地地址 */
	private String wdLocationPath;
	
	/** 状态 1-启用 2-禁用 */
	private Integer status;
	
	/** 创建时间 */
	private java.util.Date createTime;
	
	/** 修改时间 */
	private java.util.Date updateTime;
	
}
