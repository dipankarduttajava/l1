package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicBocDetailEntryDao;

@Repository
public class LicBocDetailEntryDaoImpl implements LicBocDetailEntryDao,Serializable{
	private Logger log = Logger.getLogger(LicBocDetailEntryDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<LicOblApplicationMst> findApplicationForBocEntry(Long premListNo, BranchMst branchMst){
		List<LicOblApplicationMst> list =null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
            criteria.createAlias("loam.licPremApplMappings","lpam");
            criteria.createAlias("loam.licProposerDtls","lpd");
            criteria.createAlias("loam.licInsuredDtls","lid");
            criteria.createAlias("loam.branchMst", "bm");
            criteria.createAlias("loam.oblHubMst", "ohm");            
            criteria.createAlias("loam.picBranchMstId", "pbmi");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            
            criteria.add(Restrictions.eq("lpam.licPremiumListDtls.id", premListNo));
            criteria.add(Restrictions.eq("lpam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.eq("lbt.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
            
            criteria.addOrder(Order.desc("loam.id"));
            
            list=criteria.list();
        } catch (Exception e) {
        	log.info("LicBocDetailEntryDaoImpl findApplicationForBocEntry Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public Boolean saveBoc(List<LicOblApplicationMst>  applicationMsts){
		Session session = null;
		Transaction tx = null;
		Boolean status = false;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(LicOblApplicationMst obj : applicationMsts){
            	session.update(obj);
            }
            tx.commit();
            status=true;
        } catch (Exception e) {
        	log.info("LicBocDetailEntryDaoImpl saveBoc Error", e);
        	if(tx != null) tx.rollback();
        	status = false;
        } finally {
            session.close();
        }
        return status;
	}
	
	public Boolean saveBocForReq(List<LicRequirementDtls>  licRequirementDtls){
		Session session = null;
		Transaction tx = null;
		Boolean status = false;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(LicRequirementDtls obj: licRequirementDtls){
            	session.update(obj);
            }
            tx.commit();
            status = true;
        } catch (Exception e) {
        	log.info("LicBocDetailEntryDaoImpl saveBocForReq Error", e);
        	if(tx != null) tx.rollback();
        	status = false;
        } finally {
            session.close();
        }
        return status;
	}

	@Override
	public List<LicRequirementDtls> findRequirmentDtlsForBocEntry(Long premListNo, BranchMst branchMst) {
		List<LicRequirementDtls> list = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
            criteria.createAlias("lrd.licOblApplicationMst","loam");
            criteria.createAlias("lrd.licPremReqMappings","lprm");
            criteria.createAlias("loam.licProposerDtls","lpd");
            criteria.createAlias("loam.licInsuredDtls","lid");
            criteria.createAlias("loam.branchMst", "bm");
            criteria.createAlias("loam.picBranchMstId", "pbmi");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            
            criteria.add(Restrictions.eq("lprm.licPremiumListDtls.id", premListNo));
            criteria.add(Restrictions.eq("lprm.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            
            criteria.addOrder(Order.desc("lrd.id"));
            
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicBocDetailEntryDaoImpl findRequirmentDtlsForBocEntry Error", e);
        } finally {
            session.close();
        }
		return list;
	}

	@Override
	public List<LicOblApplicationMst> findbocReport(Date businessFromDate, Date businessToDate, List<LicHubMst> findHubForProcess) {
			List<LicOblApplicationMst> list = null;
			Session session = null;
	        try {
	            session = sessionFactory.openSession();
	            Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
	            criteria.createAlias("loam.licPremApplMappings","lpam");
	            criteria.createAlias("loam.licProposerDtls","lpd");
	            criteria.createAlias("loam.licInsuredDtls","lid");
	            criteria.createAlias("loam.branchMst", "bm");
	            criteria.createAlias("loam.oblHubMst", "ohm");            
	            criteria.createAlias("loam.picBranchMstId", "pbmi");
	            criteria.createAlias("loam.licProductValueMst", "lpvm");
	            criteria.createAlias("loam.licBusinessTxn", "lbt");
	            criteria.createAlias("lbt.licPaymentMst", "lpm");
	            criteria.createAlias("loam.licApplBocMappings", "labm");
	            
	            criteria.add(Restrictions.eq("lpam.deleteFlag", "N"));
	            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
	            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
	            criteria.add(Restrictions.eq("lbt.deleteFlag", "N"));
	            criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
	            criteria.add(Restrictions.isNotNull("labm.id"));
	            criteria.add(Restrictions.ge("loam.businessDate", businessFromDate));
	            criteria.add(Restrictions.le("loam.businessDate", businessToDate));
	            criteria.add(Restrictions.in("loam.oblHubMst", findHubForProcess));
	            
	            criteria.addOrder(Order.desc("loam.id"));
	            
	            list=criteria.list();
		}catch(Exception e){
			log.info("LicBocDetailEntryDaoImpl findbocReport Error", e);
		}finally{
			session.close();
		}
		return list;
	}
}
