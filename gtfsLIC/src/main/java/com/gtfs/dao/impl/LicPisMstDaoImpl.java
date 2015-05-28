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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicCmsMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPisMst;
import com.gtfs.dao.interfaces.LicPisMstDao;

@Repository
public class LicPisMstDaoImpl implements LicPisMstDao,Serializable{
	private Logger log = Logger.getLogger(LicPisMstDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Object> findApplicationforPis(BranchMst branchMst){
		List<Object> list = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();            
            Query query = session.createQuery("SELECT "
            		+ "loam.oblApplNo,"
            		+ "loam.businessDate,"
            		+ "coalesce((SELECT sum(lpd1.amount) FROM LicPaymentDtls as lpd1 WHERE lpd1.payMode = 'C' and  lpd1.shortPremFlag is null and lpd1.licPaymentMst = lpm),0.0) as cash,"
            		+ "coalesce((SELECT sum(lpd2.amount) FROM LicPaymentDtls as lpd2 WHERE (lpd2.payMode = 'Q' or lpd2.payMode = 'D')  and lpd2.licPaymentMst = lpm and lpd2.payeeName = 'LIFE INSURANCE CORPORATION OF INDIA (LICI)'),0.0) as ins,"
            		+ "coalesce((SELECT sum(lpd3.amount) FROM LicPaymentDtls as lpd3 WHERE (lpd3.payMode = 'Q' or lpd3.payMode = 'D')  and lpd3.licPaymentMst = lpm and lpd3.payeeName = 'SARADA INSURANCE CONSULTANCY LTD'),0.0) as tie,"
            		+ "loam.id "

            		+ "FROM "
            		+ "LicOblApplicationMst as loam inner join "
            		+ "loam.licBusinessTxn as lbt inner join "
            		+ "lbt.licPaymentMst as lpm "

            		+ "WHERE "
            		+ "loam.branchMst = :branchMst "
            		+ "and loam.deleteFlag = 'N' "
            		+ "and loam.migrationFlag = 'N' "
            		+ "and loam.secondaryEntryFlag = 'Y' "
            		+ "and loam.licPisMst IS NULL ");
            
            query.setParameter("branchMst", branchMst);
            
            list = query.list();
        } catch (Exception e) {
        	log.info("LicPaymentDtlsDaoImpl findApplicationforPis Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<Object> findPolicyDtlsforPis(BranchMst branchMst){
		List<Object> list =null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query =session.createQuery("SELECT "
            		+ "lpm.policyNo, "
            		+ "lpd.payDate, "
            		+ "coalesce((select sum(lpd1.amount) from LicRnlPaymentDtls as lpd1 where lpd1.payMode = 'C' and  lpd1.shortPremFlag is null and lpd1.licRnlPaymentMst = lrpm),0.0) as cash,"
            		+ "coalesce((select sum(lpd2.amount) from LicRnlPaymentDtls as lpd2 where (lpd2.payMode = 'Q' or lpd2.payMode = 'D')  and lpd2.licRnlPaymentMst = lrpm and lpd2.payeeName = 'LIFE INSURANCE CORPORATION OF INDIA (LICI)'),0.0) as ins,"
            		+ "coalesce((select sum(lpd3.amount) from LicRnlPaymentDtls as lpd3 where (lpd3.payMode = 'Q' or lpd3.payMode = 'D')  and lpd3.licRnlPaymentMst = lrpm and lpd3.payeeName = 'SARADA INSURANCE CONSULTANCY LTD'),0.0) as tie,"
            		+ "lpd.id, "
            		+ "lrpm.id "
            		
            		+ "FROM "
            		+ "LicPolicyDtls as lpd inner join "
            		+ "lpd.licPolicyMst as lpm inner join "
            		+ "lpd.licPolicyPaymentMappings as lppm inner join "
            		+ "lppm.licRnlPaymentMst as lrpm "
            		
            		+ "WHERE "
            		+ "lpd.branchMst = :branchMst "
            		+ "and lpd.deleteFlag = 'N' "
            		+ "and lpd.licPisMst is null "
            		+ "order by lrpm.id");
            
            query.setParameter("branchMst", branchMst);
            
            list = query.list();
        } catch (Exception e) {
        	log.info("LicPaymentDtlsDaoImpl findPolicyDtlsforPis Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public List<Object> findApplicationforPisForRequirement(BranchMst branchMst){
		List<Object> list = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();            
            Query query =session.createQuery("select "
            		+ "loam.oblApplNo,"
            		+ "loam.businessDate,"
            		+ "coalesce((select lpd1.amount from LicPaymentDtls as lpd1 where lpd1.payMode = 'C' and shortPremFlag = 'Y' and lpd1.licRequirementDtls.id = lrd.id  and lpd1.licPaymentMst = lpm),0.0 ) as cash,"
            		//+ "coalesce((select lpd2.amount from LicPaymentDtls as lpd2 where (lpd2.payMode = 'Q' or lpd2.payMode = 'D')  and lpd2.licPaymentMst = lpm and lpd2.payeeName = 'LIFE INSURANCE CORPORATION OF INDIA (LICI)'),0.0) as ins,"
            		//+ "coalesce((select lpd3.amount from LicPaymentDtls as lpd3 where (lpd3.payMode = 'Q' or lpd3.payMode = 'D')  and lpd3.licPaymentMst = lpm and lpd3.payeeName = 'SARADA INSURANCE CONSULTANCY LTD'),0.0) as tie,"
            		+ "loam.id,"
            		+ "lrd.id "
            		
            		+ "FROM "
            		+ "LicRequirementDtls as lrd inner join "
            		+ "lrd.licOblApplicationMst as loam inner join "
            		+ "loam.licBusinessTxn as lbt inner join "
            		+ "lbt.licPaymentMst as lpm "
            		
            		+ "WHERE "
            		+ "loam.branchMst = :branchMst "
            		+ "and loam.deleteFlag = 'N' "
            		+ "and loam.migrationFlag = 'N' "
            		+ "and lrd.deleteFlag = 'N' "
            		+ "and lrd.licPisMst is null "
            		+ "and lrd.reqType ='S' "
            		+ "and lrd.branchRcvFlag = 'Y'");
            
            query.setParameter("branchMst", branchMst);
            
            list = query.list();
        } catch (Exception e) {
        	log.info("LicPaymentDtlsDaoImpl findApplicationforPisForRequirement Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public Boolean save(LicPisMst licPisMst){
		Boolean status = false;
		Session session = null;
		Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx= session.beginTransaction();
            session.save(licPisMst);
            tx.commit();
            status = true;
        }catch (Exception e) {
        	if(tx!=null)tx.rollback();
        	status = false;
        	log.info("LicPaymentDtlsDaoImpl save Error", e);
        } finally {
            session.close();
        }
        return status;
	}


	@Override
	public List<LicOblApplicationMst> findPisGeneratedReport(Date businessFromDate, Date businessToDate, String pisCms, String applNo, List<LicHubMst> licHubMsts) {
		Session session = null;
		List<LicOblApplicationMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicOblApplicationMst.class,"loam");
			criteria.createAlias("loam.licProductValueMst", "lpvm");
			criteria.createAlias("loam.licProposerDtls", "lpd");
			criteria.createAlias("loam.licInsuredDtls", "lid");
			criteria.createAlias("loam.oblHubMst", "ohm");
			criteria.createAlias("loam.branchMst", "bm");
			criteria.createAlias("loam.licBusinessTxn", "lbt");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("lbt.licPaymentMst", "lpaym");
			
			
			if(pisCms.equals("Y")){
				criteria.add(Restrictions.isNotNull("loam.licPisMst"));
			}else if(pisCms.equals("N")){
				criteria.add(Restrictions.isNull("loam.licPisMst"));
			}

			if(applNo != null){
				criteria.add(Restrictions.eq("loam.oblApplNo", applNo));
			}else{
				criteria.add(Restrictions.ge("loam.businessDate", businessFromDate));
				criteria.add(Restrictions.le("loam.businessDate", businessToDate));
			}
			
			criteria.add(Restrictions.in("loam.oblHubMst", licHubMsts));
			//criteria.add(Restrictions.eq("loam.branchMst", branchMst));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lid.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.migrationFlag", "N"));
            
            list = criteria.list();
		}catch(Exception e){
			log.info("LicPaymentDtlsDaoImpl findPisGeneratedReport Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public List<LicPisMst> findPisListForPisReport(Long pisId,Date busineeFormDate, Date businessToDate) {
		Session session = null;
		List<LicPisMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicPisMst.class,"lpm");
			criteria.createAlias("lpm.licOblApplicationMsts", "loam");
			
			if(pisId!=null){
				criteria.add(Restrictions.eq("lpm.id", pisId));
			}
			
			if(busineeFormDate!=null){
				criteria.add(Restrictions.ge("loam.businessDate", busineeFormDate));
			}
			
			if(businessToDate!=null){
				criteria.add(Restrictions.le("loam.businessDate", businessToDate));
			}
			criteria.addOrder(Order.desc("lpm.id"));
			
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicPaymentDtlsDaoImpl findPisGeneratedReport Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public List<Object> findApplicationByPis(Long pisId) {
		List<Object> list = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();            
            Query query = session.createQuery("SELECT "
            		+ "loam.oblApplNo,"
            		+ "loam.businessDate,"
            		+ "coalesce((SELECT sum(lpd1.amount) FROM LicPaymentDtls as lpd1 WHERE lpd1.payMode = 'C' and  lpd1.shortPremFlag is null and lpd1.licPaymentMst = lpm),0.0) as cash,"
            		+ "coalesce((SELECT sum(lpd2.amount) FROM LicPaymentDtls as lpd2 WHERE (lpd2.payMode = 'Q' or lpd2.payMode = 'D')  and lpd2.licPaymentMst = lpm and lpd2.payeeName = 'LIFE INSURANCE CORPORATION OF INDIA (LICI)'),0.0) as ins,"
            		+ "coalesce((SELECT sum(lpd3.amount) FROM LicPaymentDtls as lpd3 WHERE (lpd3.payMode = 'Q' or lpd3.payMode = 'D')  and lpd3.licPaymentMst = lpm and lpd3.payeeName = 'SARADA INSURANCE CONSULTANCY LTD'),0.0) as tie,"
            		+ "loam.id "
            		+ "FROM "
            		+ "LicOblApplicationMst as loam inner join "
            		+ "loam.licBusinessTxn as lbt inner join "
            		+ "lbt.licPaymentMst as lpm inner join "
            		+ "loam.licPisMst as lpms "

            		+ "WHERE "
            		+ "loam.deleteFlag = 'N' "
            		+ "and loam.migrationFlag = 'N' "
            		+ "and loam.secondaryEntryFlag = 'Y' "
            		+ "and lpms.id= :pisId ");
            

            query.setParameter("pisId", pisId);
            list = query.list();
        } catch (Exception e) {
        	log.info("LicPaymentDtlsDaoImpl findApplicationforPis Error", e);
        } finally {
            session.close();
        }
        return list;
	}


	@Override
	public List<LicCmsMst> findCmsByPisId(Long id) {
		Session session = null;
		List<LicCmsMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria= session.createCriteria(LicCmsMst.class,"lcm");
			criteria.add(Restrictions.eq("lcm.licPisMst.id", id));
			criteria.add(Restrictions.eq("lcm.deleteFlag", "N"));
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicPaymentDtlsDaoImpl findPisGeneratedReport Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
}
