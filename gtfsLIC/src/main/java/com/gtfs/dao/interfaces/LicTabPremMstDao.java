package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicTabPremMst;

public interface LicTabPremMstDao {
	List<Long> findTermsByAgeProdId(Integer age,Long productId);
	List<Integer> findMinAgeByProdId(Long productId);
	List<LicTabPremMst> findLicTabPremMstByProdIdAgeTerm(Long prodId,Integer age,Long term);
	List<Long> findPremiumPayingTermByPolicyTerm(Long policyTerm,Integer age,Long productId);
}
