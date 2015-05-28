package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;

public interface LicHubMstDao {
	List<LicHubMst> findActiveHubMst();
	LicHubMst findById(Long id);
	List<LicHubMst> findActiveHubMstByBranch(BranchMst branchMst);
	List<Long> findActiveHubIdByBranchId(Long branchId);
}
