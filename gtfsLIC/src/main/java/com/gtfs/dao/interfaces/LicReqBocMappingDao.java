package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicReqBocMapping;

public interface LicReqBocMappingDao {
	List<LicReqBocMapping> findReqBocMappingByApplication(Long id);
}
