package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicNsapDocMst;
import com.gtfs.dao.interfaces.LicNsapDocMstDao;
import com.gtfs.service.interfaces.LicNsapDocMstService;
@Service
public class LicNsapDocMstServiceImpl implements LicNsapDocMstService,Serializable{
	@Autowired
	LicNsapDocMstDao licNsapDocMstDao;
	public List<LicNsapDocMst> findLicNsapDocMstByAge(Integer age,Double sumAssured,Long productId){
		return licNsapDocMstDao.findLicNsapDocMstByAge(age,sumAssured,productId);
	}
	
	public LicNsapDocMst findById(Long id){
		return licNsapDocMstDao.findById(id);
	}
	
}
