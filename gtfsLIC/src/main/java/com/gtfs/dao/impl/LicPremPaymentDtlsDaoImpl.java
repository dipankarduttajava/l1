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

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicPremPaymentDtls;
import com.gtfs.dao.interfaces.LicPremPaymentDtlsDao;

@Repository
public class LicPremPaymentDtlsDaoImpl implements LicPremPaymentDtlsDao, Serializable {
	private Logger log = Logger.getLogger(LicPremPaymentDtlsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<LicPremPaymentDtls> findLicPremPaymentDtls(Long id,BranchMst branchMst) {
		List<LicPremPaymentDtls> list = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
           
            Criteria criteria = session.createCriteria(LicPremPaymentDtls.class,"lppd");
            criteria.createAlias("lppd.licPremiumListDtls", "lpld");
            
            criteria.add(Restrictions.eq("lpld.id", id));
            criteria.add(Restrictions.eq("lpld.deleteFlag", "N"));
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicPremPaymentDtlsDaoImpl findLicPremPaymentDtls Error", e);
        } finally {
            session.close();
        }
        return list;
	}

}
