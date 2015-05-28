package com.gtfs.dao.interfaces;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.gtfs.bean.LicNsapMst;
import com.gtfs.dao.impl.HibernateUtil;

public interface LicNsapMstDao {
	List<LicNsapMst> findNsapMstByProdIdAgeTerm(Long prodId,Integer age,Long policyTerm,Long premiumPayingTerm);
}
