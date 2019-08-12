package com.rao.service.impl.system;

import com.rao.dao.system.TSystemUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.entity.system.TSystemUser;
import service.system.TSystemUserService;

import java.util.List;

/**
 * SERVICE - TSystemUser(系统管理员表)
 * 
 * @author zijing
 * @version 2.0
 */
@Service
public class TSystemUserServiceImpl implements TSystemUserService {
	
	@Autowired
	private TSystemUserDao tSystemUserDao;

	@Override
	public List<TSystemUser> findAll() {
		return tSystemUserDao.findAll();
	}
}
