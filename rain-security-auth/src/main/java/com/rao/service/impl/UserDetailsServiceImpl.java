package com.rao.service.impl;

import com.rao.dao.RainSystemUserDao;
import com.rao.dao.UserPermissionDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.bo.LoginUserBO;
import com.rao.pojo.bo.UserPermissionBO;
import com.rao.pojo.entity.RainSystemUser;
import com.rao.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
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

    private final static String ROLE_PREFIX = "ROLE_";
    
    @Resource
    private UserPermissionDao userPermissionDao;
    @Resource
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // 规则为：userType:userName
        String[] userType = userName.split(":", 2);
        if(userType.length != 2){
            throw BusinessException.operate("内部错误");
        }
        String type = userType[0];
        userName = userType[1];

        // 通过用户名或手机号码，用户类型查询用户信息
        LoginUserBO loginUser = userService.findByUserNameOrPhoneAndUserType(userName, type);
        if (loginUser != null) {
            // 查询用户权限信息
            List<UserPermissionBO> permissionList = userPermissionDao.listPermissionByUserId(loginUser.getId());

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
            return UserExtend.build(userName, loginUser.getPassword(), loginUser.getStatus() == 1, grantedAuthorities)
                    .id(loginUser.getId())
                    .name(loginUser.getUserName())
                    .phone(loginUser.getPhone())
                    .nickName(loginUser.getNickName())
                    .email(loginUser.getEmail());
        }else {
            // 用户名不匹配
            return null;
        }
    }
}
