package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class RoleMstDto implements Serializable{	
	private Long roleId;
	private String roleName;
	private String activeFlag;
	private Long userId;
	private Date dateTime;
	private Boolean disabledFlag;
	
	
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
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Boolean getDisabledFlag() {
		return disabledFlag;
	}
	public void setDisabledFlag(Boolean disabledFlag) {
		this.disabledFlag = disabledFlag;
	}
	
}
