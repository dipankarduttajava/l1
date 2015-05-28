package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.interfaces.AccessListDao;
import com.gtfs.dao.interfaces.LicTermRiderDao;
import com.gtfs.dto.LicTermRiderDto;
import com.gtfs.service.interfaces.LicTermRiderService;

@Service
public class LicTermRiderServiceImpl implements LicTermRiderService,Serializable {
	
	@Autowired
	private LicTermRiderDao licTermRiderDao;
	
	@Override
	public List<LicTermRiderDto> findRiderAmtAndRiderTypeFromLicTermRider(Integer age, Long policyTerm, Long premiumPayingTerm, Long productId) {
		return licTermRiderDao.findRiderAmtAndRiderTypeFromLicTermRider(age, policyTerm, premiumPayingTerm, productId);
	}

}
