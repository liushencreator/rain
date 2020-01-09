package com.rao.controller;

import com.rao.pojo.bo.CurrentUserInfo;
import com.rao.service.RainMemberWalletService;
import com.rao.util.result.ResultMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 会员钱包 controller
 * @author raojing
 * @date 2020/1/9 10:06
 */
@RestController
@RequestMapping("/member/wallet")
public class RainMemberWalletController {
    
    @Resource
    private RainMemberWalletService rainMemberWalletService;

    /**
     * 初始化会员钱包
      * @param currentUserInfo
     * @return
     */    
    @PostMapping("/init")
    public ResultMessage init(CurrentUserInfo currentUserInfo){
        rainMemberWalletService.initWallet(currentUserInfo.getId());
        return ResultMessage.success().message("初始化会员钱包成功");
    }    
    
}
