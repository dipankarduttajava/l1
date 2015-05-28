package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;

public interface LicRequirementDtlsService extends Serializable{
	Boolean saveForRequirementDtls(LicRequirementDtls licRequirementDtls);
	List<LicRequirementDtls> findReqForReqEntryByApplNo(String applNo);
	List<LicRequirementDtls> findReqForActionTakenByApplNo(String applNo,List<LicHubMst> licHubMsts);
	Boolean saveForActionPoint(List<LicRequirementDtls> list);
	List<LicRequirementDtls> findPendingRequirementDtlsByApplicationNoAndReqType(String applicationNo, String reqType);
	LicRequirementDtls findById(Long id);
	List<Long> findPodRequirmentForPicDispatch(List<LicHubMst> licHubMsts);
	List<LicRequirementDtls> findRequirmentByDispatchListForPicDispatch(Long id, BranchMst branchMst);
	Boolean updateForPosViewRejectionForRenewal(List<LicRequirementDtls> LicRequirementDtlsList);
	List<LicRequirementDtls> findReqDtlsByPolicyDtlsId(LicPolicyDtls licPolicyDtls);
	List<Long> findDispatchListForPosView();
	List<LicRequirementDtls> findRequirementForPrintReceiptByDate(Date businessDate, Long branchId);
	List<LicRequirementDtls> findRequirementForPrintReceiptByApplicationNo(String applicationNo, Long branchId);
	Boolean updatePrintReceiptFlagInLicRequirementDtls(Long id);
}
