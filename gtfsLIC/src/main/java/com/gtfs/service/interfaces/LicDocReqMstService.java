package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicDocReqMst;
import com.gtfs.bean.LicRequirementDtls;

public interface LicDocReqMstService extends Serializable{
	List<LicDocReqMst> findAll();
}
