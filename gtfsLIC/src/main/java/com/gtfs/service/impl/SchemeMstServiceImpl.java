package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.SchemeMst;
import com.gtfs.dao.interfaces.SchemeMstDao;
import com.gtfs.service.interfaces.SchemeMstService;

@Service

public class SchemeMstServiceImpl implements SchemeMstService, Serializable{
	@Autowired
	private SchemeMstDao schemeMstDao;
	
	public List<SchemeMst> findAcitveSchemeMstList(){
		return schemeMstDao.findAcitveSchemeMstList();
	}
}
