package com.rao.pojo.entity.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Entity - 网链表
 * 
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNetworkLink implements Serializable {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	private Long id;
	
	/** 链接名称 */
	private String linkName;
	
	/** 链接地址 */
	private String linkPath;
	
	/** 链接图片 */
	private String linkImage;
	
	/** 链接描述 */
	private String linkDescribe;
	
	/** 播放次数 */
	private Integer broadcastNumber;
	
	/** 点赞次数 */
	private Integer praiseNumber;
	
	/** 点击次数 */
	private Integer clickNumber;
	
	/** 状态 1-上架 2-下架 */
	private Integer status;
	
	/** 创建时间 */
	private java.util.Date createTime;
	
	/** 修改时间 */
	private java.util.Date updateTime;
	
}
