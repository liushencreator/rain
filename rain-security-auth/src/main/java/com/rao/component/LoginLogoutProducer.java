package com.rao.component;

import com.alibaba.fastjson.JSON;
import com.rao.constant.user.OperationTypeEnum;
import com.rao.dto.IpInfo;
import com.rao.pojo.bo.UserLoginLogoutLogBO;
import com.rao.util.CopyUtil;
import com.rao.util.common.UserAgentUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * @author : hudelin
 * @className :LogInLogoutProducer
 * @description : 登录登出生产者
 * @date: 2019-12-31 10:59
 */
@Component
public class LoginLogoutProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendLogMsg(OperationTypeEnum type) {
        UserLoginLogoutLogBO currentUserInfo = getCurrentUserInfo();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        IpInfo ipInfo = UserAgentUtils.getIpInfo(UserAgentUtils.getIpAddr(request));
        UserLoginLogoutLogBO userLoginLogoutLogBO = CopyUtil.transToO(ipInfo, UserLoginLogoutLogBO.class);
        userLoginLogoutLogBO.setType(type.getValue());
        userLoginLogoutLogBO.setUserId(currentUserInfo.getId());
        userLoginLogoutLogBO.setUserName(currentUserInfo.getUserName());
        userLoginLogoutLogBO.setPhone(currentUserInfo.getPhone());
        userLoginLogoutLogBO.setUserType(currentUserInfo.getUserType());
        userLoginLogoutLogBO.setProvince(ipInfo.getRegion());
        rocketMQTemplate.convertAndSend("LoginLogout", userLoginLogoutLogBO);
    }

    public UserLoginLogoutLogBO getCurrentUserInfo(){
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        OAuth2Authentication auth2Authentication  = (OAuth2Authentication)authentication;
        LinkedHashMap details = (LinkedHashMap)auth2Authentication.getUserAuthentication().getDetails();
        LinkedHashMap principal = (LinkedHashMap)details.get("principal");
        UserLoginLogoutLogBO userLoginLogoutLogBO = JSON.parseObject(JSON.toJSONString(principal), UserLoginLogoutLogBO.class);
        return userLoginLogoutLogBO;
    }

}
