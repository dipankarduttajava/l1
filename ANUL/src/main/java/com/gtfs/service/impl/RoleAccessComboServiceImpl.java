package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.RoleAccessComboDao;
import com.gtfs.dto.RoleAccessComboDto;
import com.gtfs.pojo.RoleAccessCombo;
import com.gtfs.service.RoleAccessComboService;

@Service
public class RoleAccessComboServiceImpl implements RoleAccessComboService,Serializable {

	@Autowired
	private RoleAccessComboDao roleAccessComboDao;
	
	@Override
	public List<RoleAccessComboDto> findAll() {
		return roleAccessComboDao.findAll();
	}

	@Override
	public RoleAccessComboDto findById(Long id) {
		RoleAccessCombo roleAccessCombo = roleAccessComboDao.findById(id);
		RoleAccessComboDto roleAccessComboDto = new RoleAccessComboDto();
		roleAccessComboDto.setId(roleAccessCombo.getId());
		roleAccessComboDto.setRoleId(roleAccessCombo.getRoleMst().getId());
		roleAccessComboDto.setAccessId(roleAccessCombo.getAccessMst().getId());
		roleAccessComboDto.setCreatedBy(roleAccessCombo.getCreatedBy());
		roleAccessComboDto.setModifiedBy(roleAccessCombo.getModifiedBy());
		roleAccessComboDto.setDeletedBy(roleAccessCombo.getDeletedBy());
		roleAccessComboDto.setCreatedDate(roleAccessCombo.getCreatedDate());
		roleAccessComboDto.setModifiedDate(roleAccessCombo.getModifiedDate());
		roleAccessComboDto.setDeletedDate(roleAccessCombo.getDeletedDate());
		roleAccessComboDto.setDeleteFlag(roleAccessCombo.getDeleteFlag());
		return roleAccessComboDto;
	}

}
