package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.PicBranchMst;

public interface PicBranchMstDao {
	List<Long> findPicIdsForHubsByProcessName(List<Long> licHubMsts,String processName);
	List<Long> findPicIdForBranchIdByProcessName(Long branchId,String processName);
	List<PicBranchMst> findActivePicBranchMst();
	PicBranchMst findbyId(Long picBranchId);
	List<PicBranchMst> findPicsByPicIds(List<Long> picBranchIds);
}
