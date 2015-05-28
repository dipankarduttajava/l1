package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.ParentCompyMst;
import com.gtfs.dao.interfaces.ParentCompyMstDao;
import com.gtfs.service.interfaces.ParentCompyMstService;

@Service

public class ParentCompyMstServiceImpl implements ParentCompyMstService, Serializable{
	@Autowired
	private ParentCompyMstDao parentCompyMstDao;
	public List<ParentCompyMst> findAll(){
		return parentCompyMstDao.findAll();
	}
	
	public ParentCompyMst findById(Long id){
		return parentCompyMstDao.findById(id);
	}
}
