package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Calendar;
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

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicOblChecklist;
import com.gtfs.dao.interfaces.LicOblChecklistDao;

@Repository
public class LicOblChecklistDaoImpl implements LicOblChecklistDao,Serializable{
	private Logger log = Logger.getLogger(LicOblChecklistDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Boolean insertIntoLicChecklist(LicOblChecklist licOblChecklist){		
		Session session=null;
		Transaction tx=null;
		Boolean status=false;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			session.save(licOblChecklist);
			tx.commit();
			status=true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status=false;
			log.info("LicOblChecklistDaoImpl insertIntoLicChecklist Error", e);
		}finally{
			session.close();
		}
		return status;
	}
	
	
	public List<LicOblChecklist> findApplicationForPremDataEntryByDate(Date date,BranchMst branchMst){
		Calendar toNight=Calendar.getInstance();
		toNight.setTime(date);
		toNight.add(Calendar.HOUR, 23);
		toNight.add(Calendar.MINUTE, 59);
		
		Session session = null;
		List<LicOblChecklist> list = null;
		try{
			
			session=sessionFactory.openSession();
			Criteria criteria=session.createCriteria(LicOblChecklist.class,"loc");
			criteria.createAlias("loc.phaseMst", "pm");
			criteria.createAlias("loc.licProductValueMst", "lpvm");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.createAlias("lpvm.tieupCompyMst", "tcm");
			criteria.add(Restrictions.eq("apprvRejectFlag", "Y"));
			criteria.add(Restrictions.eq("preDataEntryFlag", "N"));
			criteria.add(Restrictions.eq("oblApplDate", date));
			criteria.add(Restrictions.eq("loc.branchMst", branchMst));
			criteria.add(Restrictions.eq("loc.deleteFlag", "N"));
			criteria.add(Restrictions.eq("pm.activeFlag", "Y"));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			list=criteria.list();
		}catch(Exception e){
			log.info("LicOblChecklistDaoImpl findApplicationForPremDataEntryByDate Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
	public List<LicOblChecklist> findApplicationForPremDataEntryByApplicationNo(String applicationNo, BranchMst branchMst){	
		Session session = null;
		List<LicOblChecklist> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria=session.createCriteria(LicOblChecklist.class,"loc");
			criteria.createAlias("loc.phaseMst", "pm");
			criteria.createAlias("loc.licProductValueMst", "lpvm");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			criteria.add(Restrictions.eq("apprvRejectFlag", "Y"));
			criteria.add(Restrictions.eq("preDataEntryFlag", "N"));
			criteria.add(Restrictions.eq("oblApplNo", applicationNo));
			criteria.add(Restrictions.eq("loc.branchMst", branchMst));
			criteria.add(Restrictions.eq("loc.deleteFlag", "N"));
			criteria.add(Restrictions.eq("pm.activeFlag", "Y"));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicOblChecklistDaoImpl findApplicationForPremDataEntryByApplicationNo Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public List<LicOblChecklist> findApplicationForCheckListEditByApplicationNo(String applicationNo, BranchMst branchMst) {
		Session session = null;
		List<LicOblChecklist> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria=session.createCriteria(LicOblChecklist.class,"loc");
			criteria.createAlias("loc.phaseMst", "pm");
			criteria.createAlias("loc.licProductValueMst", "lpvm");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			
			criteria.add(Restrictions.eq("apprvRejectFlag", "Y"));
			criteria.add(Restrictions.eq("preDataEntryFlag", "N"));
			criteria.add(Restrictions.eq("oblApplNo", applicationNo));
			criteria.add(Restrictions.eq("loc.branchMst", branchMst));
			criteria.add(Restrictions.eq("loc.deleteFlag", "N"));
			criteria.add(Restrictions.eq("pm.activeFlag", "Y"));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicOblChecklistDaoImpl findApplicationForCheckListEditByApplicationNo Error", e);
		}finally{
			session.close();
		}
		return list;
	}


	@Override
	public Boolean updateLicChecklist(LicOblChecklist licOblChecklist) {
		Session session=null;
		Transaction tx=null;
		Boolean status=false;
		try{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			session.update(licOblChecklist);
			tx.commit();
			status=true;
		}catch(Exception e){
			if(tx!=null)tx.rollback();
			status=false;
			log.info("LicOblChecklistDaoImpl updateLicChecklist Error", e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<LicOblChecklist> findApplicationByApplicationNo(String applicationNo) {
		Session session = null;
		List<LicOblChecklist> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria=session.createCriteria(LicOblChecklist.class,"loc");
			criteria.createAlias("loc.phaseMst", "pm");
			criteria.createAlias("loc.licProductValueMst", "lpvm");
			criteria.createAlias("loc.licOblApplicationMsts", "loams");
			criteria.createAlias("lpvm.licProductMst", "lpm");
			
			criteria.add(Restrictions.eq("oblApplNo", applicationNo));
			criteria.add(Restrictions.eq("loc.deleteFlag", "N"));
			criteria.add(Restrictions.eq("pm.activeFlag", "Y"));
			criteria.add(Restrictions.eq("lpvm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpm.deleteFlag", "N"));
			criteria.add(Restrictions.eq("loams.deleteFlag", "N"));
			list = criteria.list();
		}catch(Exception e){
			log.info("LicOblChecklistDaoImpl findApplicationByApplicationNo Error", e);
		}finally{
			session.close();
		}
		return list;
	}
}