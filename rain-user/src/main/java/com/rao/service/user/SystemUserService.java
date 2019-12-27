package com.rao.service.user;

import com.rao.pojo.dto.SaveSystemUserDTO;
import com.rao.pojo.vo.user.SystemUserDetailVO;
import com.rao.pojo.vo.user.SystemUserVO;
import com.rao.util.page.PageParam;
import com.rao.util.result.PageResult;

/**
 * @ClassName : SystemUserService  //类名
 * @Description : 系统用户相关  //描述
 * @Author : xujianjie  //作者
 * @Date: 2019-12-16 16:42  //时间
 */
public interface SystemUserService {

  /**
   * 获取系统用户列表
   *
   * @param pageParam
   * @author xujianjie
   * @date 2019/12/17 11:23
   * @throws Exception
   * @return com.rao.util.result.PageResult<com.rao.pojo.vo.user.SystemUserVO>
   */
    PageResult<SystemUserVO> getSystemUserList(PageParam pageParam);

    /**
     * 根据id查询用户的详情
     *
     * @param id
     * @author xujianjie
     * @date 2019/12/16 16:55
     * @throws Exception
     * @return com.rao.pojo.entity.user.RainSystemUser
     */
    SystemUserDetailVO findSystemUserById(Long id);

    /**
     *
     * 新增系统用户
     * @param systemUserDTO
     * @author xujianjie
     * @date 2019/12/16 17:08
     * @throws Exception
     * @return void
     */
    void insertSystemUser(SaveSystemUserDTO systemUserDTO);

    /**
     *
     * 根据id删除用户
     * @param id
     * @author xujianjie
     * @date 2019/12/16 17:15
     * @throws Exception
     * @return void
     */
    void deleteUserById(Long id);

   /**
    * 修改系统用户
    *
    * @param id
    * @param systemUserDTO
    * @author xujianjie
    * @date 2019/12/17 11:07
    * @throws Exception
    * @return void
    */
    void updateSystemUser(Long id,SaveSystemUserDTO systemUserDTO);

    /**
     * 修改用户的状态
     *
     * @param id
     * @param status
     * @author xujianjie
     * @date 2019/12/17 11:22
     * @throws Exception
     * @return void
     */
    void updateUserStatus(Long id, Integer status);

    /**
     * 管理员重新设置密码
     * @param id
     * @param password
     */
    void resetPassword(Long id,String password);
}
