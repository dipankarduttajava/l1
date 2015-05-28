package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicPaymentMst;
import com.gtfs.dao.interfaces.LicPaymentDtlsDao;

@Repository
public class LicPaymentDtlsDaoImpl implements LicPaymentDtlsDao,Serializable{
	private Logger log = Logger.getLogger(LicPaymentDtlsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicPaymentDtls> findLicPaymentDtlsByPayId(LicPaymentMst licPaymentMst){
		Session session=null;
		List<LicPaymentDtls> list=null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicPaymentDtls.class, "lpd");
			criteria.add(Restrictions.eq("lpd.licPaymentMst", licPaymentMst));
			criteria.add(Restrictions.eq("deleteFlag", "N"));
			criteria.add(Restrictions.isNull("lpd.shortPremFlag"));
			list=criteria.list();
		}catch(Exception e){
			log.info("LicPaymentDtlsDaoImpl findLicPaymentDtlsByPayId Error", e);
		}finally{
			session.close();
		}
		return list;
	}
	
	public List<LicPaymentDtls> findLicPaymentDtlsByPayIdAndLicRequirment(LicPaymentMst licPaymentMst, Long Id){
		Session session=null;
		List<LicPaymentDtls> list=null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria=session.createCriteria(LicPaymentDtls.class, "lpd");
			criteria.add(Restrictions.eq("lpd.licPaymentMst", licPaymentMst));
			criteria.add(Restrictions.eq("lpd.deleteFlag", "N"));
			criteria.add(Restrictions.eq("lpd.shortPremFlag", "Y"));
			criteria.add(Restrictions.eq("lpd.licRequirementDtls.id", Id));
			list=criteria.list();
		}catch(Exception e){
			log.info("LicPaymentDtlsDaoImpl findLicPaymentDtlsByPayIdAndLicRequirment Error", e);
		}finally{
			session.close();
		}
		return list;
	}
}
