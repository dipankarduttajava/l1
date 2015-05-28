package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicProdFineMst;

public interface LicProdFineMstDao {
	List<LicProdFineMst> findLicProdFineMstByProdIdAndFineMnth(Long prodId,Integer fineMnth);
}
