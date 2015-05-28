package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicDocReqMst;
import com.gtfs.dao.interfaces.LicDocReqMstDao;
import com.gtfs.service.interfaces.LicDocReqMstService;

@Service

public class LicDocReqMstServiceImpl implements LicDocReqMstService, Serializable{
	@Autowired
	private LicDocReqMstDao licDocReqMstDao;
	
	public List<LicDocReqMst> findAll(){
		return licDocReqMstDao.findAll();
	}

}
