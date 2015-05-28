package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.ParentCompyMst;

public interface ParentCompyMstService extends Serializable{
	List<ParentCompyMst> findAll();
	ParentCompyMst findById(Long id);
}
