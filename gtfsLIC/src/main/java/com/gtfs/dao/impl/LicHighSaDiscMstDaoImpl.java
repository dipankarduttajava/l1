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

import com.gtfs.bean.LicHighSaDiscMst;
import com.gtfs.dao.interfaces.LicHighSaDiscMstDao;
@Repository
public class LicHighSaDiscMstDaoImpl implements LicHighSaDiscMstDao,Serializable{
	private Logger log = Logger.getLogger(LicHighSaDiscMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicHighSaDiscMst> findHighSaDiscByProdIdTermSumAssured(Long prodId,Long term,Double sa){
		Session session = null;
		List<LicHighSaDiscMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicHighSaDiscMst.class);
			criteria.add(Restrictions.eq("licProductMst.id", prodId));
			criteria.add(Restrictions.le("hsdSaFrom", sa));
			criteria.add(Restrictions.ge("hsdSaTo", sa));
			criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("consTermFlag", "Y"), Restrictions.eq("term", term)), Restrictions.and(Restrictions.eq("consTermFlag", "N"), Restrictions.eq("deleteFlag", "N"))));
	          
			list = criteria.list();
		}catch(Exception e){
			log.info("LicHighSaDiscMstDaoImpl findHighSaDiscByProdIdTermSumAssured Error", e);
		}finally{
			session.close();
		}
		return list;
	}
}
