package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.bean.PrintRcptMst;
import com.gtfs.dao.interfaces.LicPrintRcptDtlsDao;

@Repository
public class LicPrintRcptDtlsDaoImpl implements LicPrintRcptDtlsDao,Serializable{
	private Logger log = Logger.getLogger(LicPrintRcptDtlsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Long> findLicPrintRcptDtlsByPrintRcptMst(PrintRcptMst printRcptMst){
		Session session = null;
        List<Long> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPrintRcptDtls.class);
            criteria.add(Restrictions.eq("printRcptMst", printRcptMst));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.setProjection(Projections.max("receiptNo"));
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findLicPrintRcptDtlsByPrintRcptMst Error", e);
        } finally {
            session.close();
        }
        return list;
	}
	
	public List<LicPrintRcptDtls> findLicPrintRcptDtlsByApplication(LicOblApplicationMst licOblApplicationMst){
		Session session = null;
        List<LicPrintRcptDtls> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPrintRcptDtls.class);
            criteria.add(Restrictions.eq("tableId", licOblApplicationMst.getId()));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findLicPrintRcptDtlsByApplication Error", e);
        } finally {
            session.close();
        }
        return list;
	}

	@Override
	public List<Long> findAllLicPrintRcptDtlsByPrintRcptMst(PrintRcptMst printRcptMst) {
		Session session = null;
        List<Long> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicPrintRcptDtls.class);
            criteria.add(Restrictions.eq("printRcptMst", printRcptMst));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            criteria.setProjection(Projections.property("receiptNo"));
            list = criteria.list();
        } catch (Exception e) {
        	log.info("LicPremiumListDaoImpl findLicPrintRcptDtlsByPrintRcptMst Error", e);
        } finally {
            session.close();
        }
        return list;
	}
}