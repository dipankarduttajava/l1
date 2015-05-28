package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.dao.interfaces.BranchMstDao;
import com.gtfs.service.interfaces.BranchMstService;

@Service

public class BranchMstServiceImpl implements BranchMstService,Serializable{
	@Autowired
	 private BranchMstDao branchMstDao;
	 
	 public List<BranchMst> findAll() {
	        return branchMstDao.findAllBranches(BranchMst.class);
	    }
	 
	 public BranchMst findById(Long id) {
		 return branchMstDao.findById(id);
	 }
}
