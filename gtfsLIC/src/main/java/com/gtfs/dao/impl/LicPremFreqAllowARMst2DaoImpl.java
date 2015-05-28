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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicPremFreqAllowARMst;
import com.gtfs.bean.LicPremFreqAllowARMst2;
import com.gtfs.dao.interfaces.LicPremFreqAllowARMst2Dao;
@Repository
public class LicPremFreqAllowARMst2DaoImpl implements LicPremFreqAllowARMst2Dao,Serializable{
	private Logger log = Logger.getLogger(LicPremFreqAllowARMst2DaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<String> findDistinctCategoryFromLicPremFreqAllowARMst2() {
		List<String> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremFreqAllowARMst2.class,"lppfaam2");
            criteria.add(Restrictions.eq("lppfaam2.deleteFlag","N"));
            criteria.setProjection(Projections.distinct(Projections.property("lppfaam2.arCategory")));
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicPremFreqAllowARMst2DaoImpl findDistinctCategoryFromLicPremFreqAllowARMst2 Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<String> findDistinctPremFreqAllowByProductIdFromLicPremFreqAllowARMst2(Long productId) {
		List<String> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremFreqAllowARMst2.class,"lppfaam2");
            criteria.add(Restrictions.eq("lppfaam2.deleteFlag","N"));
            criteria.add(Restrictions.eq("lppfaam2.productId", productId));
            criteria.setProjection(Projections.distinct(Projections.property("lppfaam2.premFreqAllow")));
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicPremFreqAllowARMst2DaoImpl findDistinctPremFreqAllowByProductIdFromLicPremFreqAllowARMst2 Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<Object> checkForAddbRiderByAgeProdIdCategoryTermPptFromLicPremFreqAllowARMst2(Integer age, Long productId, String arCategory, Long policyTerm, Long premiumPayingTerm) {
		List<Object> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremFreqAllowARMst2.class);
            criteria.add(Restrictions.le("arMinAge", age));
            criteria.add(Restrictions.ge("arMaxAge", age));
            criteria.add(Restrictions.eq("productId", productId));
            criteria.add(Restrictions.eq("arCategory", arCategory));
            criteria.add(Restrictions.eq("arTerm", policyTerm));
            criteria.add(Restrictions.eq("arPpt", premiumPayingTerm));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            
            ProjectionList proList = Projections.projectionList();
            proList.add(Projections.property("arRiderType"));
            proList.add(Projections.property("arRiderValue"));
            
            criteria.setProjection(Projections.distinct(proList));            
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremFreqAllowARMst2DaoImpl checkForAddbRiderByAgeProdIdCategoryTermPptFromLicPremFreqAllowARMst2 Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<LicPremFreqAllowARMst2> checkForSumAssuredByProdIdAndTermFromLicPremFreqAllowARMst2(Long policyTerm, Long productId) {
		List<LicPremFreqAllowARMst2> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremFreqAllowARMst2.class);
            criteria.add(Restrictions.eq("productId", productId));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("consTermFlag", "Y"), Restrictions.eq("saTerm", policyTerm)), Restrictions.and(Restrictions.eq("consTermFlag", "N"), Restrictions.eq("deleteFlag", "N"))));
          
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremFreqAllowARMst2DaoImpl checkForSumAssuredByProdIdAndTermFromLicPremFreqAllowARMst2 Error", e);
        } finally {
            session.close();
        }
        return list;
	}
}
