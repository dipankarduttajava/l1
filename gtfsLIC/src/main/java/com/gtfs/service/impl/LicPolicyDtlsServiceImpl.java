package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.dao.interfaces.LicPolicyDtlsDao;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicPolicyDtlsService;

@Service

public class LicPolicyDtlsServiceImpl implements Serializable, LicPolicyDtlsService {

	@Autowired
	private LicPolicyDtlsDao licPolicyDtlsDao;
	
	public List<LicPolicyDtls> findAll() {
		return licPolicyDtlsDao.findAll();
	}

	public List<LicPolicyDtls> findPolicyDtlsByPolicyMst(Long policyMstId) {
		return licPolicyDtlsDao.findPolicyDtlsByPolicyMst(policyMstId);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNo(String policyNo,Date sysdate) {
		return licPolicyDtlsDao.findPolicyDtlsByPolicyNo(policyNo,sysdate);
	}

	@Override
	public Boolean saveRenewalBranchEntry(List<LicPolicyDtls> licPolicyDtlses) {
		return licPolicyDtlsDao.saveRenewalBranchEntry(licPolicyDtlses);
	}

	@Override
	public List<LicPolicyDtls> findAdvPolicyDtlsByPolicyNo(String policyNo, Date sysdate,Date incrementDate) {
		return licPolicyDtlsDao.findAdvPolicyDtlsByPolicyNo(policyNo, sysdate,incrementDate);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoForDirectRenewal(String policyNo, Date sysdate) {
		return licPolicyDtlsDao.findPolicyDtlsByPolicyNoForDirectRenewal(policyNo, sysdate);
	}

	@Override
	public List<LicPolicyDtls> findLastPaidPolicyDtlsByPolicyNo(String policyNo) {
		return licPolicyDtlsDao.findLastPaidPolicyDtlsByPolicyNo(policyNo);
	}

	@Override
	public LicPolicyDtls findById(Long id) {
		return licPolicyDtlsDao.findById(id);
	}

	@Override
	public List<Object> findPolicyDtlsByHubIdAndBranchForDispatch(Date payDateFrom, Date payDateTo, Long hubId, Long branchId) {
		return licPolicyDtlsDao.findPolicyDtlsByHubIdAndBranchForDispatch(payDateFrom, payDateTo, hubId, branchId);
	}

	@Override
	public Boolean saveBrnhHubDispatchList(List<LicPolicyDtls> licPolicyDtlsList) {
		return licPolicyDtlsDao.saveBrnhHubDispatchList(licPolicyDtlsList);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRange(String policyNo, Date fromDate, Date toDate) {

		return licPolicyDtlsDao.findPolicyDtlsByPolicyNoAndDueDateRange(policyNo,fromDate,toDate);
	}

	@Override
	public List<Long> findPodApplicationsForRenewal(Long id) {
		return licPolicyDtlsDao.findPodApplicationsForRenewal(id);
	}

	@Override
	public List<LicPolicyDtls> findApplicationByDispatchListForRenewal(Long id, BranchMst branchMst) {
		return licPolicyDtlsDao.findApplicationByDispatchListForRenewal(id, branchMst);
	}

	@Override
	public List<Object> findPolicyDtlsByDispatchIdAndBranchForPod(Long dispatchId, Long branchId) {
		return licPolicyDtlsDao.findPolicyDtlsByDispatchIdAndBranchForPod(dispatchId, branchId);
	}

	@Override
	public Boolean saveBrnhHubPodDtlsForRenewal(List<LicPolicyDtls> licPolicyDtlsList) {
		return licPolicyDtlsDao.saveBrnhHubPodDtlsForRenewal(licPolicyDtlsList);
	}

	@Override
	public List<Object> findIndividualMarkingDtlsByDateForRenewal(Date fromDate, Date toDate, Long branchId) {
		return licPolicyDtlsDao.findIndividualMarkingDtlsByDateForRenewal(fromDate, toDate, branchId);
	}

	@Override
	public List<Object> findPolicyDtlsForDispatchDecision(Date payDateFrom, Date payDateTo, Long id) {
		return licPolicyDtlsDao.findPolicyDtlsForDispatchDecision(payDateFrom, payDateTo, id);
	}

	@Override
	public List<Object> findHealthValidationDtlsByDateForRenewal(Date fromDate, Date toDate, Long branchId) {
		return licPolicyDtlsDao.findHealthValidationDtlsByDateForRenewal(fromDate, toDate, branchId);
	}

	@Override
	public Boolean saveHealthValidationForRenewal(List<LicPolicyDtls> licPolicyDtlsList) {
		return licPolicyDtlsDao.saveHealthValidationForRenewal(licPolicyDtlsList);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoDueDateRangeForHealthValidation(String policyNo, Date fromDate, Date toDate) {
		return licPolicyDtlsDao.findPolicyDtlsByPolicyNoDueDateRangeForHealthValidation(policyNo, fromDate, toDate);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRangeForPremium(String policyNo, Date fromDate, Date toDate) {
		return licPolicyDtlsDao.findPolicyDtlsByPolicyNoAndDueDateRangeForPremium(policyNo, fromDate, toDate);
	}

	@Override
	public List<Object> findPolicyDtlsByDispatchIdAndBranchForRnlPicPod(Long dispatchId, Long branchId) {
		return licPolicyDtlsDao.findPolicyDtlsByDispatchIdAndBranchForRnlPicPod(dispatchId, branchId);
	}

	@Override
	public List<Long> findDispatchListForRnlPicPod() {
		return licPolicyDtlsDao.findDispatchListForRnlPicPod();
	}

	@Override
	public List<Object> findPosViewRejectionByDateForRenewal(Date fromDate, Date toDate, Long branchId) {
		return licPolicyDtlsDao.findPosViewRejectionByDateForRenewal(fromDate, toDate, branchId);
	}

	@Override
	public List<Object> findPolicyDtlsForPosViewDispatch(Long branchId) {
		return licPolicyDtlsDao.findPolicyDtlsForPosViewDispatch(branchId);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoForPos(String policyNo) {
		return licPolicyDtlsDao.findPolicyDtlsByPolicyNoForPos(policyNo);
	}

	@Override
	public List<Object> findPolicyDtlsByDispatchIdRenewalPosViewPicPod(Long dispatchId) {
		return licPolicyDtlsDao.findPolicyDtlsByDispatchIdRenewalPosViewPicPod(dispatchId);
	}

	@Override
	public List<Object> findIndividualMarkingDtlsForPos(Long branchId) {
		return licPolicyDtlsDao.findIndividualMarkingDtlsForPos(branchId);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRangeForPos(String policyNo, Date fromDate, Date toDate) {
		return licPolicyDtlsDao.findPolicyDtlsByPolicyNoAndDueDateRangeForPos(policyNo, fromDate, toDate);
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoForRpr(String policyNo, Long id) {
		return licPolicyDtlsDao.findPolicyDtlsByPolicyNoForRpr(policyNo, id);
	}

	@Override
	public Boolean updatePolicyDtlsRenewalRprStatus(List<LicPolicyDtls> licPolicyDtlsList) {
		return licPolicyDtlsDao.updatePolicyDtlsRenewalRprStatus(licPolicyDtlsList);
	}

	@Override
	public List<LicPolicyDtlsDto> findPolicyDtlsForRnlPrintReceipt(String policyNo) {
		return licPolicyDtlsDao.findPolicyDtlsForRnlPrintReceipt(policyNo);
	}

	@Override
	public Boolean saveNormalRenewalBranchEntry(List<LicPolicyDtls> licPolicyDtlsList, LicPolicyMst licPolicyMst) {
		return licPolicyDtlsDao.saveNormalRenewalBranchEntry(licPolicyDtlsList,licPolicyMst);
	}

}
