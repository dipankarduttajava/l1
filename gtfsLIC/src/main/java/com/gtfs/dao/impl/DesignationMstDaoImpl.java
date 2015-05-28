package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.DesignationMst;
import com.gtfs.dao.interfaces.DesignationMstDao;

@Repository
public class DesignationMstDaoImpl implements DesignationMstDao,Serializable{
	private Logger log = Logger.getLogger(DesignationMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<DesignationMst> findAllActiveFromDesignationMst() {
        Session session = null;
        List<DesignationMst> list = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM " + DesignationMst.class.getName() + " WHERE deleteFlag = :deleteFlag ORDER BY designation");
            query.setParameter("deleteFlag", "N");
            list = query.list();
        } catch (Exception e) {
        	log.info("DesignationMstDaoImpl findAllActiveFromDesignationMst Error", e);
        } finally {
            session.close();
        }
        return list;
    }
	
	public DesignationMst findById(Long designationId){
		Session session = null;
        DesignationMst designationMst = null;
        try {
            session = sessionFactory.openSession();
            designationMst = (DesignationMst) session.get(DesignationMst.class, designationId);
        } catch (Exception e) {
        	log.info("DesignationMstDaoImpl findById Error", e);
        } finally {
            session.close();
        }
        return designationMst;
	}
}
