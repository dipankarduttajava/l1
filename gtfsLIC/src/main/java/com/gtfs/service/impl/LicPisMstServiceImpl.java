package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicCmsMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPisMst;
import com.gtfs.dao.interfaces.LicPisMstDao;
import com.gtfs.service.interfaces.LicPisMstService;
@Service

public class LicPisMstServiceImpl implements LicPisMstService, Serializable{
	@Autowired
	private LicPisMstDao licPisMstDao;
	
	public List<Object> findApplicationforPis(BranchMst branchMst){
		return licPisMstDao.findApplicationforPis(branchMst);
	}
	
	public Boolean save(LicPisMst licPisMst){
		return licPisMstDao.save(licPisMst);
	}

	@Override
	public List<Object> findApplicationforPisForRequirement(BranchMst branchMst) {
		return licPisMstDao.findApplicationforPisForRequirement(branchMst);
	}

	@Override
	public List<Object> findPolicyDtlsforPis(BranchMst branchMst) {
		return licPisMstDao.findPolicyDtlsforPis(branchMst);
	}

	@Override
	public List<LicOblApplicationMst> findPisGeneratedReport(Date businessFromDate, Date businessToDate, String checkList, String applNo, List<LicHubMst> licHubMsts) {
		return licPisMstDao.findPisGeneratedReport(businessFromDate, businessToDate, checkList, applNo, licHubMsts);
	}

	@Override
	public List<LicPisMst> findPisListForPisReport(Long pisId,Date busineeFormDate, Date businessToDate) {
		return licPisMstDao.findPisListForPisReport(pisId,busineeFormDate,businessToDate);
	}

	@Override
	public List<Object> findApplicationByPis(Long pisId) {
		return licPisMstDao.findApplicationByPis(pisId);
	}

	@Override
	public List<LicCmsMst> findCmsByPisId(Long id) {
		return licPisMstDao.findCmsByPisId(id);
	}
}
