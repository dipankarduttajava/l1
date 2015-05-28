package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.dao.interfaces.LicNomineeDtlsDao;
import com.gtfs.service.interfaces.LicNomineeDtlsService;
@Service

public class LicNomineeDtlsServiceImpl implements LicNomineeDtlsService,Serializable{
	@Autowired
	private LicNomineeDtlsDao licNomineeDtlsDao;
	
	public List<LicNomineeDtls> findNomineeDtlsByApplication(LicOblApplicationMst licOblApplicationMst){
		return licNomineeDtlsDao.findNomineeDtlsByApplication(licOblApplicationMst);
	}
		
		
}
