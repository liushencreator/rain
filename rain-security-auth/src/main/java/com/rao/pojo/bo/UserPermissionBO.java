package com.rao.pojo.bo;

import lombok.Data;

/**
 * 用户权限 业务模型
 * @author raojing
 * @date 2019/12/5 20:40
 */
@Data
public class UserPermissionBO {

    /**
     * 角色标识
     */
    private String roleCode;

    /**
     * 权限标识
     */
    private String permissionCode;
    
}
