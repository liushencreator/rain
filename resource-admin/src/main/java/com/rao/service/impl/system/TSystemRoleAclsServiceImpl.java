package com.rao.service.impl.system;

import com.rao.dao.system.TSystemRoleAclsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.system.TSystemRoleAclsService;

/**
 * SERVICE - TSystemRoleAcls(系统角色权限表)
 * 
 * @author zijing
 * @version 2.0
 */
@Service
public class TSystemRoleAclsServiceImpl implements TSystemRoleAclsService {
	
	@Autowired
	private TSystemRoleAclsDao tSystemRoleAclsDao;

}
