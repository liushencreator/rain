package com.rao.client.user;

import com.rao.client.user.fallback.SystemUserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.result.ResultMessage;

/**
 * 系统用户 客户端
 * @author raojing
 * @date 2019/12/3 11:07
 */
@FeignClient(value = "rain-user", fallbackFactory = SystemUserClientFallback.class)
public interface SystemUserClient {

    /**
     * 通过账号查询用户信息
     * @param account
     * @return
     */
    @RequestMapping(value = "/user/system/user/{account}", method = RequestMethod.GET)
    ResultMessage findByAccount(@PathVariable("account") String account);
    
}
