package com.gtfs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "LIC_TERM_RIDER")
public class LicTermRider implements Serializable {
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
	
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "PROP_AGE_FROM", precision = 22, scale = 0)
	public Integer getPropAgeFrom() {
		return propAgeFrom;
	}
	public void setPropAgeFrom(Integer propAgeFrom) {
		this.propAgeFrom = propAgeFrom;
	}
	@Column(name = "INS_AGE_FROM", precision = 22, scale = 0)
	public Integer getInsAgeFrom() {
		return insAgeFrom;
	}
	public void setInsAgeFrom(Integer insAgeFrom) {
		this.insAgeFrom = insAgeFrom;
	}
	@Column(name = "PT", precision = 22, scale = 0)
	public Long getPt() {
		return pt;
	}
	public void setPt(Long pt) {
		this.pt = pt;
	}
	@Column(name = "PPT", precision = 22, scale = 0)
	public Long getPpt() {
		return ppt;
	}
	public void setPpt(Long ppt) {
		this.ppt = ppt;
	}
	@Column(name = "RIDER_VALUE", precision = 22, scale = 0)
	public Double getRiderAmt() {
		return riderAmt;
	}
	public void setRiderAmt(Double riderAmt) {
		this.riderAmt = riderAmt;
	}
	@Column(name = "RIDER_NSAP_VALUE", precision = 22, scale = 0)
	public Double getRiderNsapAmt() {
		return riderNsapAmt;
	}
	public void setRiderNsapAmt(Double riderNsapAmt) {
		this.riderNsapAmt = riderNsapAmt;
	}
	@Column(name = "LIC_PROD_MST_ID", precision = 22, scale = 0)
	public Long getLicProdMstId() {
		return licProdMstId;
	}
	public void setLicProdMstId(Long licProdMstId) {
		this.licProdMstId = licProdMstId;
	}
	@Column(name = "PROP_AGE_TO", precision = 22, scale = 0)
	public Integer getPropAgeTo() {
		return propAgeTo;
	}
	public void setPropAgeTo(Integer propAgeTo) {
		this.propAgeTo = propAgeTo;
	}
	@Column(name = "INS_AGE_TO", precision = 22, scale = 0)
	public Integer getInsAgeTo() {
		return insAgeTo;
	}
	public void setInsAgeTo(Integer insAgeTo) {
		this.insAgeTo = insAgeTo;
	}
	@Column(name = "RIDER_AMT_TYPE", length=20)
	public String getRiderAmtType() {
		return riderAmtType;
	}
	public void setRiderAmtType(String riderAmtType) {
		this.riderAmtType = riderAmtType;
	}
	@Column(name = "RIDER_NSAP_AMT_TYPE", length=20)
	public String getRiderNsapAmtType() {
		return riderNsapAmtType;
	}
	public void setRiderNsapAmtType(String riderNsapAmtType) {
		this.riderNsapAmtType = riderNsapAmtType;
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
