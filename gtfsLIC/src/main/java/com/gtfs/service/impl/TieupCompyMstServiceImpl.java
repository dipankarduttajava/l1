package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.TieupCompyMst;
import com.gtfs.dao.interfaces.TieupCompyMstDao;
import com.gtfs.service.interfaces.TieupCompyMstService;
@Service

public class TieupCompyMstServiceImpl implements TieupCompyMstService, Serializable{
	@Autowired
	private TieupCompyMstDao tieupCompyMstDao;
	public List<TieupCompyMst> findAllActiveTieupCompyList(){
		return tieupCompyMstDao.findAllActiveTieupCompyList();
	}
	
	public TieupCompyMst findById(Long id){
		return tieupCompyMstDao.findById(id);
	}
}
