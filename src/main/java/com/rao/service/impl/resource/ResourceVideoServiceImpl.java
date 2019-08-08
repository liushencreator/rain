package com.rao.service.impl.resource;

import com.rao.Utils.common.Paramap;
import com.rao.bean.resource.ResourceVideo;
import com.rao.dao.resource.ResourceVideoDao;
import com.rao.service.resource.ResourceVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


/**
 * SERVICE - ResourceVideo(本地视频)
 * @author zijing
 * @version 2.0
 */
@Service
public class ResourceVideoServiceImpl implements ResourceVideoService {
	
	@Autowired
	private ResourceVideoDao resourceVideoDao;

	@Override
	public Integer save(ResourceVideo param) {
		return resourceVideoDao.insert(param);
	}

	@Override
	public Integer update(ResourceVideo param) {
		return resourceVideoDao.update(param);
	}

	@Override
	public Integer delete(Long pk) {
		return resourceVideoDao.delete(pk);
	}

	@Override
	public Integer deleteByServiceId(Long serviceId) {
		return resourceVideoDao.deleteByServiceId(serviceId);
	}

	@Override
	public Integer count() {
		return resourceVideoDao.count(Paramap.create());
	}

	@Override
	public Integer count(Map<String, Object> params) {
		return resourceVideoDao.count(params);
	}

	@Override
	public ResourceVideo find(Long pk) {
		return resourceVideoDao.find(pk);
	}

	@Override
	public List<ResourceVideo> findAll() {
		return resourceVideoDao.findAll();
	}

	@Override
	public ResourceVideo find(String propertyName, Object propertyValue) {
		List<ResourceVideo> byParams = resourceVideoDao.findByParams(Paramap.create().put(propertyName, propertyValue));
		if(CollectionUtils.isEmpty(byParams)){
			return null;
		}
		return byParams.get(0);
	}

	@Override
	public List<ResourceVideo> findList(String propertyName, Object propertyValue) {
		return resourceVideoDao.findByParams(Paramap.create().put(propertyName,propertyValue));
	}

	@Override
	public List<ResourceVideo> findList(Map<String, Object> params) {
		return resourceVideoDao.findByParams(params);
	}

	@Override
	public List<ResourceVideo> findByPage(Map<String, Object> params, Integer pageNumber, Integer pageSize) {
		params.put("pageBegin",(pageNumber-1)*pageSize);
		params.put("pageSize",pageSize);
		return resourceVideoDao.findByPage(params);
	}
}