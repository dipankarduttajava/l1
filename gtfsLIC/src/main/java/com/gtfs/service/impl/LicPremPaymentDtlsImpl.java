package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicPremPaymentDtls;
import com.gtfs.dao.interfaces.LicPremPaymentDtlsDao;
import com.gtfs.service.interfaces.LicPremPaymentDtlsService;

@Service
public class LicPremPaymentDtlsImpl implements LicPremPaymentDtlsService, Serializable{
	@Autowired
	private LicPremPaymentDtlsDao licPremPaymentDtlsDao;
	
	@Override
	public List<LicPremPaymentDtls> findLicPremPaymentDtls(Long id,BranchMst branchMst) {
		return licPremPaymentDtlsDao.findLicPremPaymentDtls(id, branchMst);
	}

}
