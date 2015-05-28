package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.UserRoleRlns;
import com.gtfs.dao.interfaces.UserRoleRlnsDao;

@Repository
public class UserRoleRlnsDaoImpl implements UserRoleRlnsDao,Serializable{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<UserRoleRlns> findActiveUserRoleByUserId(Long userId) {
        Session session = null;
        List<UserRoleRlns> list = null;
        try {
        	session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(UserRoleRlns.class,"urr");
            criteria.createAlias("urr.roleMst", "rm");
            criteria.createAlias("urr.userMst", "um");
            criteria.add(Restrictions.eq("urr.activeFlag", "Y"));
            criteria.add(Restrictions.eq("rm.activeFlag", "Y"));
            criteria.add(Restrictions.eq("um.userid", userId));
            list = criteria.list();
            
        } catch (RuntimeException re) {
        	System.out.println("Dao Error");
               re.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
	
	public Long insertIntoUserRoleRlns(UserRoleRlns userRoleRlns){
		Long id=0l;
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			id = (Long) session.save(userRoleRlns);
			tx.commit();
			}catch (RuntimeException re) {
				System.out.println("Dao Error");
				re.printStackTrace();
	            if(tx!=null) tx.rollback();
	     } finally {
	         session.close();
	     }
		return id;
	}
	
	public Integer deleteFromUserRoleRlnsByRoleIdAndUserId(Long roleId, Long userid, Long loginUserId){
		Integer row=0;
		Session session = null;
		Transaction tx=null;
		try{
			 session = sessionFactory.openSession();
			 tx=session.beginTransaction();
			 Query query=session.createQuery("UPDATE UserRoleRlns SET activeFlag=:activeFlag,userId=:loginUserId,dateTime=:dateTime WHERE roleMst.roleId=:roleId and userMst.userid=:userid");
			 query.setParameter("activeFlag", "N");
			 query.setParameter("loginUserId", loginUserId);
			 query.setParameter("dateTime", new Date());
			 query.setParameter("roleId", roleId);
			 query.setParameter("userid",userid);
			 row=query.executeUpdate();
			 tx.commit();
		}catch (RuntimeException re) {
			System.out.println("Dao Error");
			re.printStackTrace();
           if(tx != null){
        	   tx.rollback();
        	   System.out.println("ROLLBACK");
           }
           row=0;
    } finally {
        session.close();
    }
	return row;
	}
}
