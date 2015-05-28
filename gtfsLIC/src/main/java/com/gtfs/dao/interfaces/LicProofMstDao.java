package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicProofMst;

public interface LicProofMstDao {
	List<LicProofMst> findAll();
	LicProofMst findById(Long id);
}
