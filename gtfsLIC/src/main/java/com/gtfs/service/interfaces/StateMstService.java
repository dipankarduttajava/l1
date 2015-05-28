package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.StateMst;


public interface StateMstService extends Serializable{
	List<StateMst> findAllActiveStateMSt();
	StateMst findById(Long stateId);
}
