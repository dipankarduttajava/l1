package com.gtfs.service;

import java.util.List;

import com.gtfs.dto.AccessMstDto;

public interface AccessMstService {
	List<AccessMstDto> findAll();
	AccessMstDto findById(Long id);
}
