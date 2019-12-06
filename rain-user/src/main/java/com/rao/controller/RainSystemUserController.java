package com.rao.controller;

import com.alibaba.fastjson.JSON;
import com.rao.pojo.bo.CurrentUserInfo;
import com.rao.service.system.RainSystemUserService;
import com.rao.util.result.ResultMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rao.pojo.vo.user.SystemUserVO;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
 * 系统用户
 * @author raojing
 * @date 2019/12/3 9:42
 */
@RestController
@RequestMapping("/system/user")
public class RainSystemUserController {
    
    @Resource
    private RainSystemUserService rainSystemUserService;

    /**
     * 根据账号查询用户信息
     * @param account
     * @return
     */
    @GetMapping("{account}")
//    @PreAuthorize("hasAuthority('admin_user_detail')")
//    @PreAuthorize("hasAnyAuthority('admin_user_detail')")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultMessage<SystemUserVO> findByAccount(@PathVariable("account") String account, CurrentUserInfo currentUser){
        SystemUserVO systemUserVO = rainSystemUserService.findByAccount(account);
        return ResultMessage.success(systemUserVO);
    }
    
    @GetMapping("test")
    public String test(){
        return "hello resource";
    }
    
}
