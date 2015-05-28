package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.RoleAccessRlns;
import com.gtfs.dao.interfaces.RoleAccessRlnsDao;

@Repository
public class RoleAccessRlnsDaoImpl implements RoleAccessRlnsDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<RoleAccessRlns> findActiveRoleAccessRlnsByAccessId(Long accessId) {
        Session session = null;
        List<RoleAccessRlns> list = null;
        try {
            session = sessionFactory.openSession();
            
            Query query = session.createQuery("from RoleAccessRlns where accessList.accessId=:accessId and activeFlag='Y'");
            query.setParameter("accessId",accessId);
            list = query.list();
            
        } catch (RuntimeException re) {
               re.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    } 
	
	public Long insertIntoRoleAccessRlns(RoleAccessRlns roleAccessRlns){
		Long id=0l;
		Session session = null;
		Transaction tx = null;
		try{
				 session=sessionFactory.openSession();
				 tx=session.beginTransaction();
				 id=(Long) session.save(roleAccessRlns);
				 tx.commit();
			}catch (RuntimeException re) {
	            if(tx!=null) tx.rollback();
	     } finally {
	         session.close();
	     }
		return id;
	}
	
	public Integer deleteRoleAccessRlnsByRoleIdAndAccessId(Long roleId,Long accessId){
		Integer row=0;
		Session session = null;
		Transaction tx=null;
		try{
			 session = sessionFactory.openSession();
			 tx=session.beginTransaction();
			 Query query=session.createQuery("update RoleAccessRlns set activeFlag=:activeFlag where roleMst.roleId=:roleId and accessList.accessId=:accessId");
			 query.setParameter("activeFlag", "N");
			 query.setParameter("roleId", roleId);
			 query.setParameter("accessId",accessId);
			 row=query.executeUpdate();
			 tx.commit();
		}catch (RuntimeException re) {
           if(tx!=null) tx.rollback();
           row=0;
    } finally {
        session.close();
    }
	return row;
	}
}
