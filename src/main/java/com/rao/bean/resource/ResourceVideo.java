package com.rao.bean.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 本地视频
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceVideo{

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	private Long id;
	
	/** 视频名字 */
	private String videoName;
	
	/** 视频地址 */
	private String videoPath;
	
	/** 视频大小 */
	private String videoSize;
	
	/** 视频描述信息 */
	private String videoDescribe;
	
	/** 播放次数 */
	private Integer broadcastNumber;
	
	/** 点赞次数 */
	private Integer praiseNumber;

	/* 服务ID*/
	private Long serviceId;
	
	/**  */
	private java.util.Date createTime;
	
	/**  */
	private java.util.Date updateTime;
	
}
