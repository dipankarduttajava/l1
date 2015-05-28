package com.gtfs.dao.interfaces;

import org.hibernate.Session;

import com.gtfs.bean.AccessList;
import com.gtfs.bean.AgentMst;
import com.gtfs.dao.impl.HibernateUtil;

public interface AgentMstDao {
	AgentMst findByAgCode(Long agCode);
}
