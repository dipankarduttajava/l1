package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.dto.LicPolicyDtlsDto;

public interface LicPolicyDtlsService extends Serializable{
	List<LicPolicyDtls> findAll();
	List<LicPolicyDtls> findPolicyDtlsByPolicyMst(Long policyMstId);
	List<LicPolicyDtls> findPolicyDtlsByPolicyNo(String policyNo, Date sysdate);
	List<LicPolicyDtls> findAdvPolicyDtlsByPolicyNo(String policyNo, Date sysdate, Date incrementDate);
	Boolean saveRenewalBranchEntry(List<LicPolicyDtls> licPolicyDtlses);
	List<LicPolicyDtls> findPolicyDtlsByPolicyNoForDirectRenewal(String policyNo, Date sysdate);
	List<LicPolicyDtls> findLastPaidPolicyDtlsByPolicyNo(String policyNo);
	LicPolicyDtls findById(Long id);
	List<Object> findPolicyDtlsByHubIdAndBranchForDispatch(Date payDateFrom, Date payDateTo, Long hubId, Long branchId);		
	Boolean saveBrnhHubDispatchList(List<LicPolicyDtls> licPolicyDtlsList);
	List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRange(String policyNo,Date fromDate,Date toDate);
	List<Long> findPodApplicationsForRenewal(Long id);
	List<LicPolicyDtls> findApplicationByDispatchListForRenewal(Long id, BranchMst branchMst);
	List<Object> findPolicyDtlsByDispatchIdAndBranchForPod(Long dispatchId, Long branchId);
	Boolean saveBrnhHubPodDtlsForRenewal(List<LicPolicyDtls> licPolicyDtlsList);	
	List<Object> findIndividualMarkingDtlsByDateForRenewal(Date fromDate, Date toDate, Long branchId);
	List<Object> findPolicyDtlsForDispatchDecision(Date payDateFrom, Date payDateTo, Long id);
	List<Object> findHealthValidationDtlsByDateForRenewal(Date fromDate, Date toDate, Long branchId);
	Boolean saveHealthValidationForRenewal(List<LicPolicyDtls> licPolicyDtlsList);
	List<LicPolicyDtls> findPolicyDtlsByPolicyNoDueDateRangeForHealthValidation(String policyNo,Date fromDate,Date toDate);
	List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRangeForPremium(String policyNo,Date fromDate,Date toDate);
	List<Object> findPolicyDtlsByDispatchIdAndBranchForRnlPicPod(Long dispatchId, Long branchId);
	List<Long> findDispatchListForRnlPicPod();
	List<Object> findPosViewRejectionByDateForRenewal(Date fromDate, Date toDate, Long branchId);
	List<Object> findPolicyDtlsForPosViewDispatch(Long branchId);
	List<LicPolicyDtls> findPolicyDtlsByPolicyNoForPos(String policyNo);
	List<Object> findPolicyDtlsByDispatchIdRenewalPosViewPicPod(Long dispatchId);
	List<Object> findIndividualMarkingDtlsForPos(Long branchId);
	List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRangeForPos(String policyNo, Date fromDate, Date toDate);
	List<LicPolicyDtls> findPolicyDtlsByPolicyNoForRpr(String policyNo, Long id);
	Boolean updatePolicyDtlsRenewalRprStatus(List<LicPolicyDtls> licPolicyDtlsList);
	List<LicPolicyDtlsDto> findPolicyDtlsForRnlPrintReceipt(String policyNo);
	Boolean saveNormalRenewalBranchEntry(List<LicPolicyDtls> licPolicyDtlsList,LicPolicyMst licPolicyMst);
}
