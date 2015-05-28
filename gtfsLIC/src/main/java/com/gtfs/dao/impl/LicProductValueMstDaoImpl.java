package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.LicProductValueMst;
import com.gtfs.dao.interfaces.LicProductValueMstDao;

@Repository
public class LicProductValueMstDaoImpl implements LicProductValueMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<LicProductValueMst> findProductValueMstByProductId(Long prodId){
		Session session = null;
        List<LicProductValueMst> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicProductValueMst.class);
            criteria.add(Restrictions.eq("licProductMst.id",prodId ));
            criteria.add(Restrictions.eq("deleteFlag","N"));
            list = criteria.list();
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return list;
	}
	
	public List<LicProductValueMst> findProductValueMstByProductIdTermSumAssuredPayMode(Long prodId, Long term, Double sumAssured, String payNature,Integer age){
		Session session = null;
		List<LicProductValueMst> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicProductValueMst.class,"pvm");
            criteria.createAlias("pvm.licProductMst", "pm");
            criteria.add(Restrictions.eq("pvm.licProductMst.id", prodId));
            criteria.add(Restrictions.eq("pvm.term",term));
            criteria.add(Restrictions.eq("pvm.sumAssured",sumAssured));
            criteria.add(Restrictions.eq("pvm.payNature",payNature));
            criteria.add(Restrictions.eq("pvm.deleteFlag", "N"));
            criteria.add(Restrictions.ge("pvm.ageFrom", age));
            criteria.add(Restrictions.le("pvm.ageTo", age));
            list = criteria.list();
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return list;
	}
}
