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

import com.gtfs.bean.ParentCompyMst;
import com.gtfs.dao.interfaces.ParentCompyMstDao;

@Repository
public class ParentCompyMstDaoImpl implements ParentCompyMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<ParentCompyMst> findAll(){
		List<ParentCompyMst> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ParentCompyMst.class);
            criteria.add(Restrictions.eq("activeFlag", "Y"));
            list=criteria.list();
            
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
	
	public ParentCompyMst findById(Long id){
		ParentCompyMst parentCompyMst=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            parentCompyMst=(ParentCompyMst) session.get(ParentCompyMst.class, id);
            
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return parentCompyMst;
	}
	
}
