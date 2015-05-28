package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicApplBocMapping;
import com.gtfs.dao.interfaces.LicApplBocMappingDao;
import com.gtfs.service.interfaces.LicApplBocMappingService;

@Service
public class LicApplBocMappingServiceImpl implements LicApplBocMappingService,Serializable {
	@Autowired
	private LicApplBocMappingDao licApplBocMappingDao;

	@Override
	public List<LicApplBocMapping> findBocMappingByApplication(Long id) {
		// TODO Auto-generated method stub
		return licApplBocMappingDao.findBocMappingByApplication(id);
	}

}
