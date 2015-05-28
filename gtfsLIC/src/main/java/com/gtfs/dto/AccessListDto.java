package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class AccessListDto implements Serializable{
	private Long accessId;
	private String accessName;
	private String createdBy;
	private Long createdById;
	private String modifiedBy;
	private String deleteFlag;
	private Date createdDate;
	private Date modifiedDate;
	private Long modifiedById;
	private Boolean disabledFlag;
	
	
	public Long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}
	public Long getModifiedById() {
		return modifiedById;
	}
	public void setModifiedById(Long modifiedById) {
		this.modifiedById = modifiedById;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long getAccessId() {
		return accessId;
	}
	public void setAccessId(Long accessId) {
		this.accessId = accessId;
	}
	public String getAccessName() {
		return accessName;
	}
	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Boolean getDisabledFlag() {
		return disabledFlag;
	}
	public void setDisabledFlag(Boolean disabledFlag) {
		this.disabledFlag = disabledFlag;
	}
	
	
}
