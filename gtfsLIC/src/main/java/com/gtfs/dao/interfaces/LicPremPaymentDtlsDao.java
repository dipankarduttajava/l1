package com.gtfs.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicPremPaymentDtls;

public interface LicPremPaymentDtlsDao extends Serializable {

	List<LicPremPaymentDtls> findLicPremPaymentDtls(Long id, BranchMst branchMst);

}
