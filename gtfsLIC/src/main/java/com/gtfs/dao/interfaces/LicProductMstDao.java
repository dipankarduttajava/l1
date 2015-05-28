package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicProductMst;

public interface LicProductMstDao {
	List<LicProductMst> findActiveLicProductMst();
	List<LicProductMst> findActiveLicProductMstForChecklist();
	LicProductMst findByProductId(Long productId);
}
