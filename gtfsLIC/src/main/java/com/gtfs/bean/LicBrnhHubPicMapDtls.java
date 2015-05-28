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
@Table(name = "LIC_BRNH_HUB_PIC_MAP_DTLS")
public class LicBrnhHubPicMapDtls implements Serializable{
	private Long id;
	private String dispatchListNo;
	private Date dispatchListDate;
	private Double totalAmount;
	private String branchHubPicFlag;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<LicPolicyDtls> licPolicyDtlses = new ArrayList<LicPolicyDtls>();	
	private List<LicPolicyDtls> hubPicLicPolicyDtlses = new ArrayList<LicPolicyDtls>();	
	private List<LicOblApplicationMst> licOblApplicationMsts = new ArrayList<LicOblApplicationMst>(0);
	private List<LicOblApplicationMst> hubPicApplicationMsts = new ArrayList<LicOblApplicationMst>(0);
	private List<LicBranchReqRcvMst> licBranchReqRcvMsts = new ArrayList<LicBranchReqRcvMst>();
	private List<LicRequirementDtls> licRequirementDtlses = new ArrayList<LicRequirementDtls>();
	
	
	@Id
	@SequenceGenerator(name="LIC_BRNH_HUB_PIC_MAP_DTLS_SEQ",sequenceName="LIC_BRNH_HUB_PIC_MAP_DTLS_SEQ")
	@GeneratedValue(generator="LIC_BRNH_HUB_PIC_MAP_DTLS_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "DISPATCH_LIST_NO", length=50)
	public String getDispatchListNo() {
		return dispatchListNo;
	}
	
	public void setDispatchListNo(String dispatchListNo) {
		this.dispatchListNo = dispatchListNo;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DISPATCH_LIST_DATE", length = 7)
	public Date getDispatchListDate() {
		return dispatchListDate;
	}
	public void setDispatchListDate(Date dispatchListDate) {
		this.dispatchListDate = dispatchListDate;
	}
	
	
	@Column(name = "TOTAL_AMOUNT", precision = 22, scale = 0)
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	@Column(name = "BRANCH_HUB_PIC_FLAG", length=10)
	public String getBranchHubPicFlag() {
		return branchHubPicFlag;
	}
	public void setBranchHubPicFlag(String branchHubPicFlag) {
		this.branchHubPicFlag = branchHubPicFlag;
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
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brnhHubMapDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicOblApplicationMst> getLicOblApplicationMsts() {
		return licOblApplicationMsts;
	}
	public void setLicOblApplicationMsts(
			List<LicOblApplicationMst> licOblApplicationMsts) {
		this.licOblApplicationMsts = licOblApplicationMsts;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hubPicMapDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicOblApplicationMst> getHubPicApplicationMsts() {
		return hubPicApplicationMsts;
	}
	public void setHubPicApplicationMsts(
			List<LicOblApplicationMst> hubPicApplicationMsts) {
		this.hubPicApplicationMsts = hubPicApplicationMsts;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licBrnhHubPicMapDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicBranchReqRcvMst> getLicBranchReqRcvMsts() {
		return licBranchReqRcvMsts;
	}
	public void setLicBranchReqRcvMsts(List<LicBranchReqRcvMst> licBranchReqRcvMsts) {
		this.licBranchReqRcvMsts = licBranchReqRcvMsts;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licBrnhHubPicMapDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicRequirementDtls> getLicRequirementDtlses() {
		return licRequirementDtlses;
	}
	public void setLicRequirementDtlses(
			List<LicRequirementDtls> licRequirementDtlses) {
		this.licRequirementDtlses = licRequirementDtlses;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brnhHubMapDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPolicyDtls> getLicPolicyDtlses() {
		return licPolicyDtlses;
	}
	public void setLicPolicyDtlses(List<LicPolicyDtls> licPolicyDtlses) {
		this.licPolicyDtlses = licPolicyDtlses;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hubPicMapDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPolicyDtls> getHubPicLicPolicyDtlses() {
		return hubPicLicPolicyDtlses;
	}
	public void setHubPicLicPolicyDtlses(List<LicPolicyDtls> hubPicLicPolicyDtlses) {
		this.hubPicLicPolicyDtlses = hubPicLicPolicyDtlses;
	}
}
