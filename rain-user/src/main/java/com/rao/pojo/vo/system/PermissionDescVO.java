package com.rao.pojo.vo.system;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 权限标识 视图模型
 * @author raojing
 * @date 2019/12/19 18:57
 */
@Data
@AllArgsConstructor
public class PermissionDescVO {

    /**
     * 权限标识
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;
    
}
