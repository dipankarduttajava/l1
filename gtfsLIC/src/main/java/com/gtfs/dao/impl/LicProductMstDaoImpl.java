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

import com.gtfs.bean.LicProductMst;
import com.gtfs.dao.interfaces.LicProductMstDao;

@Repository
public class LicProductMstDaoImpl implements LicProductMstDao,Serializable{
	private Logger log = Logger.getLogger(LicProductMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicProductMst> findActiveLicProductMst(){
		 	Session session = null;
	        List<LicProductMst> list = null;
	        try {
	            session = sessionFactory.openSession();
	            Criteria criteria = session.createCriteria(LicProductMst.class);
	            criteria.add(Restrictions.eq("deleteFlag","N"));
	            list=criteria.list();	            
	        } catch (Exception e) {
	        	log.info("LicProductMstDaoImpl findActiveLicProductMst Error", e);
	        } finally {
	            session.close();
	        }
	        return list;
	}
	
	public List<LicProductMst> findActiveLicProductMstForChecklist(){
	 	Session session = null;
        List<LicProductMst> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicProductMst.class);
            criteria.add(Restrictions.eq("deleteFlag","N"));
            criteria.add(Restrictions.ge("id",101l));
            list=criteria.list();	            
        } catch (RuntimeException re) {
               re.printStackTrace();
        } finally {
            session.close();
        }
        return list;
}
	
	public LicProductMst findByProductId(Long productId){
	 	Session session = null;
        LicProductMst licProductMst = null;
        try {
            session = sessionFactory.openSession();
            licProductMst=(LicProductMst) session.get(LicProductMst.class, productId);
        } catch (RuntimeException re) {
               re.printStackTrace();
        } finally {
            session.close();
        }
        return licProductMst;
}
}
