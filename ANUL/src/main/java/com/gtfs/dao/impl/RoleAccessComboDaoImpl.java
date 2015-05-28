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

import com.gtfs.dao.RoleAccessComboDao;
import com.gtfs.dto.AccessMstDto;
import com.gtfs.dto.RoleAccessComboDto;
import com.gtfs.dto.UserMstDto;
import com.gtfs.pojo.AccessMst;
import com.gtfs.pojo.RoleAccessCombo;

@Repository
public class RoleAccessComboDaoImpl implements RoleAccessComboDao, Serializable {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RoleAccessComboDto> findAll() {
		Session session = null;
        List<RoleAccessComboDto> list = null;
        try {            
        	session = sessionFactory.openSession();            
            SQLQuery query = (SQLQuery) session.createSQLQuery(
            		"SELECT "
            		+ "RAC.ID as id, "
            		+ "RAC.ROLE_ID as roleId, "
            		+ "RAC.ACCESS_ID as accessId, "
            		+ "RAC.CREATED_BY as createdBy, "
            		+ "RAC.MODIFIED_BY as modifiedBy, "
            		+ "RAC.DELETED_BY as deletedBy, "
            		+ "RAC.CREATED_DATE as createdDate, "
            		+ "RAC.MODIFIED_DATE as modifiedDate, "
            		+ "RAC.DELETED_DATE as deletedDate, "
            		+ "RAC.DELETE_FLAG as deleteFlag "
            		
            		+ "FROM "
            		+ "ROLE_ACCESS_COMBO RAC "
            		
            		+ "WHERE "
            		+ "RAC.DELETE_FLAG = 'N'");
            
            query.addScalar("id", LongType.INSTANCE);
            query.addScalar("roleId", LongType.INSTANCE);
            query.addScalar("accessId", LongType.INSTANCE);
            query.addScalar("createdBy", LongType.INSTANCE);
            query.addScalar("modifiedBy", LongType.INSTANCE);
            query.addScalar("deletedBy", LongType.INSTANCE);
            query.addScalar("createdDate", DateType.INSTANCE);
            query.addScalar("modifiedDate", DateType.INSTANCE);
            query.addScalar("deletedDate", DateType.INSTANCE);
            query.addScalar("deleteFlag", StringType.INSTANCE);            
            query.setResultTransformer(Transformers.aliasToBean(RoleAccessComboDto.class));
            
            list = query.list();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public RoleAccessCombo findById(Long id) {
		RoleAccessCombo roleAccessCombo = null;
	 	Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        roleAccessCombo = (RoleAccessCombo) session.get(RoleAccessCombo.class, id);	            
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return roleAccessCombo;
	}

}
