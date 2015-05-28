package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.UserRoleComboDao;
import com.gtfs.dto.UserRoleComboDto;
import com.gtfs.pojo.UserRoleCombo;
import com.gtfs.service.UserRoleComboService;

@Service
public class UserRoleComboServiceImpl implements UserRoleComboService, Serializable {

	@Autowired
	private UserRoleComboDao userRoleComboDao;
	
	@Override
	public List<UserRoleComboDto> findAll() {
		return userRoleComboDao.findAll();
	}

	@Override
	public UserRoleComboDto findById(Long id) {
		UserRoleCombo userRoleCombo = userRoleComboDao.findById(id);
		UserRoleComboDto userRoleComboDto = new UserRoleComboDto();
		userRoleComboDto.setId(userRoleCombo.getId());
		userRoleComboDto.setUserId(userRoleCombo.getUserMst().getId());
		userRoleComboDto.setRoleId(userRoleCombo.getRoleMst().getId());
		userRoleComboDto.setCreatedBy(userRoleCombo.getCreatedBy());
		userRoleComboDto.setModifiedBy(userRoleCombo.getModifiedBy());
		userRoleComboDto.setDeletedBy(userRoleCombo.getDeletedBy());
		userRoleComboDto.setCreatedDate(userRoleCombo.getCreatedDate());
		userRoleComboDto.setModifiedDate(userRoleCombo.getModifiedDate());
		userRoleComboDto.setDeletedDate(userRoleCombo.getDeletedDate());
		userRoleComboDto.setDeleteFlag(userRoleCombo.getDeleteFlag());
		return userRoleComboDto;
	}
}
