package com.rao.service.impl.user;

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

import javax.annotation.Resource;
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
         * 初始化超级管理员权限
         * 初始化普通用户权限
         */
        // 初始化超级管理员权限
        PermissionEnum permissionEnum = PermissionEnum.SYSTEM_SUPER_MANAGER_PERMISSION;
        Long managerPermissionId = initPermission(permissionEnum, now);
        // 初始化普通用户权限
        permissionEnum = PermissionEnum.C_USER_PERMISSION;
        Long userPermissionId = initPermission(permissionEnum, now);

        /* 
         * 初始化超级管理员角色
         * 初始化普通用户角色
         */
        // 初始化超级管理员角色
        RoleEnum roleEnum = RoleEnum.SYSTEM_SUPER_MANAGER_ROLE;
        Long managerRoleId = initRole(roleEnum, now);
        // 初始化普通用户角色
        roleEnum = RoleEnum.C_USER_ROLE;
        Long userRoleId = initRole(roleEnum, now);
        
        /**
         * 关联权限和管理员角色的关系
         * 关联权限和普通用户角色的关系
         */
        // 关联权限和管理员角色的关系
        relationRolePermission(managerRoleId, managerPermissionId);
        // 关联权限和普通用户角色的关系
        relationRolePermission(userRoleId, userPermissionId);

        
        /* 
         * 初始化超级管理员用户
         */
        SuperAdminInitEnum adminInitEnum = SuperAdminInitEnum.SYSTEM_SUPER_MANAGER;
        Long userId = initSystemUser(adminInitEnum, phone, now);
        // 关联用户和角色的关系
        relationUserRole(userId, managerRoleId);
    }

    /**
     * 初始化权限信息
     * @param permissionEnum
     * @param now
     */
    private Long initPermission(PermissionEnum permissionEnum, Date now){
        Paramap paramap = Paramap.create().put("permissionCode", permissionEnum.getPermissionCode());
        List<RainPermission> permissionList = rainPermissionDao.findByParams(paramap);
        
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
        Paramap paramap = Paramap.create().put("roleCode", roleEnum.getRoleCode());
        List<RainRole> roleList = rainRoleDao.findByParams(paramap);
        
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
     * @param permissionId
     */
    private void relationRolePermission(Long roleId, Long permissionId){
        RainRolePermission rolePermission = new RainRolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rainRolePermissionDao.insert(rolePermission);
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
