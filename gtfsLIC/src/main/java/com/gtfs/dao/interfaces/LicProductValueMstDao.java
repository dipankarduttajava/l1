package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicProductValueMst;

public interface LicProductValueMstDao {
	List<LicProductValueMst> findProductValueMstByProductId(Long prodId);
	List<LicProductValueMst> findProductValueMstByProductIdTermSumAssuredPayMode(Long prodId, Long term, Double sumAssured, String payNature,Integer age);
}
