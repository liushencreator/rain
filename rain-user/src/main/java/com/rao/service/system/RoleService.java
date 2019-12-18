package com.rao.service.system;

import com.rao.pojo.dto.SaveRoleDTO;
import com.rao.pojo.vo.system.ListRoleVO;
import com.rao.pojo.vo.system.PageRoleVO;
import com.rao.pojo.vo.system.RoleDetailVO;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;

import java.util.List;

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

    /**
     * 角色列表（全部-新增用户展示）
     * @return
     */
    List<ListRoleVO> listRole();

    /**
     * 修改角色信息
     * @param id
     * @param roleDTO
     */
    void updateRole(Long id,SaveRoleDTO roleDTO);

    /**
     * 删除某个角色
     * @param id
     */
    void deleteRole(Long id);
}
