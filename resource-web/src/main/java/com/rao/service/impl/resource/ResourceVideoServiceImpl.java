package com.rao.service.impl.resource;

import com.rao.dao.resource.ResourceVideoDao;
import com.rao.util.common.PageParamsUtil;
import com.rao.util.common.Paramap;
import constant.common.DateFormatEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pojo.entity.resource.ResourceVideo;
import pojo.vo.resource.ResourceVideoVO;
import service.resource.ResourceVideoService;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
	public Integer count() {
		return resourceVideoDao.count(Paramap.create());
	}

	@Override
	public ResourceVideo find(Long pk) {
		return resourceVideoDao.find(pk);
	}

	@Override
	public List<ResourceVideo> findByPage(Map<String, Object> params, Integer pageNumber, Integer pageSize) {
		params.put("pageBegin",(pageNumber-1)*pageSize);
		params.put("pageSize",pageSize);
		return resourceVideoDao.findByPage(params);
	}

	@Override
	public List<ResourceVideoVO> listFavourite(Integer pageNumber, Integer pageSize) {
		String orderByRule = "praise_number desc ,broadcast_number desc ,click_number desc";
		Paramap paramap = PageParamsUtil.baseParam(pageNumber, pageSize, orderByRule);
		List<ResourceVideo> resourceVideoList = resourceVideoDao.findByPage(paramap);

		SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormatEnum.FORMAT_SYMBOL_EXTEND.getFormatString());
		return resourceVideoList.stream().map(item -> {
			return ResourceVideoVO.builder()
					.id(item.getId())
					.videoName(item.getVideoName())
					.videoDescribe(item.getVideoDescribe())
					.videoSize(item.getVideoSize())
					.createTime(dateFormat.format(item.getCreateTime()))
					.build();
		}).collect(Collectors.toList());
	}

	@Override
	public ResourceVideo resourceDetail(Long id) {
		ResourceVideo video = resourceVideoDao.find(id);
		// 点赞数量 +1
		Map<String, Object> incrParam = new HashMap<>(1);
		incrParam.put("id", id);
		incrParam.put("clickNumber", 1);
		resourceVideoDao.increaseStatisticsNumber(incrParam);
		return video;
	}
}