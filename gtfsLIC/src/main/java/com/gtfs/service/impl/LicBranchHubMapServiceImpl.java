package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchHubMap;
import com.gtfs.dao.interfaces.LicBranchHubMapDao;
import com.gtfs.service.interfaces.LicBranchHubMapService;

@Service

public class LicBranchHubMapServiceImpl implements LicBranchHubMapService,Serializable{
	@Autowired
	private LicBranchHubMapDao licBranchHubMapDao;
	@Override
	public List<LicBranchHubMap> findBranchHubMapsByBranch(BranchMst branchMst) {
		return licBranchHubMapDao.findBranchHubMapsByBranch(branchMst);
	}

}
