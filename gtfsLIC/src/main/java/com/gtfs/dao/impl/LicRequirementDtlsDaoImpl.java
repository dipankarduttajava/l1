package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicRequirementDtlsDao;

@Repository
public class LicRequirementDtlsDaoImpl implements LicRequirementDtlsDao, Serializable{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	public Boolean saveForRequirementDtls(LicRequirementDtls licRequirementDtls){
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		List<LicPolicyMst> list = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 session.save(licRequirementDtls);
			 Criteria criteria = session.createCriteria(LicPolicyMst.class,"lpm");
			 criteria.add(Restrictions.eq("lpm.licOblApplicationMst", licRequirementDtls.getLicOblApplicationMst()));
			 list  = criteria.list();
			 
			 for(LicPolicyMst obj : list){
				 obj.setPolicyStatus("Q");
				 session.update(obj);
			 }
			 tx.commit();
			 status=true;
		}catch(Exception e){
			if(tx!=null) tx.rollback();
			 status=false;
			 e.printStackTrace();
		}finally {
            session.close();
        }
		return status;
	}
	
	public List<LicRequirementDtls> findReqForReqEntryByApplNo(String applNo){
		List<LicRequirementDtls> list=null;
		Session session = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			 criteria.createAlias("lrd.licOblApplicationMst", "loam");
			 criteria.add(Restrictions.isNull("lrd.licBrnhHubPicPodDtls"));
			 criteria.add(Restrictions.eq("loam.oblApplNo",applNo));
			 criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
	         criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			 criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			 
			list=criteria.list();
		}catch(Exception e){
			
		}finally {
           session.close();
       }
		return list;
	}
	
	public List<LicRequirementDtls> findReqForActionTakenByApplNo(String applNo, List<LicHubMst> licHubMsts){
		List<LicRequirementDtls> list=null;
		Session session = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			 criteria.createAlias("lrd.licOblApplicationMst", "loam");
			 criteria.createAlias("loam.licInsuredDtls", "lids");
			 criteria.createAlias("loam.licProposerDtls", "lpds");
			 criteria.createAlias("loam.branchMst", "bm");
			 criteria.createAlias("loam.licProductValueMst", "lpvm");
			 criteria.add(Restrictions.eq("lrd.reqType","S" ));
			 criteria.add(Restrictions.isNull("lrd.actionType"));
			 criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));
			 
			 if(applNo != null){
				 criteria.add(Restrictions.eq("loam.oblApplNo",applNo)); 
			 }			 
			 criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
	         criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			 criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			 
			list = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
           session.close();
       }
		return list;
	}
	
	public Boolean saveForActionPoint(List<LicRequirementDtls> list){
		Boolean status=false;
		Session session = null;
		Transaction tx=null;
		try{
			 session = sessionFactory.openSession();
			 tx=session.beginTransaction();
			 
			 for(LicRequirementDtls licRequirementDtls:list){
				 session.update(licRequirementDtls);
			 }
			 
			 tx.commit();
			 status=true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status=false;
		}finally {
           session.close();
       }
		return status;
	}

	@Override
	public List<LicRequirementDtls> findPendingRequirementDtlsByApplicationNoAndReqType(String applicationNo, String reqType) {
		List<LicRequirementDtls> list=null;
		Session session = null;
		try{
			 session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			 criteria.createAlias("lrd.licOblApplicationMst", "loam");
			 //criteria.add(Restrictions.isNull("lrd.actionType"));
			 criteria.add(Restrictions.eq("lrd.reqType",reqType));
			 criteria.add(Restrictions.eq("loam.oblApplNo",applicationNo)); 
			 criteria.add(Restrictions.isNull("lrd.licBrnhHubPicPodDtls"));
			 criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
	         criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			 criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			 
			list=criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
           session.close();
       }
		return list;
	}

	@Override
	public LicRequirementDtls findById(Long id) {
		LicRequirementDtls licRequirementDtls=null;
		Session session = null;
		try{
			 session = sessionFactory.openSession();
			 licRequirementDtls = (LicRequirementDtls) session.get(LicRequirementDtls.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
           session.close();
       }
		return licRequirementDtls;
	}
	
	@Override
	public List<Long> findPodRequirmentForPicDispatch(List<LicHubMst> licHubMsts) {
		Session session=null;
		List<Long> id=null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");			
			criteria.add(Restrictions.isNotNull("lrd.licBrnhHubPicMapDtls"));
			criteria.add(Restrictions.isNull("lrd.licBrnhHubPicPodDtls"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));			
			criteria.setProjection(Projections.distinct(Projections.property("lrd.licBrnhHubPicMapDtls.id")));			
			id = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public List<LicRequirementDtls> findRequirmentByDispatchListForPicDispatch(Long id, BranchMst branchMst) {
		Session session=null;
		List<LicRequirementDtls> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicRequirementDtls.class,"lrd");
			criteria.createAlias("lrd.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lpaym");
			criteria.add(Restrictions.isNotNull("lrd.licBrnhHubPicMapDtls"));
			criteria.add(Restrictions.isNull("lrd.licBrnhHubPicPodDtls"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lrd.licBrnhHubPicMapDtls.id", id));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<LicRequirementDtls> findReqDtlsByPolicyDtlsId(LicPolicyDtls licPolicyDtls) {
		Session session=null;
		List<LicRequirementDtls> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicRequirementDtls.class, "lrd");
			criteria.createAlias("lrd.licMarkingToQcDtls", "lmtqd", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("lrd.licPolicyDtls", licPolicyDtls));

			list = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public Boolean updateForPosViewRejectionForRenewal(List<LicRequirementDtls> LicRequirementDtlsList) {
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 
			 for(LicRequirementDtls licRequirementDtls : LicRequirementDtlsList){
				 session.update(licRequirementDtls);
			 }
			 
			 tx.commit();
			 status = true;
		}catch(Exception e){
			e.printStackTrace();
			if(tx != null)tx.rollback();
			status=false;
		}finally {
           session.close();
       }
		return status;
	}

	@Override
	public List<Long> findDispatchListForPosView() {
		Session session = null;
		List<Long> id = null;
		try{
			session=sessionFactory.openSession();		
			Criteria criteria= session.createCriteria(LicRequirementDtls.class,"lrd");
			criteria.createAlias("lrd.licBrnhHubPicMapDtls", "lbhpmd");
			criteria.add(Restrictions.isNull("lrd.licBrnhHubPicPodDtls"));
			criteria.add(Restrictions.eq("lrd.dispatchReadyFlag", "Y"));
			criteria.add(Restrictions.eq("lrd.branchRcvFlag", "Y"));
			criteria.add(Restrictions.eq("lrd.colFlag", "Y"));
			criteria.add(Restrictions.isNotNull("lrd.colDate"));
			criteria.add(Restrictions.isNotNull("lrd.colBy"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			criteria.setProjection(Projections.distinct(Projections.property("lbhpmd.id")));
			
			id = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public List<LicRequirementDtls> findRequirementForPrintReceiptByDate(Date businessDate, Long branchId) {
		Session session = null;
		List<LicRequirementDtls> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			criteria.createAlias("lrd.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lpm");
			criteria.createAlias("lpvm.licProductMst", "lipm");
			
			criteria.add(Restrictions.isNotNull("lrd.branchRcvFlag"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.isNotNull("lrd.actionType"));
			criteria.add(Restrictions.ne("lrd.actionType", "IR"));
			
			criteria.add(Restrictions.eq("loam.businessDate", businessDate));

			if(branchId!=null){
				criteria.add(Restrictions.eq("loam.branchMst.branchId", branchId));
			}
			
			list = criteria.list();
		}catch(Exception e){
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<LicRequirementDtls> findRequirementForPrintReceiptByApplicationNo(String applicationNo, Long branchId) {
		Session session = null;
		List<LicRequirementDtls> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			criteria.createAlias("lrd.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lpm");
			criteria.createAlias("lpvm.licProductMst", "lipm");
			
			criteria.add(Restrictions.isNotNull("lrd.branchRcvFlag"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.isNotNull("lrd.actionType"));
			criteria.add(Restrictions.ne("lrd.actionType", "IR"));
			
			criteria.add(Restrictions.eq("loam.oblApplNo", applicationNo));

			if(branchId!=null){
				criteria.add(Restrictions.eq("loam.branchMst.branchId", branchId));
			}
			
			list = criteria.list();
		}catch(Exception e){
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public Boolean updatePrintReceiptFlagInLicRequirementDtls(Long id) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;		
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			Query query = session.createQuery("UPDATE LicRequirementDtls SET printFlag = 'Y' WHERE id=:id");
			query.setParameter("id", id);
			int rows = query.executeUpdate();
			tx.commit();
			if(rows > 0){
				status = true;
			}
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status=false;
			
		}finally{
			session.close();
		}
		return status;
	}
}
