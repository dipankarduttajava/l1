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

import com.gtfs.bean.LicProofMst;
import com.gtfs.dao.interfaces.LicProofMstDao;

@Repository
public class LicProofMstDaoImpl implements LicProofMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<LicProofMst> findAll(){
		List<LicProofMst> list = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LicProofMst.class);

            criteria.add(Restrictions.eq("deleteFlag","N"));
            list=criteria.list();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
	
	
	public LicProofMst findById(Long id){
		LicProofMst licProofMst = null;
		Session session = null;
        try {
            session = sessionFactory.openSession();
            licProofMst = (LicProofMst) session.get(LicProofMst.class, id);
            
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return licProofMst;
	}
	
	
}
