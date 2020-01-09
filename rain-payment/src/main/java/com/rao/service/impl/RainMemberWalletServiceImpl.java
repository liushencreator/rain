package com.rao.service.impl;
import java.util.Date;

import com.rao.dao.RainMemberWalletDao;
import com.rao.pojo.entity.RainMemberWallet;
import com.rao.service.RainMemberWalletService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 会员钱包 service 实现
 * @author raojing
 * @date 2020/1/9 10:04
 */
@Service
public class RainMemberWalletServiceImpl implements RainMemberWalletService {
    
    @Resource
    private RainMemberWalletDao rainMemberWalletDao;

    @Override
    public void initWallet(Long id) {
        Date now = new Date();
        RainMemberWallet memberWallet = new RainMemberWallet();
        memberWallet.setMemberId(id);
        memberWallet.setPayPassword("");
        memberWallet.setBalance(0L);
        memberWallet.setHistoryBalance(0L);
        memberWallet.setCreateTime(now);
        memberWallet.setUpdateTime(now);
        rainMemberWalletDao.insertSelective(memberWallet);
    }
}
