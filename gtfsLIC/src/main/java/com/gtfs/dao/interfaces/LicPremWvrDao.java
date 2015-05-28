package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicPremWvr;

public interface LicPremWvrDao {
	List<LicPremWvr> findLoadAmountByPropAgeTermProdId(Integer propAge,Long PremumPayingTerm,Long prodId);
}
