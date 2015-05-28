package com.gtfs.bean;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "LIC_PREMIUM_LIST_DTLS")
public class LicPremiumListDtls implements Serializable{
	private Long id;
	private String premiumListNo;
	private Date premiumListDate;	
	private String premuumType;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private List<LicPremApplMapping> licPremApplMappings = new ArrayList<LicPremApplMapping>();
	
	private List<LicPremPaymentDtls>  licPremPaymentDtlses = new ArrayList<LicPremPaymentDtls>();
	private List<LicRequirementDtls> licRequirementDtlses = new ArrayList<LicRequirementDtls>();
	private List<LicPremReqMapping> licPremReqMappings = new ArrayList<LicPremReqMapping>();
	private List<LicPremPolicyMapping> licPremPolicyMappings = new ArrayList<LicPremPolicyMapping>();
	

	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name="LIC_PREMIUM_LIST_DTLS_SEQ",sequenceName="LIC_PREMIUM_LIST_DTLS_SEQ")
	@GeneratedValue(generator="LIC_PREMIUM_LIST_DTLS_SEQ",strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "PREMIUM_LIST_NO", length = 50)
	public String getPremiumListNo() {
		return premiumListNo;
	}
	public void setPremiumListNo(String premiumListNo) {
		this.premiumListNo = premiumListNo;
	}
	
	
	@Column(name = "PREMIUM_TYPE", length = 1)
	public String getPremuumType() {
		return premuumType;
	}
	public void setPremuumType(String premuumType) {
		this.premuumType = premuumType;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PREMIUM_LIST_DATE", length = 7)
	public Date getPremiumListDate() {
		return premiumListDate;
	}
	public void setPremiumListDate(Date premiumListDate) {
		this.premiumListDate = premiumListDate;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPremiumListDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPremPaymentDtls> getLicPremPaymentDtlses() {
		return licPremPaymentDtlses;
	}
	public void setLicPremPaymentDtlses(
			List<LicPremPaymentDtls> licPremPaymentDtlses) {
		this.licPremPaymentDtlses = licPremPaymentDtlses;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPremiumListDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPremApplMapping> getLicPremApplMappings() {
		return licPremApplMappings;
	}
	public void setLicPremApplMappings(List<LicPremApplMapping> licPremApplMappings) {
		this.licPremApplMappings = licPremApplMappings;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPremiumListDtls")
	public List<LicRequirementDtls> getLicRequirementDtlses() {
		return licRequirementDtlses;
	}
	public void setLicRequirementDtlses(
			List<LicRequirementDtls> licRequirementDtlses) {
		this.licRequirementDtlses = licRequirementDtlses;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPremiumListDtls")
	public List<LicPremReqMapping> getLicPremReqMappings() {
		return licPremReqMappings;
	}
	public void setLicPremReqMappings(List<LicPremReqMapping> licPremReqMappings) {
		this.licPremReqMappings = licPremReqMappings;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPremiumListDtls")
	public List<LicPremPolicyMapping> getLicPremPolicyMappings() {
		return licPremPolicyMappings;
	}
	public void setLicPremPolicyMappings(
			List<LicPremPolicyMapping> licPremPolicyMappings) {
		this.licPremPolicyMappings = licPremPolicyMappings;
	}
	
	@Override
	public boolean equals(Object obj) {
		LicPremiumListDtls object=(LicPremiumListDtls) obj;
		return this.id.equals(object.getId());
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
