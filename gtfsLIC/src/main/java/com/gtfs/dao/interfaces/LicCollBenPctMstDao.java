package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicCollBenPctMst;

public interface LicCollBenPctMstDao {
	List<LicCollBenPctMst> findLicCollBenPctMstByProdIdTerm(Long prodId,Long term);
}
