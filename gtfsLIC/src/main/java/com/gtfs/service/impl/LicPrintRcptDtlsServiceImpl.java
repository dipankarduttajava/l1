package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.bean.PrintRcptMst;
import com.gtfs.dao.interfaces.LicPrintRcptDtlsDao;
import com.gtfs.service.interfaces.LicPrintRcptDtlsService;

@Service

public class LicPrintRcptDtlsServiceImpl implements LicPrintRcptDtlsService, Serializable{
	@Autowired
	private LicPrintRcptDtlsDao licPrintRcptDtlsDao;
	
	public List<Long> findLicPrintRcptDtlsByPrintRcptMst(PrintRcptMst printRcptMst){
		return licPrintRcptDtlsDao.findLicPrintRcptDtlsByPrintRcptMst(printRcptMst);
	}
	
	public List<LicPrintRcptDtls> findLicPrintRcptDtlsByApplication(LicOblApplicationMst licOblApplicationMst){
		return licPrintRcptDtlsDao.findLicPrintRcptDtlsByApplication(licOblApplicationMst);
	}

	@Override
	public List<Long> findAllLicPrintRcptDtlsByPrintRcptMst(PrintRcptMst printRcptMst) {
			return licPrintRcptDtlsDao.findAllLicPrintRcptDtlsByPrintRcptMst(printRcptMst);
	}
}
