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

import com.gtfs.bean.LicDocReqMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicDocReqMstDao;

@Repository
public class LicDocReqMstDaoImpl implements LicDocReqMstDao,Serializable{
	private Logger log = Logger.getLogger(LicDocReqMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<LicDocReqMst> findAll() {
		List<LicDocReqMst> list=null;
		Session session = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicDocReqMst.class,"ldrm");
			 criteria.add(Restrictions.eq("ldrm.deleteFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicDocReqMstDaoImpl findAll Error", e);
		}finally {
           session.close();
       }
		return list;
	}
	
	
}
