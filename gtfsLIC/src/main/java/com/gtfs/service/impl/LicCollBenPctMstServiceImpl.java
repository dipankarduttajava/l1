package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicCollBenPctMst;
import com.gtfs.dao.interfaces.LicCollBenPctMstDao;
import com.gtfs.service.interfaces.LicCollBenPctMstService;
@Service

public class LicCollBenPctMstServiceImpl implements LicCollBenPctMstService,Serializable{
	@Autowired
	private LicCollBenPctMstDao licCollBenPctMstDao;
	public List<LicCollBenPctMst> findLicCollBenPctMstByProdIdTerm(Long prodId,Long term){
		return licCollBenPctMstDao.findLicCollBenPctMstByProdIdTerm(prodId, term);
	}
}
