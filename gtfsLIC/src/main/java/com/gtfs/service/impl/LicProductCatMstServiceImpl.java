package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicProductCatMst;
import com.gtfs.dao.interfaces.LicProductCatMstDao;
import com.gtfs.service.interfaces.LicProductCatMstService;

@Service

public class LicProductCatMstServiceImpl implements LicProductCatMstService, Serializable{
	@Autowired
	private LicProductCatMstDao licProductCatMstDao;
	public List<LicProductCatMst> findActiveLicProdCategory(){
		return licProductCatMstDao.findActiveLicProdCategory();
	}
}
