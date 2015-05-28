package com.gtfs.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.AgentMst;
import com.gtfs.dao.interfaces.AgentMstDao;
import com.gtfs.service.interfaces.AgentMstService;

@Service

public class AgentMstServiceImpl implements AgentMstService,Serializable{
	@Autowired
	private AgentMstDao agentMstDao;
	
	public AgentMst findByAgCode(Long agCode){
		return agentMstDao.findByAgCode(agCode);
	}
}
