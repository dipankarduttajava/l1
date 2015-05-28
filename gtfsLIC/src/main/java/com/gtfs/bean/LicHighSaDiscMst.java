package com.gtfs.bean;

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
@Table(name = "LIC_HIGH_SA_DISC_MST")
public class LicHighSaDiscMst implements Serializable{
	private Long id;
	private LicProductMst licProductMst;
	private Long term;
	private String consTermFlag;
	private Double hsdSaFrom;
	private Double hsdSaTo;
	private String hsdType;
	private Double hsdValue;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	@Id
	@Column(name = "ID",nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_PROD_MST_ID")
	public LicProductMst getLicProductMst() {
		return licProductMst;
	}
	public void setLicProductMst(LicProductMst licProductMst) {
		this.licProductMst = licProductMst;
	}
	
	
	@Column(name = "TERM", precision = 22, scale = 0)
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	
	
	@Column(name = "CONS_TERM_FLAG", length=1)
	public String getConsTermFlag() {
		return consTermFlag;
	}
	public void setConsTermFlag(String consTermFlag) {
		this.consTermFlag = consTermFlag;
	}
	
	
	@Column(name = "HSD_SA_FROM", precision = 22, scale = 0)
	public Double getHsdSaFrom() {
		return hsdSaFrom;
	}
	public void setHsdSaFrom(Double hsdSaFrom) {
		this.hsdSaFrom = hsdSaFrom;
	}
	
	
	@Column(name = "HSD_SA_TO", precision = 22, scale = 0)
	public Double getHsdSaTo() {
		return hsdSaTo;
	}
	public void setHsdSaTo(Double hsdSaTo) {
		this.hsdSaTo = hsdSaTo;
	}
	
	
	@Column(name = "HSD_TYPE", length=5)
	public String getHsdType() {
		return hsdType;
	}
	public void setHsdType(String hsdType) {
		this.hsdType = hsdType;
	}
	
	
	@Column(name = "HSD_VALUE", precision = 22, scale = 0)
	public Double getHsdValue() {
		return hsdValue;
	}
	public void setHsdValue(Double hsdValue) {
		this.hsdValue = hsdValue;
	}
	
	
	@Column(name = "CREATED_BY", precision = 22, scale = 0)
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	
	@Column(name = "MODIFIED_BY", precision = 22, scale = 0)
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	@Column(name = "DELETED_BY", precision = 22, scale = 0)
	public Long getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 7)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_DATE", length = 7)
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DELETED_DATE", length = 7)
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	
	
	@Column(name = "DELETE_FLAG", length = 1)
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}
