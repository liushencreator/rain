package com.rao.controller.user;

import com.rao.annotation.BeanValid;
import com.rao.annotation.SimpleValid;
import com.rao.pojo.dto.SaveSystemUserDTO;
import com.rao.pojo.vo.user.SystemUserVO;
import com.rao.service.user.SystemUserService;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import com.rao.util.result.ResultMessage;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultMessage<PageResult<SystemUserVO>> list(@RequestBody PageParam pageParam) {
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
    public ResultMessage detail(@PathVariable("id") Long id) {
        SystemUserVO systemUser = systemUserService.findSystemUserById(id);
        return ResultMessage.success(systemUser);
    }

    /**
     * 新增用户
     *
     * @param systemUserDTO
     * @return
     */
    @PostMapping()
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
    public ResultMessage status(@PathVariable("id") Long id,
                                @SimpleValid @NotNull(message = "状态不能为空") @Range(min = 1, max = 2, message = "状态值非法") @RequestParam Integer status) {
        systemUserService.updateUserStatus(id,status);
        return ResultMessage.success();
    }

}
