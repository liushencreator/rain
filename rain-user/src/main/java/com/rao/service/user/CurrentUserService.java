package com.rao.service.user;
import com.rao.pojo.vo.user.CurrentSystemUserVO;

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
    
}
