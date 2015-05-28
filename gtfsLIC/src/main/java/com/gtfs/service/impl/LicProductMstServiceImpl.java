package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicProductMst;
import com.gtfs.dao.interfaces.LicProductMstDao;
import com.gtfs.service.interfaces.LicProductMstService;

@Service

public class LicProductMstServiceImpl implements LicProductMstService, Serializable{
	@Autowired
	private LicProductMstDao licProductMstDao;
	public List<LicProductMst> findActiveLicProductMst(){
		return licProductMstDao.findActiveLicProductMst();
	}
	
	public LicProductMst findByProductId(Long productId){
		return licProductMstDao.findByProductId(productId);
	}
	
	public List<LicProductMst> findActiveLicProductMstForChecklist(){
		return licProductMstDao.findActiveLicProductMstForChecklist();
	}
	
	
	
}
