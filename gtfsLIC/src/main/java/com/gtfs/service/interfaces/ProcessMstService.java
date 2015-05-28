package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.ProcessMst;

public interface ProcessMstService extends Serializable{
	List<ProcessMst> findAll();
	ProcessMst findById(Long id);
}
