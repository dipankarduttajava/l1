package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicMarkingToQcDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicMarkingToQcDtlsDao;

@Repository
public class LicMarkingToQcDtlsDaoImpl implements LicMarkingToQcDtlsDao,Serializable{
	private Logger log = Logger.getLogger(LicMarkingToQcDtlsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Boolean saveForConsolidateMarking(LicMarkingToQcDtls licMarkingToQcDtls){
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 session.save(licMarkingToQcDtls);
			 tx.commit();
			 status = true;
		}catch(Exception e){
			status = false;
			log.info("LicMarkingToQcDtlsDaoImpl saveForConsolidateMarking Error", e);
		}finally {
            session.close();
        }
		return status;
	}
	
	public List<LicOblApplicationMst> findIndividualMarkingDtlsByDate(String applicationNo,Date fromDate,Date toDate, List<LicHubMst> licHubMsts, BranchMst branchMst, Long branchId){
		Session session = null;
		List<LicOblApplicationMst> list=null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
			 criteria.createAlias("loam.licMarkingToQcDtls", "lmtqd");
			 criteria.createAlias("loam.licInsuredDtls", "lid");
			 criteria.createAlias("loam.licProposerDtls", "lpd");
			 criteria.createAlias("loam.branchMst", "bm");
			 criteria.createAlias("loam.licProductValueMst", "lpvm");
			 criteria.createAlias("lpvm.licProductMst", "lpm");
			 criteria.createAlias("loam.agentMst", "am");
			 
			 if(branchId != null){
				 criteria.add(Restrictions.eq("bm.branchId", branchId));
			 }
			 
			 if(applicationNo != null && applicationNo != ""){
				 criteria.add(Restrictions.eq("loam.oblApplNo", applicationNo));
			 }
			 
			 criteria.add(Restrictions.eq("lmtqd.consldMrkFlag", "Y"));
			 criteria.add(Restrictions.isNull("lmtqd.indMrkFlag"));
			 criteria.add(Restrictions.ge("loam.businessDate", fromDate));
			 criteria.add(Restrictions.le("loam.businessDate", toDate));
			 criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
	         criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			 criteria.add(Restrictions.in("loam.oblHubMst", licHubMsts));
			 list = criteria.list();
		}catch(Exception e){
			log.info("LicMarkingToQcDtlsDaoImpl findIndividualMArkingDtlsByDate Error", e);
		}finally {
           session.close();
       }
		return list;
	}
	
	public Boolean updateForIndividualMarking(List<LicOblApplicationMst> licHubMsts){
		Session session = null;
		Transaction tx=null;
		Boolean status = false;
		try{
			 session = sessionFactory.openSession();
			 tx=session.beginTransaction();
			 for(LicOblApplicationMst obj:licHubMsts){
				 session.update(obj);
			 }
			 tx.commit();
			 status = true;
		}catch(Exception e){
			if(tx!=null) tx.rollback();
			System.out.println(e);
			log.info("LicMarkingToQcDtlsDaoImpl updateForIndividualMarking Error", e);
		}finally {
          session.close();
          
      }
		return status;
	}
	
	public List<LicRequirementDtls> findIndividualMArkingDtlsByDateForReq(Date fromDate,Date toDate, List<LicHubMst> licHubMsts, BranchMst branchMst){
		Session session = null;
		List<LicRequirementDtls> list = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			 criteria.createAlias("lrd.licMarkingToQcDtls", "lmtqd");
			 criteria.createAlias("lrd.licOblApplicationMst", "loam");
			 criteria.createAlias("lrd.licBranchReqRcvMsts","lbrrm");
			 criteria.createAlias("loam.licInsuredDtls", "lid");
			 criteria.createAlias("loam.licProposerDtls", "lpd");
			 criteria.createAlias("loam.branchMst", "bm");
			 criteria.createAlias("loam.licProductValueMst", "lpvm");
			 criteria.createAlias("lpvm.licProductMst", "lpm");
			 criteria.add(Restrictions.eq("lmtqd.consldMrkFlag", "Y"));
			 criteria.add(Restrictions.isNull("lmtqd.indMrkFlag"));
			 criteria.add(Restrictions.ge("loam.businessDate", fromDate));
			 criteria.add(Restrictions.le("loam.businessDate", toDate));
			 criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
	         criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			 //criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			 criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));

			 list = criteria.list();
		}catch(Exception e){
			log.info("LicMarkingToQcDtlsDaoImpl findIndividualMArkingDtlsByDateForReq Error", e);
		}finally {
           session.close();
       }
		return list;
	}

	@Override
	public Boolean updateForIndividualMarkingForReq(List<LicRequirementDtls> licHubMsts) {
			Session session = null;
			Transaction tx=null;
			Boolean status = false;
			try{
				 session = sessionFactory.openSession();
				 tx=session.beginTransaction();
				 for(LicRequirementDtls obj:licHubMsts){
					 session.update(obj);
				 }
				 tx.commit();
				 status = true;
			}catch(Exception e){
				if(tx!=null) tx.rollback();
				log.info("LicMarkingToQcDtlsDaoImpl updateForIndividualMarkingForReq Error", e);
			}finally {
	          session.close();
	          
	      }
			return status;
		}

	@Override
	public Boolean updateForIndividualMarkingForRenewal(List<LicPolicyDtls> licPolicyDtlsList) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;
		try{
			 session = sessionFactory.openSession();
			 tx=session.beginTransaction();
			 for(LicPolicyDtls obj : licPolicyDtlsList){
				 session.merge(obj);
			 }
			 tx.commit();
			 status = true;
		}catch(Exception e){
			if(tx!=null) tx.rollback();
			log.info("LicMarkingToQcDtlsDaoImpl updateForIndividualMarkingForRenewal Error", e);
		}finally {
          session.close();
      }
		return status;
	}

	@Override
	public Boolean saveForConsolidateMarkingForPos(LicMarkingToQcDtls licMarkingToQcDtls) {
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 session.save(licMarkingToQcDtls);
			 tx.commit();
			 status = true;
		}catch(Exception e){
			status = false;
			log.info("LicMarkingToQcDtlsDaoImpl saveForConsolidateMarkingForPos Error", e);
		}finally {
            session.close();
        }
		return status;
	}

	@Override
	public Boolean updateForIndividualMarkingForPos(List<LicRequirementDtls> licRequirementDtlsList) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;
		try{
			 session = sessionFactory.openSession();
			 tx=session.beginTransaction();
			 for(LicRequirementDtls obj : licRequirementDtlsList){
				 session.merge(obj);
			 }
			 tx.commit();
			 status = true;
		}catch(Exception e){
			if(tx!=null) tx.rollback();
			log.info("LicMarkingToQcDtlsDaoImpl updateForIndividualMarkingForPos Error", e);
		}finally {
          session.close();
      }
		return status;
	}

	@Override
	public List<LicOblApplicationMst> findIndividualMarkingDtlsReport(Date businessFromDate, Date businessToDate, List<LicHubMst> findHubForProcess, Long branchId) {
		Session session = null;
		List<LicOblApplicationMst> list=null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
			 criteria.createAlias("loam.licMarkingToQcDtls", "lmtqd");
			 criteria.createAlias("loam.licInsuredDtls", "lid");
			 criteria.createAlias("loam.licProposerDtls", "lpd");
			 criteria.createAlias("loam.branchMst", "bm");
			 criteria.createAlias("loam.licProductValueMst", "lpvm");
			 criteria.createAlias("lpvm.licProductMst", "lpm");
			 criteria.createAlias("loam.agentMst", "am");
			 
			 if(branchId != null){
				 criteria.add(Restrictions.eq("bm.branchId", branchId));
			 }
			 
			 criteria.add(Restrictions.eq("lmtqd.consldMrkFlag", "Y"));
			 criteria.add(Restrictions.isNotNull("lmtqd.indMrkFlag"));
			 criteria.add(Restrictions.ge("loam.businessDate", businessFromDate));
			 criteria.add(Restrictions.le("loam.businessDate", businessToDate));
			 criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
	         criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			 criteria.add(Restrictions.in("loam.oblHubMst", findHubForProcess));
			 //criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			 list = criteria.list();
		}catch(Exception e){
			log.info("LicMarkingToQcDtlsDaoImpl findIndividualMArkingDtlsByDate Error", e);
		}finally {
           session.close();
       }
		return list;
	}
	
}
