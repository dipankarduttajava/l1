package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicHighSaDiscMst;
import com.gtfs.dao.interfaces.LicHighSaDiscMstDao;
import com.gtfs.service.interfaces.LicHighSaDiscMstService;

@Service

public class LicHighSaDiscMstServiceImpl implements LicHighSaDiscMstService,Serializable{
	@Autowired
	private LicHighSaDiscMstDao licHighSaDiscMstDao;
	
	public List<LicHighSaDiscMst> findHighSaDiscByProdIdTermSumAssured(Long prodId,Long term,Double sa){
		return licHighSaDiscMstDao.findHighSaDiscByProdIdTermSumAssured(prodId, term, sa);
	}
}
