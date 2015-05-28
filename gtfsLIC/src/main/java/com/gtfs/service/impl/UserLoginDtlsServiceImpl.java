package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.UserLoginDtls;
import com.gtfs.dao.interfaces.UserLoginDtlsDao;
import com.gtfs.service.interfaces.UserLoginDtlsService;

@Service
public class UserLoginDtlsServiceImpl implements UserLoginDtlsService, Serializable{
	@Autowired
	 private UserLoginDtlsDao userLoginDtlsDao;
	
	public List<Object> findMaximumLoginTimeOfUser(Long UserId) {
        return userLoginDtlsDao.findMaximumLoginTimeOfUser(UserId);
    }
	
	  public Long insert(UserLoginDtls userLoginDtls) {
	        return (Long) userLoginDtlsDao.insert(userLoginDtls);
	    }
}
