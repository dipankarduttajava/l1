package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicHighSaDiscMst;


public interface LicHighSaDiscMstService extends Serializable{
	List<LicHighSaDiscMst> findHighSaDiscByProdIdTermSumAssured(Long prodId,Long term,Double sa);
}
