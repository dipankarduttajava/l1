package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.ProcessMst;
import com.gtfs.dao.interfaces.ProcessMstDao;
import com.gtfs.service.interfaces.ProcessMstService;
@Service

public class ProcessMstServiceImpl implements ProcessMstService, Serializable{
	@Autowired
	private ProcessMstDao processMstDao;
	
	public List<ProcessMst> findAll(){
		return processMstDao.findAll();
	}
	
	public ProcessMst findById(Long id){
		return processMstDao.findById(id);
	}
}
