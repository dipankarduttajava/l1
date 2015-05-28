package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.LicModeRebateMst;


public interface LicModeRebateMstService extends Serializable{
	List<LicModeRebateMst> findModeRebateByProdIdPayMode(Long prodId,String payMode);
}
