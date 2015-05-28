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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "LIC_PIS_MST")
public class LicPisMst implements Serializable{
	private Long id;
	private String pisNo;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<LicOblApplicationMst> licOblApplicationMsts = new ArrayList<LicOblApplicationMst>();
	private List<LicCmsMst> licCmsMsts = new ArrayList<LicCmsMst>();
	private List<LicRequirementDtls> licRequirementDtlses = new ArrayList<LicRequirementDtls>();
	private List<LicPolicyDtls> licPolicyDtlses = new ArrayList<LicPolicyDtls>();
	
	
	@Id
	@SequenceGenerator(name="LIC_PIS_MST_SEQ",sequenceName="LIC_PIS_MST_SEQ")
	@GeneratedValue(generator="LIC_PIS_MST_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "PIS_NO",length = 50)
	public String getPisNo() {
		return pisNo;
	}
	public void setPisNo(String pisNo) {
		this.pisNo = pisNo;
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
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPisMst")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicOblApplicationMst> getLicOblApplicationMsts() {
		return licOblApplicationMsts;
	}
	public void setLicOblApplicationMsts(
			List<LicOblApplicationMst> licOblApplicationMsts) {
		this.licOblApplicationMsts = licOblApplicationMsts;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPisMst")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicCmsMst> getLicCmsMsts() {
		return licCmsMsts;
	}
	public void setLicCmsMsts(List<LicCmsMst> licCmsMsts) {
		this.licCmsMsts = licCmsMsts;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPisMst")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicRequirementDtls> getLicRequirementDtlses() {
		return licRequirementDtlses;
	}
	public void setLicRequirementDtlses(
			List<LicRequirementDtls> licRequirementDtlses) {
		this.licRequirementDtlses = licRequirementDtlses;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licPisMst")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPolicyDtls> getLicPolicyDtlses() {
		return licPolicyDtlses;
	}
	public void setLicPolicyDtlses(List<LicPolicyDtls> licPolicyDtlses) {
		this.licPolicyDtlses = licPolicyDtlses;
	}
}
