package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicInsuredDtls;
import com.gtfs.dao.interfaces.LicInsuredAddressMappingDao;
import com.gtfs.service.interfaces.LicInsuredAddressMappingService;
@Service

public class LicInsuredAddressMappingServiceImpl implements LicInsuredAddressMappingService,Serializable{
	@Autowired
	private LicInsuredAddressMappingDao licInsuredAddressMappingDao;
	
	public List<LicInsuredAddressMapping> findAddressDtlsByInsuredDtls(LicInsuredDtls licInsuredDtls){
		return licInsuredAddressMappingDao.findAddressDtlsByInsuredDtls(licInsuredDtls);
	}
	
}
