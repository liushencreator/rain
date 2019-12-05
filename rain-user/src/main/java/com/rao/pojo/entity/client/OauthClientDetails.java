package com.rao.pojo.entity.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 客户端信息
 * 
 * @author zijing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OauthClientDetails {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	private String clientId;
	
	/**  */
	private String resourceIds;
	
	/**  */
	private String clientSecret;
	
	/**  */
	private String scope;
	
	/**  */
	private String authorizedGrantTypes;
	
	/**  */
	private String webServerRedirectUri;
	
	/**  */
	private String authorities;
	
	/**  */
	private Integer accessTokenValidity;
	
	/**  */
	private Integer refreshTokenValidity;
	
	/**  */
	private String additionalInformation;
	
	/**  */
	private String autoapprove;
	
}
