package com.gtfs.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ROLE_ACCESS_COMBO")
public class RoleAccessCombo implements Serializable {
	private Long id;
	private RoleMst roleMst;
	private AccessMst accessMst;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public RoleMst getRoleMst() {
		return roleMst;
	}
	public void setRoleMst(RoleMst roleMst) {
		this.roleMst = roleMst;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCESS_ID")
	public AccessMst getAccessMst() {
		return accessMst;
	}
	public void setAccessMst(AccessMst accessMst) {
		this.accessMst = accessMst;
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
}
