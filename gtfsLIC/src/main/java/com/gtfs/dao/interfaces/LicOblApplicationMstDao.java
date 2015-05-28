package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.dto.LicOblApplicationMstDto;

public interface LicOblApplicationMstDao {
	Boolean insertDataForSecondaryDataEntry(LicOblApplicationMst licOblApplicationMst, LicPrintRcptDtls licPrintRcptDtls);
	Long insertDataForPreliminaryDataEntry(LicOblApplicationMst licOblApplicationMst);
	List<LicOblApplicationMst> findApplicationForSecondaryDataEntryByDate(Date date, BranchMst branchMst);
	List<LicPolicyMst> findApplicationByApplicationNoForReqirement(String applicationNo);
	List<LicOblApplicationMst> findApplicationForSecondaryDataEntryByApplicationNo(String applicationNo, BranchMst branchMst);
	List<LicOblApplicationMstDto> findDispatchApplicationsByBusinessDate(Date fromDate, Date toDate, BranchMst branchMst);
	List<LicOblApplicationMstDto> findApplicationByDispatchListAndBusinessDate(Date fromDate,Date toDate, Long id, BranchMst branchMst);
	List<LicOblApplicationMst> findApplicationByDispatchListForPicDispatch(Long id, BranchMst branchMst);
	List<Long> findPodApplications(Long id);
	List<Long> findPodApplicationsForPicDispatch(List<LicHubMst> licHubMsts);
	List<LicPolicyMst> findApplicationForStatusEntry(Date fromDate,Date toDate,String applicantName,Double premium,Double sumAssured,Long term,String applicationNo,String policyNo, String proposalNo, List<LicHubMst> licHubMsts);
	LicOblApplicationMst findById(Long id);
	List<Long> findPodApplicationsForReqirement(Long id);
	List<LicOblApplicationMst> findAll();
	Boolean updatePrintReceiptFlagInLicOblApplMst(Long id);
	List<LicOblApplicationMstDto> findApplicationForPrintReceiptByDate(Date date,BranchMst branchMst);
	List<LicOblApplicationMstDto> findApplicationForPrintReceiptByApplicationNo(String applicationNo, BranchMst branchMst);
	Boolean update(LicOblApplicationMst licOblApplicationMst);
	List<LicPolicyMst> findStatusEntryReport(Date fromDate, Date toDate,String applicantName, Double premium, Double sumAssured, Long term,String applicationNo, String policyNo, String proposalNo,List<LicHubMst> findHubForProcess);
	List<LicOblApplicationMstDto> findBusinessReportByBusinessDate(Date fromDate,Date toDate);
}
