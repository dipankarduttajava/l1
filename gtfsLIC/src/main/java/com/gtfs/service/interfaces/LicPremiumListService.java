package com.gtfs.service.interfaces;

import java.io.Serializable;
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


public interface LicPremiumListService extends Serializable{
	public List<LicOblApplicationMst> findApplicationForPremList(List<LicHubMst> licHubMsts,String payMode,BranchMst branchMst);
	public Boolean saveForPremiumList(List<LicPremApplMapping> licPremApplMappings);
	public List<LicPremiumListDtls> findPremiumListForDetailEntry(List<LicHubMst> licHubMsts);
	public List<LicOblApplicationMst> findLicOblApplicationMstsByPremListNo(Long premListNo, BranchMst branchMst);
	public LicPremiumListDtls findById(Long id);
	public Boolean saveForPremiumDetailDataEntryList(LicPremiumListDtls licPremiumListDtls);
	public List<Long> findPremiumListNoForBocEntry(List<LicHubMst> licHubMsts);
	public List<Long> findPremiumListForPicDelivery(List<LicHubMst> licHubMsts);
	public List<LicOblApplicationMst> findApplicationForDeliveryListOfPic(Long premListNo, BranchMst branchMst);
	public List<LicRequirementDtls> findRequirementForPremList(List<LicHubMst> licHubMsts,String payMode,BranchMst branchMst);
	public Boolean saveForPremiumListForReq(List<LicPremReqMapping> licPremReqMapping);
	public List<LicPremiumListDtls> findPremiumListForDetailEntryReq(List<LicHubMst> licHubMsts);
	public List<LicRequirementDtls> findLicRequirementDtlsByPremListNo(Long premListNo, BranchMst branchMst);
	public List<Long> findPremiumListNoForReqBocEntry(List<LicHubMst> licHubMsts);
	public List<Object> findPolicyDtlsForPremList(List<LicHubMst> licHubMsts,String payMode,String healthReq,Date payFromDate,Date payToDate);
	Boolean savePremiumListForRnl(List<LicPremPolicyMapping> licPremPolicyMappings);
	List<LicPremiumListDtls> findPremiumListForDetailEntryForRenewal(List<LicHubMst> licHubMsts);
	List<Object> findPolicyDtlsByPremListNoForRenewal(Long premListNo);
	List<Long> findPremiumListForRenewalPicDelivery(List<LicHubMst> licHubMsts);
	List<Object> findPolicyForDeliveryListOfPic(Long premListNo);
	List<Double> findCashAmtByRnlPayId(Long payId);
	List<Double> findChqDDAmtByRnlPayId(Long payId);
	public List<LicOblApplicationMst> findPremiumListReport(List<LicHubMst> findHubForProcess, Date businessFromDate, Date businessToDate, Long premListNo);
	public List<LicPremiumListDtls> findPremiumNoforPremListDetailEntryreport(List<LicHubMst> findHubForProcess);
	public List<Long> findPremiumListByBusinessDate(List<LicHubMst> findHubForProcess, Date businessFromDate,Date businessToDate);
}
