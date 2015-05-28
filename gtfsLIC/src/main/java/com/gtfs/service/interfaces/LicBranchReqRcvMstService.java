package com.gtfs.service.interfaces;

import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchReqRcvMst;
import com.gtfs.bean.LicRequirementDtls;

public interface LicBranchReqRcvMstService {
	List<LicRequirementDtls> findPrendingRequrementAtBranch(Date bnsFromDate,Date bnsToDate,Long branchId);
	Boolean save(LicBranchReqRcvMst licBranchReqRcvMst);
	List<LicBranchReqRcvMst> findRequirementForDispatch(Date businessFromDate,Date businessToDate,BranchMst branchMst);
	List<LicBranchReqRcvMst> findRequirementByDispatchList(Long dispatchListNo, BranchMst branchMst);
}
