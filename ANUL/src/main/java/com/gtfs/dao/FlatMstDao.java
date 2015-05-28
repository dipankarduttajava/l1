package com.gtfs.dao;

import java.util.List;

import com.gtfs.dto.FlatMstDto;
import com.gtfs.pojo.FlatMst;

public interface FlatMstDao {
	
	List<FlatMstDto> findAll();
	FlatMst findById(Long id);
	List<FlatMstDto> findByProjectIdFlatNo(Long projectId,String flatNo);
	List<FlatMstDto> findAvailableByProjectIdFlatNo(Long projectId,String flatNo);
	List<FlatMstDto> findForAggrementByCustomerId(Long customerId, String flatNo);
	String saveOrUpdate(FlatMst flatMst);
	String delete(Long flatId, Long userId);
	String saveOrUpdateForFlatEntry(FlatMst flatMst);
	String saveOrUpdateForFlatNegotiation(FlatMst flatMst);
}
