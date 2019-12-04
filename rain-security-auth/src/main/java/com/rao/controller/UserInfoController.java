package com.rao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 用户信息
 * @author raojing
 * @date 2019/12/4 21:57
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @GetMapping("/info")
    public Principal getUser(Principal principal) {
        log.info(principal.toString());
        return principal;
    }

}
