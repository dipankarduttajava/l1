package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gtfs.bean.GenericBusinessTxn;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyMst;

public interface LicPolicyMstService extends Serializable{
	Boolean update(LicPolicyMst licPolicyMst);
	LicOblApplicationMst findPolicyDtls(Long id);
	List<LicPolicyMst> checkPolicyNo(String policyNo);
	Boolean updateForStatusEntry(LicPolicyMst licPolicyMst,GenericBusinessTxn genericBusinessTxn);
	LicPolicyMst findById(Long id);
	List<LicPolicyMst> findApplicationForRejectedEntry(Date fromDate,Date toDate, String applicantName, Double premium,	Double sumAssured, Long term, String applicationNo,String policyNo, String proposalNo,List<LicHubMst> findHubForProcess);
	List<LicPolicyMst> findApplicationForFprAndPolicyBondDelivery(Date fromDate, Date toDate, String applicantName, Double premium, Double sumAssured, Long term, String applicationNo, String policyNo, String proposalNo, List<LicHubMst> findHubForProcess);
	List<LicPolicyMst> findPolicyInfoByPolicyNo(String policyNo);
	
	
}
