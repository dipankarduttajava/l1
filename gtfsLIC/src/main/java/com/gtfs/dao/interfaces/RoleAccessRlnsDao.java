package com.gtfs.dao.interfaces;

import java.util.List;

import com.gtfs.bean.RoleAccessRlns;

public interface RoleAccessRlnsDao {
	List<RoleAccessRlns> findActiveRoleAccessRlnsByAccessId(Long accessId);
	Long insertIntoRoleAccessRlns(RoleAccessRlns roleAccessRlns);
	Integer deleteRoleAccessRlnsByRoleIdAndAccessId(Long roleId,Long accessId);
}
