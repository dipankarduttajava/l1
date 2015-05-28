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

import com.gtfs.bean.SchemeMst;
import com.gtfs.dao.interfaces.SchemeMstDao;

@Repository
public class SchemeMstDaoImpl implements SchemeMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<SchemeMst> findAcitveSchemeMstList(){
	        Session session = null;
	        List<SchemeMst> list = null;
	        try {
	            session = sessionFactory.openSession();
	            Criteria crit = session.createCriteria(SchemeMst.class);
	            crit.add(Restrictions.eq("activeFlag", "Y"));
	            list = crit.list();
	        } catch (RuntimeException re) {
	               re.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return list;
	    }
}
