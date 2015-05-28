package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicPremFreqAllowARMst;
import com.gtfs.dao.interfaces.LicPremFreqAllowARMstDao;
@Repository
public class LicPremFreqAllowARMstDaoImpl implements LicPremFreqAllowARMstDao,Serializable{
	private Logger log = Logger.getLogger(LicPremFreqAllowARMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<String> findPayModeByProdId(Long prodId){
		List<String> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremFreqAllowARMst.class);
            criteria.add(Restrictions.eq("licProductMst.id", prodId));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.setProjection(Projections.distinct(Projections.property("premFreqAllow")));
            
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremFreqAllowARMstDaoImpl findPayModeByProdId Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<Object> checkForAddbRiderByAgeAndProdId(Integer age,Long prodId){
		List<Object> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremFreqAllowARMst.class);
            criteria.add(Restrictions.eq("licProductMst.id", prodId));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.add(Restrictions.le("arMinAge", age));
            criteria.add(Restrictions.ge("arMaxAge", age));
            ProjectionList proList = Projections.projectionList();
            proList.add(Projections.property("arRiderType"));
            proList.add(Projections.property("arRiderValue"));
            criteria.setProjection(Projections.distinct(proList));
            
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremFreqAllowARMstDaoImpl checkForAddbRiderByAgeAndProdId Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<LicPremFreqAllowARMst> checkForSumAssuredByProdIdTerm(Long term, Long prodId){
		List<LicPremFreqAllowARMst> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremFreqAllowARMst.class);
            criteria.add(Restrictions.eq("licProductMst.id", prodId));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("consTermFlag", "Y"), Restrictions.eq("saTerm", term)), Restrictions.and(Restrictions.eq("consTermFlag", "N"), Restrictions.eq("deleteFlag", "N"))));
          
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremFreqAllowARMstDaoImpl checkForSumAssuredByProdIdTerm Error", e);
        } finally {
            session.close();
        }
        return list;
	}
}
