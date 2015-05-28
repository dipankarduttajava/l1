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

import com.gtfs.bean.ProcessMst;
import com.gtfs.dao.interfaces.ProcessMstDao;
@Repository
public class ProcessMstDaoImpl implements ProcessMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<ProcessMst> findAll(){
		Session session=null;
		List<ProcessMst> list=null;
		try{
			session=sessionFactory.openSession();
			Criteria criteria=session.createCriteria(ProcessMst.class);
			criteria.add(Restrictions.eq("deleteFlag", "N"));
			list=criteria.list();
		}catch(Exception e){
			
		}finally{
			session.close();
		}
		return list;
	}
	
	
	public ProcessMst findById(Long id){
		Session session=null;
		ProcessMst processMst=null;
		try{
			session=sessionFactory.openSession();
			processMst = (ProcessMst) session.get(ProcessMst.class, id);
		}catch(Exception e){
			
		}finally{
			session.close();
		}
		return processMst;
	}
	
	
}
