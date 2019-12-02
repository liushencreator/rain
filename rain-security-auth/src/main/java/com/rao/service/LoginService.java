package com.rao.service;

import com.rao.pojo.dto.LoginDTO;

/**
 * 登录数据逻辑层
 * @author raojing
 * @date 2019/12/2 14:52
 */
public interface LoginService {

    /**
     * 登录
     * @param loginDTO
     * @return
     */
    String loginAdmin(LoginDTO loginDTO);
    
}
