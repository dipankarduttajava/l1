package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.AgentMst;
import com.gtfs.dao.interfaces.AgentMstDao;

@Repository
public class AgentMstDaoImpl implements AgentMstDao,Serializable{
	private Logger log = Logger.getLogger(AgentMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AgentMst findByAgCode(Long agCode){
		AgentMst agentMst = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            agentMst = (AgentMst) session.get(AgentMst.class, agCode);
        } catch (Exception e) {
        	log.info("AgentMstDaoImpl findByAgCode Error", e);
        } finally {
            session.close();
        }
        return agentMst;
	}
	
	
	public List<AgentMst> findByName(String name){
		List<AgentMst> list = null;
		Session session=null;
		try{
			session = sessionFactory.openSession();
			Criteria crt=session.createCriteria(AgentMst.class).
					add(Restrictions.like("agName",'%'+name+'%'));
			
			list=crt.list();
			
			/*Query query = session.createQuery("from AgentMst where agName like :agName");
			query.setParameter("agName", "%"+name+"%");
			list=query.list();*/
			
		}catch(Exception e){
			log.info("Error ",e);
		} finally{
			session.close();
		}
		return list;
	
	}
	
	
}