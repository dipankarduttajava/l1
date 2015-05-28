package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.StateMst;
import com.gtfs.dao.interfaces.StateMstDao;

@Repository
public class StateMstDaoImpl implements StateMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<StateMst> findAllActiveStateMSt(){
		 Session session = null;
		 List<StateMst> list = null;
	        try {
	            session = sessionFactory.openSession();
	            Criteria criteria = session.createCriteria(StateMst.class);
	            criteria.add(Restrictions.eq("activeFlag", "Y"));
	            list = criteria.list();
	        } catch (Exception e) {

	        } finally {
	            session.close();
	        }
	        return list;
	}
	
	public StateMst findById(Long stateId){
		Session session = null;
		 StateMst stateMst = null;
	        try {
	            session = sessionFactory.openSession();
	            stateMst=(StateMst) session.get(StateMst.class, stateId);
	        } catch (Exception e) {

	        } finally {
	            session.close();
	        }
	        return stateMst;
	}
	
}
