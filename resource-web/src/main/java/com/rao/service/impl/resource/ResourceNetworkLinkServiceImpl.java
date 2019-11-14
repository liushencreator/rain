package com.rao.service.impl.resource;

import com.rao.dao.resource.ResourceNetworkLinkDao;
import com.rao.util.common.PageParamsUtil;
import com.rao.util.common.Paramap;
import org.springframework.stereotype.Service;
import pojo.entity.resource.ResourceNetworkLink;
import pojo.vo.resource.NetworkLinkVO;
import service.resource.ResourceNetworkLinkService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 网链 service 实现
 * @author raojing
 * @date 2019/11/14 21:47
 */
@Service
public class ResourceNetworkLinkServiceImpl implements ResourceNetworkLinkService {

    @Resource
    private ResourceNetworkLinkDao resourceNetworkLinkDao;

    @Override
    public List<NetworkLinkVO> listNetworkLink(Integer pageNumber, Integer pageSize, String searchKeyWord) {
        String orderByRule = "praise_number desc ,broadcast_number desc ,click_number desc";
        Paramap paramap = PageParamsUtil.baseParam(pageNumber, pageSize, orderByRule);
        paramap.put("status", 1);
        paramap.put("keyWord", searchKeyWord);
        List<ResourceNetworkLink> networkLinkList = resourceNetworkLinkDao.findByPage(paramap);
        return networkLinkList.stream().map(item -> {
            return NetworkLinkVO.builder()
                    .id(item.getId())
                    .linkName(item.getLinkName())
                    .linkPath(item.getLinkPath())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Integer count(String searchKeyWord) {
        Paramap paramap = Paramap.create();
        paramap.put("keyWord", searchKeyWord);
        return resourceNetworkLinkDao.count(paramap);
    }

}
