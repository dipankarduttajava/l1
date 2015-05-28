package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicTabPremMst;
import com.gtfs.dao.interfaces.LicTabPremMstDao;
@Repository
public class LicTabPremMstDaoImpl implements LicTabPremMstDao, Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Long> findTermsByAgeProdId(Integer age,Long productId){
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicTabPremMst.class,"ltpm");
            criteria.createAlias("ltpm.licProductMst", "lpm");
            criteria.add(Restrictions.eq("ltpm.age", age));
            criteria.add(Restrictions.eq("ltpm.licProductMst.id", productId));
            criteria.add(Restrictions.eq("ltpm.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
            criteria.setProjection(Projections.distinct(Projections.property("ltpm.policyTerm")));	
            
            list = criteria.list();
            
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<Integer> findMinAgeByProdId(Long productId){
		List<Integer> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicTabPremMst.class,"ltpm");
            criteria.add(Restrictions.eq("ltpm.licProductMst.id", productId));
            criteria.add(Restrictions.eq("ltpm.deleteFlag", "N"));
            criteria.setProjection(Projections.distinct(Projections.min("ltpm.age")));	
            
            list = criteria.list();
            
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<LicTabPremMst> findLicTabPremMstByProdIdAgeTerm(Long prodId,Integer age,Long term){
		List<LicTabPremMst> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicTabPremMst.class,"ltpm");
            criteria.add(Restrictions.eq("ltpm.licProductMst.id", prodId));
            criteria.add(Restrictions.eq("ltpm.age", age));
            criteria.add(Restrictions.eq("ltpm.policyTerm", term));
            criteria.add(Restrictions.eq("ltpm.deleteFlag", "N"));
            
            list = criteria.list();
            
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<Long> findPremiumPayingTermByPolicyTerm(Long policyTerm, Integer age, Long productId) {
        Session session = null;
        List<Long> list = null;
        try {
            session = sessionFactory.openSession();            
            Query query = session.createQuery("SELECT premiumPayingTerm "
                    + "FROM LicTabPremMst "
                    + "WHERE policyTerm = :policyTerm "
                    + "AND age = :age "
                    + "AND licProductMst.id = :productId "
                    + "AND deleteFlag = :deleteFlag");
            query.setParameter("policyTerm", policyTerm);
            query.setParameter("age", age);
            query.setParameter("productId", productId);
            query.setParameter("deleteFlag", "N");
            list = query.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

}
