package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicPremWvr;

public interface LicPremWvrService{
	List<LicPremWvr> findLoadAmountByPropAgeTermProdId(Integer propAge,Long PremumPayingTerm,Long prodId);
}
