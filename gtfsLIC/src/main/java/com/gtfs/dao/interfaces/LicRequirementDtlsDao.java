package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;

public interface LicRequirementDtlsDao {
	Boolean saveForRequirementDtls(LicRequirementDtls licRequirementDtls);
	List<LicRequirementDtls> findReqForReqEntryByApplNo(String applNo);
	List<LicRequirementDtls> findReqForActionTakenByApplNo(String applNo,List<LicHubMst> licHubMsts);
	Boolean saveForActionPoint(List<LicRequirementDtls> list);
	List<LicRequirementDtls> findPendingRequirementDtlsByApplicationNoAndReqType(String applicationNo,String reqType);
	LicRequirementDtls findById(Long id);
	List<Long> findPodRequirmentForPicDispatch(List<LicHubMst> licHubMsts);
	List<LicRequirementDtls> findRequirmentByDispatchListForPicDispatch(Long id, BranchMst branchMst);
	List<LicRequirementDtls> findReqDtlsByPolicyDtlsId(LicPolicyDtls licPolicyDtls);
	Boolean updateForPosViewRejectionForRenewal(List<LicRequirementDtls> LicRequirementDtlsList);
	List<Long> findDispatchListForPosView();
	List<LicRequirementDtls> findRequirementForPrintReceiptByDate(Date businessDate, Long branchId);
	List<LicRequirementDtls> findRequirementForPrintReceiptByApplicationNo(String applicationNo, Long branchId);
	Boolean updatePrintReceiptFlagInLicRequirementDtls(Long id);
}
