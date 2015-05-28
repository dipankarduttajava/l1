package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicApplBocMapping;

public interface LicApplBocMappingDao {

	List<LicApplBocMapping> findBocMappingByApplication(Long id);
	

}
