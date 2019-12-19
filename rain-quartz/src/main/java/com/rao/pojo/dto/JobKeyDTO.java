package com.rao.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 任务健 数据传输模型
 * @author raojing
 * @date 2019/12/19 16:17
 */
@Data
public class JobKeyDTO {

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
    
}
