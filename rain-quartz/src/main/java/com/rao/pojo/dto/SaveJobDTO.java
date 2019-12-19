package com.rao.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 保存任务 数据传输模型
 * @author raojing
 * @date 2019/12/19 15:37
 */
@Data
public class SaveJobDTO {

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空")
    private String jobName;

    /**
     * 任务分组
     */
    @NotBlank(message = "任务分组不能为空")
    private String jobGroup;

    /**
     * 任务描述
     */
    @NotBlank(message = "任务描述不能为空")
    private String description;

    /**
     * 任务全类名
     */
    @NotBlank(message = "任务全类名不能为空")
    private String jobClassName;

    /**
     * 执行时间 cron 表达式
     */
    @NotBlank(message = "执行时间 cron 表达式不能为空")
    private String cronExpression;

    /**
     * 任务名称 用于修改
     */
    private String oldJobName;

    /**
     * 任务分组 用于修改
     */
    private String oldJobGroup;
    
}
