package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchHubPicMapping;
import com.gtfs.bean.LicHubMst;
import com.gtfs.dao.interfaces.LicHubMstDao;

@Repository
public class LicHubMstDaoImpl implements LicHubMstDao,Serializable{
	private Logger log = Logger.getLogger(LicHubMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicHubMst> findActiveHubMst(){
		List<LicHubMst> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicHubMst.class);
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            list=criteria.list();            
        } catch (Exception e) {
        	log.info("LicHubMstDaoImpl findActiveHubMst Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public LicHubMst findById(Long id){
		LicHubMst licHubMst=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            licHubMst=(LicHubMst) session.get(LicHubMst.class, id);
        } catch (Exception e) {
        	log.info("LicHubMstDaoImpl findById Error", e);
        } finally {
            session.close();
        }
        return licHubMst;
	}
	
	
	public List<LicHubMst> findActiveHubMstByBranch(BranchMst branchMst){
		List<LicHubMst> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicHubMst.class,"lhm");
            criteria.createAlias("lhm.licBranchHubMaps", "lbhm");
            criteria.add(Restrictions.eq("lbhm.branchMst", branchMst));
            criteria.add(Restrictions.eq("lhm.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lbhm.deleteFlag", "N"));
            list=criteria.list();            
        } catch (Exception e) {
        	log.info("LicHubMstDaoImpl findActiveHubMstByBranch Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<Long> findActiveHubIdByBranchId(Long branchId){
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicBranchHubPicMapping.class,"lbhp");
            criteria.createAlias("lbhp.processMst", "pm");
            criteria.add(Restrictions.eq("pm.processAbbr","OBL"));
            criteria.add(Restrictions.eq("lbhp.deleteFlag", "N"));
            criteria.setProjection(Projections.property("lbhp.destinationId"));
            criteria.add(Restrictions.eq("lbhp.sourceType","BRANCH"));
            criteria.add(Restrictions.eq("lbhp.sourceId", branchId));
            criteria.add(Restrictions.eq("lbhp.destinationType","HUB"));
            list=criteria.list();
            
        } catch (Exception e) {
        	log.info("LicHubMstDaoImpl findActiveHubIdByBranchId Error", e);
        } finally {
            session.close();
        }
        return list;
	}
}
