package com.rao.controller;

import com.rao.annotation.BeanValid;
import com.rao.constant.permission.quartz.QuartzCodeConstant;
import com.rao.pojo.dto.JobKeyDTO;
import com.rao.pojo.dto.SaveJobDTO;
import com.rao.pojo.entity.QuartzEntity;
import com.rao.service.JobService;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import com.rao.util.result.ResultMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 任务
 * @author raojing
 * @date 2019/12/18 14:05
 */
@RestController
@RequestMapping("/job")
public class JobController {
    
    @Resource
    private JobService jobService;

    /**
     * 保存或修改任务
     * @param saveJobDTO
     * @return
     */
    @PostMapping()
    @PreAuthorize("hasAuthority('" + QuartzCodeConstant.QUARTZ_TASK_SAVE + "')")
    public ResultMessage save(@BeanValid @RequestBody SaveJobDTO saveJobDTO) {
        jobService.saveJob(saveJobDTO);
        return ResultMessage.success().message("添加任务成功");
    }

    /**
     * 获取任务列表
     * @param pageParam
     * @param jobName
     * @return
     */
    @GetMapping()
    @PreAuthorize("hasAuthority('" + QuartzCodeConstant.QUARTZ_TASK_LIST + "')")
    public ResultMessage<PageResult<QuartzEntity>> list(PageParam pageParam, @RequestParam(required = false) String jobName) {
        PageResult<QuartzEntity> pageResult = jobService.listQuartzEntity(pageParam, jobName);
        return ResultMessage.success(pageResult).message("获取任务列表成功");
    }

    /**
     * 触发任务
     * @param jobKeyDTO
     * @return
     */
    @PostMapping("/trigger")
    @PreAuthorize("hasAuthority('" + QuartzCodeConstant.QUARTZ_TASK_TRIGGER + "')")
    public ResultMessage trigger(@BeanValid @RequestBody JobKeyDTO jobKeyDTO) {
        jobService.trigger(jobKeyDTO);
        return ResultMessage.success().message("触发任务成功");
    }

    /**
     * 停止任务
     * @param jobKeyDTO
     * @return
     */
    @PostMapping("/pause")
    @PreAuthorize("hasAuthority('" + QuartzCodeConstant.QUARTZ_TASK_PAUSE + "')")
    public ResultMessage pause(@BeanValid @RequestBody JobKeyDTO jobKeyDTO) {
        jobService.pause(jobKeyDTO);
        return ResultMessage.success().message("停止任务成功");
    }

    /**
     * 恢复任务
     * @param jobKeyDTO
     * @return
     */
    @PostMapping("/resume")
    @PreAuthorize("hasAuthority('" + QuartzCodeConstant.QUARTZ_TASK_RESUME + "')")
    public ResultMessage resume(@BeanValid @RequestBody JobKeyDTO jobKeyDTO) {
        jobService.resume(jobKeyDTO);
        return ResultMessage.success().message("恢复任务成功");
    }

    /**
     * 删除任务
     * @param jobKeyDTO
     * @return
     */
    @DeleteMapping()
    @PreAuthorize("hasAuthority('" + QuartzCodeConstant.QUARTZ_TASK_DELETE + "')")
    public ResultMessage remove(@BeanValid @RequestBody JobKeyDTO jobKeyDTO) {
        jobService.remove(jobKeyDTO);
        return ResultMessage.success().message("移除任务成功");
    }
}
