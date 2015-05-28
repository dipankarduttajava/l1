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
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.interfaces.LicBrnhHubPicMapDtlsDao;
import com.gtfs.dto.LicOblApplicationMstDto;

@Repository
public class LicBrnhHubPicMapDtlsDaoImpl implements LicBrnhHubPicMapDtlsDao,Serializable{
	private Logger log = Logger.getLogger(LicBrnhHubPicMapDtlsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Long saveForBranchHubDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls){
		Session session=null;
		Transaction tx=null;
		Long id=0l;
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			id=(Long) session.save(licBrnhHubPicMapDtls);
			tx.commit();
			
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			id=0l;
			log.info("LicBrnhHubPicMapDtlsDaoImpl saveForBranchHubDispatchList Error", e);
		}finally{
			session.close();
		}
		return id;
	}
	
	public Long saveForHubPicDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls){
		Session session=null;
		Transaction tx=null;
		Long id = 0l;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			id=(Long) session.save(licBrnhHubPicMapDtls);
			tx.commit();
			
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			id=0l;
			log.info("LicBrnhHubPicMapDtlsDaoImpl saveForHubPicDispatchList Error", e);
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Boolean saveForBranchHubReqDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls) {
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(licBrnhHubPicMapDtls);
            tx.commit();       
            status = true;
        } catch (Exception e) {
        	if(tx!=null) tx.rollback();
        	status = false;
        	log.info("LicBrnhHubPicMapDtlsDaoImpl saveForBranchHubReqDispatchList Error", e);
        } finally {
            session.close();
        }
        return status;
	}

	@Override
	public List<LicRequirementDtls> findRequirementsForHubPicDispatch(Date busineeFromDate, Date busineeToDate, List<LicHubMst> licHubMsts) {
		List<LicRequirementDtls> list = null;
		Session session = null;
		try {
            session = sessionFactory.openSession();   
            Criteria criteria  = session.createCriteria(LicRequirementDtls.class,"lrd");
            criteria.createAlias("lrd.licReqBocMappings", "lrbm",JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("lrd.licMarkingToQcDtls", "lmtqd",JoinType.LEFT_OUTER_JOIN);
            criteria.createAlias("lrd.licOblApplicationMst", "loam");
            criteria.createAlias("loam.branchMst", "bm");
            criteria.createAlias("loam.picBranchMstId", "pbmi");
            criteria.createAlias("loam.licProductValueMst", "lpvm");
            
            criteria.add(
            		Restrictions.or(
	            		Restrictions.and(Restrictions.isNotNull("lrbm.id"),Restrictions.eq("lrd.reqType", "S"),Restrictions.eq("lrd.dispatchReadyFlag","Y")),
	            		Restrictions.and(Restrictions.eq("lrd.reqType", "D"),Restrictions.isNotNull("lmtqd.indMrkFlag"),Restrictions.eq("lrd.dispatchReadyFlag","Y")),
	            		Restrictions.and(Restrictions.eq("lrd.actionType", "IR"),Restrictions.eq("lrd.dispatchReadyFlag", "Y"))
            		));
            criteria.add(Restrictions.in("lrd.licHubMst", licHubMsts));
            criteria.add(Restrictions.ge("loam.businessDate", busineeFromDate));
            criteria.add(Restrictions.le("loam.businessDate", busineeToDate));
            criteria.add(Restrictions.isNull("lrd.licBrnhHubPicMapDtls"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicBrnhHubPicMapDtlsDaoImpl saveForBranchHubReqDispatchList Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public Long saveForHubPicDispatchListForReq(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls) {
		Session session=null;
		Transaction tx=null;
		Long id = 0l;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			id=(Long) session.save(licBrnhHubPicMapDtls);
			tx.commit();
			
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			id=0l;
			log.info("LicBrnhHubPicMapDtlsDaoImpl saveForHubPicDispatchListForReq Error", e);
		}finally{
			session.close();
		}
		return id;
	}
	
	@Override
	public Boolean savePosViewDispatchList(LicBrnhHubPicMapDtls licBrnhHubPicMapDtls) {
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(licBrnhHubPicMapDtls);
            tx.commit();       
            status = true;
        } catch (Exception e) {
        	if(tx!=null) tx.rollback();
        	status = false;
        	log.info("LicBrnhHubPicMapDtlsDaoImpl savePosViewDispatchList Error", e);
        } finally {
            session.close();
        }
        return status;
	}

	@Override
	public List<LicOblApplicationMstDto> findBranchHubDispatchReport(Date businessFromDate, Date businessToDate, BranchMst branchMst) {
		Session session = null;
		List<LicOblApplicationMstDto> list = null;
		try{
			session=sessionFactory.openSession();
			
			SQLQuery query = (SQLQuery) session.createSQLQuery(
					"SELECT "
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
					+ "WHERE "
					+ "loam.BRNH_HUB_MAP_DTLS_ID IS NOT NULL "
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
			query.addScalar("totalReceived", DoubleType.INSTANCE);
			query.addScalar("totalAmt", DoubleType.INSTANCE);
			query.addScalar("chequeNo", StringType.INSTANCE);
			query.addScalar("cashAmount",DoubleType.INSTANCE);
			query.addScalar("insAmount", DoubleType.INSTANCE);
			
			query.setResultTransformer(Transformers.aliasToBean(LicOblApplicationMstDto.class));
            query.setParameter("businessFromDate", businessFromDate, DateType.INSTANCE);
            query.setParameter("businessToDate", businessToDate, DateType.INSTANCE);
            query.setParameter("branchMstId", branchMst.getBranchId(), LongType.INSTANCE);
			list = query.list();
		}catch(Exception e){
			log.info("LicBrnhHubPicMapDtlsDaoImpl findBranchHubDispatchReport Error", e);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<LicOblApplicationMst> findPicDispatchReport(Date businessFromDate, Date businessToDate, BranchMst branchMst) {
		List<LicOblApplicationMst> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
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
					+ "loam.hubPicMapDtls IS NOT NULL "
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
            
        } catch (Exception e) {
        	log.info("LicBrnhHubPicMapDtlsDaoImpl findPicDispatchReport Error ", e);
        } finally {
            session.close();
        }
        return list;
	}
}
