package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.RoleMstDao;
import com.gtfs.dto.RoleMstDto;
import com.gtfs.pojo.RoleMst;
import com.gtfs.service.RoleMstService;

@Service
public class RoleMstServiceImpl implements RoleMstService, Serializable {

	@Autowired
	private RoleMstDao roleMstDao;
	
	@Override
	public List<RoleMstDto> findAll() {
		return roleMstDao.findAll();
	}

	@Override
	public RoleMstDto findById(Long id) {
		RoleMst roleMst = roleMstDao.findById(id);
		RoleMstDto roleMstDto = new RoleMstDto();
		roleMstDto.setId(roleMst.getId());
		roleMstDto.setRoleName(roleMst.getRoleName());
		roleMstDto.setCreatedBy(roleMst.getCreatedBy());
		roleMstDto.setModifiedBy(roleMst.getModifiedBy());
		roleMstDto.setDeletedBy(roleMst.getDeletedBy());
		roleMstDto.setCreatedDate(roleMst.getCreatedDate());
		roleMstDto.setModifiedDate(roleMst.getModifiedDate());
		roleMstDto.setDeletedDate(roleMst.getDeletedDate());
		roleMstDto.setDeleteFlag(roleMst.getDeleteFlag());
		return roleMstDto;
	}

}
