package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.UserRoleRlns;
import com.gtfs.dao.interfaces.UserRoleRlnsDao;
import com.gtfs.service.interfaces.UserRoleRlnsService;

@Service

public class UserRoleRlnsServiceImpl implements UserRoleRlnsService, Serializable{

	@Autowired
	private UserRoleRlnsDao userRoleRlnsDao;
	
	public List<UserRoleRlns> findActiveUserRoleByUserId(Long userId){
		try{
			return userRoleRlnsDao.findActiveUserRoleByUserId(userId);
		}catch(Exception e){
			System.out.println("Service Error");
			e.printStackTrace();
			return null;
		}
	 }
	
	public Long insertIntoUserRoleRlns(UserRoleRlns userRoleRlns){
		try{
			return userRoleRlnsDao.insertIntoUserRoleRlns(userRoleRlns);
		}catch(Exception e){
			System.out.println("Service Error");
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer deleteFromUserRoleRlnsByRoleIdAndUserId(Long roleId,Long userid, Long loginUserId){
		return userRoleRlnsDao.deleteFromUserRoleRlnsByRoleIdAndUserId(roleId, userid, loginUserId);
	}
}
