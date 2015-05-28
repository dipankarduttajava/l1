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

import com.gtfs.bean.LicNsapDocMst;
import com.gtfs.dao.interfaces.LicNsapDocMstDao;

@Repository
public class LicNsapDocMstDaoImpl implements LicNsapDocMstDao,Serializable{
	private Logger log = Logger.getLogger(LicNsapDocMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicNsapDocMst> findLicNsapDocMstByAge(Integer age,Double sumAssured,Long productId){
		List<LicNsapDocMst> list = null;
		Session session = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicNsapDocMst.class);
			 criteria.add(Restrictions.le("docAgeFrom", age));
			 criteria.add(Restrictions.ge("docAgeTo", age));
			 criteria.add(Restrictions.eq("deleteFlag", "N"));
			 criteria.add(Restrictions.le("sumAssuredFrom", sumAssured));
			 criteria.add(Restrictions.ge("sumAssuredTo", sumAssured));
			 criteria.add(Restrictions.eq("licProductMst.id", productId));
			 list = criteria.list();
			 
		}catch(Exception e){
			log.info("LicNsapDocMstDaoImpl findLicNsapDocMstByAge Error", e);
		}finally {
            session.close();
        }
		return list;
	}
	
	public LicNsapDocMst findById(Long id){
		LicNsapDocMst licNsapDocMst = null;
		Session session = null;
		try{
			 session = sessionFactory.openSession();
			 licNsapDocMst = (LicNsapDocMst) session.get(LicNsapDocMst.class ,id);
			 
		}catch(Exception e){
			log.info("LicNsapDocMstDaoImpl findById Error", e);
		}finally {
            session.close();
        }
		return licNsapDocMst;
	}
	
}
