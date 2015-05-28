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

import com.gtfs.bean.LicNsapMst;
import com.gtfs.dao.interfaces.LicNsapMstDao;
@Repository
public class LicNsapMstDaoImpl implements LicNsapMstDao,Serializable{
	private Logger log = Logger.getLogger(LicNsapMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicNsapMst> findNsapMstByProdIdAgeTerm(Long prodId,Integer age,Long policyTerm,Long premiumPayingTerm){
		Session session = null;
		List<LicNsapMst> list = null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicNsapMst.class);
			criteria.add(Restrictions.eq("licProductMst.id", prodId));
			criteria.add(Restrictions.eq("age", age));
			criteria.add(Restrictions.eq("pt", policyTerm));
			criteria.add(Restrictions.eq("ppt", premiumPayingTerm));
			criteria.add(Restrictions.eq("deleteFlag", "N"));
			list=criteria.list();
		}catch(Exception e){
			log.info("LicNsapMstDaoImpl findNsapMstByProdIdAgeTerm Error", e);
		}finally{
			session.close();
		}
		return list;
	}
}
