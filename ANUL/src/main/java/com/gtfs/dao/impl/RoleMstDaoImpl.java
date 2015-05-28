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

import com.gtfs.dao.RoleMstDao;
import com.gtfs.dto.AccessMstDto;
import com.gtfs.dto.RoleMstDto;
import com.gtfs.pojo.RoleAccessCombo;
import com.gtfs.pojo.RoleMst;

@Repository
public class RoleMstDaoImpl implements RoleMstDao, Serializable {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RoleMstDto> findAll() {
		Session session = null;
        List<RoleMstDto> list = null;
        try {
            session = sessionFactory.openSession();
            SQLQuery query = (SQLQuery) session.createSQLQuery(
            		"SELECT "
            		+ "RM.ID as id, "
            		+ "RM.ROLE_NAME as roleName, "
            		+ "RM.CREATED_BY as createdBy, "
            		+ "RM.MODIFIED_BY as modifiedBy, "
            		+ "RM.DELETED_BY as deletedBy, "
            		+ "RM.CREATED_DATE as createdDate, "
            		+ "RM.MODIFIED_DATE as modifiedDate, "
            		+ "RM.DELETED_DATE as deletedDate, "
            		+ "RM.DELETE_FLAG as deleteFlag "
            		
            		+ "FROM "
            		+ "ROLE_MST RM "
            		
            		+ "WHERE "
            		+ "RM.DELETE_FLAG = 'N'");
            
            query.addScalar("id", LongType.INSTANCE);
            query.addScalar("roleName", StringType.INSTANCE);
            query.addScalar("createdBy", LongType.INSTANCE);
            query.addScalar("modifiedBy", LongType.INSTANCE);
            query.addScalar("deletedBy", LongType.INSTANCE);
            query.addScalar("createdDate", DateType.INSTANCE);
            query.addScalar("modifiedDate", DateType.INSTANCE);
            query.addScalar("deletedDate", DateType.INSTANCE);
            query.addScalar("deleteFlag", StringType.INSTANCE);            
            query.setResultTransformer(Transformers.aliasToBean(RoleMstDto.class));
            
            list = query.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public RoleMst findById(Long id) {
		RoleMst roleMst = null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        roleMst = (RoleMst) session.get(RoleMst.class, id);	            
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return roleMst;
	}
}
