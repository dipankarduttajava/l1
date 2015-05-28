package com.gtfs.service.interfaces;

import java.util.List;

import com.gtfs.bean.LicApplBocMapping;

public interface LicApplBocMappingService {

	List<LicApplBocMapping> findBocMappingByApplication(Long id);
	
	
}
