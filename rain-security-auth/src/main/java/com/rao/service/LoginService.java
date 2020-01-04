package com.rao.service;

import com.rao.pojo.dto.PasswordLoginDTO;
import com.rao.pojo.dto.SmsCodeLoginDTO;
import com.rao.pojo.dto.WxLoginDTO;
import com.rao.pojo.vo.LoginSuccessVO;

/**
 * 登录数据逻辑层
 * @author raojing
 * @date 2019/12/2 14:52
 */
public interface LoginService {

    /**
     * 后台用户账号密码登录
     * @param passwordLoginDTO
     * @return
     */
    LoginSuccessVO loginAdmin(PasswordLoginDTO passwordLoginDTO);

    /**
     * 刷新用户token
     * @param accountType
     * @param refreshToken
     * @return
     */
    LoginSuccessVO refreshToken(Integer accountType, String refreshToken);

    /**
     * 后台用户短信验证码登录
     * 通过手机号码查询用户信息，如果用户不存在，抛出异常，用户不存在
     * 如果存在，在缓存中获取之前发送成功的短信验证码（key = sms:用户id）
     * 比对参数中的短信验证码和缓存中取出的验证码是否一致，如不一致，抛出异常
     * 如一致，userDetailsService.loadUserByUsername(user)查询出来的密码修改为""的加密串（可设置一个常量）并封装成 UserDetails 对象
     * 调用service层封装的requestAccessToken 方法获取认证token，用户名为手机号码，密码为""
     * 删除缓存中用户的验证码
     * @param smsCodeLoginDTO
     * @return
     */
    LoginSuccessVO smsCodeLoginSystemUser(SmsCodeLoginDTO smsCodeLoginDTO);

    /**
     * C端用户登录微信第三方登录
     * 通过微信的code拿到openID，查询数据库，看openID是否存在
     * 如果不存在，用户表中插入一条记录，保存手机号码和用户openID，密码为""的加密串
     * 如果存在，userDetailsService.loadUserByUsername(user)查询出来的密码修改为""的加密串（可设置一个常量）并封装成 UserDetails 对象
     * 调用service层封装的requestAccessToken 方法获取认证token，用户名为微信获取的手机号码，密码为""
     * @param wxLoginDTO
     * @return
     */
    LoginSuccessVO wxLoginCUser(WxLoginDTO wxLoginDTO);
}
