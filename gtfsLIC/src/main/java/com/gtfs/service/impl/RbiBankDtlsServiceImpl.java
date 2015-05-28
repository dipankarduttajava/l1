package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.RbiBankDtls;
import com.gtfs.dao.interfaces.RbiBankDtlsDao;
import com.gtfs.service.interfaces.RbiBankDtlsService;

@Service

public class RbiBankDtlsServiceImpl implements RbiBankDtlsService, Serializable{
	@Autowired
	private RbiBankDtlsDao rbiBankDtlsDao;
	
	public List<RbiBankDtls> findRbiBankDtlsByIfscCode(String ifscCode){
		return rbiBankDtlsDao.findRbiBankDtlsByIfscCode(ifscCode);
	}
}
