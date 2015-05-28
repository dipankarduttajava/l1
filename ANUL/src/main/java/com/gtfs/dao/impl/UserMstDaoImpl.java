package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.dao.UserMstDao;
import com.gtfs.dto.UserMstDto;
import com.gtfs.pojo.UserMst;

@Repository
public class UserMstDaoImpl implements UserMstDao, Serializable {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserMstDto> findAll() {
		Session session = null;
        List<UserMstDto> list = null;
        try {
            session = sessionFactory.openSession();            
            SQLQuery query = (SQLQuery) session.createSQLQuery(
            		"SELECT "
            		+ "UM.ID as id, "
            		+ "UM.USER_NAME as userName, "
            		+ "UM.USER_TYPE as userType, "
            		+ "UM.LOGIN_ID as loginId, "
            		+ "UM.PASSWORD as password, "
            		+ "UM.CREATED_BY as createdBy, "
            		+ "UM.MODIFIED_BY as modifiedBy, "
            		+ "UM.DELETED_BY as deletedBy, "
            		+ "UM.CREATED_DATE as createdDate, "
            		+ "UM.MODIFIED_DATE as modifiedDate, "
            		+ "UM.DELETED_DATE as deletedDate, "
            		+ "UM.DELETE_FLAG as deleteFlag "
            		
            		+ "FROM "
            		+ "USER_MST UM "
            		
            		+ "WHERE "
            		+ "UM.DELETE_FLAG = 'N'");
            
            query.addScalar("id", LongType.INSTANCE);
            query.addScalar("userName", StringType.INSTANCE);
            query.addScalar("userType", StringType.INSTANCE);
            query.addScalar("loginId", StringType.INSTANCE);
            query.addScalar("password", StringType.INSTANCE);
            query.addScalar("createdBy", LongType.INSTANCE);
            query.addScalar("modifiedBy", LongType.INSTANCE);
            query.addScalar("deletedBy", LongType.INSTANCE);
            query.addScalar("createdDate", DateType.INSTANCE);
            query.addScalar("modifiedDate", DateType.INSTANCE);
            query.addScalar("deletedDate", DateType.INSTANCE);
            query.addScalar("deleteFlag", StringType.INSTANCE);            
            query.setResultTransformer(Transformers.aliasToBean(UserMstDto.class));
            
            list = query.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public UserMst findById(Long id) {
		UserMst userMst = null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        userMst = (UserMst) session.get(UserMst.class, id);	            
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return userMst;
	}

	@Override
	public Boolean saveOrUpdate(UserMst userMst) {
		Boolean status = false;
	 	Session session = null;
	 	Transaction tx = null;
	    try {
	        session = sessionFactory.openSession();
	        tx = session.beginTransaction();	        
	        session.saveOrUpdate(userMst);	
	        tx.commit();
	        status = true;
	    } catch (Exception e) {
	    	if(tx!=null) tx.rollback();
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return status;
	}

}
