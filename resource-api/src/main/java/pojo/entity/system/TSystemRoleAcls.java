package pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Entity - 系统角色权限表
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TSystemRoleAcls implements Serializable {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 角色编号 */
	private Long roleId;
	
	/** 资源编号 */
	private Long aclId;
	
}
