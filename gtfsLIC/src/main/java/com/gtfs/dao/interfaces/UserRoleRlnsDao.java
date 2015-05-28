package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.UserRoleRlns;

public interface UserRoleRlnsDao {
	List<UserRoleRlns> findActiveUserRoleByUserId(Long userId);
	Long insertIntoUserRoleRlns(UserRoleRlns userRoleRlns);
	Integer deleteFromUserRoleRlnsByRoleIdAndUserId(Long roleId, Long userid, Long loginUserId);
}
