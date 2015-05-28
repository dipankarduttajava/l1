package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicNsapDocMst;

public interface LicNsapDocMstDao {
	List<LicNsapDocMst> findLicNsapDocMstByAge(Integer age,Double sumAssured,Long productId);
	LicNsapDocMst findById(Long id);
	
}
