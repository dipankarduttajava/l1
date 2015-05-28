package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.ParentCompyMst;

public interface ParentCompyMstDao {
	List<ParentCompyMst> findAll();
	ParentCompyMst findById(Long id);
	
}
