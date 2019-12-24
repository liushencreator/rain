package com.rao.service.user;
import com.rao.pojo.vo.user.CurrentSystemUserVO;
import com.rao.pojo.vo.user.UserInfoVO;

/**
 * 系统用户 service
 * @author raojing
 * @date 2019/11/25 23:18
 */
public interface CurrentUserService {

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    CurrentSystemUserVO findById(Long id);

    /**
     * 根据用户id查询用户个人信息
     * @param id
     * @return
     */
    UserInfoVO info(Long id);

    /**
     * 用户修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param rePassword
     */
    void rePassword(Long id, String oldPassword, String newPassword, String rePassword);
}
