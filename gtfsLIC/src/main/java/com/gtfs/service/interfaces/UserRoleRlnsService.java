package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.UserRoleRlns;


public interface UserRoleRlnsService extends Serializable{
	List<UserRoleRlns> findActiveUserRoleByUserId(Long userId);
	Long insertIntoUserRoleRlns(UserRoleRlns userRoleRlns);
	Integer deleteFromUserRoleRlnsByRoleIdAndUserId(Long roleId,Long userid, Long loginUserId);
}
