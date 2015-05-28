package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPremApplMapping;
import com.gtfs.bean.LicPremPolicyMapping;
import com.gtfs.bean.LicPremReqMapping;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicPremiumListDao;

@Repository
public class LicPremiumListDaoImpl implements LicPremiumListDao,Serializable{
	private Logger log = Logger.getLogger(LicPremiumListDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Long> findPremiumListForPicDelivery(List<LicHubMst> licHubMsts){
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
            criteria.createAlias("loam.licPremApplMappings", "lpam");
            criteria.createAlias("loam.licApplBocMappings", "labm");
            criteria.createAlias("loam.licMarkingToQcDtls", "lmtqd");
            
            criteria.add(Restrictions.isNull("loam.hubPicMapDtls"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.in("loam.oblHubMst", licHubMsts));
            
            criteria.setProjection(Projections.distinct(Projections.property("lpam.licPremiumListDtls.id")));
            
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListForPicDelivery Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<Long> findPremiumListForRenewalPicDelivery(List<LicHubMst> licHubMsts){
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPremPolicyMappings", "lppm");
            criteria.add(Restrictions.isNull("lpd.hubPicMapDtls"));
            criteria.setProjection(Projections.distinct(Projections.property("lppm.licPremiumListDtls.id")));
            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
            criteria.add(Restrictions.eq("lpd.hlthReqdFlag","Y"));
            criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
            criteria.add(Restrictions.or(Restrictions.ne("lpd.payMode","C"),Restrictions.ne("lpd.hlthReqdFlag","N")));
            
            criteria.add(Restrictions.in("lpd.processHubMst",licHubMsts));
            list=criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListForRenewalPicDelivery Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<Long> findPremiumListNoForBocEntry(List<LicHubMst> licHubMsts){
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
            criteria.createAlias("loam.licPremApplMappings", "lpam");
            criteria.createAlias("loam.licApplBocMappings", "labm", JoinType.LEFT_OUTER_JOIN);

            criteria.add(Restrictions.in("loam.oblHubMst", licHubMsts));
            criteria.add(Restrictions.isNull("labm.id"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            
            criteria.setProjection(Projections.distinct(Projections.property("lpam.licPremiumListDtls.id")));

            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListNoForBocEntry Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<LicOblApplicationMst> findApplicationForDeliveryListOfPic(Long premListNo, BranchMst branchMst){
		List<LicOblApplicationMst> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            criteria.createAlias("loam.licProposerDtls", "lpd");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("loam.licProductValueMst", "lipvm");
            criteria.createAlias("loam.branchMst", "bm");
            criteria.createAlias("lipvm.licProductMst", "lipm");
            criteria.createAlias("loam.licPremApplMappings", "lpam");
            criteria.createAlias("loam.picBranchMstId", "pbm");
            criteria.createAlias("loam.oblHubMst", "ohm");
           
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.isNull("loam.hubPicMapDtls"));
            criteria.add(Restrictions.eq("lpam.licPremiumListDtls.id", premListNo));
            list=criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findApplicationForDeliveryListOfPic Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<Object> findPolicyForDeliveryListOfPic(Long premListNo){
		
        Session session = null;
		List<Object> list = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicPolicyDtls.class, "lpd");
	            criteria.createAlias("lpd.licPolicyMst", "lpm");
	            criteria.createAlias("lpd.branchMst", "bm");
	            criteria.createAlias("lpm.licOblApplicationMst", "loam");
	            criteria.createAlias("lpd.licMarkingToQcDtls", "lmtqd");
	            criteria.createAlias("loam.licInsuredDtls", "lid");
	            criteria.createAlias("loam.licProposerDtls", "lprd");
	            criteria.createAlias("loam.licProductValueMst", "lpvm");
	            criteria.createAlias("lpvm.licProductMst", "lpdm");
	            criteria.createAlias("lpd.licPremPolicyMappings", "lppm");

	            criteria.add(Restrictions.eq("lpd.paidFlag","Y"));
	            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
	            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
	            criteria.add(Restrictions.eq("lppm.licPremiumListDtls.id",premListNo));
	           
	            ProjectionList projectionList = Projections.projectionList();
	            projectionList.add(Projections.property("lpm.policyNo"));  
	            projectionList.add(Projections.property("lpd.payDate"));
	            projectionList.add(Projections.property("lid.name"));
	            projectionList.add(Projections.property("lprd.name"));
	            projectionList.add(Projections.property("lpdm.prodDesc"));
	            projectionList.add(Projections.property("lpd.hlthReqdFlag"));
	            projectionList.add(Projections.property("lpd.payMode"));
	            projectionList.add(Projections.count("lpm.policyNo"));
	            projectionList.add(Projections.min("lpd.dueDate"));
	            projectionList.add(Projections.max("lpd.dueDate"));
	            projectionList.add(Projections.property("lpvm.payNature"));

	            projectionList.add(Projections.groupProperty("lpm.policyNo"));  
	            projectionList.add(Projections.groupProperty("lpd.payDate"));
	            projectionList.add(Projections.groupProperty("lid.name"));
	            projectionList.add(Projections.groupProperty("lprd.name"));
	            projectionList.add(Projections.groupProperty("lpdm.prodDesc"));
	            projectionList.add(Projections.groupProperty("lpd.hlthReqdFlag"));
	            projectionList.add(Projections.groupProperty("lpd.payMode"));
	            projectionList.add(Projections.groupProperty("lpvm.payNature"));
	            
	            criteria.setProjection(projectionList);
	            
	            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
			 list = criteria.list();
		}catch(Exception e){
			log.info("LicPremiumListDaoImpl findPolicyForDeliveryListOfPic Error", e);
		}finally {
           session.close();
       }
		return list;
	}
	
	
	public List<LicOblApplicationMst> findApplicationForPremList(List<LicHubMst> licHubMsts,String payMode, BranchMst branchMst){
		List<LicOblApplicationMst> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            criteria.createAlias("loam.licProposerDtls", "lpd");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("loam.licProductValueMst", "lipvm");
            criteria.createAlias("loam.branchMst", "bm");
            criteria.createAlias("lipvm.licProductMst", "lipm");
            criteria.createAlias("loam.licPremApplMappings", "lpam",JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("loam.licPisMst", "lpms", JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("loam.licMarkingToQcDtls", "lmtqd", JoinType.LEFT_OUTER_JOIN);
            
            criteria.add(Restrictions.or(Restrictions.and(Restrictions.eq("lpm.payMode", "C"), Restrictions.isNotNull("lpms.id")), Restrictions.and(Restrictions.ne("lpm.payMode", "C"), Restrictions.eq("lmtqd.indMrkFlag", "Y"))));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.isNull("lpam.id"));
            criteria.add(Restrictions.in("loam.oblHubMst", licHubMsts));
            criteria.add(Restrictions.eq("lpm.payMode", payMode));
            criteria.addOrder(Order.desc("loam.id"));
            
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findApplicationForPremList Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public Boolean saveForPremiumList(List<LicPremApplMapping> licPremApplMappings){
		int count = 0;
		Boolean status=false;
	 	Session session = null;
	 	Transaction tx=null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            for(LicPremApplMapping obj:licPremApplMappings){
            	 session.save(obj); 
            	 if ( ++count % 50 == 0 ) {
            	      session.flush();
            	      session.clear();
            	  }
            }
            tx.commit();
            status=true;
        } catch (Exception e) {
        	if(tx!=null) tx.rollback();
        	status=false;
        	log.info("LicPremiumListDaoImpl saveForPremiumList Error", e);
        } finally {
            session.close();
        }
        return status;
	}
	
	
	public Boolean savePremiumListForRnl(List<LicPremPolicyMapping> licPremPolicyMappings){
		int count = 0;
		Boolean status=false;
	 	Session session = null;
	 	Transaction tx=null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            for(LicPremPolicyMapping obj:licPremPolicyMappings){
            	 session.save(obj); 
            	 if ( ++count % 50 == 0 ) {
            	      session.flush();
            	      session.clear();
            	  }
            }
            tx.commit();
            status=true;
        } catch (Exception e) {
        	if(tx!=null) tx.rollback();
        	status=false;
        	log.info("LicPremiumListDaoImpl savePremiumListForRnl Error", e);
        } finally {
            session.close();
        }
        return status;
	}
	
	public Boolean saveForPremiumDetailDataEntryList(LicPremiumListDtls licPremiumListDtls){
		Boolean status = false;
	 	Session session = null;
	 	Transaction tx=null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(licPremiumListDtls);           
            tx.commit();
            status = true;
        } catch (Exception e) {
        	if(tx!=null) tx.rollback();
        	 status = false;
        	 log.info("LicPremiumListDaoImpl saveForPremiumDetailDataEntryList Error", e);
        } finally {
            session.close();
        }
        return status;
	}
	
	
	public List<LicPremiumListDtls> findPremiumListForDetailEntry(List<LicHubMst> licHubMsts){
		List<LicPremiumListDtls> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
           
            Criteria criteria = session.createCriteria(LicPremApplMapping.class,"lpam");
            criteria.createAlias("lpam.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            criteria.createAlias("lpam.licPremiumListDtls", "lpld");
            criteria.createAlias("lpld.licPremPaymentDtlses", "lppd", JoinType.LEFT_OUTER_JOIN);
            
            criteria.add(Restrictions.eq("lpld.deleteFlag","N"));
            criteria.add(Restrictions.isNull("lppd.sentPayNo"));
            criteria.setProjection(Projections.distinct(Projections.property("lpam.licPremiumListDtls")));
            criteria.add(Restrictions.in("loam.oblHubMst", licHubMsts));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.ne("lpm.payMode","Q"));
            criteria.addOrder(Order.desc("lpam.licPremiumListDtls"));
            
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListForDetailEntry Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<LicOblApplicationMst> findLicOblApplicationMstsByPremListNo(Long premListNo, BranchMst branchMst){
		List<LicOblApplicationMst> list=null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");            
            criteria.createAlias("loam.licProposerDtls", "lpd");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("loam.picBranchMstId", "pbmi");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            criteria.createAlias("loam.licPremApplMappings", "lpam");
            
            criteria.add(Restrictions.eq("lpam.licPremiumListDtls.id", premListNo));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            list=criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findLicOblApplicationMstsByPremListNo Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public LicPremiumListDtls findById(Long id){
		LicPremiumListDtls licPremiumListDtls = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            licPremiumListDtls = (LicPremiumListDtls) session.get(LicPremiumListDtls.class, id);
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findById Error", e);
        } finally {
            session.close();
        }
        return licPremiumListDtls;
	}

	@Override
	public List<LicRequirementDtls> findRequirementForPremList(List<LicHubMst> licHubMsts, String payMode, BranchMst branchMst) {
		List<LicRequirementDtls> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
            criteria.createAlias("lrd.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            criteria.createAlias("loam.licProposerDtls", "lpd");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("loam.licProductValueMst", "lipvm");
            criteria.createAlias("loam.branchMst", "bm");
            criteria.createAlias("lipvm.licProductMst", "lipm");
            criteria.createAlias("lrd.licPremReqMappings", "lprm",JoinType.LEFT_OUTER_JOIN);
            
            criteria.add(Restrictions.isNull("lprm.id"));
            criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));
            //criteria.add(Restrictions.eq("lpm.payMode", payMode));
            criteria.add(Restrictions.eq("lrd.reqType", "S"));
            criteria.add(Restrictions.eq("lrd.dispatchReadyFlag","Y"));
            criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.ne("lrd.actionType", "IR"));
            
            criteria.addOrder(Order.desc("lrd.id"));
            
            list = criteria.list();            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findById Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public Boolean saveForPremiumListForReq(List<LicPremReqMapping> licPremReqMapping) {
		int count = 0;
		Boolean status=false;
	 	Session session = null;
	 	Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            for(LicPremReqMapping obj : licPremReqMapping){
            	 session.save(obj); 
            	 if ( ++count % 50 == 0 ) {
            	      session.flush();
            	      session.clear();
            	  }
            }
            tx.commit();
            status=true;
        } catch (Exception e) {
        	if(tx!=null) tx.rollback();
        	status = false;
        	log.info("LicPremiumListDaoImpl saveForPremiumListForReq Error", e);
        } finally {
            session.close();
        }
        return status;
	}
	
	
	public List<LicPremiumListDtls> findPremiumListForDetailEntryReq(List<LicHubMst> licHubMsts){
		List<LicPremiumListDtls> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPremReqMapping.class,"lprm");
            criteria.createAlias("lprm.licRequirementDtls", "lrd");
            criteria.createAlias("lrd.licOblApplicationMst", "loam");
            criteria.createAlias("lprm.licPremiumListDtls", "lpld");
            criteria.createAlias("lpld.licPremPaymentDtlses", "lppd", JoinType.LEFT_OUTER_JOIN);
            
            criteria.add(Restrictions.eq("lpld.deleteFlag","N"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.isNull("lppd.payMode"));
            criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));
            
            criteria.setProjection(Projections.distinct(Projections.property("lprm.licPremiumListDtls")));
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListForDetailEntryReq Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<LicRequirementDtls> findLicRequirementDtlsByPremListNo(Long premListNo, BranchMst branchMst){
		List<LicRequirementDtls> list = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
            criteria.createAlias("lrd.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licProposerDtls", "lpd");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("loam.picBranchMstId", "pbmi");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            criteria.createAlias("lrd.licPremReqMappings", "lprm");
            
            criteria.add(Restrictions.eq("lprm.licPremiumListDtls.id", premListNo));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.eq("lrd.deleteFlag","N"));
            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findLicRequirementDtlsByPremListNo Error", e);
        } finally {
            session.close();
        }
        return list;
	}


