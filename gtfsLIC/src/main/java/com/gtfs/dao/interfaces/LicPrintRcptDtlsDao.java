package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.bean.PrintRcptMst;

public interface LicPrintRcptDtlsDao {
	List<Long> findLicPrintRcptDtlsByPrintRcptMst(PrintRcptMst printRcptMst);
	List<LicPrintRcptDtls> findLicPrintRcptDtlsByApplication(LicOblApplicationMst licOblApplicationMst);
	List<Long> findAllLicPrintRcptDtlsByPrintRcptMst(PrintRcptMst printRcptMst);
	
	
}
