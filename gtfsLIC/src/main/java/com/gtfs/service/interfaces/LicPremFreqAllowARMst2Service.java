package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicPremFreqAllowARMst2;

public interface LicPremFreqAllowARMst2Service extends Serializable {
	List<String> findDistinctCategoryFromLicPremFreqAllowARMst2();
	List<String> findDistinctPremFreqAllowByProductIdFromLicPremFreqAllowARMst2(Long productId);
	List<Object> checkForAddbRiderByAgeProdIdCategoryTermPptFromLicPremFreqAllowARMst2(Integer age, Long productId, String arCategory, Long policyTerm,	Long premiumPayingTerm);
	List<LicPremFreqAllowARMst2> checkForSumAssuredByProdIdAndTermFromLicPremFreqAllowARMst2(Long policyTerm, Long productId);
}
