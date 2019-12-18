package com.rao.service.impl.user;

import com.google.common.collect.Lists;
import com.rao.constant.system.PermissionEnum;
import com.rao.constant.system.RoleEnum;
import com.rao.constant.user.SuperAdminInitEnum;
import com.rao.dao.system.RainPermissionDao;
import com.rao.dao.system.RainRoleDao;
import com.rao.dao.system.RainRolePermissionDao;
import com.rao.dao.system.RainUserRoleDao;
import com.rao.dao.user.RainSystemUserDao;
import com.rao.pojo.entity.system.RainPermission;
import com.rao.pojo.entity.system.RainRole;
import com.rao.pojo.entity.system.RainRolePermission;
import com.rao.pojo.entity.system.RainUserRole;
import com.rao.pojo.entity.user.RainSystemUser;
import com.rao.service.user.SystemInitService;
import com.rao.util.common.Paramap;
import com.rao.util.common.TwiterIdUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统初始化 service 实现
 * @author raojing
 * @date 2019/12/11 19:21
 */
@Service
public class SystemInitServiceImpl implements SystemInitService {
    
    @Resource
    private RainPermissionDao rainPermissionDao;
    @Resource
    private RainRoleDao rainRoleDao;
    @Resource
    private RainRolePermissionDao rainRolePermissionDao;
    @Resource
    private RainSystemUserDao rainSystemUserDao;
    @Resource
    private RainUserRoleDao rainUserRoleDao;
    
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    
    
    @Override
    public void systemInit(String phone) {
        Date now = new Date();

        /**
         * 初始化超级管理员权限、角色、账号信息
         */
        // 初始化超级管理员权限        
        List<Long> permissionIdList = new ArrayList(20);
        // 权限组
        PermissionEnum permissionEnum = PermissionEnum.ADMIN_PERMISSION_GROUP;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 新增权限
        permissionEnum = PermissionEnum.ADMIN_PERMISSION_ADD;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 删除权限
        permissionEnum = PermissionEnum.ADMIN_PERMISSION_DELETE;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 修改权限
        permissionEnum = PermissionEnum.ADMIN_PERMISSION_UPDATE;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 权限列表
        permissionEnum = PermissionEnum.ADMIN_PERMISSION_LIST;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 角色组
        permissionEnum = PermissionEnum.ADMIN_ROLE_GROUP;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 新增角色
        permissionEnum = PermissionEnum.ADMIN_ROLE_ADD;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 角色删除
        permissionEnum = PermissionEnum.ADMIN_ROLE_DELETE;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 角色修改
        permissionEnum = PermissionEnum.ADMIN_ROLE_UPDATE;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 角色列表
        permissionEnum = PermissionEnum.ADMIN_ROLE_LIST;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 角色列表(全部)
        permissionEnum = PermissionEnum.ADMIN_ROLE_LIST_ALL;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 角色详情
        permissionEnum = PermissionEnum.ADMIN_ROLE_DETAIL;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 系统用户组
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_GROUP;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 系统用户新增
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_ADD;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 系统用户删除
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_DELETE;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 系统用户修改
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_UPDATE;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 系统用户状态修改
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_UPDATE_STATUS;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 系统用户列表
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_LIST;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 系统用户角色列表
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_ROLE_LIST;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 系统用户详情
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_DETAIL;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        // 修改密码
        permissionEnum = PermissionEnum.ADMIN_SYSTEM_USER_RESET_PASSWORD;
        permissionIdList.add(initPermission(permissionEnum, now));
        
        
        // 初始化超级管理员角色
        RoleEnum roleEnum = RoleEnum.SYSTEM_SUPER_MANAGER_ROLE;
        Long managerRoleId = initRole(roleEnum, now);
        // 关联权限和管理员角色的关系        
        relationRolePermission(managerRoleId, permissionIdList);
        // 初始化超级管理员用户
        SuperAdminInitEnum adminInitEnum = SuperAdminInitEnum.SYSTEM_SUPER_MANAGER;
        Long userId = initSystemUser(adminInitEnum, phone, now);
        // 关联用户和角色的关系
        relationUserRole(userId, managerRoleId);


        /**
         * 初始化普通用户权限、角色信息
         */
        // 初始化普通用户权限
        permissionEnum = PermissionEnum.C_USER_PERMISSION;
        Long userPermissionId = initPermission(permissionEnum, now);
        List<Long> userPermissionIds = Arrays.asList(userPermissionId);
        // 初始化普通用户角色
        roleEnum = RoleEnum.C_USER_ROLE;
        Long userRoleId = initRole(roleEnum, now);
        // 关联权限和普通用户角色的关系
        relationRolePermission(userRoleId, userPermissionIds);
    }

