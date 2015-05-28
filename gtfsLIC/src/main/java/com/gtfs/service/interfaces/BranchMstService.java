package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.BranchMst;

public interface BranchMstService extends Serializable{
	List<BranchMst> findAll();
	BranchMst findById(Long id);
}
