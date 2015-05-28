package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.AccessList;
import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchReqRcvMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicBranchReqRcvMstDao;

@Repository
public class LicBranchReqRcvMstDaoImpl implements Serializable,LicBranchReqRcvMstDao {
	private Logger log = Logger.getLogger(LicBranchReqRcvMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LicRequirementDtls> findPrendingRequrementAtBranch(Date bnsFromDate, Date bnsToDate, Long branchId) {
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
			criteria.add(Restrictions.isNull("lrd.branchRcvFlag"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.isNotNull("lrd.actionType"));
			criteria.add(Restrictions.ne("lrd.actionType", "IR"));
			
			if(bnsFromDate != null){
				criteria.add(Restrictions.ge("loam.businessDate", bnsFromDate));
			}
			
			if(bnsFromDate!=null){
				criteria.add(Restrictions.le("loam.businessDate", bnsToDate));
			}
			
			if(branchId!=null){
				criteria.add(Restrictions.eq("loam.branchMst.branchId", branchId));
			}
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBranchReqRcvMstDaoImpl findPrendingRequrementAtBranch Error", e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public Boolean save(LicBranchReqRcvMst licBranchReqRcvMst) {
		Boolean status=false;
		Session session = null;
		Transaction tx = null;
		try{
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 session.save(licBranchReqRcvMst);
			 tx.commit();
			 status=true;
		}catch(Exception e){
			 status = false;
			 log.info("LicBranchReqRcvMstDaoImpl save Error", e);
		}finally {
            session.close();
        }
		return status;
	}

	public List<LicBranchReqRcvMst> findRequirementForDispatch(Date businessFromDate,Date businessToDate,BranchMst branchMst){
		Session session = null;
		List<LicBranchReqRcvMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicBranchReqRcvMst.class,"lbrrm");
			criteria.createAlias("lbrrm.licRequirementDtls", "lrd");
			criteria.createAlias("lrd.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lpaym");
			
			criteria.add(Restrictions.ge("loam.businessDate", businessFromDate));
			criteria.add(Restrictions.le("loam.businessDate", businessToDate));
			criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			criteria.add(Restrictions.isNull("lbrrm.licBrnhHubPicMapDtls"));
			criteria.add(Restrictions.eq("lrd.reqType","D"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBranchReqRcvMstDaoImpl findRequirementForDispatch Error", e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<LicBranchReqRcvMst> findRequirementByDispatchList(Long dispatchListNo, BranchMst branchMst) {
		Session session=null;
		List<LicBranchReqRcvMst> licBranchReqRcvMsts=null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicBranchReqRcvMst.class,"lbrrm");
			criteria.createAlias("lbrrm.licRequirementDtls", "lrd");
			criteria.createAlias("lrd.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lpaym");
			criteria.add(Restrictions.isNotNull("lbrrm.licBrnhHubPicMapDtls"));
			criteria.add(Restrictions.isNull("lbrrm.licBrnhHubPicPodDtls"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.eq("lbrrm.licBrnhHubPicMapDtls.id", dispatchListNo));
			criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lid.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("bm.activeFlag", "Y"));
			criteria.add(Restrictions.eq("lbt.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpaym.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lbrrm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			licBranchReqRcvMsts = criteria.list();
		}catch(Exception e){
			log.info("LicBranchReqRcvMstDaoImpl findRequirementByDispatchList Error", e);
		}finally{
			session.close();
		}
		return licBranchReqRcvMsts;
	}
}
