package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.DesignationMst;

public interface DesignationMstService extends Serializable{
	List<DesignationMst> findAllActiveFromDesignationMst();
	DesignationMst findById(Long designationId);
	
}
