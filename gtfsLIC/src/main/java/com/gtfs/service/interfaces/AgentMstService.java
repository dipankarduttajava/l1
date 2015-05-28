package com.gtfs.service.interfaces;

import java.io.Serializable;

import com.gtfs.bean.AgentMst;


public interface AgentMstService extends Serializable{
	public AgentMst findByAgCode(Long agCode);
}
