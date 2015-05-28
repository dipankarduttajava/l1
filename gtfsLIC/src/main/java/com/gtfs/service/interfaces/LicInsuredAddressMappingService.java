package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicInsuredDtls;

public interface LicInsuredAddressMappingService extends Serializable{
	List<LicInsuredAddressMapping> findAddressDtlsByInsuredDtls(LicInsuredDtls licInsuredDtls);
}
