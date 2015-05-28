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

import com.gtfs.dao.AccessMstDao;
import com.gtfs.dto.AccessMstDto;
import com.gtfs.pojo.AccessMst;

@Repository
public class AccessMstDaoImpl implements Serializable, AccessMstDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AccessMstDto> findAll() {
		Session session = null;
        List<AccessMstDto> list = null;
        try {
            session = sessionFactory.openSession();            
            SQLQuery query = (SQLQuery) session.createSQLQuery(
            		"SELECT "
            		+ "AM.ID as id, "
            		+ "AM.ACCESS_NAME as accessName, "
            		+ "AM.CREATED_BY as createdBy, "
            		+ "AM.MODIFIED_BY as modifiedBy, "
            		+ "AM.DELETED_BY as deletedBy, "
            		+ "AM.CREATED_DATE as createdDate, "
            		+ "AM.MODIFIED_DATE as modifiedDate, "
            		+ "AM.DELETED_DATE as deletedDate, "
            		+ "AM.DELETE_FLAG as deleteFlag "
            		
            		+ "FROM "
            		+ "ACCESS_MST AM "
            		
            		+ "WHERE "
            		+ "AM.DELETE_FLAG = 'N'");
            
            query.addScalar("id", LongType.INSTANCE);
            query.addScalar("accessName", StringType.INSTANCE);
            query.addScalar("createdBy", LongType.INSTANCE);
            query.addScalar("modifiedBy", LongType.INSTANCE);
            query.addScalar("deletedBy", LongType.INSTANCE);
            query.addScalar("createdDate", DateType.INSTANCE);
            query.addScalar("modifiedDate", DateType.INSTANCE);
            query.addScalar("deletedDate", DateType.INSTANCE);
            query.addScalar("deleteFlag", StringType.INSTANCE);
            query.setResultTransformer(Transformers.aliasToBean(AccessMstDto.class));
            
            list = query.list();
            
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public AccessMst findById(Long id) {
		AccessMst accessMst = null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        accessMst = (AccessMst) session.get(AccessMst.class, id);	            
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return accessMst;
	}
}
