package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicPremFreqAllowARMst;
import com.gtfs.dao.interfaces.LicPremFreqAllowARMstDao;
import com.gtfs.service.interfaces.LicPremFreqAllowARMstService;
@Service

public class LicPremFreqAllowARMstServiceImpl implements LicPremFreqAllowARMstService, Serializable{
	@Autowired
	private LicPremFreqAllowARMstDao licPremFreqAllowARMstDao;
	
	public List<String> findPayModeByProdId(Long prodId){
		return licPremFreqAllowARMstDao.findPayModeByProdId(prodId);
	}
	
	public List<Object> checkForAddbRiderByAgeAndProdId(Integer age,Long prodId){
		return licPremFreqAllowARMstDao.checkForAddbRiderByAgeAndProdId(age, prodId);
	}
	
	public List<LicPremFreqAllowARMst> checkForSumAssuredByProdIdTerm(Long term,Long prodId){
		return licPremFreqAllowARMstDao.checkForSumAssuredByProdIdTerm(term, prodId);
	}
}
