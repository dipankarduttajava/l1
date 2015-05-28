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

import com.gtfs.bean.LicModeRebateMst;
import com.gtfs.dao.interfaces.LicModeRebateMstDao;
@Repository
public class LicModeRebateMstDaoImpl implements LicModeRebateMstDao,Serializable{
	private Logger log = Logger.getLogger(LicModeRebateMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicModeRebateMst> findModeRebateByProdIdPayMode(Long prodId,String payMode){
		List<LicModeRebateMst> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicModeRebateMst.class);
            criteria.add(Restrictions.eq("licProductMst.id",prodId));
            criteria.add(Restrictions.eq("payMode",payMode));
            criteria.add(Restrictions.eq("deleteFlag","N"));
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicModeRebateMstDaoImpl findModeRebateByProdIdPayMode Error", e);
        } finally {
            session.close();
        }
        return list;
	}
}
