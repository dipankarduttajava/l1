package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.RoleMst;
import com.gtfs.dao.interfaces.RoleMstDao;
import com.gtfs.service.interfaces.RoleMstService;

@Service

public class RoleMstServiceImpl implements RoleMstService, Serializable{

	@Autowired
	private RoleMstDao roleMstDao;

	public List<RoleMst> findAllActiveRole(){
		return roleMstDao.findAllActiveRole();
	}
	
	public RoleMst findRoleById(Long roleId){
		return roleMstDao.findRoleById(roleId);
	}
	
	public List<RoleMst> findRoleByRoleName(String roleName){
		return roleMstDao.findRoleByRoleName(roleName);
	}
	
	public Boolean deleteRoleMst(Long roleId, Long userId){
		return roleMstDao.deleteRoleMst(roleId,userId);
	}
	
	public Boolean saveRoleMst(List<RoleMst> roleMstList){
    	return roleMstDao.saveRoleMst(roleMstList);
    }
	
}
