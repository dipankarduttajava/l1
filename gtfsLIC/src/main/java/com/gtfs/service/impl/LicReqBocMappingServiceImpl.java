package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicReqBocMapping;
import com.gtfs.dao.interfaces.LicReqBocMappingDao;
import com.gtfs.service.interfaces.LicReqBocMappingService;
@Service
public class LicReqBocMappingServiceImpl implements LicReqBocMappingService,Serializable {
	@Autowired
	private LicReqBocMappingDao licReqBocMappingDao;

	@Override
	public List<LicReqBocMapping> findReqBocMappingByApplication(Long id) {
		return licReqBocMappingDao.findReqBocMappingByApplication(id);
	}

}
