package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;


public interface LicHubMstService extends Serializable{
	List<LicHubMst> findActiveHubMst();
	LicHubMst findById(Long id);
	List<LicHubMst> findActiveHubMstByBranch(BranchMst branchMst);
	List<Long> findActiveHubIdByBranchId(Long branchId);
}
