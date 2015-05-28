package com.gtfs.dao;

import java.util.List;

import com.gtfs.dto.UserMstDto;
import com.gtfs.pojo.UserMst;

public interface UserMstDao {
	List<UserMstDto> findAll();
	UserMst findById(Long id);
	Boolean saveOrUpdate(UserMst userMst);
}
