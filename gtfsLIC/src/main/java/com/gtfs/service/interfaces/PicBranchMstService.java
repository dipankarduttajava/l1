package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.PicBranchMst;

public interface PicBranchMstService extends Serializable{
	List<Long> findPicIdForBranchIdByProcessName(Long branchId,String processName);
	List<PicBranchMst> findActivePicBranchMst();
	PicBranchMst findbyId(Long picBranchId);
	List<Long> findPicIdsForHubsByProcessName(List<Long> licHubMsts,String processName);
	List<PicBranchMst> findPicsByPicIds(List<Long> picBranchIds);
}
