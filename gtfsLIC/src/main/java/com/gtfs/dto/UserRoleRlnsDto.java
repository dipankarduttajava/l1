package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

import com.gtfs.bean.RoleMst;
import com.gtfs.bean.UserMst;

public class UserRoleRlnsDto implements Serializable{

	private Long id;
	private Long roleId;
	private String roleName;
	private String activeFlag;
	private Long userid;
	private Long userId;
	private Date dateTime;
	public Boolean roleGivenStatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Boolean getRoleGivenStatus() {
		return roleGivenStatus;
	}
	public void setRoleGivenStatus(Boolean roleGivenStatus) {
		this.roleGivenStatus = roleGivenStatus;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}

}
