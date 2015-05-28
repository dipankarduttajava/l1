package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.UserLoginDtls;

public interface UserLoginDtlsDao {
	List<Object> findMaximumLoginTimeOfUser(Long UserId);
	Long insert(UserLoginDtls userLoginDtls);
	Long save(UserLoginDtls userLoginDtls);
}
