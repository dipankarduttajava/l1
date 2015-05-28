package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gtfs.bean.AccessList;
import com.gtfs.bean.LicDocReqMst;

public interface LicDocReqMstDao {
	List<LicDocReqMst> findAll();
}
