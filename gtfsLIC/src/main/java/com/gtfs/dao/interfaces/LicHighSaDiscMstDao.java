package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicHighSaDiscMst;

public interface LicHighSaDiscMstDao {
	List<LicHighSaDiscMst> findHighSaDiscByProdIdTermSumAssured(Long prodId,Long term,Double sa);
}
