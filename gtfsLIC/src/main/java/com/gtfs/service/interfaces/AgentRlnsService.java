package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.AgentRlns;


public interface AgentRlnsService extends Serializable{
	public List<AgentRlns> findValidAgentByPhase(Long phaseId, Long agCode);
}
