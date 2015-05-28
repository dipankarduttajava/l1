package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicMarkingToQcDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicMarkingToQcDtlsDao;
import com.gtfs.service.interfaces.LicMarkingToQcDtlsService;

@Service
public class LicMarkingToQcDtlsServiceImpl implements LicMarkingToQcDtlsService,Serializable{
	@Autowired
	private LicMarkingToQcDtlsDao licMarkingToQcDtlsDao;
	
	public Boolean saveForConsolidateMarking(LicMarkingToQcDtls licMarkingToQcDtls){
		return licMarkingToQcDtlsDao.saveForConsolidateMarking(licMarkingToQcDtls);
	}
	
	public List<LicOblApplicationMst> findIndividualMarkingDtlsByDate(String applicationNo,Date fromDate,Date toDate, List<LicHubMst> licHubMsts, BranchMst branchMst, Long branchId){
		return licMarkingToQcDtlsDao.findIndividualMarkingDtlsByDate(applicationNo,fromDate, toDate, licHubMsts, branchMst, branchId);
	}
	
	public Boolean updateForIndividualMarking(List<LicOblApplicationMst> licHubMsts){
		return licMarkingToQcDtlsDao.updateForIndividualMarking(licHubMsts);
	}

	@Override
	public List<LicRequirementDtls> findIndividualMArkingDtlsByDateForReq(Date fromDate, Date toDate, List<LicHubMst> licHubMsts, BranchMst branchMst) {
		return licMarkingToQcDtlsDao.findIndividualMArkingDtlsByDateForReq(fromDate, toDate, licHubMsts, branchMst);
	}

	@Override
	public Boolean updateForIndividualMarkingForReq(List<LicRequirementDtls> licHubMsts) {
		return licMarkingToQcDtlsDao.updateForIndividualMarkingForReq(licHubMsts);
	}
	
	@Override
	public Boolean updateForIndividualMarkingForRenewal(List<LicPolicyDtls> licPolicyDtlsList){
		return licMarkingToQcDtlsDao.updateForIndividualMarkingForRenewal(licPolicyDtlsList);
	}

	@Override
	public Boolean saveForConsolidateMarkingForPos(LicMarkingToQcDtls licMarkingToQcDtls) {
		return licMarkingToQcDtlsDao.saveForConsolidateMarkingForPos(licMarkingToQcDtls);
	}

	@Override
	public Boolean updateForIndividualMarkingForPos(List<LicRequirementDtls> licRequirementDtlsList) {
		return licMarkingToQcDtlsDao.updateForIndividualMarkingForPos(licRequirementDtlsList);
	}

	@Override
	public List<LicOblApplicationMst> findIndividualMarkingDtlsReport(Date businessFromDate, Date businessToDate, List<LicHubMst> findHubForProcess, Long branchId) {
		return licMarkingToQcDtlsDao.findIndividualMarkingDtlsReport(businessFromDate, businessToDate, findHubForProcess, branchId);
	}
}
