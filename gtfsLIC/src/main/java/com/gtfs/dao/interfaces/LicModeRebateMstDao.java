package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.LicModeRebateMst;

public interface LicModeRebateMstDao {
	List<LicModeRebateMst> findModeRebateByProdIdPayMode(Long prodId,String payMode);
}
