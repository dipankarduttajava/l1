package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.UserLoginDtls;
import com.gtfs.dao.interfaces.UserLoginDtlsDao;

@Repository
public class UserLoginDtlsDaoImpl implements UserLoginDtlsDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;

	public List<Object> findMaximumLoginTimeOfUser(Long UserId) {
        Session session = null;
        List<Object> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria crit = session.createCriteria(UserLoginDtls.class);
            crit.setProjection(Projections.max("loginTime"));
            crit.add(Restrictions.eq("userid", UserId));
            list = crit.list();
        } catch (RuntimeException re) {
               re.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
	
	 public Long insert(UserLoginDtls userLoginDtls) {
	        Session session = null;
	        Transaction tx = null;
	        Long id = null;
	        try {

	            session = sessionFactory.openSession();
	            tx = session.beginTransaction();
	            id = (Long) session.save(userLoginDtls);
	            tx.commit();
	        } catch (RuntimeException re) {
	            if (tx != null) {
	                tx.rollback();
	            }
	        } finally {
	            session.close();
	        }
	        return id;
	    }
	 
	 
	 public Long save(UserLoginDtls userLoginDtls) {
		 	Long id=0l;
	        Session session = null;
	        Transaction tx = null;
	        try {

	            session = sessionFactory.openSession();
	            tx = session.beginTransaction();
	            id = (Long) session.save(userLoginDtls);
	            tx.commit();
	        } catch (RuntimeException re) {
	            if (tx != null) {
	                tx.rollback();
	            }
	        } finally {
	            session.close();
	        }
	        return id;
	    }
}
