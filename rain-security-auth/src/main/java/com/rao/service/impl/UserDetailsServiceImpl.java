package com.rao.service.impl;

import com.google.common.collect.Lists;
import com.rao.client.user.SystemUserClient;
import org.springframework.beans.BeanUtils;
import pojo.vo.user.SystemUserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import util.result.ResultMessage;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 自定义认证授权
 * @author raojing
 * @date 2019/12/2 13:34
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SystemUserClient systemUserClient;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        ResultMessage resultMessage = systemUserClient.findByAccount(userName);

        LinkedHashMap map =  (LinkedHashMap)resultMessage.getData().get("systemUserVO");

        System.out.println(map);

        SystemUserVO systemUser = (SystemUserVO)resultMessage.getData().get("systemUserVO");

        if (systemUser != null) {
            // 用户名匹配
            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            grantedAuthorities.add(grantedAuthority);
            return new User(userName, systemUser.getPassword(), grantedAuthorities);
        }else {
            // 用户名不匹配
            return null;
        }
    }
}
