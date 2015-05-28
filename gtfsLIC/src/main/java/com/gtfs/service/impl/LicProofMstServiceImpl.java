package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicProofMst;
import com.gtfs.dao.interfaces.LicProofMstDao;
import com.gtfs.service.interfaces.LicProofMstService;

@Service

public class LicProofMstServiceImpl implements LicProofMstService, Serializable{
	@Autowired
	private LicProofMstDao licProofMstDao;
	public List<LicProofMst> findAll(){
		return licProofMstDao.findAll();
	}
	
	public LicProofMst findById(Long id){
		return licProofMstDao.findById(id);
	}
}
