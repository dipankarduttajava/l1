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

import com.gtfs.bean.RbiBankDtls;
import com.gtfs.dao.interfaces.RbiBankDtlsDao;

@Repository
public class RbiBankDtlsDaoImpl implements RbiBankDtlsDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<RbiBankDtls> findRbiBankDtlsByIfscCode(String ifscCode){
		List<RbiBankDtls> list=null;
	 	Session session = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria=session.createCriteria(RbiBankDtls.class);
            criteria.add(Restrictions.eq("ifscCode", ifscCode));
            list=criteria.list();
            
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return list;
	}
}
