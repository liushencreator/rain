package com.rao.service.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 用户扩展信息
 * @author raojing
 * @date 2019/12/6 15:17
 */
@Getter
@Setter
@ToString
@Accessors(chain = true, fluent = true)
public class UserExtend extends User {

    /**
     * 用户ID（扩展字段）
     */
    private Long id;

    /**
     * 昵称（扩展字段）
     */
    private String nickName;

    /**
     * 邮箱（扩展字段）
     */
    private String email;

    private UserExtend(String username, String password, boolean locked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, locked, authorities);
    }
    
    public static UserExtend build(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities){
        return new UserExtend(username, password, enabled, authorities);
    }
    
}
