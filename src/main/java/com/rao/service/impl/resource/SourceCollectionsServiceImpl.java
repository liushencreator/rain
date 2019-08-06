package com.rao.service.impl.resource;

import com.rao.Utils.Paramap;
import com.rao.bean.resource.SourceCollections;
import com.rao.dao.resource.ResourceVideoDao;
import com.rao.dao.resource.SourceCollectionsDao;
import com.rao.service.resource.SourceCollectionsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * SERVICE - SourceCollections(本地视频)
 * @author zijing
 * @version 2.0
 */
@Service
public class SourceCollectionsServiceImpl implements SourceCollectionsService {
	
	@Resource
	private SourceCollectionsDao sourceCollectionsDao;
	@Resource
	private ResourceVideoDao resourceVideoDao;


	@Override
	public Integer save(SourceCollections param) {
		return sourceCollectionsDao.insert(param);
	}

	@Override
	public Integer delete(Long pk) {
		return sourceCollectionsDao.delete(pk);
	}

	@Override
	public Integer count(Map<String, Object> params) {
		return sourceCollectionsDao.count(params);
	}

	@Override
	public SourceCollections find(String propertyName, Object propertyValue) {
		List<SourceCollections> byParams = sourceCollectionsDao.findByParams(Paramap.create().put(propertyName, propertyValue));
		if(CollectionUtils.isEmpty(byParams)){
			return null;
		}
		return byParams.get(0);
	}

	@Override
	public List<SourceCollections> findByPage(Map<String, Object> params, Integer pageNumber, Integer pageSize) {
		params.put("pageBegin",(pageNumber-1)*pageSize);
		params.put("pageSize",pageSize);
		return sourceCollectionsDao.findByPage(params);
	}

	@Override
	public void adjustCollection() {
		sourceCollectionsDao.adjustCollection();
	}
}