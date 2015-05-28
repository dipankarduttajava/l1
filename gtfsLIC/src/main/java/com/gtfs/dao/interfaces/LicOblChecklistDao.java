package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicOblChecklist;

public interface LicOblChecklistDao {
	Boolean insertIntoLicChecklist(LicOblChecklist licOblChecklist);
	List<LicOblChecklist> findApplicationForPremDataEntryByDate(Date date,BranchMst branchMst);
	List<LicOblChecklist> findApplicationForPremDataEntryByApplicationNo(String applicationNo, BranchMst branchMst);
	List<LicOblChecklist> findApplicationForCheckListEditByApplicationNo(String applicationNo, BranchMst branchMst);
	Boolean updateLicChecklist(LicOblChecklist licOblChecklist);
	List<LicOblChecklist> findApplicationByApplicationNo(String applicationNo);
}
