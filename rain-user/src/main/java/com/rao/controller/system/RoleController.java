package com.rao.controller.system;

import com.rao.annotation.BeanValid;
import com.rao.pojo.dto.SaveRoleDTO;
import com.rao.pojo.vo.system.RoleDetailVO;
import com.rao.pojo.vo.system.RoleVO;
import com.rao.service.system.RoleService;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import com.rao.util.result.ResultMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色相关
 * @author raojing
 * @date 2019/12/8 14:06
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 新增角色
     * @param roleDTO
     * @return
     */
    @PostMapping()
    public ResultMessage saveRole(@BeanValid @RequestBody SaveRoleDTO roleDTO){
        roleService.saveRole(roleDTO);
        return ResultMessage.success().message("保存角色成功");
    }

    /**
     * 角色列表
     * @return
     */
    @GetMapping()
    public ResultMessage<PageResult<RoleVO>> pageRole(@RequestBody PageParam pageParam){
        PageResult<RoleVO> pageResult = roleService.pageRole(pageParam);
        return ResultMessage.success(pageResult);
    }

    /**
     * 角色详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultMessage<RoleDetailVO> findRole(@PathVariable Long id) {
        RoleDetailVO roleDetailVO = roleService.findById(id);
        return ResultMessage.success(roleDetailVO);
    }

}
