package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicModeRebateMst;
import com.gtfs.dao.interfaces.LicModeRebateMstDao;
import com.gtfs.service.interfaces.LicModeRebateMstService;

@Service

public class LicModeRebateMstServiceImpl implements LicModeRebateMstService,Serializable{
	@Autowired
	private LicModeRebateMstDao licModeRebateMstDao;
	
	public List<LicModeRebateMst> findModeRebateByProdIdPayMode(Long prodId,String payMode){
		return licModeRebateMstDao.findModeRebateByProdIdPayMode(prodId, payMode);
	}
}
