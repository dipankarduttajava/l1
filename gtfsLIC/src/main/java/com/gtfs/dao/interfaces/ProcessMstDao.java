package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.ProcessMst;

public interface ProcessMstDao {
	List<ProcessMst> findAll();
	ProcessMst findById(Long id);
}
