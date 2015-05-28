package com.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gtfs.bean.PhaseMst;
import com.gtfs.dao.interfaces.PhaseMstDao;

@Repository
public class PhaseMstDaoImpl implements PhaseMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	public List<PhaseMst> findActivePhaseMstList(){
		Session session = null;
        List<PhaseMst> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PhaseMst.class);
            criteria.add(Restrictions.eq("activeFlag", "Y"));
            list = criteria.list();
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return list;
	}
	
	public List<PhaseMst> findBusinessPhasesForCurrentDate(){
		Session session = null;
        List<PhaseMst> list = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(PhaseMst.class,"pm");
            criteria.createAlias("phaseBaMsts", "pbm");
            criteria.add(Restrictions.le("pbm.baFromDt", new Date()));
            criteria.add(Restrictions.ge("pbm.baToDt", new Date()));
            list = criteria.list();
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return list;
	}
	
	public PhaseMst findByPhaseId(Long phaseId){
		Session session = null;
        PhaseMst phaseMst = null;
        try {
            session = sessionFactory.openSession();
           phaseMst = (PhaseMst) session.get(PhaseMst.class, phaseId);
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return phaseMst;
	}
	
	
}
