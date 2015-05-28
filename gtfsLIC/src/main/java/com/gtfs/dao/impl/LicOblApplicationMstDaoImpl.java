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
import org.hibernate.criterion.Order;
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
import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.dao.interfaces.LicOblApplicationMstDao;
import com.gtfs.dto.LicOblApplicationMstDto;

@Repository
public class LicOblApplicationMstDaoImpl implements LicOblApplicationMstDao,Serializable{
	private Logger log = Logger.getLogger(LicOblApplicationMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Boolean insertDataForSecondaryDataEntry(LicOblApplicationMst licOblApplicationMst,LicPrintRcptDtls licPrintRcptDtls){
		Session session=null;
		Transaction tx=null;
		Boolean status=false;
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.update(licOblApplicationMst);
			session.saveOrUpdate(licPrintRcptDtls);
			tx.commit();
			status=true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status=false;
			log.info("LicOblApplicationMstDaoImpl insertDataForSecondaryDataEntry Error", e);
		}finally{
			session.close();
		}
		return status;
	}
	
	
	public Long insertDataForPreliminaryDataEntry(LicOblApplicationMst licOblApplicationMst){
		Session session=null;
		Transaction tx=null;
		Long row=0l;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			row = (Long) session.save(licOblApplicationMst);
			tx.commit();
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			row=0l;
			log.info("LicOblApplicationMstDaoImpl insertDataForPreliminaryDataEntry Error", e);
		}finally{
			session.close();
		}
		return row;
	}
	
	public List<LicOblApplicationMst> findApplicationForSecondaryDataEntryByDate(Date date, BranchMst branchMst){
		Session session=null;
		List<LicOblApplicationMst> licOblApplicationMstList=null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicOblApplicationMst.class,"loam");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("lbt.licPaymentMst", "lpym");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("lid.ageProofNature", "apn", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("lid.identityProof", "ip", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("lid.addrProof", "ap", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("lid.incomeProof", "icp", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("loam.licInsuredBankDtls", "libd", JoinType.LEFT_OUTER_JOIN);
			
			criteria.add(Restrictions.eq("loam.businessDate", date));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lid.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			licOblApplicationMstList=criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationForSecondaryDataEntryByDate Error", e);
		}finally{
			session.close();
		}
		return licOblApplicationMstList;
	}
	
	
	public List<LicOblApplicationMst> findApplicationForSecondaryDataEntryByApplicationNo(String applicationNo, BranchMst branchMst){
		Session session = null;
		List<LicOblApplicationMst> licOblApplicationMstList = null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicOblApplicationMst.class,"loam");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("lbt.licPaymentMst", "lpym");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.add(Restrictions.eq("loam.oblApplNo", applicationNo));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lid.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            
			licOblApplicationMstList=criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationForSecondaryDataEntryByApplicationNo Error", e);
		}finally{
			session.close();
		}
		return licOblApplicationMstList;
	}
	

	public List<LicPolicyMst> findApplicationByApplicationNoForReqirement(String applicationNo){
		Session session = null;
		List<LicPolicyMst> list = null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicPolicyMst.class,"lipm");
			criteria.createAlias("lipm.licOblApplicationMst", "loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.picBranchMstId", "pbmi");
			//criteria.add(Restrictions.eq("lipm.policyStatus", "Q"));
			criteria.add(Restrictions.eq("loam.oblApplNo", applicationNo));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lid.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			criteria.add(Restrictions.isNotNull("loam.hubPicPodDtls"));			
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            
			list=criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationByApplicationNoForReqirement Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
	
	public List<LicOblApplicationMstDto> findDispatchApplicationsByBusinessDate(Date fromDate, Date toDate, BranchMst branchMst){
		Session session = null;
		List<LicOblApplicationMstDto> list=null;
		try{
			session=sessionFactory.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(
					"SELECT "
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
					+ "FROM "
					+ "LIC_OBL_APPLICATION_MST loam "
					+ "INNER JOIN LIC_BUSINESS_TXN lbt "
					+ "ON loam.ID=lbt.ID "
					+ "INNER JOIN LIC_PAYMENT_MST lpm "
					+ "ON lbt.ID=lpm.ID "
					+ "INNER JOIN LIC_INSURED_DTLS lid "
					+ "ON loam.ID=lid.ID "
					+ "INNER JOIN LIC_PROPOSER_DTLS lpd "
					+ "ON loam.ID=lpd.ID "
					+ "INNER JOIN LIC_PRODUCT_VALUE_MST lpvm "
					+ "ON loam.LIC_PRODUCT_VALUE_MST_ID=lpvm.ID "
					+ "INNER JOIN LIC_PRODUCT_MST lipm "
					+ "ON lpvm.LIC_PRODUCT_MST_ID=lipm.ID "
					+ "INNER JOIN BRANCH_MST bm "
					+ "ON loam.BRANCH_MST_ID=bm.BRANCH_ID "
					+ "INNER JOIN AGENT_MST am "
					+ "ON loam.AGENT_MST_ID    =am.AG_CODE "
					+ "WHERE "
					+ "loam.BRNH_HUB_MAP_DTLS_ID is null "
					+ "and loam.LIC_PIS_MST_ID is not null "
					+ "and loam.BUSINESS_DATE >= :fromDate "
					+ "and loam.BUSINESS_DATE <= :toDate "
					+ "and loam.SECONDARY_ENTRY_FLAG = 'Y' "
					+ "and loam.BRANCH_MST_ID = :branchMstId "
					+ "and lpvm.DELETE_FLAG = 'N' "
					+ "and lpd.DELETE_FLAG = 'N' "
					+ "and lid.DELETE_FLAG = 'N' "
					+ "and lpm.DELETE_FLAG = 'N' "
					+ "and loam.DELETE_FLAG = 'N' "
					+ "and loam.MIGRATION_FLAG = 'N'");
			
			query.addScalar("id",LongType.INSTANCE);
			query.addScalar("oblApplNo",StringType.INSTANCE);
			query.addScalar("businessDate",DateType.INSTANCE);
			query.addScalar("insuredName",StringType.INSTANCE);
			query.addScalar("proposerName",StringType.INSTANCE);
			query.addScalar("branchName",StringType.INSTANCE);
			query.addScalar("prodDesc",StringType.INSTANCE);
			query.addScalar("basicPrem",DoubleType.INSTANCE);
			query.addScalar("taxOnPrem",DoubleType.INSTANCE);
			query.addScalar("payMode",StringType.INSTANCE);
			query.addScalar("payNature",StringType.INSTANCE);
			query.addScalar("totalReceived",DoubleType.INSTANCE);
			query.addScalar("totalAmt",DoubleType.INSTANCE);
			query.addScalar("chequeNo",StringType.INSTANCE);
			query.addScalar("cashAmount",DoubleType.INSTANCE);
			query.addScalar("insAmount",DoubleType.INSTANCE);
			
			query.setResultTransformer(Transformers.aliasToBean(LicOblApplicationMstDto.class));
            query.setParameter("fromDate",fromDate,DateType.INSTANCE);
            query.setParameter("toDate",toDate,DateType.INSTANCE);
            query.setParameter("branchMstId", branchMst.getBranchId(),LongType.INSTANCE);
			list = query.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findDispatchApplicationsByBusinessDate Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
	public List<LicOblApplicationMstDto> findApplicationByDispatchListAndBusinessDate(Date fromDate,Date toDate, Long id, BranchMst branchMst){
		Session session=null;
		List<LicOblApplicationMstDto> list = null;
		try{
			session=sessionFactory.openSession();			
			SQLQuery query = (SQLQuery) session.createSQLQuery(
					"SELECT "
					+ "loam.ID AS id,"
					+ "lbhpmd.id AS dispatchListNo,"
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
					+ "FROM "
					+ "LIC_OBL_APPLICATION_MST loam "
					+ "INNER JOIN LIC_BUSINESS_TXN lbt "
					+ "ON loam.ID=lbt.ID "
					+ "INNER JOIN LIC_PAYMENT_MST lpm "
					+ "ON lbt.ID=lpm.ID "
					+ "INNER JOIN LIC_INSURED_DTLS lid "
					+ "ON loam.ID=lid.ID "
					+ "INNER JOIN LIC_PROPOSER_DTLS lpd "
					+ "ON loam.ID=lpd.ID "
					+ "INNER JOIN LIC_PRODUCT_VALUE_MST lpvm "
					+ "ON loam.LIC_PRODUCT_VALUE_MST_ID=lpvm.ID "
					+ "INNER JOIN LIC_PRODUCT_MST lipm "
					+ "ON lpvm.LIC_PRODUCT_MST_ID=lipm.ID "
					+ "INNER JOIN BRANCH_MST bm "
					+ "ON loam.BRANCH_MST_ID=bm.BRANCH_ID "
					+ "INNER JOIN AGENT_MST am "
					+ "ON loam.AGENT_MST_ID    =am.AG_CODE "
					+ "INNER JOIN LIC_BRNH_HUB_PIC_MAP_DTLS lbhpmd "
					+ "ON loam.BRNH_HUB_MAP_DTLS_ID = lbhpmd.id "
					+ "WHERE "
					+ "loam.BRNH_HUB_MAP_DTLS_ID is not null "
					+ "and loam.BRNH_HUB_POD_DTLS_ID is null "
					+ "and loam.LIC_PIS_MST_ID is not null "
					+ "and ((loam.BUSINESS_DATE >= :fromDate and :fromDate is not null) or (:fromDate is null and 1=1)) "
					+ "and ((loam.BUSINESS_DATE <= :toDate and :toDate is not null) or (:toDate is null and 1=1)) "
					+ "and ((lbhpmd.id = :id and :id is not null) or (:id is null and 1=1)) "
					+ "and loam.SECONDARY_ENTRY_FLAG = 'Y' "
					+ "and loam.BRANCH_MST_ID = :branchMstId "
					+ "and lpvm.DELETE_FLAG = 'N' "
					+ "and lpd.DELETE_FLAG = 'N' "
					+ "and lid.DELETE_FLAG = 'N' "
					+ "and lpm.DELETE_FLAG = 'N' "
					+ "and loam.DELETE_FLAG = 'N' "
					+ "and loam.MIGRATION_FLAG = 'N'");
			
			query.addScalar("id",LongType.INSTANCE);
			query.addScalar("oblApplNo",StringType.INSTANCE);
			query.addScalar("businessDate",DateType.INSTANCE);
			query.addScalar("insuredName",StringType.INSTANCE);
			query.addScalar("proposerName",StringType.INSTANCE);
			query.addScalar("branchName",StringType.INSTANCE);
			query.addScalar("prodDesc",StringType.INSTANCE);
			query.addScalar("basicPrem",DoubleType.INSTANCE);
			query.addScalar("taxOnPrem",DoubleType.INSTANCE);
			query.addScalar("payMode",StringType.INSTANCE);
			query.addScalar("payNature",StringType.INSTANCE);
			query.addScalar("totalReceived",DoubleType.INSTANCE);
			query.addScalar("totalAmt",DoubleType.INSTANCE);
			query.addScalar("chequeNo",StringType.INSTANCE);
			query.addScalar("cashAmount",DoubleType.INSTANCE);
			query.addScalar("insAmount",DoubleType.INSTANCE);
			query.addScalar("dispatchListNo",StringType.INSTANCE);
			
			query.setResultTransformer(Transformers.aliasToBean(LicOblApplicationMstDto.class));
            query.setParameter("fromDate",fromDate,DateType.INSTANCE);
            query.setParameter("toDate",toDate,DateType.INSTANCE);
            query.setParameter("id",id,LongType.INSTANCE);
            query.setParameter("branchMstId", branchMst.getBranchId());
            
			list = query.list();
			
			
			
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationByDispatchList Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
	
	public  List<LicOblApplicationMst> findApplicationByDispatchListForPicDispatch(Long id, BranchMst branchMst){
		Session session=null;
		List<LicOblApplicationMst> licOblApplicationMstList=null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicOblApplicationMst.class,"loam");
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
			
			licOblApplicationMstList=criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationByDispatchListForPicDispatch Error", e);
		}finally{
			session.close();
		}
		return licOblApplicationMstList;
	}
	
	public List<Long> findPodApplicationsForReqirement(Long id){
		Session session = null;
		List<Long> podId = null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicBrnhHubPicMapDtls.class,"lbhpmd");
			criteria.createAlias("lbhpmd.licBranchReqRcvMsts", "lbrrm");
			criteria.createAlias("lbrrm.licRequirementDtls", "lrd");
			criteria.createAlias("lrd.licOblApplicationMst", "loam");
			criteria.createAlias("loam.branchMst", "bm");
			
			criteria.add(Restrictions.eq("bm.branchId", id));
			criteria.add(Restrictions.isNotNull("lbrrm.licBrnhHubPicMapDtls"));
			criteria.add(Restrictions.isNull("lbrrm.licBrnhHubPicPodDtls"));
			criteria.add(Restrictions.eq("lbrrm.deleteFlag", "N"));
			criteria.setProjection(Projections.distinct(Projections.property("lbhpmd.id")));
			podId = criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findPodApplicationsForReqirement Error", e);
		}finally{
			session.close();
		}
		return podId;
	}
	
	
	public List<Long> findPodApplications(Long id){
		Session session = null;
		List<Long> podId = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicBrnhHubPicMapDtls.class,"lbhpmd");
			criteria.createAlias("lbhpmd.licOblApplicationMsts", "loam");
			criteria.createAlias("loam.branchMst", "bm");
			
			criteria.add(Restrictions.eq("bm.id", id));
			criteria.add(Restrictions.isNotNull("loam.brnhHubMapDtls"));
			criteria.add(Restrictions.isNull("loam.brnhHubPodDtls"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.setProjection(Projections.distinct(Projections.property("lbhpmd.id")));			
			podId = criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findPodApplications Error", e);
		}finally{
			session.close();
		}
		return podId;
	}
	
	public List<Long> findPodApplicationsForPicDispatch(List<LicHubMst> licHubMsts){
		Session session = null;
		List<Long> id = null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicOblApplicationMst.class,"loam");
			criteria.createAlias("loam.hubPicMapDtls", "hpmd");
			criteria.add(Restrictions.isNull("loam.hubPicPodDtls"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
			criteria.add(Restrictions.in("loam.oblHubMst", licHubMsts));
			
			criteria.setProjection(Projections.distinct(Projections.property("hpmd.id")));
			
			id = criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findPodApplicationsForPicDispatch Error", e);
		}finally{
			session.close();
		}
		return id;
	}
	
	
	
	public List<LicPolicyMst> findStatusEntryReport(Date fromDate, Date toDate, String applicantName, Double premium, Double sumAssured, Long term, String applicationNo,String policyNo, String proposalNo, List<LicHubMst> licHubMsts){
		Session session=null;
		List<LicPolicyMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicPolicyMst.class,"lpm");
			criteria.createAlias("lpm.licOblApplicationMst", "loam", JoinType.RIGHT_OUTER_JOIN);
			criteria.createAlias("loam.licInsuredDtls", "lids");
			criteria.createAlias("loam.licProposerDtls", "lpds");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.picBranchMstId", "pbmi");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.oblHubMst", "ohm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lbt.licPaymentMst", "lipm");
			criteria.createAlias("lpvm.licProductMst", "lpmt");
			
			criteria.add(Restrictions.in("loam.oblHubMst",licHubMsts));
			//criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            //criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
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
	
	
	
	
	
	
	public List<LicPolicyMst> findApplicationForStatusEntry(Date fromDate, Date toDate, String applicantName, Double premium, Double sumAssured, Long term, String applicationNo,String policyNo, String proposalNo, List<LicHubMst> licHubMsts){
		Session session=null;
		List<LicPolicyMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicPolicyMst.class,"lpm");
			criteria.createAlias("lpm.licOblApplicationMst", "loam", JoinType.RIGHT_OUTER_JOIN);
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
			
			criteria.add(Restrictions.in("loam.oblHubMst",licHubMsts));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
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
	
	public LicOblApplicationMst findById(Long id){
		Session session=null;
		LicOblApplicationMst licOblApplicationMst=null;
		try{
			session = sessionFactory.openSession();
			licOblApplicationMst = (LicOblApplicationMst) session.get(LicOblApplicationMst.class, id);
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findById Error", e);
		}finally{
			session.close();
		}
		return licOblApplicationMst;
	}


	@Override
	public List<LicOblApplicationMst> findAll() {
		Session session=null;
		List<LicOblApplicationMst> list=null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicOblApplicationMst.class);
			criteria.add(Restrictions.eq("deleteFlag","N"));
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findAll Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public Boolean updatePrintReceiptFlagInLicOblApplMst(Long id) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;		
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			Query query = session.createQuery("UPDATE LicOblApplicationMst SET printFlag = 'Y' WHERE id=:id");
			query.setParameter("id", id);
			int rows = query.executeUpdate();
			tx.commit();
			if(rows > 0){
				status = true;
			}
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status=false;
			log.info("LicOblApplicationMstDaoImpl updatePrintReceiptFlagInLicOblApplMst Error", e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<LicOblApplicationMstDto> findApplicationForPrintReceiptByDate(Date date, BranchMst branchMst) {
		Session session = null;
		List<LicOblApplicationMstDto> list = null;
		try{
			session = sessionFactory.openSession();			
			SQLQuery query = session.createSQLQuery("SELECT "
					+ "licoblappl0_.ID AS id,"
					+ "licoblappl0_.OBL_APPL_NO AS oblApplNo,"
					+ "licoblappl0_.BUSINESS_DATE  AS businessDate,"
					+ "licinsured3_.NAME           AS insuredName,"
					+ "licpropose4_.NAME           AS proposerName,"
					+ "licproduct6_.PROD_DESC      AS prodDesc,"
					+ "licpayment2_.TOTAL_RECEIVED AS totalReceived,"
					+ "licoblappl0_.PRINT_FLAG     AS printFlag,"
					+ "branchmst7_.BRANCH_NAME     AS branchName,"
					+ "licproduct5_.PAY_NATURE     AS payNature,"
					+ "licproduct5_.POLICY_TERM    AS policyTerm,"
					+ "agentmst8_.AG_CODE          AS agCode,"
					+ "licoblappl0_.RECEIPT_NO       AS receiptNo,"
					+ "(SELECT wm_concat(licpayment11_.DRAFT_CHQ_NO) || ' bank: ' || wm_concat(licpayment11_.DRAFT_CHQ_BANK) || ' Dated: ' || wm_concat(TO_CHAR(licpayment11_.DRAFT_CHQ_DATE,'dd/mm/yyyy')) || ' in favour of ' || wm_concat(licpayment11_.PAYEE_NAME) FROM LIC_PAYMENT_DTLS licpayment11_,LIC_PAYMENT_MST licpayment12_ WHERE licpayment11_.LIC_PAYMENT_MST_ID=licpayment12_.ID AND (licpayment11_.PAY_MODE ='Q' OR licpayment11_.PAY_MODE ='D') AND licpayment11_.LIC_PAYMENT_MST_ID  =licpayment2_.ID AND licpayment11_.PAYEE_NAME ='LIFE INSURANCE CORPORATION OF INDIA (LICI)') AS chequeNo,"
					+ "NVL((SELECT SUM(licpayment9_.AMOUNT) FROM LIC_PAYMENT_DTLS licpayment9_,LIC_PAYMENT_MST licpayment10_ WHERE licpayment9_.LIC_PAYMENT_MST_ID=licpayment10_.ID AND licpayment9_.PAY_MODE  ='C' AND (licpayment9_.SHORT_PREM_FLAG   IS NULL) AND licpayment9_.LIC_PAYMENT_MST_ID  =licpayment2_.ID ), 0.0) AS cashAmount,"
					+ "NVL((SELECT SUM(licpayment11_.AMOUNT) FROM LIC_PAYMENT_DTLS licpayment11_,LIC_PAYMENT_MST licpayment12_ WHERE licpayment11_.LIC_PAYMENT_MST_ID=licpayment12_.ID AND (licpayment11_.PAY_MODE ='Q' OR licpayment11_.PAY_MODE ='D') AND licpayment11_.LIC_PAYMENT_MST_ID  =licpayment2_.ID AND licpayment11_.PAYEE_NAME ='LIFE INSURANCE CORPORATION OF INDIA (LICI)' ), 0.0) AS insAmount,"
					+ "NVL((SELECT SUM(licpayment13_.AMOUNT) FROM LIC_PAYMENT_DTLS licpayment13_, LIC_PAYMENT_MST licpayment14_ WHERE licpayment13_.LIC_PAYMENT_MST_ID=licpayment14_.ID AND (licpayment13_.PAY_MODE           ='Q' OR licpayment13_.PAY_MODE             ='D') AND licpayment13_.LIC_PAYMENT_MST_ID  =licpayment2_.ID AND licpayment13_.PAYEE_NAME          ='SARADA INSURANCE CONSULTANCY LTD' ), 0.0) AS tieAmount "
					
					+ "FROM " 
					+ "LIC_OBL_APPLICATION_MST licoblappl0_ "
					+ "INNER JOIN LIC_BUSINESS_TXN licbusines1_ "
					+ "ON licoblappl0_.ID=licbusines1_.ID "
					+ "INNER JOIN LIC_PAYMENT_MST licpayment2_ "
					+ "ON licbusines1_.ID=licpayment2_.ID "
					+ "INNER JOIN LIC_INSURED_DTLS licinsured3_ "
					+ "ON licoblappl0_.ID=licinsured3_.ID "
					+ "INNER JOIN LIC_PROPOSER_DTLS licpropose4_ "
					+ "ON licoblappl0_.ID=licpropose4_.ID "
					+ "INNER JOIN LIC_PRODUCT_VALUE_MST licproduct5_ "
					+ "ON licoblappl0_.LIC_PRODUCT_VALUE_MST_ID=licproduct5_.ID "
					+ "INNER JOIN LIC_PRODUCT_MST licproduct6_ "
					+ "ON licproduct5_.LIC_PRODUCT_MST_ID=licproduct6_.ID "
					+ "INNER JOIN BRANCH_MST branchmst7_ "
					+ "ON licoblappl0_.BRANCH_MST_ID=branchmst7_.BRANCH_ID "
					+ "INNER JOIN AGENT_MST agentmst8_ "
					+ "ON licoblappl0_.AGENT_MST_ID    =agentmst8_.AG_CODE "
					
					+ "WHERE "
					+ "licoblappl0_.BRANCH_MST_ID= :branchMstId "
					+ "AND licoblappl0_.DELETE_FLAG    ='N' "
					+ "AND licoblappl0_.MIGRATION_FLAG ='N' "
					+ "AND licoblappl0_.BUSINESS_DATE  = :businessDate");
			
			
			 
			 
			query.addScalar("oblApplNo", StringType.INSTANCE);
			query.addScalar("chequeNo", StringType.INSTANCE);
			query.addScalar("id", LongType.INSTANCE);
			query.addScalar("businessDate", DateType.INSTANCE);
			query.addScalar("insuredName", StringType.INSTANCE);
			query.addScalar("proposerName", StringType.INSTANCE);
			query.addScalar("prodDesc", StringType.INSTANCE);
			query.addScalar("totalReceived", DoubleType.INSTANCE);
			query.addScalar("printFlag", StringType.INSTANCE);
			query.addScalar("branchName", StringType.INSTANCE);
			query.addScalar("payNature", StringType.INSTANCE);
			query.addScalar("policyTerm", LongType.INSTANCE);
			query.addScalar("agCode", LongType.INSTANCE);
			query.addScalar("cashAmount", DoubleType.INSTANCE);
			query.addScalar("insAmount", DoubleType.INSTANCE);
			query.addScalar("tieAmount", DoubleType.INSTANCE);
			query.addScalar("receiptNo", StringType.INSTANCE);

			query.setParameter("branchMstId", branchMst.getBranchId());
			query.setParameter("businessDate", date);
			query.setResultTransformer(Transformers.aliasToBean(LicOblApplicationMstDto.class));
			list = query.list();
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationForPrintReceiptByDate Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
	public List<LicOblApplicationMstDto> findApplicationForPrintReceiptByApplicationNo(String applicationNo, BranchMst branchMst){
		Session session = null;
		List<LicOblApplicationMstDto> list = null;
		try{
			session = sessionFactory.openSession();			
			SQLQuery query = session.createSQLQuery("SELECT "
					+ "licoblappl0_.ID AS id,"
					+ "licoblappl0_.OBL_APPL_NO AS oblApplNo,"
					+ "licoblappl0_.BUSINESS_DATE  AS businessDate,"
					+ "licinsured3_.NAME           AS insuredName,"
					+ "licpropose4_.NAME           AS proposerName,"
					+ "licproduct6_.PROD_DESC      AS prodDesc,"
					+ "licpayment2_.TOTAL_RECEIVED AS totalReceived,"
					+ "licoblappl0_.PRINT_FLAG     AS printFlag,"
					+ "branchmst7_.BRANCH_NAME     AS branchName,"
					+ "licproduct5_.PAY_NATURE     AS payNature,"
					+ "licproduct5_.POLICY_TERM    AS policyTerm,"
					+ "agentmst8_.AG_CODE          AS agCode,"
					+ "licoblappl0_.RECEIPT_NO       AS receiptNo,"
					+ "(SELECT wm_concat(licpayment11_.DRAFT_CHQ_NO) || ' bank: ' || wm_concat(licpayment11_.DRAFT_CHQ_BANK) || ' Dated: ' || wm_concat(TO_CHAR(licpayment11_.DRAFT_CHQ_DATE,'dd/mm/yyyy')) || ' in favour of ' || wm_concat(licpayment11_.PAYEE_NAME) FROM LIC_PAYMENT_DTLS licpayment11_,LIC_PAYMENT_MST licpayment12_ WHERE licpayment11_.LIC_PAYMENT_MST_ID=licpayment12_.ID AND (licpayment11_.PAY_MODE ='Q' OR licpayment11_.PAY_MODE ='D') AND licpayment11_.LIC_PAYMENT_MST_ID  =licpayment2_.ID AND licpayment11_.PAYEE_NAME ='LIFE INSURANCE CORPORATION OF INDIA (LICI)') AS chequeNo,"
					+ "NVL((SELECT SUM(licpayment9_.AMOUNT) FROM LIC_PAYMENT_DTLS licpayment9_,LIC_PAYMENT_MST licpayment10_ WHERE licpayment9_.LIC_PAYMENT_MST_ID=licpayment10_.ID AND licpayment9_.PAY_MODE  ='C' AND (licpayment9_.SHORT_PREM_FLAG   IS NULL) AND licpayment9_.LIC_PAYMENT_MST_ID  =licpayment2_.ID ), 0.0) AS cashAmount,"
					+ "NVL((SELECT SUM(licpayment11_.AMOUNT) FROM LIC_PAYMENT_DTLS licpayment11_,LIC_PAYMENT_MST licpayment12_ WHERE licpayment11_.LIC_PAYMENT_MST_ID=licpayment12_.ID AND (licpayment11_.PAY_MODE ='Q' OR licpayment11_.PAY_MODE ='D') AND licpayment11_.LIC_PAYMENT_MST_ID  =licpayment2_.ID AND licpayment11_.PAYEE_NAME ='LIFE INSURANCE CORPORATION OF INDIA (LICI)' ), 0.0) AS insAmount,"
					+ "NVL((SELECT SUM(licpayment13_.AMOUNT) FROM LIC_PAYMENT_DTLS licpayment13_, LIC_PAYMENT_MST licpayment14_ WHERE licpayment13_.LIC_PAYMENT_MST_ID=licpayment14_.ID AND (licpayment13_.PAY_MODE           ='Q' OR licpayment13_.PAY_MODE             ='D') AND licpayment13_.LIC_PAYMENT_MST_ID  =licpayment2_.ID AND licpayment13_.PAYEE_NAME          ='SARADA INSURANCE CONSULTANCY LTD' ), 0.0) AS tieAmount "
					
					+ "FROM " 
					+ "LIC_OBL_APPLICATION_MST licoblappl0_ "
					+ "INNER JOIN LIC_BUSINESS_TXN licbusines1_ "
					+ "ON licoblappl0_.ID=licbusines1_.ID "
					+ "INNER JOIN LIC_PAYMENT_MST licpayment2_ "
					+ "ON licbusines1_.ID=licpayment2_.ID "
					+ "INNER JOIN LIC_INSURED_DTLS licinsured3_ "
					+ "ON licoblappl0_.ID=licinsured3_.ID "
					+ "INNER JOIN LIC_PROPOSER_DTLS licpropose4_ "
					+ "ON licoblappl0_.ID=licpropose4_.ID "
					+ "INNER JOIN LIC_PRODUCT_VALUE_MST licproduct5_ "
					+ "ON licoblappl0_.LIC_PRODUCT_VALUE_MST_ID = licproduct5_.ID "
					+ "INNER JOIN LIC_PRODUCT_MST licproduct6_ "
					+ "ON licproduct5_.LIC_PRODUCT_MST_ID = licproduct6_.ID "
					+ "INNER JOIN BRANCH_MST branchmst7_ "
					+ "ON licoblappl0_.BRANCH_MST_ID = branchmst7_.BRANCH_ID "
					+ "INNER JOIN AGENT_MST agentmst8_ "
					+ "ON licoblappl0_.AGENT_MST_ID = agentmst8_.AG_CODE "
					
					+ "WHERE "
					+ "licoblappl0_.BRANCH_MST_ID = :branchMstId "
					+ "AND licoblappl0_.DELETE_FLAG ='N' "
					+ "AND licoblappl0_.MIGRATION_FLAG ='N' "
					+ "AND licoblappl0_.OBL_APPL_NO = :applicationNo");
			 
			query.addScalar("oblApplNo", StringType.INSTANCE);
			query.addScalar("chequeNo", StringType.INSTANCE);
			query.addScalar("id", LongType.INSTANCE);
			query.addScalar("businessDate", DateType.INSTANCE);
			query.addScalar("insuredName", StringType.INSTANCE);
			query.addScalar("proposerName", StringType.INSTANCE);
			query.addScalar("prodDesc", StringType.INSTANCE);
			query.addScalar("totalReceived", DoubleType.INSTANCE);
			query.addScalar("printFlag", StringType.INSTANCE);
			query.addScalar("branchName", StringType.INSTANCE);
			query.addScalar("payNature", StringType.INSTANCE);
			query.addScalar("policyTerm", LongType.INSTANCE);
			query.addScalar("agCode", LongType.INSTANCE);
			query.addScalar("cashAmount", DoubleType.INSTANCE);
			query.addScalar("insAmount", DoubleType.INSTANCE);
			query.addScalar("tieAmount", DoubleType.INSTANCE);
			query.addScalar("receiptNo", StringType.INSTANCE);

			query.setParameter("branchMstId", branchMst.getBranchId());
			query.setParameter("applicationNo", applicationNo);
			query.setResultTransformer(Transformers.aliasToBean(LicOblApplicationMstDto.class));
			list = query.list();	
		}catch(Exception e){
			log.info("LicOblApplicationMstDaoImpl findApplicationForSecondaryDataEntryByApplicationNo Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public Boolean update(LicOblApplicationMst licOblApplicationMst) {
		Session session = null;
		Transaction tx = null;
		Boolean status = false;		
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.update(licOblApplicationMst);
			tx.commit();
			status = true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status=false;
			log.info("LicOblApplicationMstDaoImpl update Error", e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<LicOblApplicationMstDto> findBusinessReportByBusinessDate(Date fromDate, Date toDate) {
	Session session=null;
	List<LicOblApplicationMstDto> list = null;
	try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("select loam.oblApplNo as oblApplNo,"
				+ "loam.businessDate as businessDate,"
				+ "loam.finYr as fiscalYear,"
				+ "am.agCode as agCode,"
				+ "am.agName as agName,"
				+ "bm.branchName as branchName,"
				+ "lid.name as insuredName,"
				+ "lid.dob as insuredDob,"
				+ "lpd.name as proposerName,"
				+ "lpd.dob as proposerDob,"
				+ "lpm.prodDesc as prodDesc,"
				+ "lpvm.sumAssured as sumAssured,"
				+ "lpvm.payNature as payNature "
				+ "from "
				+"LicOblApplicationMst as loam "
				+"inner join loam.agentMst as am "
				+"inner join loam.branchMst as bm "
				+"inner join loam.licInsuredDtls as lid "
				+"inner join loam.licProposerDtls as lpd "
				+"inner join loam.licProductValueMst as lpvm "
				+"inner join lpvm.licProductMst as lpm "
				+"where loam.businessDate between :fromDate and :toDate");
		
		query.setResultTransformer(Transformers.aliasToBean(LicOblApplicationMstDto.class));
		
		query.setParameter("fromDate",fromDate);
		query.setParameter("toDate", toDate);
		
		list = query.list();
		
	}catch(Exception e){
		log.info("LicOblApplicationMstDaoImpl findBusinessReportByBusinessDate Error", e);
	}finally{
		session.close();
	}
		
		return list;
	}
	
	
	
}
