package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicBocDetailEntryDao;
import com.gtfs.service.interfaces.LicBocDetailEntryService;

@Service

public class LicBocDetailEntryServiceImpl implements LicBocDetailEntryService,Serializable{
	@Autowired
	private LicBocDetailEntryDao licBocDetailEntryDao;
	
	public List<LicOblApplicationMst> findApplicationForBocEntry(Long premListNo, BranchMst branchMst){
		return licBocDetailEntryDao.findApplicationForBocEntry(premListNo, branchMst);
	}
	
	public Boolean saveBoc(List<LicOblApplicationMst>  applicationMsts){
		return licBocDetailEntryDao.saveBoc(applicationMsts);
	}
	
	public Boolean saveBocForReq(List<LicRequirementDtls>  licRequirementDtls){
		return licBocDetailEntryDao.saveBocForReq(licRequirementDtls);
	}

	@Override
	public List<LicRequirementDtls> findRequirmentDtlsForBocEntry(Long premListNo, BranchMst branchMst) {
		return licBocDetailEntryDao.findRequirmentDtlsForBocEntry(premListNo, branchMst);
	}

	@Override
	public List<LicOblApplicationMst> findbocReport(Date businessFromDate, Date businessToDate, List<LicHubMst> findHubForProcess) {
		return licBocDetailEntryDao.findbocReport(businessFromDate, businessToDate, findHubForProcess);
	}
}
