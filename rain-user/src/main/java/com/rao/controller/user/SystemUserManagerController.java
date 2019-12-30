package com.rao.controller.user;

import com.rao.annotation.BeanValid;
import com.rao.annotation.SimpleValid;
import com.rao.constant.permission.user.SystemCodeConstant;
import com.rao.constant.permission.user.UserCodeConstant;
import com.rao.pojo.dto.SaveSystemUserDTO;
import com.rao.pojo.vo.user.SystemUserDetailVO;
import com.rao.pojo.vo.user.SystemUserVO;
import com.rao.pojo.vo.user.UserRoleListVO;
import com.rao.service.user.SystemUserService;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import com.rao.util.result.ResultMessage;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 系统用户管理
 *
 * @author raojing
 * @date 2019/12/12 13:40
 */
@RestController
@RequestMapping("/manager/system_user")
public class SystemUserManagerController {

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 用户列表
     *
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + UserCodeConstant.ADMIN_SYSTEM_USER_LIST + "')")
    public ResultMessage<PageResult<SystemUserVO>> list(PageParam pageParam) {
        PageResult<SystemUserVO> systemUserList = systemUserService.getSystemUserList(pageParam);
        return ResultMessage.success(systemUserList);
    }

    /**
     * 用户详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserCodeConstant.ADMIN_SYSTEM_USER_DETAIL + "')")
    public ResultMessage<SystemUserDetailVO> detail(@PathVariable("id") Long id) {
        SystemUserDetailVO systemUser = systemUserService.findSystemUserById(id);
        return ResultMessage.success(systemUser);
    }

    /**
     * 新增用户
     *
     * @param systemUserDTO
     * @return
     */
    @PostMapping()
    @PreAuthorize("hasAuthority('" + UserCodeConstant.ADMIN_SYSTEM_USER_ADD + "')")
    public ResultMessage addUser(@BeanValid @RequestBody SaveSystemUserDTO systemUserDTO) {
        systemUserService.insertSystemUser(systemUserDTO);
        return ResultMessage.success();
    }

    /**
     * 修改用户
     *
     * @param id
     * @param systemUserDTO
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserCodeConstant.ADMIN_SYSTEM_USER_UPDATE + "')")
    public ResultMessage updateUser(@PathVariable("id") Long id,
                                    @BeanValid @RequestBody SaveSystemUserDTO systemUserDTO) {
        systemUserService.updateSystemUser(id,systemUserDTO);
        return ResultMessage.success();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserCodeConstant.ADMIN_SYSTEM_USER_DELETE + "')")
    public ResultMessage deleteUser(@PathVariable("id") Long id) {
        systemUserService.deleteUserById(id);
        return ResultMessage.success();
    }

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status/{id}")
    @PreAuthorize("hasAuthority('" + UserCodeConstant.ADMIN_SYSTEM_USER_UPDATE_STATUS + "')")
    public ResultMessage status(@PathVariable("id") Long id,
                                @SimpleValid @NotNull(message = "状态不能为空") @Range(min = 1, max = 2, message = "状态值非法") @RequestParam Integer status) {
        systemUserService.updateUserStatus(id,status);
        return ResultMessage.success();
    }

    /**
     * 重置密码
     * @param id
     * @param password
     * @return
     */
    @PostMapping("/reset_password/{id}")
    @PreAuthorize("hasAuthority('" + UserCodeConstant.ADMIN_SYSTEM_USER_RESET_PASSWORD + "')")
    public ResultMessage resetPassword(@PathVariable("id") Long id,
                                       @SimpleValid @NotBlank(message = "密码不能为空") @RequestParam String password) {
        systemUserService.resetPassword(id, password);
        return ResultMessage.success();
    }

    /**
     * 用户角色列表
     * @param id
     * @return
     */
    @GetMapping("/roles/{id}")
    @PreAuthorize("hasAuthority('" + UserCodeConstant.ADMIN_SYSTEM_USER_ROLE_LIST + "')")
    public ResultMessage<UserRoleListVO> userRoles(@PathVariable("id") Long id){
        UserRoleListVO userRoleListVO =systemUserService.userRoles(id);
        return ResultMessage.success(userRoleListVO);
    }

}
