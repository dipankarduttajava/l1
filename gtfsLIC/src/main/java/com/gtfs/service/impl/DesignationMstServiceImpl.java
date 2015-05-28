package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.DesignationMst;
import com.gtfs.dao.interfaces.DesignationMstDao;
import com.gtfs.service.interfaces.DesignationMstService;

@Service

public class DesignationMstServiceImpl implements DesignationMstService,Serializable{
	@Autowired
	private DesignationMstDao designationMstDao;
	
	public List<DesignationMst> findAllActiveFromDesignationMst() {
		return designationMstDao.findAllActiveFromDesignationMst();
	}
	
	public DesignationMst findById(Long designationId){
		return designationMstDao.findById(designationId);
	}
	
}
