package com.rao.service.resource;


import com.rao.bean.resource.ServicePath;

import java.util.List;

/**
 * SERVICE - ServicePath(服务地址)
 *
 * @author zijing
 * @version 2.0
 */
public interface ServicePathService {

    /**
     * 根据主键删除
     */
    Integer delete(Long pk);

    /**
     * 根据主键查询数据
     */
    ServicePath find(Long pk);

    /**
     * 查询所有数据
     */
    List<ServicePath> findAll();

    /**
     * 保存服务信息
     *
     * @param path
     * @return
     */
    Integer saveService(ServicePath path);

    /**
     * 初始化资源
     * <p>
     * 通过当前系统，根据配置查询出服务路径
     * 查询当前服务是否初始化有资源文件
     * 通过服务路径查询所有资源文件
     * 过滤掉已经初始化的资源文件， insert
     * 找出已经初始化的资源文件但在改路径中不存在的资源， delete
     *
     * @param id
     */
    void initResourceData(Long id);

}