	@Override
	public List<Long> findPremiumListNoForReqBocEntry(List<LicHubMst> licHubMsts) {
		List<Long> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
            criteria.createAlias("lrd.licOblApplicationMst", "loam");
            criteria.createAlias("lrd.licPremReqMappings", "lprm");
            criteria.createAlias("lrd.licReqBocMappings", "lrbm",JoinType.LEFT_OUTER_JOIN);
            criteria.setProjection(Projections.distinct(Projections.property("lprm.licPremiumListDtls.id")));
            criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));
            criteria.add(Restrictions.eq("lrd.deleteFlag","N"));
            criteria.add(Restrictions.isNull("lrbm.id"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListNoForReqBocEntry Error", e);
        } finally {
            session.close();
        }
        return list;
	}


	@Override
	public List<Object> findPolicyDtlsForPremList(List<LicHubMst> licHubMsts,String payMode, String healthReq, Date payFromDate, Date payToDate) {
		Session session = null;
		List<Object> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class, "lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.createAlias("lpd.branchMst", "bm");
            criteria.createAlias("lpm.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("loam.licProposerDtls", "lprd");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("lpvm.licProductMst", "lpdm");
            criteria.createAlias("lpd.licPremPolicyMappings", "lppm",JoinType.LEFT_OUTER_JOIN);
           
            
            if(payFromDate!=null){
            	criteria.add(Restrictions.ge("lpd.payDate", payFromDate));
            }
            if(payToDate!=null){
            	criteria.add(Restrictions.le("lpd.payDate", payToDate));
            }
            criteria.add(Restrictions.eq("lpd.payMode",payMode));
            criteria.add(Restrictions.eq("lpd.hlthReqdFlag",healthReq));
            
            if(payMode.equals("C") && healthReq.equals("N")){
            	criteria.add(Restrictions.in("lpd.oblHubMst", licHubMsts));
            }else{
            	criteria.add(Restrictions.in("lpd.processHubMst", licHubMsts));
            }
			
            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
            criteria.add(Restrictions.eq("lpd.premListReadyFlag", "Y"));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
            criteria.add(Restrictions.isNull("lppm.id"));
            criteria.add(Restrictions.eq("lpd.healthValidated", "Y"));
            
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("lpm.policyNo"));  
            projectionList.add(Projections.property("lpd.payDate"));
            projectionList.add(Projections.property("lid.name"));
            projectionList.add(Projections.property("lprd.name"));
            projectionList.add(Projections.property("lpdm.prodDesc"));
            projectionList.add(Projections.property("lpd.hlthReqdFlag"));
            projectionList.add(Projections.property("lpd.payMode"));
            projectionList.add(Projections.count("lpm.policyNo"));
            projectionList.add(Projections.min("lpd.dueDate"));
            projectionList.add(Projections.max("lpd.dueDate"));
            projectionList.add(Projections.property("lpvm.payNature"));

            projectionList.add(Projections.groupProperty("lpm.policyNo"));  
            projectionList.add(Projections.groupProperty("lpd.payDate"));
            projectionList.add(Projections.groupProperty("lid.name"));
            projectionList.add(Projections.groupProperty("lprd.name"));
            projectionList.add(Projections.groupProperty("lpdm.prodDesc"));
            projectionList.add(Projections.groupProperty("lpd.hlthReqdFlag"));
            projectionList.add(Projections.groupProperty("lpd.payMode"));
            projectionList.add(Projections.groupProperty("lpvm.payNature"));
            
            criteria.setProjection(projectionList);
            
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));

            list = criteria.list();
        } catch (RuntimeException e) {
        	log.info("LicPremiumListDaoImpl findPolicyDtlsForPremList Error", e);
        } finally {
            session.close();
        }
        return list;
	}


	@Override
	public List<LicPremiumListDtls> findPremiumListForDetailEntryForRenewal(List<LicHubMst> licHubMsts) {
		List<LicPremiumListDtls> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
           
            Criteria criteria = session.createCriteria(LicPremPolicyMapping.class,"lppm");
            criteria.createAlias("lppm.licPolicyDtls", "lpd");
            criteria.createAlias("lppm.licPremiumListDtls", "lpld");
            criteria.createAlias("lpld.licPremPaymentDtlses", "lppd", JoinType.LEFT_OUTER_JOIN);
            
            criteria.add(Restrictions.eq("lpld.deleteFlag","N"));
            criteria.add(Restrictions.isNull("lppd.sentPayNo"));
            criteria.setProjection(Projections.distinct(Projections.property("lppm.licPremiumListDtls")));
            criteria.add(Restrictions.in("lpd.processHubMst", licHubMsts));
            criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
            list=criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListForDetailEntryForRenewal Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	@Override
	public List<Object> findPolicyDtlsByPremListNoForRenewal(Long premListNo) {
		Session session = null;
		List<Object> list = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("SELECT "
            		+ "lpm.policyNo, "
            		+ "lpd.payDate, "
					+ "lid.name, "
					+ "lprd.name, "
					+ "lpdm.prodDesc, "
					+ "lpd.hlthReqdFlag, "
					+ "lpd.payMode, "
					+ "count(lpm.policyNo), "
					+ "min(lpd.dueDate), "
					+ "max(lpd.dueDate), "
					+ "lpvm.payNature, "
					+ "lrpm.id "
            		
            		+ "FROM "
            		+ "LicPolicyDtls as lpd inner join "
            		+ "lpd.licPolicyMst as lpm inner join "
            		+ "lpd.licPremPolicyMappings as lppm inner join "
            		+ "lpd.licPolicyPaymentMappings as lppym inner join "
					+ "lpm.licOblApplicationMst as loam inner join "
					+ "loam.licInsuredDtls as lid inner join "
					+ "loam.licProposerDtls as lprd inner join "
					+ "loam.licProductValueMst as lpvm inner join "
					+ "lpvm.licProductMst as lpdm inner join "
					+ "lppm.licPremiumListDtls as lpld inner join "
					+ "lppym.licRnlPaymentMst as lrpm "
            		
            		+ "WHERE "
            		+ "lpld.id = :premListNo "
            		+ "AND lpd.renewalType = 'NORMAL' "
            		+ "AND lpd.deleteFlag = 'N' "
					+ "GROUP BY lpm.policyNo, lpd.payDate, lpd.hlthReqdFlag, lpd.payMode, lid.name, lprd.name, lpdm.prodDesc, lpvm.payNature, lrpm.id");
            
			query.setParameter("premListNo", premListNo);
			
			list = query.list();
		} catch (Exception e) {
			log.info("LicPremiumListDaoImpl findPolicyDtlsByPremListNoForRenewal Error", e);
		} finally {
			session.close();
		}
		return list;
	}


	@Override
	public List<Double> findCashAmtByRnlPayId(Long payId) {
		Session session = null;
		List<Double> list = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("SELECT "
					+ "coalesce(sum(lrpd.amount), 0.0) "
            		
            		+ "FROM "
            		+ "LicRnlPaymentDtls as lrpd "
            		
            		+ "WHERE "
            		+ "lrpd.payMode = 'C' "
            		+ "AND lrpd.shortPremFlag IS NULL "
            		+ "AND lrpd.licRnlPaymentMst.id = :payId");
			query.setParameter("payId", payId);
			
			list = query.list();
		} catch (Exception e) {
			log.info("LicPremiumListDaoImpl findCashAmtByRnlPayId Error", e);
		} finally {
			session.close();
		}
		return list;
	}


	@Override
	public List<Double> findChqDDAmtByRnlPayId(Long payId) {
		Session session = null;
		List<Double> list = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("SELECT "
					+ "coalesce(sum(lrpd.amount), 0.0) "
            		
            		+ "FROM "
            		+ "LicRnlPaymentDtls as lrpd "
            		
            		+ "WHERE "
            		+ "(lrpd.payMode = 'Q' OR lrpd.payMode = 'D') "
            		+ "AND lrpd.licRnlPaymentMst.id = :payId");
			query.setParameter("payId", payId);
			
			list = query.list();
		} catch (Exception e) {
			log.info("LicPremiumListDaoImpl findChqDDAmtByRnlPayId Error", e);
		} finally {
			session.close();
		}
		return list;
	}


	@Override
	public List<LicOblApplicationMst> findPremiumListReport(List<LicHubMst> findHubForProcess, Date businessFromDate, Date businessToDate, Long premListNo) {
		List<LicOblApplicationMst> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            
            Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            criteria.createAlias("loam.licProposerDtls", "lpd");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("loam.licProductValueMst", "lipvm");
            criteria.createAlias("loam.picBranchMstId", "pbmi");
            criteria.createAlias("loam.oblHubMst", "ohm");
            criteria.createAlias("lipvm.licProductMst", "lipm");
            criteria.createAlias("loam.licPremApplMappings", "lpam");
            criteria.createAlias("loam.licPisMst", "lpms");
            criteria.createAlias("loam.licMarkingToQcDtls", "lmtqd", JoinType.LEFT_OUTER_JOIN);
            
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.eq("lipvm.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lid.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
            criteria.add(Restrictions.ge("loam.businessDate", businessFromDate));
            criteria.add(Restrictions.le("loam.businessDate", businessToDate));
            criteria.add(Restrictions.in("loam.oblHubMst", findHubForProcess));
            
            if(premListNo != null){
            	 criteria.add(Restrictions.eq("lpam.licPremiumListDtls.id", premListNo));
            }
            criteria.addOrder(Order.desc("loam.id"));
            
            list = criteria.list();
           
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListReport Error", e);
        } finally {
            session.close();
        }
        return list;
	}


	@Override
	public List<LicPremiumListDtls> findPremiumNoforPremListDetailEntryreport(List<LicHubMst> findHubForProcess) {
		List<LicPremiumListDtls> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
           
            Criteria criteria = session.createCriteria(LicPremApplMapping.class,"lpam");
            criteria.createAlias("lpam.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licBusinessTxn", "lbt");
            criteria.createAlias("lbt.licPaymentMst", "lpm");
            criteria.createAlias("lpam.licPremiumListDtls", "lpld");
            criteria.createAlias("lpld.licPremPaymentDtlses", "lppd");
            
            criteria.setProjection(Projections.distinct(Projections.property("lpam.licPremiumListDtls")));
            criteria.add(Restrictions.in("loam.oblHubMst", findHubForProcess));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.eq("lpld.deleteFlag","N"));
            criteria.add(Restrictions.ne("lpm.payMode","Q"));
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumNoforPremListDetailEntryreport Error", e);
        } finally {
            session.close();
        }
        return list;
	}


	@Override
	public List<Long> findPremiumListByBusinessDate(List<LicHubMst> findHubForProcess, Date businessFromDate,Date businessToDate) {
		List<Long> list = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
            
            Criteria criteria = session.createCriteria(LicPremApplMapping.class,"lpam");
            criteria.createAlias("lpam.licOblApplicationMst", "loam");
            criteria.createAlias("lpam.licPremiumListDtls", "lpld");
            criteria.add(Restrictions.ge("loam.businessDate", businessFromDate));
            criteria.add(Restrictions.le("loam.businessDate", businessToDate));
            criteria.add(Restrictions.in("loam.oblHubMst", findHubForProcess));
            criteria.add(Restrictions.eq("lpam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lpld.deleteFlag", "N"));
            
            criteria.setProjection(Projections.distinct(Projections.property("lpld.id")));
            
            list =  criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findPremiumListByBusinessDate Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
}
