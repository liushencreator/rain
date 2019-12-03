package com.rao.client.user.fallback;

import com.rao.client.user.SystemUserClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import util.result.ResultMessage;

/**
 * 熔断器
 * @author raojing
 * @date 2019/12/3 11:09
 */
@Component
public class SystemUserClientFallback implements FallbackFactory<SystemUserClient> {
    
    @Override
    public SystemUserClient create(Throwable throwable) {
        return new SystemUserClient(){

            @Override
            public ResultMessage findByAccount(String account) {
                return ResultMessage.fail().addMessage("获取用户信息失败");
            }
        };
    }
}
