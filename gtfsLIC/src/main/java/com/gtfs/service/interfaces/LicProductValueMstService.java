package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicProductValueMst;

public interface LicProductValueMstService extends Serializable{
	List<LicProductValueMst> findProductValueMstByProductId(Long prodId);
	List<LicProductValueMst> findProductValueMstByProductIdTermSumAssuredPayMode(Long prodId, Long term, Double sumAssured, String payNature, Integer age);
}
