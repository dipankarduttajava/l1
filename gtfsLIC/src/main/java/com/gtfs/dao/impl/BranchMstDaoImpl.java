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

import com.gtfs.bean.BranchMst;
import com.gtfs.dao.interfaces.BranchMstDao;

@Repository
public class BranchMstDaoImpl implements BranchMstDao,Serializable{
	private Logger log = Logger.getLogger(BranchMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<BranchMst> findAllBranches(Class<BranchMst> clazz) {
        Session session = null;
        List<BranchMst> list = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM " + clazz.getName() + " WHERE activeFlag=:activeFlag ORDER BY branchName");
            query.setParameter("activeFlag", "Y");
            list = query.list();
        } catch (Exception e) {
        	log.info("BranchMstDaoImpl findAllBranches Error", e);
        } finally {
            session.close();
        }
        return list;
    }
	
	public BranchMst findById(Long id) {
        Session session = null;
        BranchMst branchMst=null;
        try {
            session = sessionFactory.openSession();
            branchMst = (BranchMst) session.get(BranchMst.class, id);
        } catch (Exception e) {
        	log.info("BranchMstDaoImpl findById Error", e);
        } finally {
            session.close();
        }
        return branchMst;
    }
}
