package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.dao.interfaces.LicHubMstDao;
import com.gtfs.service.interfaces.LicHubMstService;

@Service

public class LicHubMstServiceImpl implements LicHubMstService,Serializable{
	@Autowired
	private LicHubMstDao licHubMstDao;
	
	public List<LicHubMst> findActiveHubMst(){
		return licHubMstDao.findActiveHubMst();
	}
	
	public LicHubMst findById(Long id){
		return licHubMstDao.findById(id);
	}
	
	public List<LicHubMst> findActiveHubMstByBranch(BranchMst branchMst){
		return licHubMstDao.findActiveHubMstByBranch(branchMst);
	}
	
	public List<Long> findActiveHubIdByBranchId(Long branchId){
		return licHubMstDao.findActiveHubIdByBranchId(branchId);
	}
}
