package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicProductValueMst;
import com.gtfs.dao.interfaces.LicProductValueMstDao;
import com.gtfs.service.interfaces.LicProductValueMstService;

@Service

public class LicProductValueMstServiceImpl implements LicProductValueMstService, Serializable{
	
	@Autowired
	private LicProductValueMstDao licProductValueMstDao;
	
	public List<LicProductValueMst> findProductValueMstByProductId(Long prodId){
		return licProductValueMstDao.findProductValueMstByProductId(prodId);
	}
	
	public List<LicProductValueMst> findProductValueMstByProductIdTermSumAssuredPayMode(Long prodId, Long term, Double sumAssured, String payNature, Integer age){
		return licProductValueMstDao.findProductValueMstByProductIdTermSumAssuredPayMode(prodId,term,sumAssured,payNature, age);
	}
}
