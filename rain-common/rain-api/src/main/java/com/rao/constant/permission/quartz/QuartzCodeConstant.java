package com.rao.constant.permission.quartz;

import com.rao.annotation.PermissionDesc;

/**
 * Quartz 服务-权限标识
 * @author raojing
 * @date 2019/12/19 17:28
 */
public interface QuartzCodeConstant {

    /********************************* 任务调度相关 *********************************/
    /**
     * Quartz任务组
     */
    @PermissionDesc(desc = "Quartz任务组")
    String QUARTZ_TASK_GROUP = "quartz:task:group";

    /**
     * Quartz任务保存
     */
    @PermissionDesc(desc = "Quartz任务保存")
    String QUARTZ_TASK_SAVE = "quartz:task:save";

    /**
     * Quartz任务列表
     */
    @PermissionDesc(desc = "Quartz任务列表")
    String QUARTZ_TASK_LIST = "quartz:task:list";

    /**
     * Quartz任务删除
     */
    @PermissionDesc(desc = "Quartz任务删除")
    String QUARTZ_TASK_DELETE = "quartz:task:delete";

    /**
     * Quartz任务触发
     */
    @PermissionDesc(desc = "Quartz任务触发")
    String QUARTZ_TASK_TRIGGER = "quartz:task:trigger";

    /**
     * Quartz任务停止
     */
    @PermissionDesc(desc = "Quartz任务停止")
    String QUARTZ_TASK_PAUSE = "quartz:task:pause";

    /**
     * Quartz任务恢复
     */
    @PermissionDesc(desc = "Quartz任务恢复")
    String QUARTZ_TASK_RESUME = "quartz:task:resume";    
    /********************************* 任务调度相关 *********************************/
    
}
