package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.dao.interfaces.LicOblApplicationMstDao;
import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.LicOblApplicationMstService;

@Repository
public class LicOblApplicationMstServiceImpl implements LicOblApplicationMstService, Serializable{
	@Autowired
	private LicOblApplicationMstDao licOblApplicationMstDao;
	
	public LicOblApplicationMst findById(Long id){
		return licOblApplicationMstDao.findById(id);
	}
	
	public Long insertDataForPreliminaryDataEntry(LicOblApplicationMst licOblApplicationMst){
		return licOblApplicationMstDao.insertDataForPreliminaryDataEntry(licOblApplicationMst);
	}
	
	public List<LicOblApplicationMst> findApplicationForSecondaryDataEntryByDate(Date date, BranchMst branchMst){
		return licOblApplicationMstDao.findApplicationForSecondaryDataEntryByDate(date, branchMst);
	}
	
	public List<LicOblApplicationMst> findApplicationForSecondaryDataEntryByApplicationNo(String applicationNo, BranchMst branchMst){
		return licOblApplicationMstDao.findApplicationForSecondaryDataEntryByApplicationNo(applicationNo, branchMst);
	}
	
	
	public List<LicPolicyMst> findApplicationByApplicationNoForReqirement(String applicationNo){
		return licOblApplicationMstDao.findApplicationByApplicationNoForReqirement(applicationNo);
	}	
	public Boolean insertDataForSecondaryDataEntry(LicOblApplicationMst licOblApplicationMst,LicPrintRcptDtls licPrintRcptDtls){
		return licOblApplicationMstDao.insertDataForSecondaryDataEntry(licOblApplicationMst,licPrintRcptDtls);
	}
	
	public List<LicOblApplicationMstDto> findDispatchApplicationsByBusinessDate(Date fromDate,Date toDate, BranchMst branchMst){
		return licOblApplicationMstDao.findDispatchApplicationsByBusinessDate(fromDate, toDate, branchMst);
	}
	
	public List<Long> findPodApplications(Long id){
		return licOblApplicationMstDao.findPodApplications(id);
	}
	
	public  List<LicOblApplicationMstDto> findApplicationByDispatchListAndBusinessDate(Date fromDate,Date toDate, Long id, BranchMst branchMst){
		return licOblApplicationMstDao.findApplicationByDispatchListAndBusinessDate(fromDate, toDate, id, branchMst);
	}
	
	public List<Long> findPodApplicationsForPicDispatch(List<LicHubMst> licHubMsts){
		return licOblApplicationMstDao.findPodApplicationsForPicDispatch(licHubMsts);
	}
	
	public  List<LicOblApplicationMst> findApplicationByDispatchListForPicDispatch(Long id, BranchMst branchMst){
		return licOblApplicationMstDao.findApplicationByDispatchListForPicDispatch(id, branchMst);
	}
	
	
	public List<LicPolicyMst> findApplicationForStatusEntry(Date fromDate,Date toDate,String applicantName,Double premium,Double sumAssured,Long term,String applicationNo,String policyNo, String proposalNo, List<LicHubMst> licHubMsts){
		return licOblApplicationMstDao.findApplicationForStatusEntry(fromDate, toDate, applicantName, premium, sumAssured, term, applicationNo,policyNo, proposalNo, licHubMsts);
	}

	@Override
	public List<Long> findPodApplicationsForReqirement(Long id) {
		return licOblApplicationMstDao.findPodApplicationsForReqirement(id);
	}

	@Override
	public List<LicOblApplicationMst> findAll() {
		return licOblApplicationMstDao.findAll();
	}

	@Override
	public Boolean updatePrintReceiptFlagInLicOblApplMst(Long id) {
		return licOblApplicationMstDao.updatePrintReceiptFlagInLicOblApplMst(id);
	}

	@Override
	public List<LicOblApplicationMstDto> findApplicationForPrintReceiptByDate(Date date, BranchMst branchMst) {
		return licOblApplicationMstDao.findApplicationForPrintReceiptByDate(date,branchMst);
	}

	@Override
	public List<LicOblApplicationMstDto> findApplicationForPrintReceiptByApplicationNo(String applicationNo, BranchMst branchMst) {
		return licOblApplicationMstDao.findApplicationForPrintReceiptByApplicationNo(applicationNo,branchMst);
	}

	@Override
	public Boolean update(LicOblApplicationMst licOblApplicationMst) {
		return licOblApplicationMstDao.update(licOblApplicationMst);
	}

	@Override
	public List<LicPolicyMst> findStatusEntryReport(Date fromDate, Date toDate,String applicantName, Double premium, Double sumAssured, Long term,String applicationNo, String policyNo, String proposalNo,List<LicHubMst> findHubForProcess) {
		return licOblApplicationMstDao.findStatusEntryReport(fromDate,toDate,applicantName,premium,sumAssured,term,applicationNo,policyNo,proposalNo,findHubForProcess);
	}

	@Override
	public List<LicOblApplicationMstDto> findBusinessReportByBusinessDate(Date fromDate, Date toDate) {
		return licOblApplicationMstDao.findBusinessReportByBusinessDate(fromDate, toDate);
	}	
}
