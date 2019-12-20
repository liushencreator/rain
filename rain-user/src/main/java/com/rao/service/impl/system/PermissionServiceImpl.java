package com.rao.service.impl.system;

import com.google.common.collect.Lists;
import com.rao.annotation.PermissionDesc;
import com.rao.dao.system.RainPermissionDao;
import com.rao.dao.system.RainRolePermissionDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.dto.SavePermissionDTO;
import com.rao.pojo.entity.system.RainPermission;
import com.rao.pojo.entity.system.RainRolePermission;
import com.rao.pojo.vo.system.PermissionDescVO;
import com.rao.pojo.vo.system.PermissionVO;
import com.rao.service.system.PermissionService;
import com.rao.util.common.PermissionClazzUtil;
import com.rao.util.common.TwiterIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限 service 实现
 *
 * @author raojing
 * @date 2019/12/8 14:23
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private RainPermissionDao rainPermissionDao;

    @Resource
    private RainRolePermissionDao rainRolePermissionDao;

    @Override
    public void savePermission(SavePermissionDTO permissionDTO) {
        String permissionCode = permissionDTO.getPermissionCode();
        // 获取代码中所有的权限标识
        List<String> codeList = allPermissionCode().stream().map(PermissionDescVO::getPermissionCode).collect(Collectors.toList());
        if(!codeList.contains(permissionCode)){
            throw BusinessException.operate("非法的权限标识符");
        }
        
        // 判断权限标识是否已经存在
        Example countExample = new Example(RainPermission.class);
        countExample.createCriteria().andEqualTo("permissionCode", permissionCode);
        int count = rainPermissionDao.selectCountByExample(countExample);

        if (count > 0) {
            throw BusinessException.operate(permissionCode + " 权限标识已存在");
        }
        
        RainPermission permission = new RainPermission();
        BeanUtils.copyProperties(permissionDTO, permission);
        Date now = new Date();
        permission.setId(TwiterIdUtil.getTwiterId());
        permission.setParentId(loadParentId(permissionDTO.getParentId()));
        permission.setCreateTime(now);
        permission.setUpdateTime(now);
        rainPermissionDao.insert(permission);
    }

    @Override
    public List<PermissionVO> listPermission() {
        List<RainPermission> permissionList = rainPermissionDao.selectAll();
        List<PermissionVO> permissionVOList = new ArrayList<>();
        for (RainPermission permission : permissionList) {
            PermissionVO permissionVO = new PermissionVO();
            BeanUtils.copyProperties(permission, permissionVO);
            if (permission.getParentId() < 0) {
                permissionVO.setChildren(buildChildList(permissionList, permission.getId()));
                permissionVOList.add(permissionVO);
            }
        }
        return permissionVOList;
    }

    @Override
    public void updatePermission(Long id, SavePermissionDTO permissionDTO) {
        String permissionCode = permissionDTO.getPermissionCode();
        // 获取代码中所有的权限标识
        List<String> codeList = allPermissionCode().stream().map(PermissionDescVO::getPermissionCode).collect(Collectors.toList());
        if(!codeList.contains(permissionCode)){
            throw BusinessException.operate("非法的权限标识符");
        }
        
        RainPermission permission = rainPermissionDao.selectByPrimaryKey(id);
        if (null == permission) {
            throw BusinessException.operate(id + "不存在");
        }
        // 更改上级权限parentId时,判断上级权限是否存在以及是否存在下级权限的权限无法
        Long parentId = permissionDTO.getParentId();
        if (!permission.getParentId().equals(parentId)) {
            parentId = loadParentId(parentId);
            List<RainPermission> rainPermissions = rainPermissionDao.listByParentId(parentId);
            if (!CollectionUtils.isEmpty(rainPermissions)) {
                throw BusinessException.operate("该权限存在下级权限，无法更改上级权限");
            }
        }
        
        // 判断权限标识是否已经存在
        Example countExample = new Example(RainPermission.class);
        countExample.createCriteria()
                .andNotEqualTo("id", id)
                .andEqualTo("permissionCode", permissionCode);
        int count = rainPermissionDao.selectCountByExample(countExample);
        if (count > 0) {
            throw BusinessException.operate(permissionCode + " 权限标识已存在");
        }

        BeanUtils.copyProperties(permissionDTO, permission);
        permission.setUpdateTime(new Date());
        rainPermissionDao.updateByPrimaryKeySelective(permission);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deletePermission(Long id) {
        List<RainPermission> rainPermissions = rainPermissionDao.listByParentId(id);
        if (!CollectionUtils.isEmpty(rainPermissions)) {
            throw BusinessException.operate("该权限存在下级权限，无法删除");
        }
        rainPermissionDao.deleteByPrimaryKey(id);
        //删除角色权限中间表
        Example deleteExample = new Example(RainRolePermission.class);
        deleteExample.createCriteria().andEqualTo("permissionId", id);
        rainRolePermissionDao.deleteByExample(deleteExample);
    }

    @Override
    public List<PermissionDescVO> permissionCode() {
        // 获取代码中所有的权限标识
        List<PermissionDescVO> codeList = allPermissionCode();
        // 获取数据库中已经添加的权限标识
        List<RainPermission> permissionList = rainPermissionDao.selectAll();
        List<String> existCodeList = permissionList.stream().map(item -> {
            return item.getPermissionCode();
        }).collect(Collectors.toList());
        // 求所有的权限标识和已经添加的权限标识的差集
        return codeList.stream().filter(item -> !existCodeList.contains(item.getPermissionCode())).collect(Collectors.toList());
    }

    /**
     * 获取上级权限id
     *
     * @param parentId
     * @return
     */
    private Long loadParentId(Long parentId) {
        if (parentId == null || parentId == 0) {
            return -1L;
        }
        RainPermission parentPermission = rainPermissionDao.selectByPrimaryKey(parentId);
        if (parentPermission == null) {
            throw BusinessException.operate("上级权限不存在");
        }
        return parentId;
    }

    /**
     * 递归获取子分类
     *
     * @param permissionList
     * @param id
     * @return
     */
    private List<PermissionVO> buildChildList(List<RainPermission> permissionList, Long id) {
        List<PermissionVO> permissionVOList = new ArrayList<>();
        for (RainPermission permission : permissionList) {
            if (permission.getParentId().equals(id)) {
                PermissionVO permissionVO = new PermissionVO();
                BeanUtils.copyProperties(permission, permissionVO);
                permissionVO.setChildren(buildChildList(permissionList, permission.getId()));
                permissionVOList.add(permissionVO);
            }
        }
        return permissionVOList;
    }

    /**
     * 获取全部的权限标识
     * @return
     */
    private List<PermissionDescVO> allPermissionCode(){
        // 获取包下所有class
        List<Class> clazzList = PermissionClazzUtil.allPermissionClazz();
        try{
            // 获取代码中所有的权限标识
            return buildCode(clazzList);
        }catch (Exception e){
            throw BusinessException.operate("获取权限标识失败");
        }
    }

    /**
     * 通过反射获取权限标识
     * @param clazzList
     * @return
     * @throws Exception
     */
    private List<PermissionDescVO> buildCode(List<Class> clazzList) throws Exception{
        List<PermissionDescVO> codeList = Lists.newArrayList();
        for (Class clazz : clazzList) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String code = String.valueOf(field.get(clazz));
                PermissionDesc annotation = field.getAnnotation(PermissionDesc.class);
                String desc = annotation != null ? annotation.desc() : "";
                codeList.add(new PermissionDescVO(code, desc));
            }
        }
        return codeList;
    }

}
