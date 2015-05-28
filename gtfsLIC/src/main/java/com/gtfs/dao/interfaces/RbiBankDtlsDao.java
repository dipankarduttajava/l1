package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.RbiBankDtls;

public interface RbiBankDtlsDao {
	List<RbiBankDtls> findRbiBankDtlsByIfscCode(String ifscCode);
}
