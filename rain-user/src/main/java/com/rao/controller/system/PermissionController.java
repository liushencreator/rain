package com.rao.controller.system;

import com.rao.annotation.BeanValid;
import com.rao.pojo.dto.SavePermissionDTO;
import com.rao.pojo.vo.system.PermissionVO;
import com.rao.service.system.PermissionService;
import com.rao.util.result.ResultMessage;
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
    public ResultMessage savePermission(@BeanValid @RequestBody SavePermissionDTO permissionDTO){
        permissionService.savePermission(permissionDTO);
        return ResultMessage.success().addMessage("保存权限成功");
    }

    /**
     * 权限列表
     * @return
     */
    @GetMapping()
    public ResultMessage<List<PermissionVO>> listPermission(){
        List<PermissionVO> permissionVOList = permissionService.listPermission();
        return ResultMessage.success(permissionVOList);
    }

}
