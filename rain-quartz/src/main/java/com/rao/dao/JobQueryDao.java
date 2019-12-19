package com.rao.dao;

import com.rao.pojo.entity.QuartzEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务查询 dao
 * @author raojing
 * @date 2019/12/19 11:19
 */
public interface JobQueryDao {

    /**
     * 获取任务列表
     * @param jobName
     * @return
     */
    List<QuartzEntity> listJobByParams(@Param("jobName") String jobName);
    
}
