package com.rao.service;

import com.rao.pojo.dto.JobKeyDTO;
import com.rao.pojo.dto.SaveJobDTO;
import com.rao.pojo.entity.QuartzEntity;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;

/**
 * 任务 service
 * @author raojing
 * @date 2019/12/18 14:05
 */
public interface JobService {

    /**
     * 保存或修改触发器
     * @param saveJobDTO
     */
    void saveJob(SaveJobDTO saveJobDTO);

    /**
     * 获取任务列表
     * @param pageParam
     * @param jobName
     * @return
     */
    PageResult<QuartzEntity> listQuartzEntity(PageParam pageParam, String jobName);

    /**
     * 触发任务
     * @param jobKeyDTO
     */
    void trigger(JobKeyDTO jobKeyDTO);

    /**
     * 停止任务
     * @param jobKeyDTO
     */
    void pause(JobKeyDTO jobKeyDTO);

    /**
     * 恢复任务
     * @param jobKeyDTO
     */
    void resume(JobKeyDTO jobKeyDTO);

    /**
     * 删除任务
     * @param jobKeyDTO
     */
    void remove(JobKeyDTO jobKeyDTO);
}