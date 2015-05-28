package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.AccessMstDao;
import com.gtfs.dto.AccessMstDto;
import com.gtfs.pojo.AccessMst;
import com.gtfs.service.AccessMstService;

@Service
public class AccessMstServiceImpl implements Serializable, AccessMstService{
	
	@Autowired
	private AccessMstDao accessMstDao;
	
	@Override
	public List<AccessMstDto> findAll() {
		return accessMstDao.findAll();
	}

	@Override
	public AccessMstDto findById(Long id) {
		AccessMst accessMst = accessMstDao.findById(id);
		AccessMstDto accessMstDto = new AccessMstDto();
		accessMstDto.setId(accessMst.getId());
		accessMstDto.setAccessName(accessMst.getAccessName());
		accessMstDto.setCreatedBy(accessMst.getCreatedBy());
		accessMstDto.setModifiedBy(accessMst.getModifiedBy());
		accessMstDto.setDeletedBy(accessMst.getDeletedBy());
		accessMstDto.setCreatedDate(accessMst.getCreatedDate());
		accessMstDto.setModifiedDate(accessMst.getModifiedDate());
		accessMstDto.setDeletedDate(accessMst.getDeletedDate());
		accessMstDto.setDeleteFlag(accessMst.getDeleteFlag());
		return accessMstDto;
	}
	
}
