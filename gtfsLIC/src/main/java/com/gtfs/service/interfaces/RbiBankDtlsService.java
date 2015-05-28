package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.RbiBankDtls;


public interface RbiBankDtlsService extends Serializable{
	public List<RbiBankDtls> findRbiBankDtlsByIfscCode(String ifscCode);
}
