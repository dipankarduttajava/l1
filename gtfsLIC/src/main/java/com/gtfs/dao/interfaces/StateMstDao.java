package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.StateMst;

public interface StateMstDao {
	List<StateMst> findAllActiveStateMSt();
	StateMst findById(Long stateId);
}
