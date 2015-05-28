package com.gtfs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtfs.dao.UserMstDao;
import com.gtfs.dto.UserMstDto;
import com.gtfs.pojo.UserMst;
import com.gtfs.service.UserMstService;

@Service
public class UserMstServiceImpl implements UserMstService, Serializable {

	@Autowired
	private UserMstDao userMstDao;
	
	@Override
	public List<UserMstDto> findAll() {
		return userMstDao.findAll();
	}

	@Override
	public UserMstDto findById(Long id) {
		UserMst userMst = userMstDao.findById(id);
		UserMstDto userMstDto = new UserMstDto();
		userMstDto.setId(userMst.getId());
		userMstDto.setUserName(userMst.getUserName());
		userMstDto.setUserType(userMst.getUserType());
		userMstDto.setLoginId(userMst.getLoginId());
		userMstDto.setPassword(userMst.getPassword());
		userMstDto.setCreatedBy(userMst.getCreatedBy());
		userMstDto.setModifiedBy(userMst.getModifiedBy());
		userMstDto.setDeletedBy(userMst.getDeletedBy());
		userMstDto.setCreatedDate(userMst.getCreatedDate());
		userMstDto.setModifiedDate(userMst.getModifiedDate());
		userMstDto.setDeletedDate(userMst.getDeletedDate());
		userMstDto.setDeleteFlag(userMst.getDeleteFlag());
		return userMstDto;
	}

	@Override
	public Boolean saveOrUpdate(UserMstDto userMstDto) {		
		UserMst userMst = new UserMst();
		userMst.setId(userMstDto.getId());
		userMst.setUserName(userMstDto.getUserName());
		userMst.setUserType(userMstDto.getUserType());
		userMst.setLoginId(userMstDto.getLoginId());
		userMst.setPassword(userMstDto.getPassword());
		userMst.setCreatedBy(userMstDto.getCreatedBy());
		userMst.setModifiedBy(userMstDto.getModifiedBy());
		userMst.setDeletedBy(userMstDto.getDeletedBy());
		userMst.setCreatedDate(userMstDto.getCreatedDate());
		userMst.setModifiedDate(userMstDto.getModifiedDate());
		userMst.setDeletedDate(userMstDto.getDeletedDate());
		userMst.setDeleteFlag(userMstDto.getDeleteFlag());		
		return userMstDao.saveOrUpdate(userMst);
	}

}
