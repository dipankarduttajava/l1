package com.gtfs.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.dao.interfaces.LicPremReqMappingDao;
import com.gtfs.service.interfaces.LicPremReqMappingService;

@Service

public class LicPremReqMappingServiceImpl implements LicPremReqMappingService,Serializable{
	@Autowired
	private LicPremReqMappingDao licPremReqMappingDao;
}
