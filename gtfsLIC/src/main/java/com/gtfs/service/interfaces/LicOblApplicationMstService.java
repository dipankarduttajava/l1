package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.dto.LicOblApplicationMstDto;


public interface LicOblApplicationMstService extends Serializable{
	LicOblApplicationMst findById(Long id);
	Long insertDataForPreliminaryDataEntry(LicOblApplicationMst licOblApplicationMst);
	List<LicOblApplicationMst> findApplicationForSecondaryDataEntryByDate(Date date, BranchMst branchMst);
	List<LicOblApplicationMst> findApplicationForSecondaryDataEntryByApplicationNo(String applicationNo, BranchMst branchMst);
	List<LicPolicyMst> findApplicationByApplicationNoForReqirement(String applicationNo);
	Boolean insertDataForSecondaryDataEntry(LicOblApplicationMst licOblApplicationMst, LicPrintRcptDtls licPrintRcptDtls);
	List<LicOblApplicationMstDto> findDispatchApplicationsByBusinessDate(Date fromDate,Date toDate, BranchMst branchMst);
	List<Long> findPodApplications(Long id);
	List<LicOblApplicationMstDto> findApplicationByDispatchListAndBusinessDate(Date fromDate,Date toDate, Long id, BranchMst branchMst);
	List<Long> findPodApplicationsForPicDispatch(List<LicHubMst> licHubMsts);
	List<LicOblApplicationMst> findApplicationByDispatchListForPicDispatch(Long id, BranchMst branchMst);
	List<LicPolicyMst> findApplicationForStatusEntry(Date fromDate,Date toDate,String applicantName,Double premium,Double sumAssured,Long term,String applicationNo,String policyNo, String proposalNo, List<LicHubMst> licHubMsts);
	List<Long> findPodApplicationsForReqirement(Long id);	
	List<LicOblApplicationMst> findAll();
	Boolean updatePrintReceiptFlagInLicOblApplMst(Long id);
	List<LicOblApplicationMstDto> findApplicationForPrintReceiptByDate(Date date, BranchMst branchMst);
	List<LicOblApplicationMstDto> findApplicationForPrintReceiptByApplicationNo(String applicationNo, BranchMst branchMst);
	
	Boolean update(LicOblApplicationMst licOblApplicationMst);
	List<LicPolicyMst> findStatusEntryReport(Date fromDate, Date toDate,String applicantName, Double premium, Double sumAssured, Long term,String applicationNo, String policyNo, String proposalNo,List<LicHubMst> findHubForProcess);
	List<LicOblApplicationMstDto> findBusinessReportByBusinessDate(Date fromDate, Date toDate);
}
