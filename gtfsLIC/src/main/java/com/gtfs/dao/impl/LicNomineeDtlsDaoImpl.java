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

import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.dao.interfaces.LicNomineeDtlsDao;
@Repository
public class LicNomineeDtlsDaoImpl implements LicNomineeDtlsDao,Serializable{
	private Logger log = Logger.getLogger(LicNomineeDtlsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicNomineeDtls> findNomineeDtlsByApplication(LicOblApplicationMst licOblApplicationMst){
		 Session session = null;
	        List<LicNomineeDtls> list = null;
	        try {
	            session = sessionFactory.openSession();
	            Criteria criteria = session.createCriteria(LicNomineeDtls.class);
	            criteria.add(Restrictions.eq("licOblApplicationMst", licOblApplicationMst));
	            criteria.add(Restrictions.eq("deleteFlag", "N"));		        
	            list = criteria.list();
	        } catch (Exception e) {
	        	log.info("LicNomineeDtlsDaoImpl findNomineeDtlsByApplication Error", e);
	        } finally {
	            session.close();
	        }
		return list;
	}
}
