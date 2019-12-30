package com.rao.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rao.dao.system.RainRoleDao;
import com.rao.dao.system.RainUserRoleDao;
import com.rao.dao.user.RainSystemUserDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.dto.SaveSystemUserDTO;
import com.rao.pojo.entity.system.RainPermission;
import com.rao.pojo.entity.system.RainRole;
import com.rao.pojo.entity.system.RainUserRole;
import com.rao.pojo.entity.user.RainSystemUser;
import com.rao.pojo.vo.user.SystemUserDetailVO;
import com.rao.pojo.vo.user.SystemUserVO;
import com.rao.pojo.vo.user.UserRoleListVO;
import com.rao.pojo.vo.user.UserRoleVO;
import com.rao.service.user.SystemUserService;
import com.rao.util.CopyUtil;
import com.rao.util.common.TwiterIdUtil;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName : SystemUserServiceImpl  //类名
 * @Description : 系统用户操作相关  //描述
 * @Author : xujianjie  //作者
 * @Date: 2019-12-16 16:43  //时间
 */
@Service

public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;
    @Resource
    private RainUserRoleDao userRoleDao;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private RainRoleDao rainRoleDao;

    @Override
    public PageResult<SystemUserVO> getSystemUserList(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNumber(), pageParam.getPageSize());
        List<RainSystemUser> systemUserList = rainSystemUserDao.selectAll();
        PageInfo pageInfo = PageInfo.of(systemUserList);
        // 封装视图模型
        List<SystemUserVO> systemUserVOList = CopyUtil.transToOList(systemUserList, SystemUserVO.class);

        return PageResult.build(pageInfo.getTotal(), systemUserVOList);
    }

    @Override
    public SystemUserDetailVO findSystemUserById(Long id) {
        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        SystemUserDetailVO systemUserDetailVO = CopyUtil.transToO(rainSystemUser, SystemUserDetailVO.class);

        //查询用户下的角色
        Example userRoleExample = new Example(RainUserRole.class);
        userRoleExample.createCriteria().andEqualTo("userId", id);
        List<RainUserRole> userRoleList = userRoleDao.selectByExample(userRoleExample);

        //查询关联表

        //封装用户角色信息
        List<UserRoleVO> userRoleVOList = CopyUtil.transToOList(userRoleList, UserRoleVO.class);
        systemUserDetailVO.setUserRoleVOList(userRoleVOList);
        return systemUserDetailVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSystemUser(SaveSystemUserDTO systemUserDTO) {

        RainSystemUser systemUser = new RainSystemUser();
        BeanUtils.copyProperties(systemUserDTO, systemUser);

        //判断用户名是否已经存在
        String userName = systemUser.getUserName();
        Example countExample = new Example(RainPermission.class);
        countExample.createCriteria().andEqualTo("userName", userName);
        int count = rainSystemUserDao.selectCountByExample(countExample);

        if (count > 0) {
            throw BusinessException.operate(userName + "已存在");
        }
        //保存系统用户信息
        Date now = new Date();
        Long userId = TwiterIdUtil.getTwiterId();
        systemUser.setId(userId);
        systemUser.setCreateTime(now);
        systemUser.setUpdateTime(now);
        rainSystemUserDao.insertSelective(systemUser);

        List<Long> roleIds = systemUserDTO.getRoleId();

        //保存用户角色关系
        List<RainUserRole> userRoleList = roleIds.stream().map(item -> {
            return RainUserRole.builder()
                    .roleId(item)
                    .userId(userId)
                    .build();
        }).collect(Collectors.toList());
        userRoleDao.batchSaveRelation(userRoleList);
    }

    @Override
    public void deleteUserById(Long id) {
        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (null == rainSystemUser) {
            throw BusinessException.operate(id + "不存在");
        }
        RainSystemUser user=new RainSystemUser();
        user.setId(id);
        user.setStatus(4);
        user.setDeleteTime(new Date());
        rainSystemUserDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateSystemUser(Long id, SaveSystemUserDTO systemUserDTO) {

        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (null == rainSystemUser) {
            throw BusinessException.operate(id + "不存在");
        }

        //更新用户表的信息
        BeanUtils.copyProperties(systemUserDTO, rainSystemUser);
//        passwordEncoder.matches()
        rainSystemUserDao.updateByPrimaryKeySelective(rainSystemUser);

        //删除用户旧的角色信息
        Example userRoleExample = new Example(RainPermission.class);
        userRoleExample.createCriteria().andEqualTo("userId", id);
        userRoleDao.deleteByExample(userRoleExample);

        //保存信息角色信息
        List<RainUserRole> userRoleList = systemUserDTO.getRoleId().stream().map(item -> {
            return RainUserRole.builder()
                    .roleId(item)
                    .userId(id)
                    .build();
        }).collect(Collectors.toList());
        userRoleDao.batchSaveRelation(userRoleList);
    }

    @Override
    public void updateUserStatus(Long id, Integer status) {

        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (null == rainSystemUser) {
            throw BusinessException.operate(id + "不存在");
        }

        //更新用户表的信息
        rainSystemUser.setStatus(status);
        rainSystemUser.setUpdateTime(new Date());
        rainSystemUserDao.updateByPrimaryKeySelective(rainSystemUser);
    }

    @Override
    public void resetPassword(Long id, String password) {
        RainSystemUser rainSystemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (null == rainSystemUser) {
            throw BusinessException.operate(id + "不存在");
        }
        RainSystemUser systemUser=new RainSystemUser();
        systemUser.setPassword(passwordEncoder.encode(password));
        rainSystemUserDao.updateByPrimaryKey(systemUser);
    }

    @Override
    public UserRoleListVO userRoles(Long id) {
        //查询用户角色关联
        Example userRoleExample = new Example(RainUserRole.class);
        userRoleExample.createCriteria().andEqualTo("userId", id);
        List<RainUserRole> rainUserRoleList = userRoleDao.selectByExample(userRoleExample);

        //查询角色
        if(!CollectionUtils.isEmpty(rainUserRoleList)){
            Example roleIdExample = new Example(RainUserRole.class);
            roleIdExample.createCriteria().andIn("id",rainUserRoleList.stream().map(item->item.getRoleId()).collect(Collectors.toList()));
            List<RainRole> rainRoleList = rainRoleDao.selectByExample(roleIdExample);
            List<UserRoleVO> userRoleVOList = CopyUtil.transToOList(rainRoleList, UserRoleVO.class);
            return UserRoleListVO.builder().roleList(userRoleVOList).build();
        }else{
            return UserRoleListVO.builder().roleList(new ArrayList<>()).build();
        }
    }
}
