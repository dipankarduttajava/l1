package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPremApplMapping;
import com.gtfs.bean.LicPremPolicyMapping;
import com.gtfs.bean.LicPremReqMapping;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicPremiumListDao;
import com.gtfs.service.interfaces.LicPremiumListService;

@Service
public class LicPremiumListServiceImpl implements LicPremiumListService, Serializable{
	@Autowired
	private LicPremiumListDao licPremiumListDao;
	
	public List<LicOblApplicationMst> findApplicationForPremList(List<LicHubMst> licHubMsts,String payMode,BranchMst branchMst){
		return licPremiumListDao.findApplicationForPremList(licHubMsts, payMode, branchMst);
	}
	
	public Boolean saveForPremiumList(List<LicPremApplMapping> licPremApplMappings){
		return licPremiumListDao.saveForPremiumList(licPremApplMappings);
	}
	
	public List<LicPremiumListDtls> findPremiumListForDetailEntry(List<LicHubMst> licHubMsts){
		return licPremiumListDao.findPremiumListForDetailEntry(licHubMsts);
	}
	
	public List<LicOblApplicationMst> findLicOblApplicationMstsByPremListNo(Long premListNo, BranchMst branchMst){
		return licPremiumListDao.findLicOblApplicationMstsByPremListNo(premListNo, branchMst);
	}
	
	public LicPremiumListDtls findById(Long id){
		return licPremiumListDao.findById(id);
	}
	public Boolean saveForPremiumDetailDataEntryList(LicPremiumListDtls licPremiumListDtls){
		return licPremiumListDao.saveForPremiumDetailDataEntryList(licPremiumListDtls);
	}
	
	public List<Long> findPremiumListNoForBocEntry(List<LicHubMst> licHubMsts){
		return licPremiumListDao.findPremiumListNoForBocEntry(licHubMsts);
	}
	
	public List<Long> findPremiumListForPicDelivery(List<LicHubMst> licHubMsts){
		return licPremiumListDao.findPremiumListForPicDelivery(licHubMsts);
	}
	
	public List<LicOblApplicationMst> findApplicationForDeliveryListOfPic(Long premListNo, BranchMst branchMst){
		return licPremiumListDao.findApplicationForDeliveryListOfPic(premListNo, branchMst);
	}

	public List<LicRequirementDtls> findRequirementForPremList(List<LicHubMst> licHubMsts, String payMode, BranchMst branchMst) {
		return licPremiumListDao.findRequirementForPremList(licHubMsts, payMode, branchMst);
	}

	@Override
	public Boolean saveForPremiumListForReq(List<LicPremReqMapping> licPremReqMapping) {
		return licPremiumListDao.saveForPremiumListForReq(licPremReqMapping);
	}

	@Override
	public List<LicPremiumListDtls> findPremiumListForDetailEntryReq(List<LicHubMst> licHubMsts) {
		return licPremiumListDao.findPremiumListForDetailEntryReq(licHubMsts);
	}

	@Override
	public List<LicRequirementDtls> findLicRequirementDtlsByPremListNo(Long premListNo, BranchMst branchMst) {
		return licPremiumListDao.findLicRequirementDtlsByPremListNo(premListNo, branchMst);
	}

	@Override
	public List<Long> findPremiumListNoForReqBocEntry(List<LicHubMst> licHubMsts) {
		return licPremiumListDao.findPremiumListNoForReqBocEntry(licHubMsts);
	}

	@Override
	public List<Object> findPolicyDtlsForPremList(List<LicHubMst> licHubMsts,String payMode, String healthReq, Date payFromDate, Date payToDate) {
		return licPremiumListDao.findPolicyDtlsForPremList(licHubMsts,payMode,healthReq,payFromDate,payToDate);
	}

	@Override
	public Boolean savePremiumListForRnl(List<LicPremPolicyMapping> licPremPolicyMappings) {
		return licPremiumListDao.savePremiumListForRnl(licPremPolicyMappings);
	}

	@Override
	public List<LicPremiumListDtls> findPremiumListForDetailEntryForRenewal(List<LicHubMst> licHubMsts) {
		return licPremiumListDao.findPremiumListForDetailEntryForRenewal(licHubMsts);
	}
	
	@Override
	public List<Object> findPolicyDtlsByPremListNoForRenewal(Long premListNo) {
		return licPremiumListDao.findPolicyDtlsByPremListNoForRenewal(premListNo);
	}

	@Override
	public List<Long> findPremiumListForRenewalPicDelivery(List<LicHubMst> licHubMsts) {
		return licPremiumListDao.findPremiumListForRenewalPicDelivery(licHubMsts);
	}

	@Override
	public List<Object> findPolicyForDeliveryListOfPic(Long premListNo) {
		return licPremiumListDao.findPolicyForDeliveryListOfPic(premListNo);
	}

	@Override
	public List<Double> findCashAmtByRnlPayId(Long payId) {
		return licPremiumListDao.findCashAmtByRnlPayId(payId);
	}

	@Override
	public List<Double> findChqDDAmtByRnlPayId(Long payId) {
		return licPremiumListDao.findChqDDAmtByRnlPayId(payId);
	}

	@Override
	public List<LicOblApplicationMst> findPremiumListReport(List<LicHubMst> findHubForProcess, Date businessFromDate, Date businessToDate,Long premListNo) {
		return licPremiumListDao.findPremiumListReport(findHubForProcess, businessFromDate, businessToDate, premListNo);
	}

	@Override
	public List<LicPremiumListDtls> findPremiumNoforPremListDetailEntryreport(List<LicHubMst> findHubForProcess) {
		return licPremiumListDao.findPremiumNoforPremListDetailEntryreport(findHubForProcess);
	}

	@Override
	public List<Long> findPremiumListByBusinessDate(List<LicHubMst> findHubForProcess, Date businessFromDate,Date businessToDate) {
		return licPremiumListDao.findPremiumListByBusinessDate(findHubForProcess,businessFromDate,businessToDate);
	}
}
