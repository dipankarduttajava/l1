package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicProductCatMst;


public interface LicProductCatMstService extends Serializable{
	public List<LicProductCatMst> findActiveLicProdCategory();
}
