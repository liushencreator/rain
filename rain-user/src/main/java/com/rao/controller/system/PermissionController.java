package com.rao.controller.system;

import com.rao.annotation.BeanValid;
import com.rao.constant.permission.user.SystemCodeConstant;
import com.rao.pojo.dto.SavePermissionDTO;
import com.rao.pojo.vo.system.PermissionVO;
import com.rao.service.system.PermissionService;
import com.rao.util.result.ResultMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限相关
 * @author raojing
 * @date 2019/12/8 14:05
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    /**
     * 新增权限
     * @param permissionDTO
     * @return
     */
    @PostMapping()
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_PERMISSION_ADD + "')")
    public ResultMessage savePermission(@BeanValid @RequestBody SavePermissionDTO permissionDTO){
        permissionService.savePermission(permissionDTO);
        return ResultMessage.success().message("保存权限成功");
    }

    /**
     * 权限列表
     * @return
     */
    @GetMapping()
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_PERMISSION_LIST + "')")
    public ResultMessage<List<PermissionVO>> listPermission(){
        List<PermissionVO> permissionVOList = permissionService.listPermission();
        return ResultMessage.success(permissionVOList);
    }

    /**
     * 更新权限
     *
     * @param id
     * @param permissionDTO
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_PERMISSION_UPDATE + "')")
    public ResultMessage updatePermission(@PathVariable Long id, @BeanValid @RequestBody SavePermissionDTO permissionDTO) {
        permissionService.updatePermission(id, permissionDTO);
        return ResultMessage.success().message("更新权限成功");
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + SystemCodeConstant.ADMIN_PERMISSION_DELETE + "')")
    public ResultMessage deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResultMessage.success().message("删除权限成功");
    }

}
