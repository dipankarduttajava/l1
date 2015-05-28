package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.UserMst;


public interface UserMstService extends Serializable{
	 List<UserMst> login(String loginId, String password);
	 Boolean doChangePasswordProcess(Long userId, Long branchId, String newPassword);
	 List<UserMst> findActiveUserByUserId(Long userId);
	 List<UserMst> findActiveUserInfoByUserId(Long userId);
	 List<UserMst> findActiveUserInfoByUserName(String userName);
	 List<UserMst> findAllActiveUserInfo();
	 UserMst findById(Long userId);
	 Boolean deleteUserMst(Long userId, Long userLoginId);
	 
}
