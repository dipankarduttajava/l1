package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.GenericBusinessTxn;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.dao.interfaces.LicPolicyMstDao;
import com.gtfs.service.interfaces.LicPolicyMstService;

@Service
public class LicPolicyMstServiceImpl implements LicPolicyMstService, Serializable{
	@Autowired
	private LicPolicyMstDao licPolicyMstDao;
	
	public Boolean update(LicPolicyMst licPolicyMst){
		return licPolicyMstDao.update(licPolicyMst);
	}

	@Override
	public LicOblApplicationMst findPolicyDtls(Long id) {
		return licPolicyMstDao.findPolicyDtls(id);
	}

	@Override
	public List<LicPolicyMst> checkPolicyNo(String policyNo) {
		return licPolicyMstDao.checkPolicyNo(policyNo);
	}

	@Override
	public Boolean updateForStatusEntry(LicPolicyMst licPolicyMst,GenericBusinessTxn genericBusinessTxn) {
		return licPolicyMstDao.updateForStatusEntry(licPolicyMst,genericBusinessTxn);
	}

	@Override
	public LicPolicyMst findById(Long id) {
		return licPolicyMstDao.findById(id);
	}

	@Override
	public List<LicPolicyMst> findApplicationForRejectedEntry(Date fromDate,Date toDate, String applicantName, Double premium,Double sumAssured, Long term, String applicationNo,String policyNo, String proposalNo,List<LicHubMst> findHubForProcess) {
		return licPolicyMstDao.findApplicationForRejectedEntry(fromDate,toDate,applicantName,premium,sumAssured,term,applicationNo,policyNo,proposalNo,findHubForProcess);
	}

	@Override
	public List<LicPolicyMst> findApplicationForFprAndPolicyBondDelivery(Date fromDate, Date toDate, String applicantName, Double premium, Double sumAssured, Long term, String applicationNo, String policyNo, String proposalNo, List<LicHubMst> findHubForProcess) {
		return licPolicyMstDao.findApplicationForFprAndPolicyBondDelivery(fromDate,toDate,applicantName,premium,sumAssured,term,applicationNo,policyNo,proposalNo,findHubForProcess);

	}

	@Override
	public List<LicPolicyMst> findPolicyInfoByPolicyNo(String policyNo) {
		return licPolicyMstDao.findPolicyInfoByPolicyNo(policyNo);
	}

}
