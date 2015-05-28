package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicOblApplicationMstDto;

public interface LicBrnhHubPicMapDtlsService extends Serializable{
	Long saveForBranchHubDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls);
	Long saveForHubPicDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls);
	Boolean saveForBranchHubReqDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls);
	List<LicRequirementDtls> findRequirementsForHubPicDispatch(Date busineeFromDate,Date busineeToDate,List<LicHubMst> licHubMsts);
	Long saveForHubPicDispatchListForReq(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls);
	Boolean savePosViewDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls);
	List<LicOblApplicationMstDto> findBranchHubDispatchReport(Date businessFromDate, Date businessToDate, BranchMst branchMst);
	List<LicOblApplicationMst> findPicDispatchReport(Date businessFromDate,	Date businessToDate, BranchMst branchMst);
}
