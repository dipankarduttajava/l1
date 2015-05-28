package com.gtfs.dao.interfaces;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.gtfs.bean.DesignationMst;
import com.gtfs.dao.impl.HibernateUtil;

public interface DesignationMstDao {
	List<DesignationMst> findAllActiveFromDesignationMst();
	DesignationMst findById(Long designationId);
}
