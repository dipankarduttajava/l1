package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicNsapDocMst;

public interface LicNsapDocMstService extends Serializable{
	List<LicNsapDocMst> findLicNsapDocMstByAge(Integer age,Double sumAssured,Long productId);
	LicNsapDocMst findById(Long id);
	
}
