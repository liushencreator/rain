package pojo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Entity - 系统管理员表
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TSystemUser implements Serializable {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 主键 */
	private Long id;
	
	/** 创建时间 */
	private java.util.Date createDate;
	
	/** 用户名 */
	private String userName;
	
	/** 密码 */
	private String password;
	
	/** 昵称 */
	private String nickname;
	
	/** 是否启用 */
	private Integer isEnabled;
	
	/** 是否锁定 */
	private Integer isLocked;
	
	/** 登录失败次数 */
	private Integer loginFailureCount;
	
	/** 锁定时间 */
	private java.util.Date lockedDate;
	
	/** 登录时间 */
	private java.util.Date loginDate;
	
	/** 登录IP */
	private String loginIp;
	
	/** 邮箱 */
	private String email;
	
	/** 角色 */
	private Long roleId;
	
	/** 头像 */
	private String avatar;
	
	/** 修改时间 */
	private java.util.Date updateDate;
	
	/** 主题默认1，2为Classic */
	private Integer theme;
	
	/**  */
	private String phone;
	
	/** 用户类型 */
	private Integer userType;
	
	/** 备注 */
	private String content;
	
}
