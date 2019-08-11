package pojo.entity.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 收藏表
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceCollections{

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	private Long id;
	
	/** 收藏名字 */
	private String collectionName;
	
	/** 收藏地址 */
	private String collectionPath;
	
	/** 收藏类型 1-视频 */
	private Integer collectionType;
	
	/** 资源ID */
	private Long resourceId;
	
	/**  */
	private java.util.Date createTime;
	
	/**  */
	private java.util.Date updateTime;
	
}
