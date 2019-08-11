package com.rao.service.impl.resource;

import com.rao.dao.resource.ResourceLocationsConfigDao;
import com.rao.util.common.Paramap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pojo.entity.resource.ResourceLocationsConfig;
import service.resource.ResourceLocationsConfigService;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * SERVICE - ResourceLocationsConfig(本地映射路径配置)
 * 
 * @author zijing
 * @version 2.0
 */
@Service
public class ResourceLocationsConfigServiceImpl implements ResourceLocationsConfigService {
	
	@Autowired
	private ResourceLocationsConfigDao resourceLocationsConfigDao;

	@Override
	public Integer save(ResourceLocationsConfig param) {
		return resourceLocationsConfigDao.insert(param);
	}

	@Override
	public Integer update(ResourceLocationsConfig param) {
		return resourceLocationsConfigDao.update(param);
	}

	@Override
	public Integer delete(Integer pk) {
		return resourceLocationsConfigDao.delete(pk);
	}

	@Override
	public Integer deleteAll(Integer... ids) {
		return resourceLocationsConfigDao.deleteAll(Paramap.create().put("ids",ids));
	}

	@Override
	public Integer count() {
		return resourceLocationsConfigDao.count(Paramap.create());
	}

	@Override
	public Integer count(Map<String, Object> params) {
		return resourceLocationsConfigDao.count(params);
	}

	@Override
	public ResourceLocationsConfig find(Integer pk) {
		return resourceLocationsConfigDao.find(pk);
	}

	@Override
	public List<ResourceLocationsConfig> findAll() {
		return resourceLocationsConfigDao.findAll();
	}

	@Override
	public ResourceLocationsConfig find(String propertyName, Object propertyValue) {
		List<ResourceLocationsConfig> byParams = resourceLocationsConfigDao.findByParams(Paramap.create().put(propertyName, propertyValue));
		if(CollectionUtils.isEmpty(byParams)){
			return null;
		}
		return byParams.get(0);
	}

	@Override
	public List<ResourceLocationsConfig> findList(String propertyName, Object propertyValue) {
		return resourceLocationsConfigDao.findByParams(Paramap.create().put(propertyName,propertyValue));
	}

	@Override
	public List<ResourceLocationsConfig> findList(Map<String, Object> params) {
		return resourceLocationsConfigDao.findByParams(params);
	}

	@Override
	public Integer saveOrUpdate(ResourceLocationsConfig config) {
		Date now = new Date();
		config.setUpdateTime(now);
		if(config.getId() == null){
			config.setCreateTime(now);
			return resourceLocationsConfigDao.insertSelective(config);
		}else{
			return resourceLocationsConfigDao.update(config);
		}
	}
}
