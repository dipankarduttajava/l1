package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.action.HubPicLicPodForReqAction;
import com.gtfs.bean.AccessList;
import com.gtfs.dao.interfaces.AccessListDao;

@Repository
public class AccessListDaoImpl implements AccessListDao, Serializable{
	private Logger log = Logger.getLogger(AccessListDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Boolean saveAccessList(List<AccessList> accessList){
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 for(AccessList obj:accessList){
				 session.saveOrUpdate(obj);
			 }
			 tx.commit();
			 status = true;
		}catch(Exception e){
			 status = false;
			 log.info("AccessListDaoImpl saveAccessList Error", e);
		}finally {
            session.close();
        }
		return status;
	}
	
	
	public AccessList findById(Long accessId){
		AccessList accessList=null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        accessList = (AccessList) session.get(AccessList.class, accessId);	            
	    } catch (Exception e) {
	    	log.info("AccessListDaoImpl findById Error", e);
	    } finally {
	        session.close();
	    }
	    return accessList;
	}
	
	public List<Object> findAccessListByUserId(Long userId) {
        Session session = null;
        List<Object> list = null;
        try {
            session = sessionFactory.openSession();            
            Query query = session.createQuery("SELECT distinct al.accessId,al.accessName "
                    + "FROM AccessList al, UserRoleRlns urr, RoleAccessRlns rar, RoleMst rm "
                    + "WHERE al.accessId = rar.accessList.accessId "
                    + "and urr.roleMst.roleId = rm.roleId "
                    + "and urr.roleMst.roleId = rar.roleMst.roleId "
                    + "and rar.roleMst.roleId = rm.roleId "
                    + "and al.deleteFlag = 'N' "
                    + "and rar.activeFlag = 'Y' "
                    + "and urr.activeFlag = 'Y' "
                    + "and rm.activeFlag = 'Y' "
                    + "and urr.userMst.userid = :userId");
            query.setParameter("userId", userId);
            list = query.list();
            
        } catch (Exception e) {
        	log.info("AccessListDaoImpl findAccessListByUserId Error", e);
        } finally {
            session.close();
        }
        return list;
    }
	
	public List<AccessList> findAll(){
		Session session = null;
        List<AccessList> list = null;
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(AccessList.class).list();
        } catch (Exception e) {
        	log.info("AccessListDaoImpl findAll Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<Object> findAllActiveAccessList(){
		Session session = null;
        List<Object> list = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery(
            		"select al.accessId,al.accessName,um1.userName,um2.userName,al.createdDate,al.modifiedDate,um1.userid,um2.userid "
            		+ "from AccessList al,"
            		+ "UserMst um1,"
            		+ "UserMst um2 "
            		+ "where al.createdBy = um1.userid "
            		+ "and al.modifiedBy = um2.userid "
            		+ "and al.deleteFlag=:deleteFlag "
            		+ "order by al.accessId desc");
            query.setParameter("deleteFlag", "N");
           // query.setCacheable(true);
            list = query.list();
            
        } catch (Exception e) {
        	log.info("AccessListDaoImpl findAllActiveAccessList Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public Boolean deleteAccessList(Long accessId, Long userId){
		Boolean status=false;
		Session session = null;
	     Transaction tx = null;
	      try{
	    	 session = sessionFactory.openSession();
	         tx = session.beginTransaction();
	         Query query=session.createQuery("update AccessList set deleteFlag=:deleteFlag,deletedBy=:deletedBy,deletedDate=:deletedDate where accessId=:accessId"); 
	         query.setParameter("deleteFlag", "Y");
	         query.setParameter("deletedBy", userId);
	         query.setParameter("accessId", accessId);
	         query.setParameter("deletedDate", new Date());
	         int rows = query.executeUpdate();
	         if(rows>0){
	        	 tx.commit(); 
	        	 status=true;
	         }else{
	        	 tx.rollback();
	        	 status=false;
	         }	        
	      }catch (Exception e) {
	         if (tx!=null) tx.rollback();
	         status=false;
	         log.info("AccessListDaoImpl deleteAccessList Error", e);
	      }finally {
	         session.close(); 
	      }
	      return status;
	}
	
	
	public List<Object> findAllActiveAccessListByAccessName(String accessName){
		Session session = null;
        List<Object> list = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery(
            		"select al.accessId,al.accessName,um1.userName,um2.userName,al.createdDate,al.modifiedDate,um1.userid,um2.userid "
            		+ "from AccessList al,"
            		+ "UserMst um1,"
            		+ "UserMst um2 "
            		+ "where al.createdBy = um1.userid "
            		+ "and al.modifiedBy = um2.userid "
            		+ "and al.deleteFlag=:deleteFlag "
            		+ "and  upper(al.accessName) like  upper(:accessName) "
            		+ "order by al.accessId desc");
            query.setParameter("deleteFlag", "N");
            query.setParameter("accessName", "%"+accessName+"%");
           // query.setCacheable(true);
            list = query.list();
        } catch (Exception e) {
        	log.info("AccessListDaoImpl findAllActiveAccessListByAccessName Error", e);
        } finally {
            session.close();
        }
        return list;
	}
}