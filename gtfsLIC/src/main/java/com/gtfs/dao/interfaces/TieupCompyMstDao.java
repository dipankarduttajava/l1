package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.TieupCompyMst;

public interface TieupCompyMstDao {
	List<TieupCompyMst> findAllActiveTieupCompyList();
	TieupCompyMst findById(Long id);
}
