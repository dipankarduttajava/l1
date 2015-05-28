package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtfs.bean.AccessList;
import com.gtfs.dao.interfaces.AccessListDao;
import com.gtfs.service.interfaces.AccessListService;


@Service
public class AccessListServiceImpl implements AccessListService, Serializable{
	@Autowired
	private AccessListDao accessListDao;

    public List<Object> findAccessListByUserId(Long userId){
        return accessListDao.findAccessListByUserId(userId);
    }
    
    public @ResponseBody List<AccessList> findAll(){
    	return accessListDao.findAll();
    }
    
    public List<Object> findAllActiveAccessList(){
    	return accessListDao.findAllActiveAccessList();
    }
    
    public List<Object> findAllActiveAccessListByAccessName(String accessName){
    	return accessListDao.findAllActiveAccessListByAccessName(accessName);
    }
    
    public Boolean saveAccessList(List<AccessList> accessList){
    	return accessListDao.saveAccessList(accessList);
    }
    
    public Boolean deleteAccessList(Long accessId,Long userId){
    	return accessListDao.deleteAccessList(accessId, userId);
    }
    
    public AccessList findById(Long accessId){
    	return accessListDao.findById(accessId);
    }
    
}
