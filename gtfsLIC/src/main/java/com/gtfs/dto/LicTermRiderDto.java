package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class LicTermRiderDto implements Serializable {
	private Long id;
	private Integer propAgeFrom;
	private Integer insAgeFrom;
	private Integer propAgeTo;
	private Integer insAgeTo;
	private Long pt;
	private Long ppt;
	private Double riderAmt;
	private Double riderNsapAmt;
	private Long licProdMstId;
	private String riderAmtType;
	private String riderNsapAmtType;
	private String deleteFlag;
	private Long deletedBy;
	private Date deletedDate;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPropAgeFrom() {
		return propAgeFrom;
	}
	public void setPropAgeFrom(Integer propAgeFrom) {
		this.propAgeFrom = propAgeFrom;
	}
	public Integer getInsAgeFrom() {
		return insAgeFrom;
	}
	public void setInsAgeFrom(Integer insAgeFrom) {
		this.insAgeFrom = insAgeFrom;
	}
	public Integer getPropAgeTo() {
		return propAgeTo;
	}
	public void setPropAgeTo(Integer propAgeTo) {
		this.propAgeTo = propAgeTo;
	}
	public Integer getInsAgeTo() {
		return insAgeTo;
	}
	public void setInsAgeTo(Integer insAgeTo) {
		this.insAgeTo = insAgeTo;
	}
	public Long getPt() {
		return pt;
	}
	public void setPt(Long pt) {
		this.pt = pt;
	}
	public Long getPpt() {
		return ppt;
	}
	public void setPpt(Long ppt) {
		this.ppt = ppt;
	}
	public Double getRiderAmt() {
		return riderAmt;
	}
	public void setRiderAmt(Double riderAmt) {
		this.riderAmt = riderAmt;
	}
	public Double getRiderNsapAmt() {
		return riderNsapAmt;
	}
	public void setRiderNsapAmt(Double riderNsapAmt) {
		this.riderNsapAmt = riderNsapAmt;
	}
	public Long getLicProdMstId() {
		return licProdMstId;
	}
	public void setLicProdMstId(Long licProdMstId) {
		this.licProdMstId = licProdMstId;
	}
	public String getRiderAmtType() {
		return riderAmtType;
	}
	public void setRiderAmtType(String riderAmtType) {
		this.riderAmtType = riderAmtType;
	}
	public String getRiderNsapAmtType() {
		return riderNsapAmtType;
	}
	public void setRiderNsapAmtType(String riderNsapAmtType) {
		this.riderNsapAmtType = riderNsapAmtType;
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
	
	
	
	
}
