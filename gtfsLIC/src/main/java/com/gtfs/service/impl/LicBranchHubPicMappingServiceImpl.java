package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicBranchHubPicMapping;
import com.gtfs.bean.ProcessMst;
import com.gtfs.dao.interfaces.LicBranchHubPicMappingDao;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;

@Service

public class LicBranchHubPicMappingServiceImpl implements LicBranchHubPicMappingService,Serializable{
	@Autowired
	private LicBranchHubPicMappingDao licBranchHubPicMappingDao;
	
	public List<Long> findHubIdForBranchIdByProcessName(Long branchId,String processName){
		return licBranchHubPicMappingDao.findHubIdForBranchIdByProcessName(branchId, processName);
	}
	
	public List<LicBranchHubPicMapping> findSourceByIdTypeAndProcss(Long sourceId,String sourceType,ProcessMst processMst){
		return licBranchHubPicMappingDao.findSourceByIdTypeAndProcss(sourceId, sourceType, processMst);
	}
	
	public List<LicBranchHubPicMapping> findDestinationByIdTypeAndProcss(Long sourceId,String sourceType,ProcessMst processMst){
		return licBranchHubPicMappingDao.findDestinationByIdTypeAndProcss(sourceId, sourceType, processMst);
	}
	
	
	public Boolean saveForMapping(List<LicBranchHubPicMapping> list){
		return licBranchHubPicMappingDao.saveForMapping(list);
	}

	@Override
	public List<Long> findHubIdForRnlDespatch(Long branchId) {
		return licBranchHubPicMappingDao.findHubIdForRnlDespatch(branchId);
	}
	
}
