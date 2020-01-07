package com.rao.service;

import com.rao.pojo.bo.LoginUserBO;
import com.rao.pojo.dto.SmsSendDTO;

/**
 * 用户 service
 * @author raojing
 * @date 2019/12/7 23:39
 */
public interface UserService {

    /**
     * 通过用户名或手机号码，用户类型查询用户信息
     * @param userName
     * @param type
     * @return
     */
    LoginUserBO findByUserNameOrPhoneAndUserType(String userName, String type);

    /**
     * 检查账号状态
     * @param smsSendDTO
     */
    void checkAccount(SmsSendDTO smsSendDTO);
}
