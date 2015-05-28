package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicRequirementDtls;

public interface LicBocDetailEntryDao {
	List<LicOblApplicationMst> findApplicationForBocEntry(Long premListNo, BranchMst branchMst);
	Boolean saveBoc(List<LicOblApplicationMst>  applicationMsts);
	Boolean saveBocForReq(List<LicRequirementDtls>  licRequirementDtls);
	List<LicRequirementDtls> findRequirmentDtlsForBocEntry(Long premListNo, BranchMst branchMst);
	List<LicOblApplicationMst> findbocReport(Date businessFromDate, Date businessToDate, List<LicHubMst> findHubForProcess);
}
