package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicNsapMst;

public interface LicNsapMstService extends Serializable{
	List<LicNsapMst> findNsapMstByProdIdAgeTerm(Long prodId,Integer age,Long policyTerm,Long premiumPayingTerm);
}
