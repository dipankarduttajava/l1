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
@Table(name = "LIC_PREM_FREQ_ALLOW_AR_MST_2")
public class LicPremFreqAllowARMst2 implements Serializable{
	private Long id;
	private Long productId;
	private Long saTerm;
	private String consTermFlag;
	private String premFreqAllow;
	private Double minSa;
	private Double maxSa;
	private Double premSlab;
	private String arRiderType;
	private Double arRiderValue;
	private Integer arMinAge;
	private Integer arMaxAge;
	private String arCategory;
	private Long arTerm;
	private Long arPpt;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	/* GETTER SETTER */
	
	@Id
	@Column(name = "ID",nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "LIC_PROD_MST_ID", precision = 22, scale = 0)
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	@Column(name = "SA_TERM", precision = 22, scale = 0)
	public Long getSaTerm() {
		return saTerm;
	}
	public void setSaTerm(Long saTerm) {
		this.saTerm = saTerm;
	}
	
	@Column(name = "CONS_TERM_FLAG", length=1)
	public String getConsTermFlag() {
		return consTermFlag;
	}
	public void setConsTermFlag(String consTermFlag) {
		this.consTermFlag = consTermFlag;
	}
	
	@Column(name = "PREM_FREQ_ALLOW", length=5)
	public String getPremFreqAllow() {
		return premFreqAllow;
	}
	public void setPremFreqAllow(String premFreqAllow) {
		this.premFreqAllow = premFreqAllow;
	}
	
	@Column(name = "MIN_SA", precision = 22, scale = 0)
	public Double getMinSa() {
		return minSa;
	}
	public void setMinSa(Double minSa) {
		this.minSa = minSa;
	}
	
	@Column(name = "MAX_SA", precision = 22, scale = 0)
	public Double getMaxSa() {
		return maxSa;
	}
	public void setMaxSa(Double maxSa) {
		this.maxSa = maxSa;
	}
	
	@Column(name = "PREM_SLAB", precision = 22, scale = 0)
	public Double getPremSlab() {
		return premSlab;
	}
	public void setPremSlab(Double premSlab) {
		this.premSlab = premSlab;
	}
	
	@Column(name = "AR_RIDER_TYPE", length=5)
	public String getArRiderType() {
		return arRiderType;
	}
	public void setArRiderType(String arRiderType) {
		this.arRiderType = arRiderType;
	}
	
	@Column(name = "AR_RIDER_VALUE", precision = 22, scale = 0)
	public Double getArRiderValue() {
		return arRiderValue;
	}
	public void setArRiderValue(Double arRiderValue) {
		this.arRiderValue = arRiderValue;
	}
	
	@Column(name = "AR_MIN_AGE", precision = 22, scale = 0)
	public Integer getArMinAge() {
		return arMinAge;
	}
	public void setArMinAge(Integer arMinAge) {
		this.arMinAge = arMinAge;
	}
	
	@Column(name = "AR_MAX_AGE", precision = 22, scale = 0)
	public Integer getArMaxAge() {
		return arMaxAge;
	}
	public void setArMaxAge(Integer arMaxAge) {
		this.arMaxAge = arMaxAge;
	}
	
	@Column(name = "AR_CATEGORY", length=50)
	public String getArCategory() {
		return arCategory;
	}
	public void setArCategory(String arCategory) {
		this.arCategory = arCategory;
	}
	
	@Column(name = "AR_TERM", precision = 22, scale = 0)
	public Long getArTerm() {
		return arTerm;
	}
	public void setArTerm(Long arTerm) {
		this.arTerm = arTerm;
	}
	
	@Column(name = "AR_PPT", precision = 22, scale = 0)
	public Long getArPpt() {
		return arPpt;
	}
	public void setArPpt(Long arPpt) {
		this.arPpt = arPpt;
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
