package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.PicBranchMst;
import com.gtfs.dao.interfaces.PicBranchMstDao;
import com.gtfs.service.interfaces.PicBranchMstService;
@Service

public class PicBranchMstServiceImpl implements PicBranchMstService, Serializable{
	@Autowired
	private PicBranchMstDao picBranchMstDao;
	public List<Long> findPicIdForBranchIdByProcessName(Long branchId,String processName){
		return picBranchMstDao.findPicIdForBranchIdByProcessName(branchId, processName);
	}
	
	public List<PicBranchMst> findActivePicBranchMst(){
		return picBranchMstDao.findActivePicBranchMst();
	}
	
	public PicBranchMst findbyId(Long picBranchId){
		return picBranchMstDao.findbyId(picBranchId);
	}
	
	public List<Long> findPicIdsForHubsByProcessName(List<Long> licHubMsts,String processName){
		return picBranchMstDao.findPicIdsForHubsByProcessName(licHubMsts, processName);
	}
	
	public List<PicBranchMst> findPicsByPicIds(List<Long> picBranchIds){
		return picBranchMstDao.findPicsByPicIds(picBranchIds);
	}
}
