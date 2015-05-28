package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicOblApplicationMstDto;

public interface LicBrnhHubPicPodDtlsDao {
	Long saveForBranchHubPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	Long saveForHubPicPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	List<Object> findDistinctPodDtlsForConsldMarking(List<LicHubMst> licHubMsts);
	List<LicOblApplicationMst> findApplicationByPodId(Long id);
	Boolean saveForBranchHubPodDtlsForReq(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	List<Object> findDistinctPodDtlsConsldMarkingForReq(List<LicHubMst> licHubMsts);
	List<LicRequirementDtls> findApplicationByPodIdForReq(Long id);
	Long saveForBranchHubPodDtlsForRenewal(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	List<Object> findDistinctPodDtlsForConsldMarkingForRnl(List<LicHubMst> licHubMsts);
	List<LicPolicyDtls> findPolicyDtlsByPodId(Long PodId);
	Long updatePicPodDtlsForRnl(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	Long updatePicPodDtlsForRenewalPosView(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls);
	List<Object> findDistinctPodDtlsForConsldMarkingForPos(List<LicHubMst> licHubMsts);
	List<LicRequirementDtls> findRequiremrntDtlsByPodIdForPos(Long PodId);
	List<LicOblApplicationMstDto> findBranchHubPodReport(Date businessFromDate, Date businessToDate, BranchMst branchMst);
	List<LicOblApplicationMst> findPicPodReport(Date businessFromDate, Date businessToDate, BranchMst branchMst);
}
