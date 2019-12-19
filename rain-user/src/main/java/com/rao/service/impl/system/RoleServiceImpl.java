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
import com.rao.pojo.vo.system.ListRoleVO;
import com.rao.pojo.vo.system.PageRoleVO;
import com.rao.pojo.vo.system.RoleDetailVO;
import com.rao.service.system.RoleService;
import com.rao.util.CopyUtil;
import com.rao.util.common.TwiterIdUtil;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

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
        checkPermission(permissions);

        // 保存角色信息
        Long roleId = TwiterIdUtil.getTwiterId();
        Date now = new Date();
        RainRole role = new RainRole();
        BeanUtils.copyProperties(roleDTO, role);
        role.setId(roleId);
        role.setCreateTime(now);
        role.setUpdateTime(now);
        rainRoleDao.insert(role);
        saveRolePermission(roleId, permissions);
    }

    @Override
    public PageResult<PageRoleVO> pageRole(PageParam pageParam) {
        // 分页
        PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize());
        List<RainRole> rainRoleList = rainRoleDao.selectAll();
        PageInfo<RainRole> pageInfo = PageInfo.of(rainRoleList);
        // 封装视图模型
        List<PageRoleVO> roleVOList = CopyUtil.transToOList(rainRoleList, PageRoleVO.class);
        return PageResult.build(pageInfo.getTotal(), roleVOList);
    }

    @Override
    public RoleDetailVO findById(Long id) {
        RainRole rainRole = rainRoleDao.selectByPrimaryKey(id);
        if(null==rainRole){
            throw BusinessException.operate(id + "不存在");
        }
        RoleDetailVO roleDetailVO = CopyUtil.transToO(rainRole, RoleDetailVO.class);
        Example example = new Example(RainRolePermission.class);
        example.createCriteria().andEqualTo("roleId", id);
        List<RainRolePermission> rainRolePermissions = rainRolePermissionDao.selectByExample(example);
        List<String> permissionIdList = rainRolePermissions.stream().map(item -> String.valueOf(item.getPermissionId())).collect(Collectors.toList());
        roleDetailVO.setPermissionList(permissionIdList);
        return roleDetailVO;
    }

    @Override
    public List<ListRoleVO> listRole() {
        List<RainRole> rainRoleList = rainRoleDao.selectAll();
        List<ListRoleVO> roleList = CopyUtil.transToOList(rainRoleList, ListRoleVO.class);
        return roleList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateRole(Long id, SaveRoleDTO roleDTO) {
        RainRole rainRole = rainRoleDao.selectByPrimaryKey(id);
        List<Long> permissions = roleDTO.getPermissions();
        if(null==rainRole){
            throw BusinessException.operate(id + "不存在");
        }
        checkPermission(permissions);
        BeanUtils.copyProperties(roleDTO, rainRole);
        rainRoleDao.updateByPrimaryKeySelective(rainRole);
        //删除角色权限中间表,再保存新的关系
        deleteRolePermission(id);
        saveRolePermission(id, permissions);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        RainRole rainRole = rainRoleDao.selectByPrimaryKey(id);
        if (null == rainRole) {
            throw BusinessException.operate(id + "不存在");
        }
        rainRoleDao.deleteByPrimaryKey(id);
        deleteRolePermission(id);
    }

    /**
     * 删除角色权限中间表
     * @param roleId
     */
    private void deleteRolePermission(Long roleId){
        //删除角色权限中间表
        Example deleteExample = new Example(RainRolePermission.class);
        deleteExample.createCriteria().andEqualTo("roleId", roleId);
        rainRolePermissionDao.deleteByExample(deleteExample);
    }

    /**
     * 检验权限是否存在
     * @param permissions
     */
    private void checkPermission(List<Long> permissions){
        List<RainPermission> permissionList =  permissionDao.listByPermissionIds(permissions);
        if(CollectionUtils.isEmpty(permissionList)){
            // 根据权限id查询出的权限信息为空
            throw BusinessException.operate("非法操作，权限信息为空");
        }else if(permissionList.size() != permissions.size()){
            // 查询出来的权限信息数量和参数数量不匹配
            throw BusinessException.operate("非法操作，权限信息有误");
        }
    }

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissions
     * @return
     */
    private List<RainRolePermission> saveRolePermission(Long roleId,List<Long> permissions){
        // 保存角色权限关系
        List<RainRolePermission> rolePermissionList = permissions.stream().map(item -> {
            return RainRolePermission.builder()
                    .roleId(roleId)
                    .permissionId(item)
                    .build();
        }).collect(Collectors.toList());
        rainRolePermissionDao.batchSaveRelation(rolePermissionList);
        return rolePermissionList;
    }

}
