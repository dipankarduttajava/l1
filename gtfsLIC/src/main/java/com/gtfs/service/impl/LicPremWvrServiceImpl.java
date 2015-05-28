package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.bean.LicPremWvr;
import com.gtfs.dao.interfaces.LicPremWvrDao;
import com.gtfs.service.interfaces.LicPremWvrService;

@Service
public class LicPremWvrServiceImpl implements LicPremWvrService,Serializable {
	@Autowired
	private LicPremWvrDao licPremWvrDao;

	@Override
	public List<LicPremWvr> findLoadAmountByPropAgeTermProdId(Integer propAge,Long PremumPayingTerm, Long prodId) {
		return licPremWvrDao.findLoadAmountByPropAgeTermProdId(propAge, PremumPayingTerm, prodId);
	}
}
