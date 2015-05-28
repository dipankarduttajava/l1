package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicNsapMst;
import com.gtfs.dao.interfaces.LicNsapMstDao;
import com.gtfs.service.interfaces.LicNsapMstService;
@Service

public class LicNsapMstServiceImpl implements  LicNsapMstService,Serializable{
	@Autowired
	private LicNsapMstDao licNsapMstDao;
	
	public List<LicNsapMst> findNsapMstByProdIdAgeTerm(Long prodId,Integer age,Long policyTerm,Long premiumPayingTerm){
		return licNsapMstDao.findNsapMstByProdIdAgeTerm(prodId, age, policyTerm,premiumPayingTerm);
	}
}
