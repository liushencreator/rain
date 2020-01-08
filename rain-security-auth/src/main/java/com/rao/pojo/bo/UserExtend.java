package com.rao.pojo.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
public class UserExtend extends User {

    /**
     * 用户ID（扩展字段）
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

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

    public UserExtend id(Long id){
        this.id = id;
        return this;
    }

    public UserExtend name(String name){
        this.name = name;
        return this;
    }

    public UserExtend phone(String phone){
        this.phone = phone;
        return this;
    }

    public UserExtend nickName(String nickName){
        this.nickName = nickName;
        return this;
    }

    public UserExtend email(String email){
        this.email = email;
        return this;
    }

}
