package pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Entity - 系统角色表
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TSystemRole implements Serializable {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 编号 */
	private Long id;
	
	/** 创建时间 */
	private java.util.Date createDate;
	
	/** 角色名称 */
	private String roleName;
	
	/** 是否内置 */
	private Integer isSystem;
	
	/** 描述 */
	private String descriptions;
	
	/** 修改时间 */
	private java.util.Date updateDate;
	
	/** 创建者 */
	private Long creator;
	
	/** 修改者 */
	private Long updator;
	
	/** 区域类型 1-平台 2-区域 */
	private Integer platType;
	
}
