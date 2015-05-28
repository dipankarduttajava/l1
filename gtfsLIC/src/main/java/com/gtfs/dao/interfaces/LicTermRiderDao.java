package com.gtfs.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.dto.LicTermRiderDto;

public interface LicTermRiderDao extends Serializable {

	List<LicTermRiderDto> findRiderAmtAndRiderTypeFromLicTermRider(Integer age, Long policyTerm, Long premiumPayingTerm, Long productId);
}
