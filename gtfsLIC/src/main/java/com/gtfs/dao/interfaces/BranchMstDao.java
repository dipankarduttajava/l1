package com.gtfs.dao.interfaces;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.gtfs.bean.BranchMst;
import com.gtfs.dao.impl.HibernateUtil;

public interface BranchMstDao {
	List<BranchMst> findAllBranches(Class<BranchMst> clazz);
	BranchMst findById(Long id);
}
