package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gtfs.bean.PrintRcptMst;
import com.gtfs.dao.interfaces.PrintRcptMstDao;
import com.gtfs.service.interfaces.PrintRcptMstService;

@Service

public class PrintRcptMstServiceImpl implements PrintRcptMstService, Serializable{
	@Autowired
	private PrintRcptMstDao printRcptMstDao;
	public Boolean saveForPrintReceipt(PrintRcptMst printRcptMst){
		return printRcptMstDao.saveForPrintReceipt(printRcptMst);
	}
	
	public List<Long> findMaximunReceiptNoByPrefix(String prefix){
		return printRcptMstDao.findMaximunReceiptNoByPrefix(prefix);
	}
	
	public List<String> findPrintRcptPrefixByBranchParentTieupCoId(Long branchId,Long  tieupCompyId,Long parentCompyId){
		return printRcptMstDao.findPrintRcptPrefixByBranchParentTieupCoId(branchId, tieupCompyId, parentCompyId);
	}
	
	public List<PrintRcptMst> findPrintRcptMstByPrefixBranchIdTieupCoIdParentCoId(String prefix,Long branchId,Long tieupCoId,Long parentCoId){
		return printRcptMstDao.findPrintRcptMstByPrefixBranchIdTieupCoIdParentCoId(prefix, branchId, tieupCoId, parentCoId);
	}

		
	
}
