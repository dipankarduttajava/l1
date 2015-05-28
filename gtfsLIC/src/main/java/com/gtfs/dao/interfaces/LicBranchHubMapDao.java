package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchHubMap;

public interface LicBranchHubMapDao {
	List<LicBranchHubMap> findBranchHubMapsByBranch(BranchMst branchMst);
}
