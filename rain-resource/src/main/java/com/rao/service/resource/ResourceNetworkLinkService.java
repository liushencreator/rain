package com.rao.service.resource;

import pojo.vo.resource.NetworkLinkVO;

import java.util.List;

/**
 * 网链 service
 * @author raojing
 * @date 2019/11/14 21:45
 */
public interface ResourceNetworkLinkService {

    /**
     * 分页查询网链列表
     * @param pageNumber
     * @param pageSize
     * @param searchKeyWord
     * @return
     */
    List<NetworkLinkVO> listNetworkLink(Integer pageNumber, Integer pageSize, String searchKeyWord);

    /**
     * 根据关键字统计数量
     * @param searchKeyWord
     * @return
     */
    Integer count(String searchKeyWord);
}
