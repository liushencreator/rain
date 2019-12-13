package com.rao.quartz.job;

/**
 * @ClassName : MyJob  //类名
 * @Description : 测试定时任务  //描述
 * @Author : xujianjie  //作者
 * @Date: 2019-11-28 13:26  //时间
 */

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import com.rao.quartz.service.VipService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@DisallowConcurrentExecution
public class VipJob implements Job, Serializable {

    @Autowired
    private VipService vipService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        System.out.println(retStrFormatNowDate);
        vipService.updateVipStatus();
    }
}
