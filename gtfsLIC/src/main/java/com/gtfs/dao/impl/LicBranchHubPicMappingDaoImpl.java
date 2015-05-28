package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicBranchHubPicMapping;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.ProcessMst;
import com.gtfs.dao.interfaces.LicBranchHubPicMappingDao;

@Repository
public class LicBranchHubPicMappingDaoImpl implements LicBranchHubPicMappingDao,Serializable{
	private Logger log = Logger.getLogger(LicBranchHubPicMappingDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Long> findHubIdForBranchIdByProcessName(Long branchId, String processName){
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicBranchHubPicMapping.class,"lbhpm");
            criteria.createAlias("lbhpm.processMst", "pm");
            criteria.setProjection(Projections.projectionList().add(Projections.property("destinationId")));
            
            criteria.add(Restrictions.eq("lbhpm.sourceType","BRANCH"));
            criteria.add(Restrictions.eq("lbhpm.destinationType","HUB"));
            criteria.add(Restrictions.eq("lbhpm.sourceId",branchId));
            criteria.add(Restrictions.eq("lbhpm.deleteFlag","N"));
            criteria.add(Restrictions.eq("pm.processAbbr",processName));
            list=criteria.list();
        } catch (Exception e) {
        	log.info("LicBranchHubPicMappingDaoImpl findHubIdForBranchIdByProcessName Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<LicBranchHubPicMapping> findSourceByIdTypeAndProcss(Long sourceId,String sourceType,ProcessMst processMst){
		List<LicBranchHubPicMapping> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicBranchHubPicMapping.class,"lbhpm");
            criteria.createAlias("lbhpm.processMst", "pm");
            criteria.add(Restrictions.eq("lbhpm.sourceType",sourceType));
            criteria.add(Restrictions.eq("lbhpm.sourceId",sourceId));
            criteria.add(Restrictions.eq("lbhpm.deleteFlag","N"));
            criteria.add(Restrictions.eq("lbhpm.processMst",processMst));
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicBranchHubPicMappingDaoImpl findSourceByIdTypeAndProcss Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<LicBranchHubPicMapping> findDestinationByIdTypeAndProcss(Long destinationId,String destinationType,ProcessMst processMst){
		List<LicBranchHubPicMapping> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicBranchHubPicMapping.class,"lbhpm");
            criteria.createAlias("lbhpm.processMst", "pm");
            criteria.add(Restrictions.eq("lbhpm.destinationType",destinationType));
            criteria.add(Restrictions.eq("lbhpm.destinationId",destinationId));
            criteria.add(Restrictions.eq("lbhpm.deleteFlag","N"));
            criteria.add(Restrictions.eq("lbhpm.processMst",processMst));
            list=criteria.list();
        } catch (Exception e) {
        	log.info("LicBranchHubPicMappingDaoImpl findDestinationByIdTypeAndProcss Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public Boolean saveForMapping(List<LicBranchHubPicMapping> list){
		Boolean status=false;
	 	Session session = null;
	 	Transaction tx=null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(LicBranchHubPicMapping obj:list){
            	session.save(obj);
            }
            tx.commit();
            status = true;
        } catch (Exception e) {
        	if(tx!=null)tx.rollback();
        	status=false;
        	log.info("LicBranchHubPicMappingDaoImpl saveForMapping Error", e);
        } finally {
            session.close();
        }
        return status;
		
	}

	@Override
	public List<Long> findHubIdForRnlDespatch(Long branchId) {
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");            
            criteria.add(Restrictions.eq("lpd.branchMst.branchId", branchId));
            criteria.add(Restrictions.eq("lpd.paidFlag","Y"));
            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
            criteria.add(Restrictions.isNull("lpd.brnhHubMapDtls"));
            criteria.add(Restrictions.eq("lpd.brnhHubDespFlag", "Y"));
            criteria.setProjection(Projections.distinct(Projections.property("lpd.oblHubMst.id")));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicBranchHubPicMappingDaoImpl findHubIdForRnlDespatch Error", e);
        } finally {
            session.close();
        }
        return list;
	}	
}
