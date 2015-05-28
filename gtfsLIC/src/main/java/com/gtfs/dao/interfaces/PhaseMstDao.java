package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.PhaseMst;

public interface PhaseMstDao {
	List<PhaseMst> findActivePhaseMstList();
	List<PhaseMst> findBusinessPhasesForCurrentDate();
	PhaseMst findByPhaseId(Long phaseId);
	
}
