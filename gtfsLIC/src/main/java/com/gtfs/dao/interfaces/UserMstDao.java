package com.gtfs.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.UserMst;

public interface UserMstDao {
	List<UserMst> login(String loginId, String password);
	List<UserMst> findActiveUserByUserId(Long userId);
	UserMst findById(Serializable id);
	Long save(UserMst userMst);
	Boolean update(UserMst userMst);
	List<UserMst> findActiveUserInfoByUserId(Long userId);
	List<UserMst> findActiveUserInfoByUserName(String userName);
	List<UserMst> findAllActiveUserInfo();
	Boolean deleteUserMst(Long userId, Long userLoginId);
}
