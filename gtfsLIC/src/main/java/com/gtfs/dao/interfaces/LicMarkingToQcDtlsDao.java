package com.gtfs.dao.interfaces;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicMarkingToQcDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dao.impl.HibernateUtil;

public interface LicMarkingToQcDtlsDao {
	Boolean saveForConsolidateMarking(LicMarkingToQcDtls licMarkingToQcDtls);
	List<LicOblApplicationMst> findIndividualMarkingDtlsByDate(String applicationNo,Date fromDate,Date toDate, List<LicHubMst> licHubMsts, BranchMst branchMst, Long branchId);
	Boolean updateForIndividualMarking(List<LicOblApplicationMst> licHubMsts);
	List<LicRequirementDtls> findIndividualMArkingDtlsByDateForReq(Date fromDate,Date toDate, List<LicHubMst> licHubMsts, BranchMst branchMst);	
	Boolean updateForIndividualMarkingForReq(List<LicRequirementDtls> licHubMsts);
	Boolean updateForIndividualMarkingForRenewal(List<LicPolicyDtls> licPolicyDtlsList);
	Boolean saveForConsolidateMarkingForPos(LicMarkingToQcDtls licMarkingToQcDtls);
	Boolean updateForIndividualMarkingForPos(List<LicRequirementDtls> licRequirementDtlsList);
	List<LicOblApplicationMst> findIndividualMarkingDtlsReport(Date businessFromDate, Date businessToDate, List<LicHubMst> findHubForProcess, Long branchId);
}
