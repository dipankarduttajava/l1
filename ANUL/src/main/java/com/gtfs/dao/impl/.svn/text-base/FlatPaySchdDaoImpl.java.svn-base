package com.gtfs.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gtfs.dao.FlatPaySchdDao;
import com.gtfs.pojo.FlatMst;
import com.gtfs.pojo.FlatPaySchd;
@Repository
public class FlatPaySchdDaoImpl implements FlatPaySchdDao, Serializable {
	
	private Logger log = Logger.getLogger(FlatPaySchdDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public FlatPaySchd findById(Long flatPaySchdId) {
		FlatPaySchd flatPaySchd = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			flatPaySchd = ( FlatPaySchd ) session.get(FlatPaySchd.class, flatPaySchdId);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flatPaySchd;
	}

}
