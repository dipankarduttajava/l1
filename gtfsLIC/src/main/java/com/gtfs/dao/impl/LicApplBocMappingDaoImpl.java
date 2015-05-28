package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicApplBocMapping;
import com.gtfs.dao.interfaces.LicApplBocMappingDao;

@Repository
public class LicApplBocMappingDaoImpl implements LicApplBocMappingDao,Serializable {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LicApplBocMapping> findBocMappingByApplication(Long id) {
		Session session = null;
        List<LicApplBocMapping> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicApplBocMapping.class,"labm");
            criteria.createAlias("labm.licBocDetailEntry", "lbde");
            criteria.add(Restrictions.eq("labm.licOblApplicationMst.id", id));
            list = criteria.list();
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return list;
	}

}
