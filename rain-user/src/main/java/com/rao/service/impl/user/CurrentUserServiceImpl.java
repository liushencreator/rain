package com.rao.service.impl.user;

import com.rao.dao.system.RainRoleDao;
import com.rao.dao.system.RainUserRoleDao;
import com.rao.dao.user.RainSystemUserDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.entity.system.RainRole;
import com.rao.pojo.entity.system.RainUserRole;
import com.rao.pojo.entity.user.RainSystemUser;
import com.rao.pojo.vo.user.UserInfoVO;
import com.rao.pojo.vo.user.UserRoleVO;
import com.rao.util.CopyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.rao.service.user.CurrentUserService;
import com.rao.pojo.vo.user.CurrentSystemUserVO;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户 service 实现
 * @author raojing
 * @date 2019/11/25 23:19
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;
    @Resource
    private RainUserRoleDao userRoleDao;
    @Resource
    private RainRoleDao rainRoleDao;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public CurrentSystemUserVO findById(Long id) {
        RainSystemUser systemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if(systemUser != null){
            CurrentSystemUserVO systemUserVO = new CurrentSystemUserVO();
            BeanUtils.copyProperties(systemUser, systemUserVO);
            return systemUserVO;
        }else{
            throw BusinessException.operate("用户不存在");
        }
    }

    @Override
    public UserInfoVO info(Long id) {
        RainSystemUser systemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if(systemUser != null){
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(systemUser, userInfoVO);

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
                userInfoVO.setUserRoleVOList(userRoleVOList);
            }
            return userInfoVO;
        }else{
            throw BusinessException.operate("用户不存在");
        }
    }

    @Override
    public void rePassword(Long id, String oldPassword, String newPassword, String rePassword) {
        RainSystemUser systemUser = rainSystemUserDao.selectByPrimaryKey(id);
        if (systemUser != null) {
            if(!newPassword.equals(rePassword)){
                throw BusinessException.operate("新密码与确认密码不一致");
            }
            //校验旧密码
            boolean matched = passwordEncoder.matches(oldPassword, systemUser.getPassword());
            if (matched) {
                if(oldPassword.equals(newPassword)){
                    throw BusinessException.operate("新密码与旧密码相同");
                }
                RainSystemUser updateUser = new RainSystemUser();
                updateUser.setId(id);
                updateUser.setPassword(passwordEncoder.encode(newPassword));
                rainSystemUserDao.updateByPrimaryKeySelective(updateUser);
            }else{
                throw BusinessException.operate("旧密码错误");
            }
        } else {
            throw BusinessException.operate("用户不存在");
        }
    }

}
