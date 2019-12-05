package com.rao.service.impl;

import com.rao.dao.RainSystemUserDao;
import com.rao.pojo.entity.RainSystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义认证授权
 * @author raojing
 * @date 2019/12/2 13:34
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(userName);
        if (systemUser != null) {
            // 用户名匹配
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            grantedAuthorities.add(grantedAuthority);
            return new User(userName, systemUser.getPassword(), grantedAuthorities);
        }else {
            // 用户名不匹配
            return null;
        }
    }
}
