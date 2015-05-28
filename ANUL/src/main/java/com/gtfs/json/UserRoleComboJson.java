package com.gtfs.json;

import java.util.List;

import com.gtfs.dto.UserRoleComboDto;

public interface UserRoleComboJson {
	List<UserRoleComboDto> findAll();
	UserRoleComboDto findById(Long id);
}
