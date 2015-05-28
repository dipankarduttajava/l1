package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicPremFreqAllowARMst;

public interface LicPremFreqAllowARMstService extends Serializable{
	List<String> findPayModeByProdId(Long prodId);
	List<Object> checkForAddbRiderByAgeAndProdId(Integer age,Long prodId);
	List<LicPremFreqAllowARMst> checkForSumAssuredByProdIdTerm(Long term,Long prodId);
}
