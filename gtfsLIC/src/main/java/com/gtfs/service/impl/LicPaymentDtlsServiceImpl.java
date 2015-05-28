package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicPaymentMst;
import com.gtfs.dao.interfaces.LicPaymentDtlsDao;
import com.gtfs.service.interfaces.LicPaymentDtlsService;

@Service

public class LicPaymentDtlsServiceImpl implements LicPaymentDtlsService, Serializable{
	@Autowired
	private LicPaymentDtlsDao licPaymentDtlsDao;
	public List<LicPaymentDtls> findLicPaymentDtlsByPayId(LicPaymentMst licPaymentMst){
		return licPaymentDtlsDao.findLicPaymentDtlsByPayId(licPaymentMst);
	}
	
	public List<LicPaymentDtls> findLicPaymentDtlsByPayIdAndLicRequirment(LicPaymentMst licPaymentMst, Long Id){
		return licPaymentDtlsDao.findLicPaymentDtlsByPayIdAndLicRequirment(licPaymentMst, Id);
	}
}
