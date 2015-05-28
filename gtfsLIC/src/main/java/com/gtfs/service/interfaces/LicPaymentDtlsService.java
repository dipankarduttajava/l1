package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicPaymentMst;


public interface LicPaymentDtlsService extends Serializable{
	List<LicPaymentDtls> findLicPaymentDtlsByPayId(LicPaymentMst licPaymentMst);
	List<LicPaymentDtls> findLicPaymentDtlsByPayIdAndLicRequirment(LicPaymentMst licPaymentMst, Long Id);
}
