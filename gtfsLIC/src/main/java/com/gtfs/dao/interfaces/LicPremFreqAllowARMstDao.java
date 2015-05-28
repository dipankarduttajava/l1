package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicPremFreqAllowARMst;

public interface LicPremFreqAllowARMstDao {
	List<String> findPayModeByProdId(Long prodId);
	List<Object> checkForAddbRiderByAgeAndProdId(Integer age,Long prodId);
	List<LicPremFreqAllowARMst> checkForSumAssuredByProdIdTerm(Long term,Long prodId);
}
