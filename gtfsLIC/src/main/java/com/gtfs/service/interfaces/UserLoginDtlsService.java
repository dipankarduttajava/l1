package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.UserLoginDtls;


public interface UserLoginDtlsService extends Serializable{
	List<Object> findMaximumLoginTimeOfUser(Long UserId);
	Long insert(UserLoginDtls userLoginDtls);
}
