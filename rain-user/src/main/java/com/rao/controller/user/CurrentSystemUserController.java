package com.rao.controller.user;

import com.rao.annotation.BeanValid;
import com.rao.pojo.bo.CurrentUserInfo;
import com.rao.pojo.dto.RePasswordDTO;
import com.rao.pojo.vo.user.UserInfoVO;
import com.rao.service.user.CurrentUserService;
import com.rao.util.result.ResultMessage;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.rao.pojo.vo.user.CurrentSystemUserVO;

import javax.annotation.Resource;

/**
 * 系统用户
 * @author raojing
 * @date 2019/12/3 9:42
 */
@RestController
@RequestMapping("/current/system_user")
public class CurrentSystemUserController {

    @Resource
    private CurrentUserService currentUserService;

    /**
     * 查询当前用户信息
     * @return
     */
    @GetMapping()
    public ResultMessage<CurrentSystemUserVO> findByAccount(CurrentUserInfo currentUser){
        CurrentSystemUserVO systemUserVO = currentUserService.findById(currentUser.getId());
        return ResultMessage.success(systemUserVO);
    }

    /**
     * 查询当前用户详细信息
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "查询当前用户详细信息",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 401, message = "未登录"),
            @ApiResponse(code = 403, message = "未授权"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误"),
    })
    public ResultMessage<UserInfoVO> findUserInfo(CurrentUserInfo currentUser) {
        UserInfoVO userInfoVO = currentUserService.info(currentUser.getId());
        return ResultMessage.success(userInfoVO);
    }

    /**
     * 修改用户密码
     * @return
     */
    @PutMapping("/password")
    public ResultMessage rePassword(CurrentUserInfo currentUser, @BeanValid @RequestBody RePasswordDTO rePasswordDTO) {
        currentUserService.rePassword(currentUser.getId(), rePasswordDTO.getOldPassword(), rePasswordDTO.getNewPassword(),rePasswordDTO.getRePassword());
        return ResultMessage.success();
    }

}
