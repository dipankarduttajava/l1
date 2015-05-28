package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicCmsMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPisMst;
import com.gtfs.dao.impl.HibernateUtil;

public interface LicPisMstDao {
	List<Object> findApplicationforPis(BranchMst branchMst);
	List<Object> findApplicationforPisForRequirement(BranchMst branchMst);
	Boolean save(LicPisMst licPisMst);
	List<Object> findPolicyDtlsforPis(BranchMst branchMst);
	List<LicOblApplicationMst> findPisGeneratedReport(Date businessFromDate, Date businessToDate, String pisCms, String applNo, List<LicHubMst> licHubMsts);
	List<LicPisMst> findPisListForPisReport(Long pisId, Date busineeFormDate,Date businessToDate);
	List<Object> findApplicationByPis(Long pisId);
	List<LicCmsMst> findCmsByPisId(Long id);
	
}
