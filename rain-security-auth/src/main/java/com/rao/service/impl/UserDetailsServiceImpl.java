package com.rao.service.impl;

import com.rao.dao.RainSystemUserDao;
import com.rao.dao.UserPermissionDao;
import com.rao.pojo.bo.UserPermissionBO;
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
import java.util.stream.Collectors;

/**
 * 自定义认证授权
 * @author raojing
 * @date 2019/12/2 13:34
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static String ROLE_PREFIX = "ROLE_";
    
    @Resource
    private RainSystemUserDao rainSystemUserDao;
    @Resource
    private UserPermissionDao userPermissionDao;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(userName);
        if (systemUser != null) {
            // 查询用户权限信息
            List<UserPermissionBO> permissionList = userPermissionDao.listPermissionByUserId(systemUser.getId());

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            
            permissionList.forEach(item -> {
                
                String roleCode = item.getRoleCode();
                if(!roleCode.startsWith(ROLE_PREFIX)){
                    roleCode = ROLE_PREFIX + roleCode;
                }
                // 角色标识
                SimpleGrantedAuthority roleAuthority = new SimpleGrantedAuthority(roleCode);
                grantedAuthorities.add(roleAuthority);

                // 权限标识
                roleAuthority = new SimpleGrantedAuthority(item.getPermissionCode());
                grantedAuthorities.add(roleAuthority);
            });
            
            // 返回用户信息
            return new User(userName, systemUser.getPassword(), grantedAuthorities);
        }else {
            // 用户名不匹配
            return null;
        }
    }
}
