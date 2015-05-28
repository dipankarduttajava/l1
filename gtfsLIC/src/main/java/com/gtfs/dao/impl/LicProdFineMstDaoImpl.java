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

import com.gtfs.bean.LicProdFineMst;
import com.gtfs.dao.interfaces.LicProdFineMstDao;

@Repository
public class LicProdFineMstDaoImpl implements LicProdFineMstDao,Serializable{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LicProdFineMst> findLicProdFineMstByProdIdAndFineMnth(Long prodId, Integer fineMnth) {
		Session session = null;
		List<LicProdFineMst> list = null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria=session.createCriteria(LicProdFineMst.class,"lpfm");
			criteria.add(Restrictions.eq("licProductMst.id", prodId));
			criteria.add(Restrictions.eq("fineMnth", fineMnth));
			criteria.add(Restrictions.eq("deleteFlag", "N"));
			list=criteria.list();
		}catch(Exception e){
			
		}finally{
			session.close();
		}
		return list;
	}

}
