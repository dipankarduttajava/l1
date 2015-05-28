package com.gtfs.dto;

import java.io.Serializable;

public class RoleAccessRlnsDto implements Serializable{
	private Long accessId;
	private Long roleId;
	private String roleName;
	public Boolean roleGivenStatus;
	
	
	public Boolean getRoleGivenStatus() {
		return roleGivenStatus;
	}
	public void setRoleGivenStatus(Boolean roleGivenStatus) {
		this.roleGivenStatus = roleGivenStatus;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getAccessId() {
		return accessId;
	}
	public void setAccessId(Long accessId) {
		this.accessId = accessId;
	}
	
}
