package com.rao.pojo.vo;

import lombok.Data;

/**
 * 用户信息 视图模型
 * @author raojing
 * @date 2019/12/2 15:11
 */
@Data
public class UserInfoVO {

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickName;
    
}
