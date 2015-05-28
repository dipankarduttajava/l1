package com.gtfs.service;

import java.util.List;

import com.gtfs.dto.FlatMstDto;

public interface FlatMstService {
	List<FlatMstDto> findAll();
	FlatMstDto findById(Long id);
	List<FlatMstDto> findByProjectIdFlatNo(Long projectId,String flatNo);
	List<FlatMstDto> findAvailableByProjectIdFlatNo(Long projectId,String flatNo);
	List<FlatMstDto> findForAggrementByCustomerId(Long customerId, String flatNo);
	String saveOrUpdate(FlatMstDto flatMstDto);
	String delete(Long flatId, Long userId);
	String saveOrUpdateForFlatEntry(FlatMstDto flatMstDto);
	String saveOrUpdateForFlatNegotiation(FlatMstDto flatMstDto);
}
