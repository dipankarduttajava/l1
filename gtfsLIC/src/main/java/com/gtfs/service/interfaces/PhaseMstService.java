package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.PhaseMst;

public interface PhaseMstService extends Serializable{
	List<PhaseMst> findBusinessPhasesForCurrentDate();
	PhaseMst findByPhaseId(Long phaseId);
}
