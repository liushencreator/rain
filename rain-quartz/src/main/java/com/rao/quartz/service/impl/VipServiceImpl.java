package com.rao.quartz.service.impl;

import com.rao.quartz.dynamicquery.DynamicQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rao.quartz.service.VipService;

/**
 * @ClassName : VipServiceImpl  //类名
 * @Description : vip实现  //描述
 * @Author : xujianjie  //作者
 * @Date: 2019-11-28 13:32  //时间
 */
@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private DynamicQuery dynamicQuery;

    @Override
    @Transactional
    public void updateVipStatus() {
        StringBuffer nativeSql = new StringBuffer();
        nativeSql.append("update vip set status = 2");
        dynamicQuery.updateVipStatus(nativeSql.toString());
    }
}
