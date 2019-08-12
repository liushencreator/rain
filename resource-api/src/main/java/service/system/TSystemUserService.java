package service.system;

import pojo.entity.system.TSystemUser;

import java.util.List;

/**
 * SERVICE - TSystemUser(系统管理员表)
 * 
 * @author zijing
 * @version 2.0
 */
public interface TSystemUserService {

    List<TSystemUser> findAll();

}
