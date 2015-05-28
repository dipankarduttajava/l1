package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicPaymentMst;

public interface LicPaymentDtlsDao {
	List<LicPaymentDtls> findLicPaymentDtlsByPayId(LicPaymentMst licPaymentMst);
	List<LicPaymentDtls> findLicPaymentDtlsByPayIdAndLicRequirment(LicPaymentMst licPaymentMst, Long Id);
}
