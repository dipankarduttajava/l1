package com.gtfs.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicTermRider;
import com.gtfs.dao.interfaces.LicTermRiderDao;
import com.gtfs.dto.LicTermRiderDto;

@Repository
public class LicTermRiderDaoImpl implements LicTermRiderDao{
	private Logger log = Logger.getLogger(LicTermRiderDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<LicTermRiderDto> findRiderAmtAndRiderTypeFromLicTermRider(Integer age, Long policyTerm, Long premiumPayingTerm, Long productId) {
		List<LicTermRiderDto> list = null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicTermRider.class);
            criteria.add(Restrictions.le("insAgeTo", age));
            criteria.add(Restrictions.ge("insAgeFrom", age));
            criteria.add(Restrictions.eq("licProdMstId", productId));
            criteria.add(Restrictions.eq("pt", policyTerm));
            criteria.add(Restrictions.eq("ppt", premiumPayingTerm));
            criteria.add(Restrictions.eq("deleteFlag", "N"));
            
            ProjectionList proList = Projections.projectionList();
            proList.add(Projections.property("riderAmt"),"riderAmt");
            proList.add(Projections.property("riderNsapAmt"), "riderNsapAmt" );
            proList.add(Projections.property("riderAmtType"), "riderAmtType");
            
            criteria.setProjection(Projections.distinct(proList));   
            
            criteria.setResultTransformer(Transformers.aliasToBean(LicTermRiderDto.class));
            list = criteria.list();
            
        } catch (Exception e) {
        	log.info("LicTermRiderDaoImpl findRiderAmtAndRiderTypeFromLicTermRider Error", e);
        } finally {
            session.close();
        }
        return list;
	}

}
