package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.RoleAccessRlns;
import com.gtfs.dao.interfaces.RoleAccessRlnsDao;
import com.gtfs.service.interfaces.RoleAccessRlnsService;

@Service

public class RoleAccessRlnsServiceImpl  implements RoleAccessRlnsService, Serializable{
	@Autowired
	private RoleAccessRlnsDao roleAccessRlnsDao;
	
	public List<RoleAccessRlns> findActiveRoleAccessRlnsByAccessId(Long accessId) {
		return roleAccessRlnsDao.findActiveRoleAccessRlnsByAccessId(accessId);
	}
	
	public Long insertIntoRoleAccessRlns(RoleAccessRlns roleAccessRlns){
		return roleAccessRlnsDao.insertIntoRoleAccessRlns(roleAccessRlns);
	}
	
	public Integer deleteRoleAccessRlnsByRoleIdAndAccessId(Long roleId,Long accessId){
		return roleAccessRlnsDao.deleteRoleAccessRlnsByRoleIdAndAccessId(roleId, accessId);
	}
	
}
