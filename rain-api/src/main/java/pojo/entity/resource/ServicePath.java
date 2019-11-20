package pojo.entity.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Entity - 服务地址
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicePath implements Serializable {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	private Long id;

	/* 配置id */
	private Integer configId;
	
	/** 服务名称 */
	private String serviceName;
	
	/** 服务地址 */
	private String pathDir;
	
	/** 描述 */
	private String serviceDesc;
	
	/**  */
	private java.util.Date createTime;
	
	/**  */
	private java.util.Date updateTime;
	
}