    /**
     * 初始化权限信息
     * @param permissionEnum
     * @param now
     */
    private Long initPermission(PermissionEnum permissionEnum, Date now){
        Example example = new Example(RainPermission.class);
        example.createCriteria().andEqualTo("permissionCode", permissionEnum.getPermissionCode());
        List<RainPermission> permissionList = rainPermissionDao.selectByExample(example);
        
        long permissionId = TwiterIdUtil.getTwiterId();
        if(CollectionUtils.isEmpty(permissionList)){
            RainPermission permission = new RainPermission();
            permission.setId(TwiterIdUtil.getTwiterId());
            permission.setPermissionName(permissionEnum.getPermissionName());
            permission.setPermissionCode(permissionEnum.getPermissionCode());
            permission.setPermissionDesc(permissionEnum.getPermissionDesc());
            permission.setParentId(-1L);
            permission.setCreateTime(now);
            permission.setUpdateTime(now);
            rainPermissionDao.insert(permission);
        }else{
            permissionId = permissionList.get(0).getId();
        }
        return permissionId;
    }

    /**
     * 初始化角色信息
     * @param roleEnum
     * @param now
     */
    private Long initRole(RoleEnum roleEnum, Date now){
        Example example = new Example(RainRole.class);
        example.createCriteria().andEqualTo("roleCode", roleEnum.getRoleCode());
        List<RainRole> roleList = rainRoleDao.selectByExample(example);
        
        long roleId = TwiterIdUtil.getTwiterId();
        if(CollectionUtils.isEmpty(roleList)){
            RainRole role = new RainRole();
            role.setId(roleId);
            role.setRoleName(roleEnum.getRoleName());
            role.setRoleCode(roleEnum.getRoleCode());
            role.setRoleDesc(roleEnum.getRoleDesc());
            role.setCreateTime(now);
            role.setUpdateTime(now);
            rainRoleDao.insert(role);
        }else{
            roleId = roleList.get(0).getId();
        }
        return roleId;        
    }

    /**
     * 初始化超级管理员
     * @param superAdminInitEnum
     * @param now
     */
    private Long initSystemUser(SuperAdminInitEnum superAdminInitEnum, String phone, Date now){
        RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(superAdminInitEnum.getInitUserName());
        Long userId = TwiterIdUtil.getTwiterId();
        if(systemUser == null){
            systemUser = new RainSystemUser();
            systemUser.setId(userId);
            systemUser.setUserName(superAdminInitEnum.getInitUserName());
            systemUser.setPhone(phone);
            systemUser.setPassword(passwordEncoder.encode(superAdminInitEnum.getInitPassword()));
            systemUser.setNickName("");
            systemUser.setEmail("");
            systemUser.setAvatar("");
            systemUser.setStatus(1);
            systemUser.setCreateTime(now);
            systemUser.setUpdateTime(now);
            rainSystemUserDao.insert(systemUser);
        }else{
            userId = systemUser.getId();
        }
        return userId;
    }

    /**
     * 关联角色和权限
     * @param roleId
     * @param permissionIds
     */
    private void relationRolePermission(Long roleId, List<Long> permissionIds){
        // 删除这个角色关联的所有权限
        rainRolePermissionDao.deleteByRoleIdAndPermissions(roleId, permissionIds);
        // 保存角色和权限的关系
        List<RainRolePermission> rolePermissionList = new ArrayList<>(permissionIds.size());
        for (Long permissionId : permissionIds) {
            rolePermissionList.add(new RainRolePermission(roleId, permissionId));
        }
        rainRolePermissionDao.insertList(rolePermissionList);
    }

    /**
     * 关联用户和角色
     * @param userId
     * @param roleId
     */
    private void relationUserRole(Long userId, Long roleId){
        RainUserRole userRole = new RainUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        rainUserRoleDao.insert(userRole);
    }
    
}
