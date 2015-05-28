package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicOblApplicationMst;

public interface LicNomineeDtlsDao {
	List<LicNomineeDtls> findNomineeDtlsByApplication(LicOblApplicationMst licOblApplicationMst);
}
