package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

import com.gtfs.bean.LicPisMst;

public class LicCmsMstDto implements Serializable{
	private Long id;
	private Double amount;
	private String cmsNo;
	private Long createdBy;
	private Date createdDate;
	private String deleteFlag;
	private Long deletedBy;
	private Date deletedDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private String payMode;
	private LicPisMst licPisMst;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCmsNo() {
		return cmsNo;
	}
	public void setCmsNo(String cmsNo) {
		this.cmsNo = cmsNo;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Long getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public LicPisMst getLicPisMst() {
		return licPisMst;
	}
	public void setLicPisMst(LicPisMst licPisMst) {
		this.licPisMst = licPisMst;
	}
	
	@Override
	public boolean equals(Object obj) {
		LicCmsMstDto licCmsMstDto = (LicCmsMstDto) obj;
		return (payMode+amount).equals(licCmsMstDto.getPayMode()+licCmsMstDto.getAmount());
	}
	
	@Override
	public int hashCode() {
		return (payMode+amount).hashCode();
	}
}
