package com.gtfs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LIC_TAB_PREM_MST")
public class LicTabPremMst implements Serializable{
	private Long id;
	private LicProductMst licProductMst;
	private Integer age;
	private Long premiumPayingTerm;
	private Long policyTerm;
	private Double tabPrem;
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
	
	
	@Column(name = "AGE", precision = 22, scale = 0)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	@Column(name = "POLICY_TERM", precision = 22, scale = 0)
	public Long getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(Long policyTerm) {
		this.policyTerm = policyTerm;
	}
	
	
	@Column(name = "PREMIUM_PAYING_TERM", precision = 22, scale = 0)
	public Long getPremiumPayingTerm() {
		return premiumPayingTerm;
	}
	public void setPremiumPayingTerm(Long premiumPayingTerm) {
		this.premiumPayingTerm = premiumPayingTerm;
	}
	
	
	@Column(name = "TAB_PREM", precision = 22, scale = 0)
	public Double getTabPrem() {
		return tabPrem;
	}
	public void setTabPrem(Double tabPrem) {
		this.tabPrem = tabPrem;
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
