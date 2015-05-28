package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicOblApplicationMstDto;


public interface LicBrnhHubPicPodDtlsService extends Serializable{
	Long saveForBranchHubPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	List<Object> findDistinctPodDtlsForConsldMarking(List<LicHubMst> licHubMsts) throws Exception;
	List<LicOblApplicationMst> findApplicationByPodId(Long id);
	Long saveForHubPicPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	Boolean saveForBranchHubPodDtlsForReq(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	List<Object> findDistinctPodDtlsConsldMarkingForReq(List<LicHubMst> licHubMsts) throws Exception;
	List<LicRequirementDtls> findApplicationByPodIdForReq(Long id);
	Long saveForBranchHubPodDtlsForRenewal(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	List<Object> findDistinctPodDtlsForConsldMarkingForRnl(List<LicHubMst> licHubMsts) throws Exception;
	List<LicPolicyDtls> findPolicyDtlsByPodId(Long PodId);
	Long updatePicPodDtlsForRnl(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	Long updatePicPodDtlsForRenewalPosView(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	List<Object> findDistinctPodDtlsForConsldMarkingForPos(List<LicHubMst> licHubMsts) throws Exception;
	List<LicRequirementDtls> findRequiremrntDtlsByPodIdForPos(Long PodId);
	List<LicOblApplicationMstDto> findBranchHubPodReport(Date businessFromDate, Date businessToDate, BranchMst branchMst);
	List<LicOblApplicationMst> findPicPodReport(Date businessFromDate, Date businessToDate, BranchMst branchMst);
}
