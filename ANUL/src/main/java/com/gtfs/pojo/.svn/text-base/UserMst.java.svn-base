package com.gtfs.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER_MST")
public class UserMst implements Serializable {
	private Long id;
	private String userName;
	private String userType;
	private String loginId;
	private String password;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate; 
	private String deleteFlag;
	
	private List<UserRoleCombo> userRoleCombos = new ArrayList<UserRoleCombo>();
		
	@Id
	@SequenceGenerator(name="USER_MST_SEQ",sequenceName="USER_MST_SEQ")
	@GeneratedValue(generator="USER_MST_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="USER_NAME",length=50)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="USER_TYPE",length=10)
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(name="LOGIN_ID",length=20)
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	@Column(name="PASSWORD",length=20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "CREATED_BY", precision = 22, scale = 0)
	public Long getCreatedBy() {
	return this.createdBy;
	}
	public void setCreatedBy(Long createdBy) {
	this.createdBy = createdBy;
	}
	@Column(name = "MODIFIED_BY", precision = 22, scale = 0)
	public Long getModifiedBy() {
	return this.modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
	this.modifiedBy = modifiedBy;
	}
	@Column(name = "DELETED_BY", precision = 22, scale = 0)
	public Long getDeletedBy() {
	return this.deletedBy;
	}
	public void setDeletedBy(Long deletedBy) {
	this.deletedBy = deletedBy;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 7)
	public Date getCreatedDate() {
	return this.createdDate;
	}
	public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_DATE", length = 7)
	public Date getModifiedDate() {
	return this.modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DELETED_DATE", length = 7)
	public Date getDeletedDate() {
	return this.deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
	this.deletedDate = deletedDate;
	}
	@Column(name = "DELETE_FLAG", length = 1)
	public String getDeleteFlag() {
	return this.deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
	this.deleteFlag = deleteFlag;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userMst")
	public List<UserRoleCombo> getUserRoleCombos() {
		return userRoleCombos;
	}
	public void setUserRoleCombos(List<UserRoleCombo> userRoleCombos) {
		this.userRoleCombos = userRoleCombos;
	}
	
	
}
