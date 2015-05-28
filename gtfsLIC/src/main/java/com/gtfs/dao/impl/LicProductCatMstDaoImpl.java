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

import com.gtfs.bean.LicProductCatMst;
import com.gtfs.dao.interfaces.LicProductCatMstDao;

@Repository
public class LicProductCatMstDaoImpl implements LicProductCatMstDao,Serializable{
	private Logger log = Logger.getLogger(LicProductCatMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicProductCatMst> findActiveLicProdCategory(){
		 Session session = null;
	        List<LicProductCatMst> list = null;
	        try {
	            session = sessionFactory.openSession();
	            Criteria criteria = session.createCriteria(LicProductCatMst.class);
	            criteria.add(Restrictions.eq("deleteFlag", "N"));
	            list = criteria.list();
	        } catch (Exception e) {
	        	log.info("LicProductCatMstDaoImpl findActiveLicProdCategory Error", e);
	        } finally {
	            session.close();
	        }
	        return list;
	}
}
