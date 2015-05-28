package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicPremFreqAllowARMst2;
import com.gtfs.dao.interfaces.LicPremFreqAllowARMst2Dao;
import com.gtfs.dao.interfaces.LicPremFreqAllowARMstDao;
import com.gtfs.service.interfaces.LicPremFreqAllowARMst2Service;
@Service

public class LicPremFreqAllowARMst2ServiceImpl implements LicPremFreqAllowARMst2Service, Serializable{
	@Autowired
	private LicPremFreqAllowARMst2Dao licPremFreqAllowARMst2Dao;

	@Override
	public List<String> findDistinctCategoryFromLicPremFreqAllowARMst2() {
		return licPremFreqAllowARMst2Dao.findDistinctCategoryFromLicPremFreqAllowARMst2();
	}

	@Override
	public List<String> findDistinctPremFreqAllowByProductIdFromLicPremFreqAllowARMst2(Long productId) {
		return licPremFreqAllowARMst2Dao.findDistinctPremFreqAllowByProductIdFromLicPremFreqAllowARMst2(productId);
	}

	@Override
	public List<Object> checkForAddbRiderByAgeProdIdCategoryTermPptFromLicPremFreqAllowARMst2(Integer age, Long productId, String arCategory, Long policyTerm, Long premiumPayingTerm) {
		return licPremFreqAllowARMst2Dao.checkForAddbRiderByAgeProdIdCategoryTermPptFromLicPremFreqAllowARMst2(age, productId, arCategory, policyTerm, premiumPayingTerm);
	}

	@Override
	public List<LicPremFreqAllowARMst2> checkForSumAssuredByProdIdAndTermFromLicPremFreqAllowARMst2(Long policyTerm, Long productId) {
		return licPremFreqAllowARMst2Dao.checkForSumAssuredByProdIdAndTermFromLicPremFreqAllowARMst2(policyTerm, productId);

	}
}
