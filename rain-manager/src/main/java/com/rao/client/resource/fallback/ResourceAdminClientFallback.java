package com.rao.client.resource.fallback;

import com.rao.client.resource.ResourceAdminClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import util.result.ResultMessage;

/**
 * 熔断器
 * @author raojing
 * @date 2019/11/21 11:45
 */
@Component
public class ResourceAdminClientFallback implements FallbackFactory<ResourceAdminClient> {
    
    @Override
    public ResourceAdminClient create(Throwable throwable) {
        return new ResourceAdminClient(){
            
            @Override
            public ResultMessage testLocal() {
                return ResultMessage.fail().addMessage("获取资源失败");
            }
            
        };
    }
}
