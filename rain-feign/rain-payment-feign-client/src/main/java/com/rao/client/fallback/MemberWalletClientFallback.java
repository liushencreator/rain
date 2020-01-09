package com.rao.client.fallback;

import com.rao.client.MemberWalletClient;
import com.rao.util.result.ResultMessage;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 熔断器
 * @author raojing
 * @date 2020/1/9 10:47
 */
@Component
public class MemberWalletClientFallback implements FallbackFactory<MemberWalletClient> {
    @Override
    public MemberWalletClient create(Throwable throwable) {
        return new MemberWalletClient(){

            @Override
            public ResultMessage init() {
                return ResultMessage.fail().message("初始化会员钱包失败");
            }
            
        };
    }
}
