package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicPremWvr;
import com.gtfs.dao.interfaces.LicPremWvrDao;

@Repository
public class LicPremWvrDaoImpl implements Serializable, LicPremWvrDao {
	private Logger log = Logger.getLogger(LicPremWvrDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicPremWvr> findLoadAmountByPropAgeTermProdId(Integer propAge,Long PremumPayingTerm,Long prodId){
		Session session = null;
		List<LicPremWvr> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremWvr.class);
            criteria.add(Restrictions.le("propAgeFrom", propAge));
            criteria.add(Restrictions.ge("propAgeTo", propAge));
            criteria.add(Restrictions.eq("ppt", PremumPayingTerm));
            criteria.add(Restrictions.eq("prodMstId", prodId));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            list = criteria.list();
        } catch (RuntimeException e) {
        	log.info("LicPremWvrDaoImpl findLoadAmountByPropAgeTermProdId Error", e);
        } finally {
            session.close();
        }
        return list;
		
	}
}
