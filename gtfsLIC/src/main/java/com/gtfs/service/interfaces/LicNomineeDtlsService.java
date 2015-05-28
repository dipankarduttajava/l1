package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicOblApplicationMst;

public interface LicNomineeDtlsService extends Serializable{
	List<LicNomineeDtls> findNomineeDtlsByApplication(LicOblApplicationMst licOblApplicationMst);
}
