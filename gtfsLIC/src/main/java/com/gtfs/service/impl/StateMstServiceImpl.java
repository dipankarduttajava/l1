package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.StateMst;
import com.gtfs.dao.interfaces.StateMstDao;
import com.gtfs.service.interfaces.StateMstService;

@Service

public class StateMstServiceImpl implements StateMstService, Serializable{
	@Autowired
	private StateMstDao stateMstDao;
	public List<StateMst> findAllActiveStateMSt(){
		return stateMstDao.findAllActiveStateMSt();
	}
	
	public StateMst findById(Long stateId){
		return stateMstDao.findById(stateId);
	}
}
