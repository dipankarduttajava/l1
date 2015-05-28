package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.dto.LicTermRiderDto;

public interface LicTermRiderService extends Serializable{
	List<LicTermRiderDto> findRiderAmtAndRiderTypeFromLicTermRider(Integer age, Long policyTerm, Long premiumPayingTerm, Long productId);
}
