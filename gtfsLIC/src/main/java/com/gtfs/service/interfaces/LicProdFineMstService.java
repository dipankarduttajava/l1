package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicProdFineMst;

public interface LicProdFineMstService extends Serializable{
	List<LicProdFineMst> findLicProdFineMstByProdIdAndFineMnth(Long prodId,Integer fineMnth);
}
