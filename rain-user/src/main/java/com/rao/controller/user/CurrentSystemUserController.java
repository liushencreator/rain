package com.rao.controller.user;

import com.rao.pojo.bo.CurrentUserInfo;
import com.rao.service.user.CurrentUserService;
import com.rao.util.result.ResultMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rao.pojo.vo.user.SystemUserVO;

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
    public ResultMessage<SystemUserVO> findByAccount(CurrentUserInfo currentUser){
        SystemUserVO systemUserVO = currentUserService.findById(currentUser.getId());
        return ResultMessage.success(systemUserVO);
    }
    
    @GetMapping("test")
    public String test(){
        return "hello resource";
    }
    
}
