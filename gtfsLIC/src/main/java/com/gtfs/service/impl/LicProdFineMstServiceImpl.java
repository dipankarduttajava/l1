package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicProdFineMst;
import com.gtfs.dao.interfaces.LicProdFineMstDao;
import com.gtfs.service.interfaces.LicProdFineMstService;

@Service

public class LicProdFineMstServiceImpl implements LicProdFineMstService,Serializable{
	@Autowired
	private LicProdFineMstDao licProdFineMstDao;

	@Override
	public List<LicProdFineMst> findLicProdFineMstByProdIdAndFineMnth(Long prodId, Integer fineMnth) {
		return licProdFineMstDao.findLicProdFineMstByProdIdAndFineMnth(prodId, fineMnth);
	}
}
