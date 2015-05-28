package com.gtfs.service;

import java.util.List;

import com.gtfs.dto.RoleMstDto;

public interface RoleMstService {
	List<RoleMstDto> findAll();
	RoleMstDto findById(Long id);
}
