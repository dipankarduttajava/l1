package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicBrnhHubPicMapDtlsDao;
import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.LicBrnhHubPicMapDtlsService;
@Service

public class LicBrnhHubPicMapDtlsServiceImpl implements LicBrnhHubPicMapDtlsService,Serializable{
	@Autowired
	private LicBrnhHubPicMapDtlsDao licBrnhHubPicMapDtlsDao;
	
	public Long saveForBranchHubDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls){
		return licBrnhHubPicMapDtlsDao.saveForBranchHubDispatchList(licBrnhHubPicMapDtls);
	}
	
	public Long saveForHubPicDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls){
		return licBrnhHubPicMapDtlsDao.saveForHubPicDispatchList(licBrnhHubPicMapDtls);
	}

	@Override
	public Boolean saveForBranchHubReqDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls) {
		return licBrnhHubPicMapDtlsDao.saveForBranchHubReqDispatchList(licBrnhHubPicMapDtls);
	}

	@Override
	public List<LicRequirementDtls> findRequirementsForHubPicDispatch(Date busineeFromDate, Date busineeToDate, List<LicHubMst> licHubMsts) {
		return licBrnhHubPicMapDtlsDao.findRequirementsForHubPicDispatch(busineeFromDate, busineeToDate, licHubMsts);
	}

	@Override
	public Long saveForHubPicDispatchListForReq(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls) {
		return licBrnhHubPicMapDtlsDao.saveForHubPicDispatchListForReq(licBrnhHubPicMapDtls);
	}

	@Override
	public Boolean savePosViewDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls) {
		return licBrnhHubPicMapDtlsDao.savePosViewDispatchList(licBrnhHubPicMapDtls);
	}

	@Override
	public List<LicOblApplicationMstDto> findBranchHubDispatchReport(Date businessFromDate, Date businessToDate, BranchMst branchMst) {
		return licBrnhHubPicMapDtlsDao.findBranchHubDispatchReport(businessFromDate, businessToDate, branchMst);
	}

	@Override
	public List<LicOblApplicationMst> findPicDispatchReport(Date businessFromDate, Date businessToDate, BranchMst branchMst) {
		return licBrnhHubPicMapDtlsDao.findPicDispatchReport(businessFromDate, businessToDate, branchMst);
	}
}
