package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicInsuredDtls;

public interface LicInsuredAddressMappingDao {
	List<LicInsuredAddressMapping> findAddressDtlsByInsuredDtls(LicInsuredDtls licInsuredDtls);
}
