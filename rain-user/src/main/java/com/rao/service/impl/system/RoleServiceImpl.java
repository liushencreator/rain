package com.rao.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rao.dao.system.RainPermissionDao;
import com.rao.dao.system.RainRoleDao;
import com.rao.dao.system.RainRolePermissionDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.dto.SaveRoleDTO;
import com.rao.pojo.entity.system.RainPermission;
import com.rao.pojo.entity.system.RainRole;
import com.rao.pojo.entity.system.RainRolePermission;
import com.rao.pojo.vo.system.RoleDetailVO;
import com.rao.pojo.vo.system.RoleVO;
import com.rao.service.system.RoleService;
import com.rao.util.CopyUtil;
import com.rao.util.common.TwiterIdUtil;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色 service 实现
 * @author raojing
 * @date 2019/12/8 14:24
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RainRoleDao rainRoleDao;
    @Resource
    private RainPermissionDao permissionDao;
    @Resource
    private RainRolePermissionDao rainRolePermissionDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveRole(SaveRoleDTO roleDTO) {
        List<Long> permissions = roleDTO.getPermissions();
        List<RainPermission> permissionList =  permissionDao.listByPermissionIds(permissions);
        if(CollectionUtils.isEmpty(permissionList)){
            // 根据权限id查询出的权限信息为空
            throw BusinessException.operate("非法操作，权限信息为空");
        }else if(permissionList.size() != permissions.size()){
            // 查询出来的权限信息数量和参数数量不匹配
            throw BusinessException.operate("非法操作，权限信息有误");
        }

        // 保存角色信息
        Long roleId = TwiterIdUtil.getTwiterId();
        Date now = new Date();
        RainRole role = new RainRole();
        BeanUtils.copyProperties(roleDTO, role);
        role.setId(roleId);
        role.setCreateTime(now);
        role.setUpdateTime(now);
        rainRoleDao.insert(role);

        // 保存角色权限关系
        List<RainRolePermission> rolePermissionList = permissions.stream().map(item -> {
            return RainRolePermission.builder()
                    .roleId(roleId)
                    .permissionId(item)
                    .build();
        }).collect(Collectors.toList());
        rainRolePermissionDao.batchSaveRelation(rolePermissionList);
    }

    @Override
    public PageResult<RoleVO> pageRole(PageParam pageParam) {
        // 分页
        PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize());
        List<RainRole> rainRoleList = rainRoleDao.selectAll();
        PageInfo<RainRole> pageInfo = PageInfo.of(rainRoleList);
        // 封装视图模型
        List<RoleVO> roleVOList = CopyUtil.transToOList(rainRoleList, RoleVO.class);
        return PageResult.build(pageInfo.getTotal(), roleVOList);
    }

    @Override
    public RoleDetailVO findById(Long id) {
        RainRole rainRole = rainRoleDao.selectByPrimaryKey(id);
        if(null==rainRole){
            throw BusinessException.operate(id + "不存在");
        }
        RoleDetailVO roleDetailVO = CopyUtil.transToO(rainRole, RoleDetailVO.class);
        List<RainRolePermission> rainRolePermissionList = rainRolePermissionDao.listByRoleId(id);

        return roleDetailVO;
    }


}
