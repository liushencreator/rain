package com.rao.client;

import com.rao.client.fallback.MemberWalletClientFallback;
import com.rao.configuration.FeignRequestConfiguration;
import com.rao.constant.server.ServiceInstanceConstant;
import com.rao.util.result.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户钱包 客户端
 * @author raojing
 * @date 2020/1/9 10:27
 */
@FeignClient(
        value = ServiceInstanceConstant.RAIN_PAYMENT, 
        path = "/payment", 
        configuration = FeignRequestConfiguration.class, 
        fallbackFactory = MemberWalletClientFallback.class
)
public interface MemberWalletClient {

    /**
     * 初始化会员钱包
     * @return
     */
    @PostMapping("/member/wallet/init")
    ResultMessage init();
    
}
