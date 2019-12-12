package com.rao.controller.system;

import com.rao.annotation.BeanValid;
import com.rao.pojo.dto.SaveRoleDTO;
import com.rao.pojo.vo.system.RoleVO;
import com.rao.service.system.RoleService;
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
    public ResultMessage<List<RoleVO>> listRole(){
        
        return ResultMessage.success();
    }

}
