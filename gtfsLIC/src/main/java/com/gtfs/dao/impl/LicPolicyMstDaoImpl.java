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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.GenericBusinessTxn;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.dao.interfaces.LicPolicyMstDao;
@Repository
public class LicPolicyMstDaoImpl implements LicPolicyMstDao,Serializable{
	private Logger log = Logger.getLogger(LicPolicyMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Boolean update(LicPolicyMst licPolicyMst){
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(licPolicyMst);
			tx.commit();
			status = true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status = false;
			log.info("LicPolicyMstDaoImpl update Error", e);
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public LicOblApplicationMst findPolicyDtls(Long id) {
		LicOblApplicationMst loam = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            loam = (LicOblApplicationMst) session.get(LicOblApplicationMst.class, id);
        } catch (Exception e) {
        	log.info("LicPolicyMstDaoImpl findPolicyDtls Error", e);
        } finally {
            session.close();
        }
        return loam;
	}

	@Override
	public List<LicPolicyMst> checkPolicyNo(String policyNo) {
		List<LicPolicyMst> list = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM LicPolicyMst WHERE policyNo = :policyNo AND deleteFlag = :deleteFlag ");
			query.setParameter("policyNo", policyNo);
			query.setParameter("deleteFlag", "N");
			
			list = query.list();
		}catch(Exception e){
			log.info("LicPolicyMstDaoImpl checkPolicyNo Error", e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public Boolean updateForStatusEntry(LicPolicyMst licPolicyMst,GenericBusinessTxn genericBusinessTxn) {
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(licPolicyMst);
			session.save(genericBusinessTxn);
			tx.commit();
			status = true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status = false;
			log.info("LicPolicyMstDaoImpl update Error", e);
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public LicPolicyMst findById(Long id) {
		LicPolicyMst licPolicyMst = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			licPolicyMst = (LicPolicyMst) session.get(LicPolicyMst.class, id);
		}catch(Exception e){
			log.info("LicPolicyMstDaoImpl checkPolicyNo Error", e);
		}finally{
			session.close();
		}
		return licPolicyMst;
	}

	@Override
	public List<LicPolicyMst> findApplicationForRejectedEntry(Date fromDate,Date toDate, String applicantName, Double premium,Double sumAssured, Long term, String applicationNo,String policyNo, String proposalNo,List<LicHubMst> findHubForProcess) {
		Session session = null;
		List<LicPolicyMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicPolicyMst.class,"lpm");
			criteria.createAlias("lpm.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licInsuredDtls", "lids");
			criteria.createAlias("loam.licProposerDtls", "lpds");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.picBranchMstId", "pbmi");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.oblHubMst", "ohm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lipm");
			criteria.createAlias("lpvm.licProductMst", "lpmt");
			criteria.createAlias("lpvm.schemeMst", "sm");
			criteria.createAlias("loam.agentMst", "am");
			criteria.createAlias("lbt.phaseMst", "pm");
			criteria.createAlias("lpvm.tieupCompyMst", "tcm");
			
			criteria.add(Restrictions.in("loam.oblHubMst",findHubForProcess));
			criteria.add(Restrictions.eq("loam.deleteFlag", "Y"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.isNotNull("loam.hubPicPodDtls"));
			
			if(fromDate != null){
				criteria.add(Restrictions.ge("loam.businessDate", fromDate));
			}
			
			if(proposalNo !=null && proposalNo !=""){
				criteria.add(Restrictions.eq("lpm.proposalNo", proposalNo));
			}
			
			if(policyNo!=null && policyNo !=""){
				criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
			}
			
			if(toDate != null){
				criteria.add(Restrictions.le("loam.businessDate", toDate));
			}
			if(applicantName != null && applicantName != ""){
				criteria.add(Restrictions.eq("lids.name", applicantName));
			}
			if(premium != null){
				criteria.add(Restrictions.eq("lpvm.premAmt", premium));
			}
			if(sumAssured != null){
				criteria.add(Restrictions.eq("lpvm.sumAssured", sumAssured));
			}
			if(term != null){
				criteria.add(Restrictions.eq("lpvm.term", term));
			}
			if(applicationNo != null && applicationNo != ""){
				criteria.add(Restrictions.eq("loam.oblApplNo", applicationNo));
			}
			
			criteria.addOrder(Order.desc("loam.id"));
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationForStatusEntry Error", e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<LicPolicyMst> findApplicationForFprAndPolicyBondDelivery(Date fromDate, Date toDate, String applicantName, Double premium, Double sumAssured, Long term, String applicationNo, String policyNo, String proposalNo, List<LicHubMst> findHubForProcess) {
		Session session = null;
		List<LicPolicyMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicPolicyMst.class,"lpm");
			criteria.createAlias("lpm.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licInsuredDtls", "lids");
			criteria.createAlias("loam.licProposerDtls", "lpds");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.picBranchMstId", "pbmi");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.oblHubMst", "ohm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lipm");
			criteria.createAlias("lpvm.licProductMst", "lpmt");
			criteria.createAlias("lpvm.schemeMst", "sm");
			criteria.createAlias("loam.agentMst", "am");
			criteria.createAlias("lbt.phaseMst", "pm");
			criteria.createAlias("lpvm.tieupCompyMst", "tcm");
			
			criteria.add(Restrictions.in("loam.oblHubMst",findHubForProcess));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.fprFlag","Y"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.isNotNull("loam.hubPicPodDtls"));
			
			if(fromDate != null){
				criteria.add(Restrictions.ge("loam.businessDate", fromDate));
			}
			
			if(proposalNo !=null && proposalNo !=""){
				criteria.add(Restrictions.eq("lpm.proposalNo", proposalNo));
			}
			
			if(policyNo!=null && policyNo !=""){
				criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
			}
			
			if(toDate != null){
				criteria.add(Restrictions.le("loam.businessDate", toDate));
			}
			if(applicantName != null && applicantName != ""){
				criteria.add(Restrictions.eq("lids.name", applicantName));
			}
			if(premium != null){
				criteria.add(Restrictions.eq("lpvm.premAmt", premium));
			}
			if(sumAssured != null){
				criteria.add(Restrictions.eq("lpvm.sumAssured", sumAssured));
			}
			if(term != null){
				criteria.add(Restrictions.eq("lpvm.term", term));
			}
			if(applicationNo != null && applicationNo != ""){
				criteria.add(Restrictions.eq("loam.oblApplNo", applicationNo));
			}
			
			criteria.addOrder(Order.desc("loam.id"));
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicPolicyMstDaoImpl findApplicationForFprAndPolicyBondDelivery Error", e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<LicPolicyMst> findPolicyInfoByPolicyNo(String policyNo) {
		Session session = null;
		List<LicPolicyMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicPolicyMst.class,"lpm");
			criteria.createAlias("lpm.licOblApplicationMst", "loam");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            criteria.createAlias("loam.licInsuredDtls", "lid");
            criteria.createAlias("lpvm.licProductMst", "lpdm");
            criteria.add(Restrictions.eq("lpm.policyNo", policyNo));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
            
            list = criteria.list();
            
		}catch(Exception e){
			log.info("LicPolicyMstDaoImpl findPolicyInfoByPolicyNo Error", e);
		}finally{
			session.close();
		}
		return list;
	}
}
