package com.rao.service.system;

import com.rao.pojo.dto.SaveRoleDTO;
import com.rao.pojo.vo.system.RoleDetailVO;
import com.rao.pojo.vo.system.PageRoleVO;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;

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

    /**
     * 权限列表
     * @param pageParam
     * @return
     */
    PageResult<PageRoleVO> pageRole(PageParam pageParam);

    /**
     * 获取角色信息
     * @param id
     * @return
     */
    RoleDetailVO findById(Long id);
}
