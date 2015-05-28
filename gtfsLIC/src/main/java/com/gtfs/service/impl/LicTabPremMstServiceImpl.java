package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicTabPremMst;
import com.gtfs.dao.interfaces.LicTabPremMstDao;
import com.gtfs.service.interfaces.LicTabPremMstService;
@Service

public class LicTabPremMstServiceImpl implements LicTabPremMstService, Serializable{
	
	@Autowired
	private LicTabPremMstDao licTabPremMstDao;
	
	public List<Long> findTermsByAgeProdId(Integer age,Long productId){
		return licTabPremMstDao.findTermsByAgeProdId(age, productId);
	}
	
	public List<Integer> findMinAgeByProdId(Long productId){
		return licTabPremMstDao.findMinAgeByProdId(productId);
	}

	public List<LicTabPremMst> findLicTabPremMstByProdIdAgeTerm(Long prodId,Integer age,Long term){
		return licTabPremMstDao.findLicTabPremMstByProdIdAgeTerm(prodId, age, term);
	}
	
	

	public List<Long> findPremiumPayingTermByPolicyTerm(Long policyTerm, Integer age,Long productId) {
		return licTabPremMstDao.findPremiumPayingTermByPolicyTerm(policyTerm, age, productId);
	}
	
}
