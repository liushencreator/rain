package com.rao.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity - 系统用户表
 * 
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RainSystemUser {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	private Long id;
	
	/** 用户名 */
	private String userName;
	
	/** 手机号码 */
	private String phone;
	
	/** 登录密码 */
	private String password;
	
	/** 用户昵称 */
	private String nickName;
	
	/** 邮箱 */
	private String email;
	
	/** 头像 */
	private String avatar;
	
	/** 状态 1-启用 2-禁用 3-锁定 4-删除 */
	private Integer status;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;
	
	/** 删除时间 */
	private Date deleteTime;
	
}
