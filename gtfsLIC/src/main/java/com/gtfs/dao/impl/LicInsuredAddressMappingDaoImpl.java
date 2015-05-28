package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicInsuredDtls;
import com.gtfs.dao.interfaces.LicInsuredAddressMappingDao;
@Repository
public class LicInsuredAddressMappingDaoImpl implements LicInsuredAddressMappingDao,Serializable{
	private Logger log = Logger.getLogger(LicInsuredAddressMappingDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicInsuredAddressMapping> findAddressDtlsByInsuredDtls(LicInsuredDtls licInsuredDtls){
		Session session = null;
		List<LicInsuredAddressMapping> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LicInsuredAddressMapping.class,"liam");
			criteria.createAlias("liam.licAddressDtls", "lad");
			criteria.createAlias("lad.stateMst", "sm", JoinType.LEFT_OUTER_JOIN);
			
			criteria.add(Restrictions.eq("liam.licInsuredDtls", licInsuredDtls));
			criteria.add(Restrictions.eq("liam.deleteFlag", "N"));
			
			list = criteria.list();
		}catch(Exception e){
			log.info("LicInsuredAddressMappingDaoImpl findAddressDtlsByInsuredDtls Error", e);
		}finally{
			session.close();
		}
		return list;
	}
}
