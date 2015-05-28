package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.AgentRlns;
import com.gtfs.dao.interfaces.AgentRlnsDao;
import com.gtfs.service.interfaces.AgentRlnsService;

@Service

public class AgentRlnsServiceImpl implements AgentRlnsService,Serializable{
	
	@Autowired
	private AgentRlnsDao agentRlnsDao;

	public List<AgentRlns> findValidAgentByPhase(Long phaseId, Long agCode){
		return agentRlnsDao.findValidAgentByPhase(phaseId,agCode);
	}
}
