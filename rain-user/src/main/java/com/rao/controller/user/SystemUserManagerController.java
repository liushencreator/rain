package com.rao.controller.user;

import com.rao.annotation.BeanValid;
import com.rao.annotation.SimpleValid;
import com.rao.pojo.dto.SaveSystemUserDTO;
import com.rao.util.result.ResultMessage;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 系统用户管理
 * @author raojing
 * @date 2019/12/12 13:40
 */
@RestController
@RequestMapping("/manager/system_user")
public class SystemUserManagerController {

    /**
     * 用户列表
     * @return
     */
    @GetMapping()
    public ResultMessage list(){
        
        return ResultMessage.success();
    }

    /**
     * 用户详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultMessage detail(@PathVariable("id") Long id){
        
        return ResultMessage.success();
    }

    /**
     * 新增用户
     * @param systemUserDTO
     * @return
     */
    @PostMapping()
    public ResultMessage addUser(@BeanValid @RequestBody SaveSystemUserDTO systemUserDTO){
        
        return ResultMessage.success();
    }

    /**
     * 修改用户
     * @param id
     * @param systemUserDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResultMessage updateUser(@PathVariable("id") Long id,
                                    @BeanValid @RequestBody SaveSystemUserDTO systemUserDTO){
        
        return ResultMessage.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultMessage deleteUser(@PathVariable("id") Long id){
        
        return ResultMessage.success();
    }

    /**
     * 修改用户状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status/{id}")
    public ResultMessage status(@PathVariable("id") Long id,
                                @SimpleValid @NotNull(message = "状态不能为空") @Range(min = 1, max = 2, message = "状态值非法") @RequestParam Integer status){
        
        return ResultMessage.success();
    }
    
}
