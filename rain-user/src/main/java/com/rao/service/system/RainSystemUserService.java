package com.rao.service.system;
import pojo.vo.user.SystemUserVO;

/**
 * 系统用户 service
 * @author raojing
 * @date 2019/11/25 23:18
 */
public interface RainSystemUserService {

    /**
     * 根据账号查询用户信息
     * @param account
     * @return
     */
    SystemUserVO findByAccount(String account);
    
}
