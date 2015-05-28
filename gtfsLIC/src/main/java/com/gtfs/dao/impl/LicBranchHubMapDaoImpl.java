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
import com.gtfs.bean.LicBranchHubMap;
import com.gtfs.bean.LicHubMst;
import com.gtfs.dao.interfaces.LicBranchHubMapDao;

@Repository
public class LicBranchHubMapDaoImpl implements LicBranchHubMapDao,Serializable{
	private Logger log = Logger.getLogger(LicBranchHubMapDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LicBranchHubMap> findBranchHubMapsByBranch(BranchMst branchMst) {
		List<LicBranchHubMap> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicBranchHubMap.class,"lbhm");
            criteria.createAlias("lbhm.licHubMst", "lhm");
            criteria.createAlias("lbhm.processMst", "pm");
            criteria.add(Restrictions.eq("lbhm.branchMst", branchMst));
            
            criteria.add(Restrictions.eq("lhm.deleteFlag", "N"));
            criteria.add(Restrictions.eq("lbhm.deleteFlag", "N"));
            list=criteria.list();
            
        } catch (Exception e) {
        	log.info("LicBranchHubMapDaoImpl findBranchHubMapsByBranch Error", e);
        } finally {
            session.close();
        }
        return list;
	}

}
