package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.dao.UserRoleComboDao;
import com.gtfs.dto.RoleAccessComboDto;
import com.gtfs.dto.UserRoleComboDto;
import com.gtfs.pojo.UserMst;
import com.gtfs.pojo.UserRoleCombo;

@Repository
public class UserRoleComboDaoImpl implements UserRoleComboDao, Serializable {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserRoleComboDto> findAll() {
		Session session = null;
        List<UserRoleComboDto> list = null;
        try {            
        	session = sessionFactory.openSession();            
            SQLQuery query = (SQLQuery) session.createSQLQuery(
            		"SELECT "
            		+ "URC.ID as id, "
            		+ "URC.USER_ID as userId, "
            		+ "URC.ROLE_ID as roleId, "
            		+ "URC.CREATED_BY as createdBy, "
            		+ "URC.MODIFIED_BY as modifiedBy, "
            		+ "URC.DELETED_BY as deletedBy, "
            		+ "URC.CREATED_DATE as createdDate, "
            		+ "URC.MODIFIED_DATE as modifiedDate, "
            		+ "URC.DELETED_DATE as deletedDate, "
            		+ "URC.DELETE_FLAG as deleteFlag "
            		
            		+ "FROM "
            		+ "USER_ROLE_COMBO URC "
            		
            		+ "WHERE "
            		+ "URC.DELETE_FLAG = 'N'");
            
            query.addScalar("id", LongType.INSTANCE);
            query.addScalar("userId", LongType.INSTANCE);
            query.addScalar("roleId", LongType.INSTANCE);
            query.addScalar("createdBy", LongType.INSTANCE);
            query.addScalar("modifiedBy", LongType.INSTANCE);
            query.addScalar("deletedBy", LongType.INSTANCE);
            query.addScalar("createdDate", DateType.INSTANCE);
            query.addScalar("modifiedDate", DateType.INSTANCE);
            query.addScalar("deletedDate", DateType.INSTANCE);
            query.addScalar("deleteFlag", StringType.INSTANCE);            
            query.setResultTransformer(Transformers.aliasToBean(UserRoleComboDto.class));
            
            list = query.list();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public UserRoleCombo findById(Long id) {
		UserRoleCombo userRoleCombo = null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        userRoleCombo = (UserRoleCombo) session.get(UserRoleCombo.class, id);	            
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return userRoleCombo;
	}

}
