package service.resource;

import pojo.entity.resource.ResourceLocationsConfig;

import java.util.List;
import java.util.Map;

/**
 * SERVICE - ResourceLocationsConfig(本地映射路径配置)
 * 
 * @author zijing
 * @version 2.0
 */
public interface ResourceLocationsConfigService {

    /**
     * 保存
     */
    abstract Integer save(ResourceLocationsConfig param);

    /**
     * 更新
     */
    abstract Integer update(ResourceLocationsConfig param);

    /**
     * 根据主键删除
     */
    abstract Integer delete(Integer pk);

    /**
     * 删除列表
     */
    abstract Integer deleteAll(Integer... ids);

    /**
     * 查询实体对象数量
     */
    abstract Integer count();

    /**
     * 根据条件查询实体对象数量
     */
    abstract Integer count(Map<String, Object> params);

    /**
     * 根据主键查询数据
     */
    abstract ResourceLocationsConfig find(Integer pk);

    /**
     * 查询所有数据
     */
    abstract List<ResourceLocationsConfig> findAll();

    /**
     * 根据条件查询数据
     */
    abstract ResourceLocationsConfig find(String propertyName, Object propertyValue);

    /**
     * 根据属性名和属性值获取实体对象集合.
     */
    public List<ResourceLocationsConfig> findList(String propertyName, Object propertyValue);

    /**
     * 根据条件查询数据
     */
    abstract List<ResourceLocationsConfig> findList(Map<String, Object> params);

    /**
     * 保存或修改
     * @param config
     * @return
     */
    Integer saveOrUpdate(ResourceLocationsConfig config);
}
