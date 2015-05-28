package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicBranchHubPicMapping;
import com.gtfs.bean.ProcessMst;


public interface LicBranchHubPicMappingService extends Serializable{
	List<Long> findHubIdForBranchIdByProcessName(Long branchId, String processName);
	List<LicBranchHubPicMapping> findSourceByIdTypeAndProcss(Long sourceId,String sourceType,ProcessMst processMst);
	List<LicBranchHubPicMapping> findDestinationByIdTypeAndProcss(Long sourceId,String sourceType,ProcessMst processMst);
	Boolean saveForMapping(List<LicBranchHubPicMapping> list);
	List<Long> findHubIdForRnlDespatch(Long branchId);
	
}
