package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.PrintRcptMst;


public interface PrintRcptMstService extends Serializable{
	Boolean saveForPrintReceipt(PrintRcptMst printRcptMst);
	List<Long> findMaximunReceiptNoByPrefix(String prefix);
	List<String> findPrintRcptPrefixByBranchParentTieupCoId(Long branchId,Long  tieupCompyId,Long parentCompyId);
	List<PrintRcptMst> findPrintRcptMstByPrefixBranchIdTieupCoIdParentCoId(String prefix,Long branchId,Long tieupCoId,Long parentCoId);

		
	
}
