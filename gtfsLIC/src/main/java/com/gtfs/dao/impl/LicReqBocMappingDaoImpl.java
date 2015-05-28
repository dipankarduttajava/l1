package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicApplBocMapping;
import com.gtfs.bean.LicReqBocMapping;
import com.gtfs.dao.interfaces.LicReqBocMappingDao;
@Repository
public class LicReqBocMappingDaoImpl implements LicReqBocMappingDao,Serializable {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<LicReqBocMapping> findReqBocMappingByApplication(Long id) {
		Session session = null;
        List<LicReqBocMapping> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicReqBocMapping.class,"lrbm");
            criteria.createAlias("lrbm.licBocDetailEntry", "lbde");
            criteria.createAlias("lrbm.licRequirementDtls", "lrd");
            criteria.createAlias("lrd.licOblApplicationMst", "loam");
            
            criteria.add(Restrictions.eq("loam.id", id));
            criteria.add(Restrictions.eq("lrbm.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lbde.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lrd.deleteFlag", "N"));
            criteria.add(Restrictions.eq("loam.deleteFlag", "N"));
            list = criteria.list();
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return list;
	}

}
