package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicRequirementDtlsDao;
import com.gtfs.service.interfaces.LicRequirementDtlsService;

@Service

public class LicRequirementDtlsServiceImpl implements LicRequirementDtlsService, Serializable{
	@Autowired
	private LicRequirementDtlsDao licRequirementDtlsDao;
	
	public Boolean saveForRequirementDtls(LicRequirementDtls licRequirementDtls){
		return licRequirementDtlsDao.saveForRequirementDtls(licRequirementDtls);
	}
	
	public List<LicRequirementDtls> findReqForReqEntryByApplNo(String applNo){
		return licRequirementDtlsDao.findReqForReqEntryByApplNo(applNo);
	}
	
	public List<LicRequirementDtls> findReqForActionTakenByApplNo(String applNo,List<LicHubMst> licHubMsts){
		return licRequirementDtlsDao.findReqForActionTakenByApplNo(applNo,licHubMsts);
	}
	
	public Boolean saveForActionPoint(List<LicRequirementDtls> list){
		return licRequirementDtlsDao.saveForActionPoint(list);
	}

	@Override
	public List<LicRequirementDtls> findPendingRequirementDtlsByApplicationNoAndReqType(String applicationNo, String reqType) {
		return licRequirementDtlsDao.findPendingRequirementDtlsByApplicationNoAndReqType(applicationNo, reqType);
	}

	@Override
	public LicRequirementDtls findById(Long id) {
		return licRequirementDtlsDao.findById(id);
	}
	
	@Override
	public List<Long> findPodRequirmentForPicDispatch(List<LicHubMst> licHubMsts) {
		return licRequirementDtlsDao.findPodRequirmentForPicDispatch(licHubMsts);
	}
	
	@Override
	public List<LicRequirementDtls> findRequirmentByDispatchListForPicDispatch(Long id, BranchMst branchMst) {
		return licRequirementDtlsDao.findRequirmentByDispatchListForPicDispatch(id, branchMst);
	}

	@Override
	public Boolean updateForPosViewRejectionForRenewal(List<LicRequirementDtls> LicRequirementDtlsList) {
		return licRequirementDtlsDao.updateForPosViewRejectionForRenewal(LicRequirementDtlsList);
	}

	@Override
	public List<LicRequirementDtls> findReqDtlsByPolicyDtlsId(LicPolicyDtls licPolicyDtls) {
		return licRequirementDtlsDao.findReqDtlsByPolicyDtlsId(licPolicyDtls);
	}

	@Override
	public List<Long> findDispatchListForPosView() {
		return licRequirementDtlsDao.findDispatchListForPosView();
	}

	@Override
	public List<LicRequirementDtls> findRequirementForPrintReceiptByDate(Date businessDate, Long branchId) {
		return licRequirementDtlsDao.findRequirementForPrintReceiptByDate(businessDate,branchId);
	}

	@Override
	public List<LicRequirementDtls> findRequirementForPrintReceiptByApplicationNo(String applicationNo, Long branchId) {
		return licRequirementDtlsDao.findRequirementForPrintReceiptByApplicationNo(applicationNo,branchId);
	}

	@Override
	public Boolean updatePrintReceiptFlagInLicRequirementDtls(Long id) {
		return licRequirementDtlsDao.updatePrintReceiptFlagInLicRequirementDtls(id);
	}

}
