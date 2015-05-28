package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicProofMst;


public interface LicProofMstService extends Serializable{
	List<LicProofMst> findAll();
	LicProofMst findById(Long id);
}
