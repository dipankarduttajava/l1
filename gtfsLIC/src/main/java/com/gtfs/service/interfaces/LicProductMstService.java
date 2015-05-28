package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicProductMst;

public interface LicProductMstService extends Serializable{
	List<LicProductMst> findActiveLicProductMst();
	LicProductMst findByProductId(Long productId);
	List<LicProductMst> findActiveLicProductMstForChecklist();
}
