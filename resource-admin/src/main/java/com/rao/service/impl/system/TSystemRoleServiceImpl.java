package com.rao.service.impl.system;

import com.rao.dao.system.TSystemRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.system.TSystemRoleService;

/**
 * SERVICE - TSystemRole(系统角色表)
 * 
 * @author zijing
 * @version 2.0
 */
@Service
public class TSystemRoleServiceImpl implements TSystemRoleService {
	
	@Autowired
	private TSystemRoleDao tSystemRoleDao;

}
