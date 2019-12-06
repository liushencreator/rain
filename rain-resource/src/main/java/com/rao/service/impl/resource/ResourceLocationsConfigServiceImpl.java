package com.rao.service.impl.resource;

import com.rao.dao.resource.ResourceLocationsConfigDao;
import com.rao.pojo.entity.resource.ResourceLocationsConfig;
import com.rao.service.resource.ResourceLocationsConfigService;
import org.springframework.stereotype.Service;
import com.rao.util.common.PageParamsUtil;
import com.rao.util.common.Paramap;

import javax.annotation.Resource;
import java.util.List;


/**
 * SERVICE - ResourceLocationsConfig(本地映射路径配置)
 *
 * @author zijing
 * @version 2.0
 */
@Service
public class ResourceLocationsConfigServiceImpl implements ResourceLocationsConfigService {

	@Resource
	private ResourceLocationsConfigDao resourceLocationsConfigDao;


	@Override
	public List<ResourceLocationsConfig> listConfigByPage(Integer pageNumber, Integer pageSize) {
		Paramap paramap = PageParamsUtil.buildPageParam(pageNumber, pageSize);
		List<ResourceLocationsConfig> locationsConfigList = resourceLocationsConfigDao.findByPage(paramap);
		return locationsConfigList;
	}

	@Override
	public int count() {
		Paramap paramap = Paramap.create();
		return resourceLocationsConfigDao.count(paramap);
	}

}
