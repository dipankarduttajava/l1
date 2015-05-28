package com.gtfs.dao.interfaces;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.gtfs.bean.AgentRlns;
import com.gtfs.dao.impl.HibernateUtil;

public interface AgentRlnsDao {
	List<AgentRlns> findValidAgentByPhase(Long phaseId, Long agCode);
}
