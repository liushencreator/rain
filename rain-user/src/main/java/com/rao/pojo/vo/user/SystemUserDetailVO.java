package com.rao.pojo.vo.user;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author : hudelin
 * @className :SystemUserDetailVO
 * @description : 系统用户详情
 * @date: 2019-12-27 10:22
 */
@Data
public class SystemUserDetailVO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 角色列表
     */
    private List<UserRoleVO> userRoleVOList;
}
