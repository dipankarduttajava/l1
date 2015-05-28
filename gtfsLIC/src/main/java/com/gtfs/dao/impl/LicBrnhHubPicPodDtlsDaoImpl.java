package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicBrnhHubPicPodDtlsDao;
import com.gtfs.dto.LicOblApplicationMstDto;

@Repository
public class LicBrnhHubPicPodDtlsDaoImpl implements LicBrnhHubPicPodDtlsDao,Serializable{
	private Logger log = Logger.getLogger(LicBrnhHubPicPodDtlsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Long saveForBranchHubPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls){
		Session session=null;
		Transaction tx=null;
		Long id=0l;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			id=(Long) session.save(licBrnhHubPicPodDtls);
			tx.commit();
			
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			id=0l;
			log.info("LicBrnhHubPicPodDtlsDaoImpl saveForBranchHubPodDtls Error", e);
		}finally{
			session.close();
		}
		return id;
	}
	
	
	public Long saveForHubPicPodDtls(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls){
		Session session=null;
		Transaction tx=null;
		Long id=0l;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			id=(Long) session.save(licBrnhHubPicPodDtls);
			tx.commit();			
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			id=0l;
			log.info("LicBrnhHubPicPodDtlsDaoImpl saveForHubPicPodDtls Error", e);
		}finally{
			session.close();
		}
		return id;
	}
	
	public List<Object> findDistinctPodDtlsForConsldMarking(List<LicHubMst> licHubMsts){
		Session session = null;
		List<Object> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
			criteria.createAlias("loam.brnhHubPodDtls", "bhpd");
			criteria.createAlias("bhpd.employee", "emp", JoinType.LEFT_OUTER_JOIN);

			criteria.add(Restrictions.in("loam.oblHubMst", licHubMsts));
			criteria.add(Restrictions.isNull("loam.licMarkingToQcDtls"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));

			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("bhpd.podNo"));
			projList.add(Projections.property("bhpd.podDate"));
			projList.add(Projections.property("emp.userName"));
			projList.add(Projections.property("emp.userid"));
			projList.add(Projections.property("bhpd.id"));
			projList.add(Projections.property("bhpd.courierName"));
			criteria.setProjection(Projections.distinct(projList));

			list = criteria.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findDistinctPodDtlsForConsldMarking Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
	
	public List<Object> findDistinctPodDtlsForConsldMarkingForRnl(List<LicHubMst> licHubMsts){
		Session session = null;
		List<Object> list = null;
		try{
			session = sessionFactory.openSession();
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("bhpd.podNo"));
			projList.add(Projections.property("bhpd.podDate"));
			projList.add(Projections.property("emp.userName"));
			projList.add(Projections.property("emp.userid"));
			projList.add(Projections.property("bhpd.id"));
			projList.add(Projections.property("bhpd.courierName"));
			
			Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
			criteria.createAlias("lpd.brnhHubPodDtls", "bhpd");
			criteria.createAlias("bhpd.employee", "emp", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.in("lpd.oblHubMst", licHubMsts));
			criteria.add(Restrictions.isNull("lpd.licMarkingToQcDtls"));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
            
			criteria.setProjection(Projections.distinct(projList));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findDistinctPodDtlsForConsldMarkingForRnl Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
	public List<LicOblApplicationMst> findApplicationByPodId(Long id){
		Session session = null;
		List<LicOblApplicationMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicOblApplicationMst.class,"loam");
			criteria.createAlias("loam.brnhHubPodDtls", "bhpd");
			criteria.add(Restrictions.eq("bhpd.id", id));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findApplicationByPodId Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public Boolean saveForBranchHubPodDtlsForReq(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		Session session=null;
		Transaction tx=null;
		Boolean status = false;
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.save(licBrnhHubPicPodDtls);
			tx.commit();
			status = true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status = false;
			log.info("LicBrnhHubPicPodDtlsDaoImpl saveForBranchHubPodDtlsForReq Error", e);
		}finally{
			session.close();
		}
		return status;
	}
	
	
	public List<Object> findDistinctPodDtlsConsldMarkingForReq(List<LicHubMst> licHubMsts){
		Session session = null;
		List<Object> list = null;
		try{
			session = sessionFactory.openSession();
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("lbhppd.podNo"));
			projList.add(Projections.property("lbhppd.podDate"));
			projList.add(Projections.property("emp.userName"));
			projList.add(Projections.property("emp.userid"));
			projList.add(Projections.property("lbhppd.id"));
			projList.add(Projections.property("lbhppd.courierName"));
			Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			criteria.createAlias("lrd.licBranchReqRcvMsts", "lbrrm");
			criteria.createAlias("lbrrm.licBrnhHubPicPodDtls", "lbhppd");
			criteria.createAlias("lbhppd.employee", "emp", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));
			criteria.add(Restrictions.isNull("lrd.licMarkingToQcDtls"));
			criteria.add(Restrictions.eq("lrd.branchHubPodFlag","Y"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			criteria.setProjection(Projections.distinct(projList));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findDistinctPodDtlsConsldMarkingForReq Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public List<LicRequirementDtls> findApplicationByPodIdForReq(Long id) {
		Session session = null;
		List<LicRequirementDtls> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			criteria.createAlias("lrd.licBranchReqRcvMsts", "lbrrm");
			criteria.createAlias("lbrrm.licBrnhHubPicPodDtls", "lbhppd");
			criteria.add(Restrictions.eq("lbhppd.id", id));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findApplicationByPodIdForReq Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public Long saveForBranchHubPodDtlsForRenewal(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		Session session=null;
		Transaction tx=null;
		Long id=0l;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			id=(Long) session.save(licBrnhHubPicPodDtls);
			tx.commit();
			
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			id=0l;
			log.info("LicBrnhHubPicPodDtlsDaoImpl saveForBranchHubPodDtlsForRenewal Error", e);
		}finally{
			session.close();
		}
		return id;
	}


	@Override
	public List<LicPolicyDtls> findPolicyDtlsByPodId(Long PodId) {
		Session session = null;
		List<LicPolicyDtls> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicPolicyDtls.class,"lpd");
			criteria.createAlias("lpd.brnhHubPodDtls", "bhpd");
			criteria.add(Restrictions.eq("bhpd.id", PodId));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findPolicyDtlsByPodId Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public Long updatePicPodDtlsForRnl(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		Session session=null;
		Transaction tx=null;
		Long id=0l;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			id=(Long) session.save(licBrnhHubPicPodDtls);
			tx.commit();			
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			id=0l;
			log.info("LicBrnhHubPicPodDtlsDaoImpl updatePicPodDtlsForRnl Error", e);
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Long updatePicPodDtlsForRenewalPosView(LicBrnhHubPicPodDtls licBrnhHubPicPodDtls) {
		Session session=null;
		Transaction tx=null;
		Long id=0l;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			id=(Long) session.save(licBrnhHubPicPodDtls);
			tx.commit();			
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			id=0l;
			log.info("LicBrnhHubPicPodDtlsDaoImpl updatePicPodDtlsForRenewalPosView Error", e);
		}finally{
			session.close();
		}
		return id;
	}


	@Override
	public List<Object> findDistinctPodDtlsForConsldMarkingForPos(List<LicHubMst> licHubMsts) {
		Session session = null;
		List<Object> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			criteria.createAlias("lrd.licBrnhHubPicPodDtls", "lbhpd");
			criteria.createAlias("lbhpd.employee", "emp", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));
			criteria.add(Restrictions.isNull("lrd.licMarkingToQcDtls"));
			criteria.add(Restrictions.isNotNull("lrd.licBrnhHubPicPodDtls"));
			criteria.add(Restrictions.eq("lrd.branchHubPodFlag", "Y"));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("lbhpd.podNo"));
			projList.add(Projections.property("lbhpd.podDate"));
			projList.add(Projections.property("emp.userName"));
			projList.add(Projections.property("emp.userid"));
			projList.add(Projections.property("lbhpd.id"));
			projList.add(Projections.property("lbhpd.courierName"));
            
			criteria.setProjection(Projections.distinct(projList));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findDistinctPodDtlsForConsldMarkingForPos Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public List<LicRequirementDtls> findRequiremrntDtlsByPodIdForPos(Long PodId) {
		Session session = null;
		List<LicRequirementDtls> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicRequirementDtls.class,"lrd");
			criteria.createAlias("lrd.licBrnhHubPicPodDtls", "lbhpd");
			criteria.add(Restrictions.eq("lbhpd.id", PodId));
			criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findRequiremrntDtlsByPodIdForPos Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public List<LicOblApplicationMstDto> findBranchHubPodReport(Date businessFromDate, Date businessToDate, BranchMst branchMst) {
		Session session = null;
		List<LicOblApplicationMstDto> list = null;
		try{
			session = sessionFactory.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("SELECT "
					+ "lbhppd.POD_NO AS podListNo,"
					+ "hm.HUB_NAME AS hubName,"
					+ "lbhpmd.ID AS dispatchListNo,"
					+ "loam.ID AS id,"
					+ "loam.OBL_APPL_NO AS oblApplNo,"
					+ "loam.BUSINESS_DATE AS businessDate,"
					+ "lid.NAME AS insuredName,"
					+ "lpd.NAME AS proposerName,"
					+ "bm.BRANCH_NAME AS branchName,"
					+ "lipm.PROD_DESC AS prodDesc,"
					+ "lpvm.BASIC_PREM AS basicPrem,"
					+ "lpvm.TAX_ON_PREM AS taxOnPrem,"
					+ "lpm.PAY_MODE AS payMode,"
					+ "lpvm.PAY_NATURE AS payNature,"
					+ "lpm.TOTAL_RECEIVED AS totalReceived,"
					+ "lpvm.TOTAL_AMT AS totalAmt,"
					+ "NVL((SELECT SUM(lpd1.AMOUNT) FROM LIC_PAYMENT_DTLS lpd1,LIC_PAYMENT_MST lpm1 WHERE lpd1.LIC_PAYMENT_MST_ID=lpm1.ID AND lpd1.PAY_MODE  ='C' AND (lpd1.SHORT_PREM_FLAG   IS NULL) AND lpd1.LIC_PAYMENT_MST_ID  =lpm.ID ), 0.0) AS cashAmount,"
					+ "NVL((SELECT SUM(lpd2.AMOUNT) FROM LIC_PAYMENT_DTLS lpd2,LIC_PAYMENT_MST lpm2 WHERE lpd2.LIC_PAYMENT_MST_ID=lpm2.ID AND (lpd2.PAY_MODE ='Q' OR lpd2.PAY_MODE ='D') AND lpd2.LIC_PAYMENT_MST_ID  =lpm.ID AND lpd2.PAYEE_NAME ='LIFE INSURANCE CORPORATION OF INDIA (LICI)' ), 0.0) AS insAmount,"
					+ "(SELECT wm_concat(lpd3.DRAFT_CHQ_NO) || ' bank: ' || wm_concat(lpd3.DRAFT_CHQ_BANK) || 'Dated: ' || wm_concat(TO_CHAR(lpd3.DRAFT_CHQ_DATE,'dd/mm/yyyy')) || ' in favour of ' || wm_concat(lpd3.PAYEE_NAME) FROM LIC_PAYMENT_DTLS lpd3,LIC_PAYMENT_MST lpm3 WHERE lpd3.LIC_PAYMENT_MST_ID=lpm3.ID AND (lpd3.PAY_MODE ='Q' OR lpd3.PAY_MODE ='D') AND lpd3.LIC_PAYMENT_MST_ID  =lpm.ID AND lpd3.PAYEE_NAME ='LIFE INSURANCE CORPORATION OF INDIA (LICI)') AS chequeNo "
					+ "FROM LIC_OBL_APPLICATION_MST loam "
					+ "INNER JOIN LIC_BUSINESS_TXN lbt "
					+ "ON loam.ID = lbt.ID "
					+ "INNER JOIN LIC_PAYMENT_MST lpm "
					+ "ON lbt.ID = lpm.ID "
					+ "INNER JOIN LIC_INSURED_DTLS lid "
					+ "ON loam.ID = lid.ID "
					+ "INNER JOIN LIC_PROPOSER_DTLS lpd "
					+ "ON loam.ID = lpd.ID "
					+ "INNER JOIN LIC_PRODUCT_VALUE_MST lpvm "
					+ "ON loam.LIC_PRODUCT_VALUE_MST_ID = lpvm.ID "
					+ "INNER JOIN LIC_PRODUCT_MST lipm "
					+ "ON lpvm.LIC_PRODUCT_MST_ID = lipm.ID "
					+ "INNER JOIN BRANCH_MST bm "
					+ "ON loam.BRANCH_MST_ID = bm.BRANCH_ID "
					+ "INNER JOIN AGENT_MST am "
					+ "ON loam.AGENT_MST_ID = am.AG_CODE "
					+ "INNER JOIN LIC_BRNH_HUB_PIC_MAP_DTLS lbhpmd "
					+ "ON loam.BRNH_HUB_MAP_DTLS_ID = lbhpmd.ID "
					+ "INNER JOIN LIC_HUB_MST hm "
					+ "ON loam.OBL_HUB_MST_ID = hm.ID "
					+ "INNER JOIN LIC_BRNH_HUB_PIC_POD_DTLS lbhppd "
					+ "ON loam.BRNH_HUB_POD_DTLS_ID = lbhppd.ID "
					+ "WHERE "
					+ "loam.BRNH_HUB_MAP_DTLS_ID IS NOT NULL "
					+ "AND loam.BRNH_HUB_POD_DTLS_ID IS NOT NULL "
					+ "AND loam.LIC_PIS_MST_ID IS NOT NULL "
					+ "AND loam.BUSINESS_DATE >= :businessFromDate "
					+ "AND loam.BUSINESS_DATE <= :businessToDate "
					+ "AND loam.SECONDARY_ENTRY_FLAG = 'Y' "
					+ "AND loam.BRANCH_MST_ID = :branchMstId "
					+ "AND lpvm.DELETE_FLAG = 'N' "
					+ "AND lpd.DELETE_FLAG = 'N' "
					+ "AND lid.DELETE_FLAG = 'N' "
					+ "AND lpm.DELETE_FLAG = 'N' "
					+ "AND loam.DELETE_FLAG = 'N' "
					+ "AND loam.MIGRATION_FLAG = 'N'");
			
			
			query.addScalar("hubName", StringType.INSTANCE);
			query.addScalar("podListNo", StringType.INSTANCE);
			query.addScalar("dispatchListNo", StringType.INSTANCE);
			query.addScalar("id", LongType.INSTANCE);
			query.addScalar("oblApplNo", StringType.INSTANCE);
			query.addScalar("businessDate", DateType.INSTANCE);
			query.addScalar("insuredName", StringType.INSTANCE);
			query.addScalar("proposerName", StringType.INSTANCE);
			query.addScalar("branchName", StringType.INSTANCE);
			query.addScalar("prodDesc", StringType.INSTANCE);
			query.addScalar("basicPrem", DoubleType.INSTANCE);
			query.addScalar("taxOnPrem", DoubleType.INSTANCE);
			query.addScalar("payMode", StringType.INSTANCE);
			query.addScalar("payNature", StringType.INSTANCE);
			query.addScalar("totalReceived",DoubleType.INSTANCE);
			query.addScalar("totalAmt", DoubleType.INSTANCE);
			query.addScalar("chequeNo", StringType.INSTANCE);
			query.addScalar("cashAmount",DoubleType.INSTANCE);
			query.addScalar("insAmount", DoubleType.INSTANCE);
			
			query.setResultTransformer(Transformers.aliasToBean(LicOblApplicationMstDto.class));
            query.setParameter("businessFromDate", businessFromDate, DateType.INSTANCE);
            query.setParameter("businessToDate", businessToDate, DateType.INSTANCE);
            query.setParameter("branchMstId", branchMst.getBranchId(), LongType.INSTANCE);
			list = query.list();
			
			/*
			Criteria criteria= session.createCriteria(LicOblApplicationMst.class,"loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("loam.oblHubMst", "ohm");
			criteria.createAlias("loam.brnhHubMapDtls", "bhmd");
			criteria.createAlias("loam.brnhHubPodDtls", "bhpd");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lpaym");
			
			criteria.add(Restrictions.isNotNull("loam.brnhHubMapDtls"));
			criteria.add(Restrictions.isNotNull("loam.brnhHubPodDtls"));
			criteria.add(Restrictions.ge("loam.businessDate", businessFromDate));
			criteria.add(Restrictions.le("loam.businessDate", businessToDate));
			criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lid.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("bm.activeFlag", "Y"));
			criteria.add(Restrictions.eq("lbt.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpaym.deleteFlag", "N"));
			
			list = criteria.list();
			*/
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationByDispatchList Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public List<LicOblApplicationMst> findPicPodReport(Date businessFromDate, Date businessToDate, BranchMst branchMst) {
		Session session=null;
		List<LicOblApplicationMst> list = null;
		try{
			session=sessionFactory.openSession();
			Query query = session.createQuery("SELECT loam FROM LicOblApplicationMst as loam inner join "
                    + "loam.licBusinessTxn as lbt inner join "
                    + "lbt.licPaymentMst as lpm inner join "
                    + "loam.licProposerDtls as lpd inner join "
                    + "loam.licInsuredDtls as lid inner join fetch "
                    + "loam.licProductValueMst as lpvm inner join fetch "
                    + "loam.branchMst as bm inner join "
                    + "loam.licPremApplMappings as lpam inner join fetch "
                    + "loam.picBranchMstId as pbm inner join fetch "
                    + "loam.oblHubMst as ohm inner join fetch "
                    + "loam.hubPicMapDtls as hpmd inner join fetch "
                    + "lpvm.licProductMst as lipm "

					+ "WHERE "
					+ "loam.hubPicPodDtls IS NOT NULL "
					+ "AND loam.businessDate >= :businessFromDate "
					+ "AND loam.businessDate <= :businessToDate "
					+ "AND loam.branchMst = :branchMst "
					+ "AND loam.deleteFlag = 'N' "
            		+ "AND loam.migrationFlag = 'N' "
            		+ "AND lpvm.deleteFlag = 'N' "
            		+ "AND lpd.deleteFlag = 'N' "
            		+ "AND lid.deleteFlag = 'N' "
            		+ "AND lpm.deleteFlag = 'N' ");
            
            
            query.setParameter("businessFromDate", businessFromDate);
            query.setParameter("businessToDate", businessToDate);
            query.setParameter("branchMst", branchMst);
            list = (List<LicOblApplicationMst>) query.list();
			
			
			/*Criteria criteria= session.createCriteria(LicOblApplicationMst.class,"loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lpaym");
			criteria.createAlias("loam.picBranchMstId", "pbm");
            criteria.createAlias("loam.oblHubMst", "ohm");
            
			criteria.add(Restrictions.isNotNull("loam.hubPicMapDtls"));
			criteria.add(Restrictions.isNull("loam.hubPicPodDtls"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.eq("loam.hubPicMapDtls.id", id));
			
			licOblApplicationMstList=criteria.list();*/
		}catch(Exception e){
			log.info("LicBrnhHubPicPodDtlsDaoImpl findPicPodReport Error ", e);
		}finally{
			session.close();
		}
		return list;
	}
}
