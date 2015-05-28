package com.gtfs.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.gtfs.bean.RoleAccessRlns;

public interface RoleAccessRlnsService extends Serializable{
	List<RoleAccessRlns> findActiveRoleAccessRlnsByAccessId(Long accessId);
	Long insertIntoRoleAccessRlns(RoleAccessRlns roleAccessRlns);
	Integer deleteRoleAccessRlnsByRoleIdAndAccessId(Long roleId,Long accessId);
	
}
