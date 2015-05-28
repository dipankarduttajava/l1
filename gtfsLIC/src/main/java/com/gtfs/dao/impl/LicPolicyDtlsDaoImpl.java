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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicPolicyDtlsDao;
import com.gtfs.dto.LicPolicyDtlsDto;

@Repository
public class LicPolicyDtlsDaoImpl implements Serializable, LicPolicyDtlsDao {
	private Logger log = Logger.getLogger(LicPolicyDtlsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<LicPolicyDtls> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LicPolicyDtls> findPolicyDtlsByPolicyMst(Long policyMstId) {
		Session session = null;
		List<LicPolicyDtls> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class);
            criteria.add(Restrictions.eq("licPolicyMst.id", policyMstId));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            list = criteria.list();
        } catch (RuntimeException e) {
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyMst Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNo(String policyNo, Date sysdate) {
		Session session = null;
		List<LicPolicyDtls> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.createAlias("lpm.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("lpvm.licProductMst", "lpdm");
            criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
            criteria.add(Restrictions.le("lpd.dueDate", sysdate));
            criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lpd.paidFlag", "N"));
            criteria.addOrder(Order.asc("lpd.dueDate"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            list = criteria.list();
        } catch (RuntimeException e) {
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyNo Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public Boolean saveRenewalBranchEntry(List<LicPolicyDtls> licPolicyDtlses) {
		Session session = null;
		Transaction tx=null;
		Boolean status = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for(LicPolicyDtls obj : licPolicyDtlses){
				session.update(obj);
			}
			tx.commit();
			status = true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status = false;
			log.info("LicPolicyDtlsDaoImpl saveRenewalBranchEntry Error", e);
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public List<LicPolicyDtls> findAdvPolicyDtlsByPolicyNo(String policyNo, Date sysdate, Date incrementDate) {
		
		Session session = null;
		List<LicPolicyDtls> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.createAlias("lpm.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("lpvm.licProductMst", "lpdm");
            criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
            criteria.add(Restrictions.gt("lpd.dueDate", sysdate));
            criteria.add(Restrictions.le("lpd.dueDate", incrementDate));
            criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lpd.paidFlag", "N"));
            criteria.addOrder(Order.asc("lpd.dueDate"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            list = criteria.list();
        } catch (RuntimeException e) {
        	log.info("LicPolicyDtlsDaoImpl findAdvPolicyDtlsByPolicyNo Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoForDirectRenewal(String policyNo, Date sysdate) {
		Session session = null;
		List<LicPolicyDtls> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.createAlias("lpm.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("lpvm.licProductMst", "lpdm");
            criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
            criteria.add(Restrictions.le("lpd.dueDate", sysdate));
            criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
            criteria.addOrder(Order.asc("lpd.dueDate"));
            criteria.add(Restrictions.eq("lpd.paidFlag", "N"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            list = criteria.list();
        } catch (RuntimeException e) {
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyNoForDirectRenewal Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<LicPolicyDtls> findLastPaidPolicyDtlsByPolicyNo(String policyNo) {
		Session session = null;
		List<LicPolicyDtls> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.createAlias("lpm.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("lpvm.licProductMst", "lpdm");
            criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
            criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
            criteria.addOrder(Order.desc("lpd.dueDate"));
            criteria.add(Restrictions.eq("lpd.paidFlag", "Y"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));            
            criteria.setMaxResults(1);
            
            list = criteria.list();
        } catch (RuntimeException e) {
        	log.info("LicPolicyDtlsDaoImpl findLastPaidPolicyDtlsByPolicyNo Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public LicPolicyDtls findById(Long id) {
		Session session = null;
		LicPolicyDtls licPolicyDtls = null;
        try {
            session = sessionFactory.openSession();
            licPolicyDtls = (LicPolicyDtls) session.get(LicPolicyDtls.class, id);
        } catch (RuntimeException e) {
        	log.info("LicPolicyDtlsDaoImpl findById Error", e);
        } finally {
            session.close();
        }
        return licPolicyDtls;
	}
	
	
	@Override
	public List<Object> findPolicyDtlsByDispatchIdAndBranchForPod(Long dispatchId, Long branchId) {
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
            
            criteria.add(Restrictions.eq("lpd.branchMst.branchId", branchId));
            criteria.add(Restrictions.eq("lpd.paidFlag","Y"));
            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
            criteria.add(Restrictions.eq("lpd.brnhHubDespFlag", "Y"));
            criteria.add(Restrictions.isNotNull("lpd.brnhHubMapDtls"));
            criteria.add(Restrictions.isNull("lpd.brnhHubPodDtls"));
            criteria.add(Restrictions.eq("lpd.brnhHubMapDtls.id",dispatchId));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
           
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
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByDispatchIdAndBranchForPod Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	@Override
	public List<Object> findPolicyDtlsByHubIdAndBranchForDispatch(Date payDateFrom, Date payDateTo, Long hubId, Long branchId) {
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
            
            criteria.add(Restrictions.ge("lpd.payDate", payDateFrom));
			criteria.add(Restrictions.le("lpd.payDate", payDateTo));
            criteria.add(Restrictions.eq("lpd.branchMst.branchId", branchId));
            criteria.add(Restrictions.eq("lpd.oblHubMst.id", hubId));
            criteria.add(Restrictions.eq("lpd.paidFlag","Y"));
            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
            criteria.add(Restrictions.eq("lpd.brnhHubDespFlag", "Y"));
            criteria.add(Restrictions.isNull("lpd.brnhHubMapDtls"));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
           
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
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByHubIdAndBranchForDispatch Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public Boolean saveBrnhHubDispatchList(List<LicPolicyDtls> licPolicyDtlsList) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            for(int i=0;i<licPolicyDtlsList.size();i++){
            	session.update(licPolicyDtlsList.get(i));
            	if( i % 50 == 0 ) { 
                    session.flush();
                    session.clear();
                }
            }            
            tx.commit();
            status = true;
        } catch (RuntimeException e) {
              if(tx!=null)tx.rollback();
              log.info("LicPolicyDtlsDaoImpl saveBrnhHubDispatchList Error", e);
        } finally {
            session.close();
        }
        return status;
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRange(String policyNo, Date fromDate, Date toDate) {
		Session session = null;
		List<LicPolicyDtls> licPolicyDtlsList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.createAlias("lpd.licMarkingToQcDtls", "lmtqd", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.eq("lpm.policyNo",policyNo));
            criteria.add(Restrictions.ge("lpd.dueDate",fromDate));
            criteria.add(Restrictions.le("lpd.dueDate",toDate));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
            licPolicyDtlsList = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyNoAndDueDateRange Error", e);
        } finally {
            session.close();
        }
        return licPolicyDtlsList;
	}

	@Override
	public List<Long> findPodApplicationsForRenewal(Long id) {
		Session session = null;
		List<Long> podId = null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicBrnhHubPicMapDtls.class,"lbhpmd");
			criteria.createAlias("lbhpmd.licPolicyDtlses", "lpds");
			criteria.createAlias("lpds.branchMst", "bm");
			
			criteria.add(Restrictions.isNotNull("lpds.brnhHubMapDtls"));
			criteria.add(Restrictions.isNull("lpds.brnhHubPodDtls"));
			criteria.add(Restrictions.eq("lpds.deleteFlag", "N"));
			criteria.add(Restrictions.eq("bm.branchId", id));
			
			criteria.setProjection(Projections.distinct(Projections.property("lbhpmd.id")));
			podId = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findPodApplicationsForRenewal Error", e);
		}finally{
			session.close();
		}
		return podId;
	}

	@Override
	public Boolean saveBrnhHubPodDtlsForRenewal(List<LicPolicyDtls> licPolicyDtlsList) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            for(int i=0;i<licPolicyDtlsList.size();i++){
            	session.update(licPolicyDtlsList.get(i));
            	if( i % 50 == 0 ) { 
                    session.flush();
                    session.clear();
                }
            }
            
            tx.commit();
            status = true;
        } catch (RuntimeException e) {
              if(tx!=null)tx.rollback();
              log.info("LicPolicyDtlsDaoImpl saveBrnhHubPodDtlsForRenewal Error", e);
        } finally {
            session.close();
        }
        return status;
	}

	@Override
	public List<Object> findIndividualMarkingDtlsByDateForRenewal(Date fromDate, Date toDate, Long branchId) {
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
	            
	            criteria.add(Restrictions.ge("lpd.payDate", fromDate));
				criteria.add(Restrictions.le("lpd.payDate", toDate));
	            criteria.add(Restrictions.eq("lpd.branchMst.branchId", branchId));
	            criteria.add(Restrictions.eq("lpd.paidFlag","Y"));
	            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
	            criteria.add(Restrictions.eq("lmtqd.consldMrkFlag", "Y"));
				 criteria.add(Restrictions.isNull("lmtqd.indMrkFlag"));
	            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
	           
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
			log.info("LicPolicyDtlsDaoImpl findIndividualMarkingDtlsByDateForRenewal Error", e);
		}finally {
           session.close();
       }
		return list;
	}

	@Override
	public List<LicPolicyDtls> findApplicationByDispatchListForRenewal(Long id, BranchMst branchMst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findPolicyDtlsForDispatchDecision(Date payDateFrom, Date payDateTo, Long id) {
		Session session = null;
		List<Object> list = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicPolicyDtls.class, "lpd");
	            criteria.createAlias("lpd.licPolicyMst", "lpm");
	            criteria.createAlias("lpd.branchMst", "bm");
	            criteria.createAlias("lpm.licOblApplicationMst", "loam");
	            criteria.createAlias("loam.licInsuredDtls", "lid");
	            criteria.createAlias("loam.licProposerDtls", "lprd");
	            criteria.createAlias("loam.licProductValueMst", "lpvm");
	            criteria.createAlias("lpvm.licProductMst", "lpdm");
	            
	            criteria.add(Restrictions.ge("lpd.payDate", payDateFrom));
				criteria.add(Restrictions.le("lpd.payDate", payDateTo));
				//criteria.add(Restrictions.eq("bm.branchId", id));
	            criteria.add(Restrictions.eq("lpd.paidFlag","Y"));
	            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
	            criteria.add(Restrictions.eq("lpd.brnhHubDespFlag", "N"));
	            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
	            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
	           
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
	            projectionList.add(Projections.property("bm.branchName"));

	            projectionList.add(Projections.groupProperty("lpm.policyNo"));  
	            projectionList.add(Projections.groupProperty("lpd.payDate"));
	            projectionList.add(Projections.groupProperty("lid.name"));
	            projectionList.add(Projections.groupProperty("lprd.name"));
	            projectionList.add(Projections.groupProperty("lpdm.prodDesc"));
	            projectionList.add(Projections.groupProperty("lpd.hlthReqdFlag"));
	            projectionList.add(Projections.groupProperty("lpd.payMode"));
	            projectionList.add(Projections.groupProperty("lpvm.payNature"));
	            projectionList.add(Projections.groupProperty("bm.branchName"));
	            
	            criteria.setProjection(projectionList);
			 list = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findPolicyDtlsForDispatchDecision Error", e);
		}finally {
           session.close();
       }
		return list;
	}

	@Override
	public List<Object> findHealthValidationDtlsByDateForRenewal(Date fromDate, Date toDate, Long branchId) {
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
	            
	            criteria.add(Restrictions.ge("lpd.payDate", fromDate));
				criteria.add(Restrictions.le("lpd.payDate", toDate));
	            criteria.add(Restrictions.eq("lpd.paidFlag","Y"));
	            criteria.add(Restrictions.eq("lpd.hlthReqdFlag","Y"));
	            criteria.add(Restrictions.isNull("lpd.healthValidated"));
	            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
				criteria.add(Restrictions.eq("lmtqd.indMrkFlag", "Y"));
	            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
	           
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
			log.info("LicPolicyDtlsDaoImpl findHealthValidationDtlsByDateForRenewal Error", e);
		}finally {
           session.close();
       }
		return list;
	}

	@Override
	public Boolean saveHealthValidationForRenewal(List<LicPolicyDtls> licPolicyDtlsList) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for(LicPolicyDtls obj : licPolicyDtlsList){
				session.merge(obj);
			}
			tx.commit();
			status = true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status = false;
			log.info("LicPolicyDtlsDaoImpl saveHealthValidationForRenewal Error", e);
		}finally{
			session.close();
		}
		return status;
	}
	
	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoDueDateRangeForHealthValidation(String policyNo, Date fromDate, Date toDate) {
		Session session = null;
		List<LicPolicyDtls> licPolicyDtlsList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.createAlias("lpd.licMarkingToQcDtls", "lmtqd",JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("lpd.licPolicyBankDtls", "lpbd",JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
            criteria.add(Restrictions.ge("lpd.dueDate", fromDate));
            criteria.add(Restrictions.le("lpd.dueDate", toDate));
            criteria.add(Restrictions.eq("lpd.hlthReqdFlag","Y"));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
            licPolicyDtlsList = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyNoDueDateRangeForHealthValidation Error", e);
        } finally {
            session.close();
        }
        return licPolicyDtlsList;
	}
	
	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRangeForPremium(String policyNo, Date fromDate, Date toDate) {
		Session session = null;
		List<LicPolicyDtls> licPolicyDtlsList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.add(Restrictions.eq("lpm.policyNo",policyNo));
            criteria.add(Restrictions.ge("lpd.dueDate",fromDate));
            criteria.add(Restrictions.le("lpd.dueDate",toDate));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
            licPolicyDtlsList = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyNoAndDueDateRangeForPremium Error", e);
        } finally {
            session.close();
        }
        return licPolicyDtlsList;
	}

	@Override
	public List<Object> findPolicyDtlsByDispatchIdAndBranchForRnlPicPod(Long dispatchId, Long branchId) {
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
 
            criteria.add(Restrictions.eq("lpd.branchMst.branchId", branchId));
            criteria.add(Restrictions.eq("lpd.paidFlag","Y"));
            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
            criteria.add(Restrictions.isNotNull("lpd.hubPicMapDtls"));
            criteria.add(Restrictions.isNull("lpd.hubPicPodDtls"));
            criteria.add(Restrictions.eq("lpd.hubPicMapDtls.id",dispatchId));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
           
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
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByDispatchIdAndBranchForRnlPicPod Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<Long> findDispatchListForRnlPicPod() {
		Session session = null;
		List<Long> id = null;
		try{
			session=sessionFactory.openSession();		
			Criteria criteria= session.createCriteria(LicPolicyDtls.class,"lpd");
			criteria.createAlias("lpd.hubPicMapDtls", "hpmd");
			criteria.add(Restrictions.isNull("lpd.hubPicPodDtls"));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.setProjection(Projections.distinct(Projections.property("hpmd.id")));
			
			id = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findDispatchListForRnlPicPod Error", e);
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public List<Object> findPosViewRejectionByDateForRenewal(Date fromDate, Date toDate, Long branchId) {
		Session session = null;
		List<Object> list = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicPolicyDtls.class, "lpd");
	            criteria.createAlias("lpd.licPolicyMst", "lpm");
	            criteria.createAlias("lpd.branchMst", "bm");
	            criteria.createAlias("lpd.licMarkingToQcDtls", "lmtqd");
	            criteria.createAlias("lpd.licRequirementDtlses", "lrds");
	            criteria.createAlias("lpm.licOblApplicationMst", "loam");
	            criteria.createAlias("loam.licInsuredDtls", "lid");
	            criteria.createAlias("loam.licProposerDtls", "lprd");
	            criteria.createAlias("loam.licProductValueMst", "lpvm");
	            criteria.createAlias("lpvm.licProductMst", "lpdm");
	            	            
	            criteria.add(Restrictions.ge("lpd.payDate", fromDate));
	            criteria.add(Restrictions.le("lpd.payDate", toDate));
	            
	            if(branchId != null){
	            	criteria.add(Restrictions.eq("lpd.branchMst.branchId", branchId));
	            }
	            
	            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
	            criteria.add(Restrictions.eq("lpd.healthValidated", "N"));
	            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
	            criteria.add(Restrictions.eq("lpd.migrationFlag", "N"));
	            criteria.add(Restrictions.isNull("lrds.branchRcvFlag"));
	           
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
			 list = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findPosViewRejectionByDateForRenewal Error", e);
		}finally {
           session.close();
       }
		return list;
	}

	@Override
	public List<Object> findPolicyDtlsForPosViewDispatch(Long branchId) {
		Session session = null;
		List<Object> list = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicPolicyDtls.class, "lpd");
	            criteria.createAlias("lpd.licPolicyMst", "lpm");
	            criteria.createAlias("lpd.branchMst", "bm");
	            criteria.createAlias("lpd.licRequirementDtlses", "lrds");
	            criteria.createAlias("lpm.licOblApplicationMst", "loam");
	            criteria.createAlias("lrds.licHubMst", "lhm");
	            criteria.createAlias("loam.licInsuredDtls", "lid");
	            criteria.createAlias("loam.licProposerDtls", "lprd");
	            criteria.createAlias("loam.licProductValueMst", "lpvm");
	            criteria.createAlias("lpvm.licProductMst", "lpdm");
	            
	            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
	            criteria.add(Restrictions.eq("lpd.healthValidated", "N"));
	            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
	            criteria.add(Restrictions.eq("lpd.migrationFlag", "N"));
	            criteria.add(Restrictions.eq("lrds.branchRcvFlag", "Y"));
	            criteria.add(Restrictions.eq("lrds.colFlag", "Y"));
				criteria.add(Restrictions.isNotNull("lrds.colDate"));
				criteria.add(Restrictions.isNotNull("lrds.colBy"));
	            criteria.add(Restrictions.isNull("lrds.dispatchReadyFlag"));
	            criteria.add(Restrictions.isNull("lrds.licBrnhHubPicMapDtls"));
	           
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
	            projectionList.add(Projections.property("lhm.hubName"));

	            projectionList.add(Projections.groupProperty("lpm.policyNo"));  
	            projectionList.add(Projections.groupProperty("lpd.payDate"));
	            projectionList.add(Projections.groupProperty("lid.name"));
	            projectionList.add(Projections.groupProperty("lprd.name"));
	            projectionList.add(Projections.groupProperty("lpdm.prodDesc"));
	            projectionList.add(Projections.groupProperty("lpd.hlthReqdFlag"));
	            projectionList.add(Projections.groupProperty("lpd.payMode"));
	            projectionList.add(Projections.groupProperty("lpvm.payNature"));
	            projectionList.add(Projections.groupProperty("lhm.hubName"));
	            
	            criteria.setProjection(projectionList);
			 list = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findPolicyDtlsForPosViewDispatch Error", e);
		}finally {
           session.close();
       }
		return list;
	}
	
	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoForPos(String policyNo) {
		Session session = null;
		List<LicPolicyDtls> licPolicyDtlsList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
            criteria.add(Restrictions.eq("lpd.healthValidated","N"));
            
            licPolicyDtlsList = criteria.list();            
        } catch (Exception e) {
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyNoForPos Error", e);
        } finally {
            session.close();
        }
        return licPolicyDtlsList;
	}

	@Override
	public List<Object> findPolicyDtlsByDispatchIdRenewalPosViewPicPod(Long dispatchId) {
		Session session = null;
		List<Object> list = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicPolicyDtls.class, "lpd");
	            criteria.createAlias("lpd.licPolicyMst", "lpm");
	            criteria.createAlias("lpd.licRequirementDtlses", "lrds");
	            criteria.createAlias("lrds.licHubMst", "lhm");
	            criteria.createAlias("lpm.licOblApplicationMst", "loam");
	            criteria.createAlias("loam.licInsuredDtls", "lid");
	            criteria.createAlias("loam.licProposerDtls", "lprd");
	            criteria.createAlias("loam.licProductValueMst", "lpvm");
	            criteria.createAlias("lpvm.licProductMst", "lpdm");
	            
	            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
	            criteria.add(Restrictions.eq("lpd.healthValidated", "N"));
	            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
	            criteria.add(Restrictions.eq("lpd.migrationFlag", "N"));
	            criteria.add(Restrictions.eq("lrds.branchRcvFlag", "Y"));
	            criteria.add(Restrictions.eq("lrds.colFlag", "Y"));
	            criteria.add(Restrictions.eq("lrds.licBrnhHubPicMapDtls.id", dispatchId));
				criteria.add(Restrictions.isNotNull("lrds.colDate"));
				criteria.add(Restrictions.isNotNull("lrds.colBy"));
	            criteria.add(Restrictions.isNotNull("lrds.dispatchReadyFlag"));
	            criteria.add(Restrictions.isNull("lrds.licBrnhHubPicPodDtls"));
	           
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
	            projectionList.add(Projections.property("lhm.hubName"));

	            projectionList.add(Projections.groupProperty("lpm.policyNo"));  
	            projectionList.add(Projections.groupProperty("lpd.payDate"));
	            projectionList.add(Projections.groupProperty("lid.name"));
	            projectionList.add(Projections.groupProperty("lprd.name"));
	            projectionList.add(Projections.groupProperty("lpdm.prodDesc"));
	            projectionList.add(Projections.groupProperty("lpd.hlthReqdFlag"));
	            projectionList.add(Projections.groupProperty("lpd.payMode"));
	            projectionList.add(Projections.groupProperty("lpvm.payNature"));
	            projectionList.add(Projections.groupProperty("lhm.hubName"));
	            
	            criteria.setProjection(projectionList);
			 list = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByDispatchIdRenewalPosViewPicPod Error", e);
		}finally {
           session.close();
       }
		return list;
	}

	@Override
	public List<Object> findIndividualMarkingDtlsForPos(Long branchId) {
		Session session = null;
		List<Object> list = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicRequirementDtls.class, "lrd");
			 	criteria.createAlias("lrd.licPolicyDtls", "lpd");
			 	criteria.createAlias("lpd.licPolicyMst", "lpm");
	            criteria.createAlias("lpd.branchMst", "bm");
	            criteria.createAlias("lpm.licOblApplicationMst", "loam");
	            criteria.createAlias("loam.licInsuredDtls", "lid");
	            criteria.createAlias("loam.licProposerDtls", "lprd");
	            criteria.createAlias("loam.licProductValueMst", "lpvm");
	            criteria.createAlias("lpvm.licProductMst", "lpdm");
	            criteria.createAlias("lrd.licMarkingToQcDtls", "lmtqd");
	            
	            criteria.add(Restrictions.eq("lpd.branchMst.branchId", branchId));
	            criteria.add(Restrictions.eq("lpd.renewalType", "NORMAL"));
	            criteria.add(Restrictions.eq("lmtqd.consldMrkFlag", "Y"));
				criteria.add(Restrictions.isNull("lmtqd.indMrkFlag"));
	            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
	            criteria.add(Restrictions.eq("lrd.deleteFlag","N"));
	           
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
	            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			 list = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findIndividualMarkingDtlsForPos Error", e);
		}finally {
           session.close();
       }
		return list;
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoAndDueDateRangeForPos(String policyNo, Date fromDate, Date toDate) {
		Session session = null;
		List<LicPolicyDtls> licPolicyDtlsList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
            criteria.createAlias("lpd.licPolicyMst", "lpm");
            criteria.createAlias("lpd.licRequirementDtlses", "lrds");
            criteria.createAlias("lrds.licMarkingToQcDtls", "lmtqd");
            
            criteria.add(Restrictions.eq("lpm.policyNo",policyNo));
            criteria.add(Restrictions.eq("lmtqd.consldMrkFlag", "Y"));
			criteria.add(Restrictions.isNull("lmtqd.indMrkFlag"));
            //criteria.add(Restrictions.ge("lpd.dueDate",fromDate));
            //criteria.add(Restrictions.le("lpd.dueDate",toDate));
            criteria.add(Restrictions.eq("lpd.deleteFlag","N"));
            licPolicyDtlsList = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyNoAndDueDateRangeForPos Error", e);
        } finally {
            session.close();
        }
        return licPolicyDtlsList;
	}

	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPolicyNoForRpr(String policyNo, Long id) {
		Session session = null;
		List<LicPolicyDtls> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicPolicyDtls.class, "lpd");
			criteria.createAlias("lpd.branchMst", "bm");
			criteria.createAlias("lpd.licPolicyMst", "lpm");
			criteria.createAlias("lpm.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licInsuredDtls", "lids");
			criteria.createAlias("loam.licProposerDtls", "lpds");
			
			criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
			//criteria.add(Restrictions.eq("bm.branchId", id));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.add(Restrictions.isNotNull("lpd.hubPicPodDtls"));
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findPolicyDtlsByPolicyNoForRpr Error", e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public Boolean updatePolicyDtlsRenewalRprStatus(List<LicPolicyDtls> licPolicyDtlsList) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for(LicPolicyDtls obj : licPolicyDtlsList){
				session.update(obj);
			}
			tx.commit();
			status = true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status = false;
			log.info("LicPolicyDtlsDaoImpl updatePolicyDtlsRenewalRprStatus Error", e);
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public List<LicPolicyDtlsDto> findPolicyDtlsForRnlPrintReceipt(String policyNo) {
		Session session = null;
		List<LicPolicyDtlsDto> list = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("SELECT "
					+ "max(lpd.dueDate) as fromDueDate,"
					+ "min(lpd.dueDate) as toDueDate,"
					+ "lpd.paidFlag as paidFlag,"
					+ "lpd.payDate as payDate,"
					+ "coalesce(lpd.printFlag,'N') as printFlag,"
					+ "lrpm.fineAmt as fineAmount,"
					+ "lrpm.totalReceivable as totalReceivable,"
					+ "lrpm.totalReceived as totalReceived,"
					+ "tcm.tieCompyName as tieupCompyName,"
					+ "lpom.prodDesc as prodName,"
					+ "lid.name as insuredName "
					
					+ "FROM "
					+ "LicPolicyDtls as lpd "
					+ "inner join lpd.licPolicyMst as lpm "
					+ "inner join lpd.licPolicyPaymentMappings as lppm "
					+ "inner join lppm.licRnlPaymentMst as lrpm "
					+ "inner join lpm.licOblApplicationMst as loam "
					+ "inner join loam.licProductValueMst as lpvm "
					+ "inner join lpvm.licProductMst as lpom "
					+ "inner join lpvm.tieupCompyMst tcm "
					+ "inner join loam.licInsuredDtls as lid "
					
					+ "WHERE "
					+ "lpm.policyNo = :policyNo "
					+ "and lpd.renewalMonth <> 0 "
					+ "and lpd.paidFlag = 'Y' "
					+ "group by lpd.paidFlag, lpd.payDate, lpd.printFlag, lpd.payDate, lpd.printFlag, lrpm.fineAmt, lrpm.totalReceivable, lrpm.totalReceived, tcm.tieCompyName, lpom.prodDesc, lid.name");
			
			query.setResultTransformer(Transformers.aliasToBean(LicPolicyDtlsDto.class));
			query.setParameter("policyNo", policyNo);
			list = query.list();
		}catch(Exception e){
			log.info("LicPolicyDtlsDaoImpl findPolicyDtlsForRnlPrintReceipt Error", e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public Boolean saveNormalRenewalBranchEntry(List<LicPolicyDtls> licPolicyDtlsList, LicPolicyMst licPolicyMst) {
		Session session = null;
		Transaction tx=null;
		Boolean status = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for(LicPolicyDtls obj : licPolicyDtlsList){
				session.update(obj);
			}
			
			session.update(licPolicyMst);
			tx.commit();
			status = true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status = false;
			log.info("LicPolicyDtlsDaoImpl saveRenewalBranchEntry Error", e);
		}finally{
			session.close();
		}
		return status;
	}
}
