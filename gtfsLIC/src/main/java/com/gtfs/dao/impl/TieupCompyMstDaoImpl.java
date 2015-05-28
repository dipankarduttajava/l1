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

import com.gtfs.bean.TieupCompyMst;
import com.gtfs.dao.interfaces.TieupCompyMstDao;

@Repository
public class TieupCompyMstDaoImpl implements TieupCompyMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<TieupCompyMst> findAllActiveTieupCompyList(){
		Session session = null;
		List<TieupCompyMst> list = null;
		try {
			session = sessionFactory.openSession();
			Criteria  criteria= session.createCriteria(TieupCompyMst.class);
			criteria.add(Restrictions.eq("activeFlag", "Y"));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public TieupCompyMst findById(Long id){
		Session session = null;
		TieupCompyMst tieupCompyMst = null;
		try {
			session = sessionFactory.openSession();
			tieupCompyMst = (TieupCompyMst) session.get(TieupCompyMst.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tieupCompyMst;
	}
}
