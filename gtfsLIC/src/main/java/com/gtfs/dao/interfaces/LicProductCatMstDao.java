package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicProductCatMst;

public interface LicProductCatMstDao {
	List<LicProductCatMst> findActiveLicProdCategory();
}
