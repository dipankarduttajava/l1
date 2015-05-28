package com.gtfs.dao;

import java.util.List;

import com.gtfs.dto.RoleAccessComboDto;
import com.gtfs.pojo.RoleAccessCombo;

public interface RoleAccessComboDao {
	List<RoleAccessComboDto> findAll();
	RoleAccessCombo findById(Long id);
}
