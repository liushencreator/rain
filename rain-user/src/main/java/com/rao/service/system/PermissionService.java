package com.rao.service.system;

import com.rao.pojo.dto.SavePermissionDTO;
import com.rao.pojo.vo.system.PermissionVO;

import java.util.List;

/**
 * 权限 service
 * @author raojing
 * @date 2019/12/8 14:22
 */
public interface PermissionService {

    /**
     * 保存权限信息
     * @param permissionDTO
     */
    void savePermission(SavePermissionDTO permissionDTO);

    /**
     * 权限列表
     * @return
     */
    List<PermissionVO> listPermission();
}
