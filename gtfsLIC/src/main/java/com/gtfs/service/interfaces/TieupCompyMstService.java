package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.TieupCompyMst;

public interface TieupCompyMstService extends Serializable{
	List<TieupCompyMst> findAllActiveTieupCompyList();
	TieupCompyMst findById(Long id);
}
