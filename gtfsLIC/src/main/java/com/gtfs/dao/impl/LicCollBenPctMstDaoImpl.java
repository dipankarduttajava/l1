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

import com.gtfs.bean.LicCollBenPctMst;
import com.gtfs.dao.interfaces.LicCollBenPctMstDao;
@Repository
public class LicCollBenPctMstDaoImpl implements LicCollBenPctMstDao,Serializable{
	private Logger log = Logger.getLogger(LicCollBenPctMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicCollBenPctMst> findLicCollBenPctMstByProdIdTerm(Long prodId,Long term){
		List<LicCollBenPctMst> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicCollBenPctMst.class,"lcbpm");
            criteria.createAlias("lcbpm.tieupCompyMst", "tcm");
            criteria.createAlias("lcbpm.schemeMst", "sm");
            criteria.add(Restrictions.eq("lcbpm.licProductMst.id",prodId));
            criteria.add(Restrictions.le("lcbpm.termFrom", term));
            criteria.add(Restrictions.ge("lcbpm.termTo", term));
            criteria.add(Restrictions.eq("lcbpm.deleteFlag", "N"));
            list=criteria.list();
        } catch (Exception e) {
        	log.info("LicCollBenPctMstDaoImpl findLicCollBenPctMstByProdIdTerm Error", e);
        } finally {
            session.close();
        }
        return list;
	}
}
