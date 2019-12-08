package com.rao.service.impl.system;

import com.rao.dao.system.RainPermissionDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.dto.SavePermissionDTO;
import com.rao.pojo.entity.system.RainPermission;
import com.rao.service.system.PermissionService;
import com.rao.util.common.Paramap;
import com.rao.util.common.TwiterIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 权限 service 实现
 * @author raojing
 * @date 2019/12/8 14:23
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private RainPermissionDao rainPermissionDao;

    @Override
    public void savePermission(SavePermissionDTO permissionDTO) {
        RainPermission permission = new RainPermission();
        BeanUtils.copyProperties(permissionDTO, permission);

        // 判断权限标识是否已经存在
        String permissionCode = permissionDTO.getPermissionCode();
        Paramap paramap = Paramap.create().put("permissionCode", permissionCode);
        Integer count = rainPermissionDao.count(paramap);
        if(count > 0){
            throw BusinessException.operate(permissionCode + " 权限标识已存在");
        }

        Date now = new Date();
        permission.setId(TwiterIdUtil.getTwiterId());
        permission.setParentId(loadParentId(permissionDTO.getParentId()));
        permission.setCreateTime(now);
        permission.setUpdateTime(now);
        rainPermissionDao.insert(permission);
    }

    /**
     * 获取上级权限id
     * @param parentId
     * @return
     */
    private Long loadParentId(Long parentId){
        if(parentId == null || parentId == 0) {
            return -1L;
        }
        RainPermission parentPermission = rainPermissionDao.find(parentId);
        if(parentPermission == null) {
            throw BusinessException.operate("上级权限不存在");
        }
        return parentId;
    }

}
