package com.rao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rao.dao.JobQueryDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.dto.JobKeyDTO;
import com.rao.pojo.dto.SaveJobDTO;
import com.rao.pojo.entity.QuartzEntity;
import com.rao.service.JobService;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 任务 service 实现
 * @author raojing
 * @date 2019/12/18 14:05
 */
@Slf4j
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Resource
    private JobQueryDao jobQueryDao;
    @Resource
    private Scheduler scheduler;

    @Override
    public void saveJob(SaveJobDTO saveJobDTO) {
        try{
            //如果是修改  展示旧的 任务
            if (saveJobDTO.getOldJobGroup() != null) {
                JobKey key = JobKey.jobKey(saveJobDTO.getOldJobName(), saveJobDTO.getOldJobGroup());
                TriggerKey triggerKey = TriggerKey.triggerKey(saveJobDTO.getJobName(), saveJobDTO.getJobGroup());
                // 停止触发器  
                scheduler.pauseTrigger(triggerKey);
                // 移除触发器  
                scheduler.unscheduleJob(triggerKey);
                // 删除任务
                scheduler.deleteJob(key);
            }
            Class clazz = Class.forName(saveJobDTO.getJobClassName());
            clazz.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(clazz).withIdentity(saveJobDTO.getJobName(),
                    saveJobDTO.getJobGroup())
                    .withDescription(saveJobDTO.getDescription()).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(saveJobDTO.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + saveJobDTO.getJobName(), saveJobDTO.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        }catch (Exception e){
            log.error("保存任务失败-->{}", e.getMessage());
            throw BusinessException.operate("保存任务失败");
        }
    }

    @Override
    public PageResult<QuartzEntity> listQuartzEntity(PageParam pageParam, String jobName) {
        PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize());
        List<QuartzEntity> quartzEntities = jobQueryDao.listJobByParams(jobName);
        PageInfo<QuartzEntity> pageInfo = new PageInfo<>(quartzEntities);
        return PageResult.build(pageInfo.getTotal(), quartzEntities);
    }

    @Override
    public void trigger(JobKeyDTO jobKeyDTO) {
        try {
            JobKey key = new JobKey(jobKeyDTO.getJobName(), jobKeyDTO.getJobGroup());
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            log.error("触发任务失败-->{}", e.getMessage());
            throw BusinessException.operate("触发任务失败");
        }
    }

    @Override
    public void pause(JobKeyDTO jobKeyDTO) {
        try {
            JobKey key = new JobKey(jobKeyDTO.getJobName(), jobKeyDTO.getJobGroup());
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            log.error("停止任务失败-->{}", e.getMessage());
            throw BusinessException.operate("停止任务失败");
        }
    }

    @Override
    public void resume(JobKeyDTO jobKeyDTO) {
        try {
            JobKey key = new JobKey(jobKeyDTO.getJobName(), jobKeyDTO.getJobGroup());
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            log.error("恢复任务失败-->{}", e.getMessage());
            throw BusinessException.operate("恢复任务失败");
        }
    }

    @Override
    public void remove(JobKeyDTO jobKeyDTO) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobKeyDTO.getJobName(), jobKeyDTO.getJobGroup());
            // 停止触发器  
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器  
            scheduler.unscheduleJob(triggerKey);
            // 删除任务  
            scheduler.deleteJob(JobKey.jobKey(jobKeyDTO.getJobName(), jobKeyDTO.getJobGroup()));
        } catch (Exception e) {
            log.error("删除任务失败-->{}", e.getMessage());
            throw BusinessException.operate("删除任务失败");
        }
    }

}