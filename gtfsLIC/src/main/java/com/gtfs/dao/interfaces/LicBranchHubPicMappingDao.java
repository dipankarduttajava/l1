package com.gtfs.dao.interfaces;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.gtfs.bean.LicBranchHubPicMapping;
import com.gtfs.bean.ProcessMst;
import com.gtfs.dao.impl.HibernateUtil;

public interface LicBranchHubPicMappingDao {
	List<Long> findHubIdForBranchIdByProcessName(Long branchId,String processName);
	List<LicBranchHubPicMapping> findSourceByIdTypeAndProcss(Long sourceId,String sourceType,ProcessMst processMst);
	List<LicBranchHubPicMapping> findDestinationByIdTypeAndProcss(Long destinationId,String destinationType,ProcessMst processMst);
	Boolean saveForMapping(List<LicBranchHubPicMapping> list);
	List<Long> findHubIdForRnlDespatch(Long branchId);
}
