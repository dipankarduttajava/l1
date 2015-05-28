package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.bean.PrintRcptMst;

public interface LicPrintRcptDtlsService extends Serializable{
	List<Long> findLicPrintRcptDtlsByPrintRcptMst(PrintRcptMst printRcptMst);
	List<LicPrintRcptDtls> findLicPrintRcptDtlsByApplication(LicOblApplicationMst licOblApplicationMst);
	List<Long> findAllLicPrintRcptDtlsByPrintRcptMst(PrintRcptMst printRcptMst);
}
