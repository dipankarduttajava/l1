package com.gtfs.service.interfaces;

import java.util.List;

import com.gtfs.bean.LicReqBocMapping;

public interface LicReqBocMappingService {
	List<LicReqBocMapping> findReqBocMappingByApplication(Long id);

}
