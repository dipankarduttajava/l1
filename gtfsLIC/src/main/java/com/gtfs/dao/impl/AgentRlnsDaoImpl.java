package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.AgentRlns;
import com.gtfs.dao.interfaces.AgentRlnsDao;

@Repository
public class AgentRlnsDaoImpl implements AgentRlnsDao,Serializable{
	private Logger log = Logger.getLogger(AgentRlnsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<AgentRlns> findValidAgentByPhase(Long phaseId, Long agCode){
		Session session = null;
		List<AgentRlns> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(AgentRlns.class,"ar");
			criteria.add(Restrictions.eq("ar.phaseMst.phaseId", phaseId));
			criteria.add(Restrictions.eq("ar.agentMst.agCode",agCode));
			criteria.add(Restrictions.eq("ar.activeFlag", "Y"));
			
			list = criteria.list();
		}catch(Exception e){
			log.info("AgentRlnsDaoImpl findValidAgentByPhase Error", e);
		}finally{
			session.close();
		}
		return list;
	}
}
