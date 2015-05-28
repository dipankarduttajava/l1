package com.gtfs.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


public class UserMstDto implements Serializable{

	private Long userid;
	private String userName;
	private String login;
	private String passwd;
	private Long desigId;
	private String desigName;
	private Long hoId;
	private String hoName;
	private Long regionId;
	private String regionName;
	private Long divId;
	private String divName;
	private Long branchId;
	private String branchName;
	private String activeFlag;
	private Date lastLogin;
	private String remarks;
	private Long tieupCompId;
	private String tieupCompName;
	private Long userId;
	private Date dateTime;
	private String superUserFlag;
	private String dept;
	private Boolean disabledFlag;
	
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Long getDesigId() {
		return desigId;
	}
	public void setDesigId(Long desigId) {
		this.desigId = desigId;
	}
	public String getDesigName() {
		return desigName;
	}
	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}
	public Long getHoId() {
		return hoId;
	}
	public void setHoId(Long hoId) {
		this.hoId = hoId;
	}
	public String getHoName() {
		return hoName;
	}
	public void setHoName(String hoName) {
		this.hoName = hoName;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Long getDivId() {
		return divId;
	}
	public void setDivId(Long divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getTieupCompId() {
		return tieupCompId;
	}
	public void setTieupCompId(Long tieupCompId) {
		this.tieupCompId = tieupCompId;
	}
	public String getTieupCompName() {
		return tieupCompName;
	}
	public void setTieupCompName(String tieupCompName) {
		this.tieupCompName = tieupCompName;
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
	public String getSuperUserFlag() {
		return superUserFlag;
	}
	public void setSuperUserFlag(String superUserFlag) {
		this.superUserFlag = superUserFlag;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Boolean getDisabledFlag() {
		return disabledFlag;
	}
	public void setDisabledFlag(Boolean disabledFlag) {
		this.disabledFlag = disabledFlag;
	}
	
}
