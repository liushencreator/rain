package com.rao.pojo.entity.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
@Table(name = "resource_locations_config")
public class ResourceLocationsConfig implements Serializable {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Id
	private Integer id;

	/* 配置名称 */
	@Column(name = "config_name")
	private String configName;
	
	/** linux或Mac-本地地址 */
	@Column(name = "lm_location_path")
	private String lmLocationPath;
	
	/** windows-本地地址 */
	@Column(name = "wd_location_path")
	private String wdLocationPath;
	
	/** 状态 1-启用 2-禁用 */
	@Column(name = "status")
	private Integer status;
	
	/** 创建时间 */
	@Column(name = "create_time")
	private java.util.Date createTime;
	
	/** 修改时间 */
	@Column(name = "update_time")
	private java.util.Date updateTime;
	
}
