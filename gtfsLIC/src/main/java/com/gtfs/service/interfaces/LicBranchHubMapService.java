package com.gtfs.service.interfaces;

import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchHubMap;

public interface LicBranchHubMapService {
	List<LicBranchHubMap> findBranchHubMapsByBranch(BranchMst branchMst);
}
