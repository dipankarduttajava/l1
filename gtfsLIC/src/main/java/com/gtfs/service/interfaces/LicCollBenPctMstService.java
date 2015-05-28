package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicCollBenPctMst;

public interface LicCollBenPctMstService extends Serializable{
	public List<LicCollBenPctMst> findLicCollBenPctMstByProdIdTerm(Long prodId,Long term);
}
