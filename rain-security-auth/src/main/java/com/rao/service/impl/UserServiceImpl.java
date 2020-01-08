package com.rao.service.impl;

import com.rao.constant.common.StateConstants;
import com.rao.constant.sms.SmsOperationTypeEnum;
import com.rao.constant.user.UserTypeEnum;
import com.rao.dao.RainSystemUserDao;
import com.rao.exception.BusinessException;
import com.rao.pojo.bo.LoginUserBO;
import com.rao.pojo.dto.SmsSendDTO;
import com.rao.pojo.entity.RainSystemUser;
import com.rao.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户 service 实现
 * @author raojing
 * @date 2019/12/7 23:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RainSystemUserDao rainSystemUserDao;

    @Override
    public LoginUserBO findByUserNameOrPhoneAndUserType(String userName, String type) {
        LoginUserBO loginUser = null;
        if(UserTypeEnum.ADMIN.getValue().equals(type)){
            // 系统管理员用户
            RainSystemUser systemUser = rainSystemUserDao.findByUserNameOrPhone(userName);
            loginUser = new LoginUserBO();
            BeanUtils.copyProperties(systemUser, loginUser);
        }else if(UserTypeEnum.C_USER.getValue().equals(type)){
            // C 端用户(暂无)
            
        }
        return loginUser;
    }

    @Override
    public void checkAccount(SmsSendDTO smsSendDTO) {
        SmsOperationTypeEnum operationType = SmsOperationTypeEnum.ofType(smsSendDTO.getType());
        UserTypeEnum userType = UserTypeEnum.ofValue(smsSendDTO.getAccountType());
        LoginUserBO userBO = this.findByUserNameOrPhoneAndUserType(smsSendDTO.getPhone(), userType.getValue());
        switch (operationType){
            case LOGIN:
                // 登录
            case RESET_PWD:
                // 找回密码
            case VERIFY_IDENTITY:
                // 验证身份
                if(userBO == null){
                    throw BusinessException.operate("用户不存在哦");
                }
                if(!userBO.getStatus().equals(StateConstants.STATE_ENABLE)){
                    throw BusinessException.operate("账户不可用");
                }
                break;
            case REGISTER:
                // 注册
                if(userBO != null){
                    throw BusinessException.operate("用户已注册");
                }
                break;
            default:
                throw BusinessException.operate("检查账号失败");
        }
    }

}
