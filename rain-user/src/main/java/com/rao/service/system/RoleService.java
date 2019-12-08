package com.rao.service.system;

import com.rao.pojo.dto.SaveRoleDTO;

/**
 * 角色 service
 * @author raojing
 * @date 2019/12/8 14:23
 */
public interface RoleService {

    /**
     * 保存角色信息
     * @param roleDTO
     */
    void saveRole(SaveRoleDTO roleDTO);

}
