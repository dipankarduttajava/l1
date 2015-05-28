package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchReqRcvMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicBranchReqRcvMstDao;
import com.gtfs.service.interfaces.LicBranchReqRcvMstService;

@Service

public class LicBranchReqRcvMstServiceImpl implements Serializable,LicBranchReqRcvMstService {
	@Autowired
	private LicBranchReqRcvMstDao licBranchReqRcvMstDao;
	
	@Override
	public List<LicRequirementDtls> findPrendingRequrementAtBranch(Date bnsFromDate, Date bnsToDate, Long branchId) {
		return licBranchReqRcvMstDao.findPrendingRequrementAtBranch(bnsFromDate, bnsToDate, branchId);
	}

	@Override
	public Boolean save(LicBranchReqRcvMst licBranchReqRcvMst) {
		return licBranchReqRcvMstDao.save(licBranchReqRcvMst);
	}
	
	public List<LicBranchReqRcvMst> findRequirementForDispatch(Date businessFromDate,Date businessToDate,BranchMst branchMst){
		return licBranchReqRcvMstDao. findRequirementForDispatch(businessFromDate, businessToDate, branchMst);
	}

	@Override
	public List<LicBranchReqRcvMst> findRequirementByDispatchList(Long dispatchListNo, BranchMst branchMst) {
		return licBranchReqRcvMstDao.findRequirementByDispatchList(dispatchListNo,branchMst);
	}

}
