package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPremApplMapping;
import com.gtfs.bean.LicPremPolicyMapping;
import com.gtfs.bean.LicPremReqMapping;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.bean.LicRequirementDtls;

public interface LicPremiumListDao {
	List<Object> findPolicyForDeliveryListOfPic(Long premListNo);
	List<Long> findPremiumListForPicDelivery(List<LicHubMst> licHubMsts);
	List<Long> findPremiumListNoForBocEntry(List<LicHubMst> licHubMsts);
	List<LicOblApplicationMst> findApplicationForDeliveryListOfPic(Long premListNo, BranchMst branchMst);
	List<LicOblApplicationMst> findApplicationForPremList(List<LicHubMst> licHubMsts,String payMode, BranchMst branchMst);
	Boolean saveForPremiumList(List<LicPremApplMapping> licPremApplMappings);
	Boolean saveForPremiumDetailDataEntryList(LicPremiumListDtls licPremiumListDtls);
	List<LicPremiumListDtls> findPremiumListForDetailEntry(List<LicHubMst> licHubMsts);
	List<LicOblApplicationMst> findLicOblApplicationMstsByPremListNo(Long premListNo, BranchMst branchMst);
	LicPremiumListDtls findById(Long id);
	List<LicRequirementDtls> findRequirementForPremList(List<LicHubMst> licHubMsts, String payMode,BranchMst branchMst);
	Boolean saveForPremiumListForReq(List<LicPremReqMapping> licPremReqMapping);
	List<LicPremiumListDtls> findPremiumListForDetailEntryReq(List<LicHubMst> licHubMsts);
	List<LicRequirementDtls> findLicRequirementDtlsByPremListNo(Long premListNo, BranchMst branchMst);
	List<Long> findPremiumListNoForReqBocEntry(List<LicHubMst> licHubMsts);
	List<Object> findPolicyDtlsForPremList(List<LicHubMst> licHubMsts,String payMode, String healthReq, Date payFromDate, Date payToDate);
	Boolean savePremiumListForRnl(List<LicPremPolicyMapping> licPremPolicyMappings);
	List<LicPremiumListDtls> findPremiumListForDetailEntryForRenewal(List<LicHubMst> licHubMsts);
	List<Object> findPolicyDtlsByPremListNoForRenewal(Long premListNo);
	List<Long> findPremiumListForRenewalPicDelivery(List<LicHubMst> licHubMsts);
	List<Double> findCashAmtByRnlPayId(Long payId);
	List<Double> findChqDDAmtByRnlPayId(Long payId);
	List<LicOblApplicationMst> findPremiumListReport(List<LicHubMst> findHubForProcess, Date businessFromDate, Date businessToDate, Long premListNo);
	List<LicPremiumListDtls> findPremiumNoforPremListDetailEntryreport(List<LicHubMst> findHubForProcess);
	List<Long> findPremiumListByBusinessDate(List<LicHubMst> findHubForProcess,Date businessFromDate, Date businessToDate);
}
