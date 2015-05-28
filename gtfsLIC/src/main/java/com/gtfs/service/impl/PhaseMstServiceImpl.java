package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.PhaseMst;
import com.gtfs.dao.interfaces.PhaseMstDao;
import com.gtfs.service.interfaces.PhaseMstService;

@Service

public class PhaseMstServiceImpl implements PhaseMstService, Serializable{
	@Autowired
	private PhaseMstDao phaseMstDao ;
	
	public List<PhaseMst> findBusinessPhasesForCurrentDate(){
		return phaseMstDao.findBusinessPhasesForCurrentDate();
	}
	
	public PhaseMst findByPhaseId(Long phaseId){
		return phaseMstDao.findByPhaseId(phaseId);
	}
}
