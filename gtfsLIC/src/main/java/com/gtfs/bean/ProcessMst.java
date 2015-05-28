package com.gtfs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROCESS_MST")
public class ProcessMst implements Serializable{
	private Long id;
	private String processName;
	private String processAbbr;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	private Set<LicBranchHubPicMapping> licBranchHubPicMappings = new HashSet<LicBranchHubPicMapping>(0);
	private List<LicBranchHubMap> licBranchHubMaps = new ArrayList<LicBranchHubMap>();
	
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "PROCESS_NAME", length = 50)
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	@Column(name = "PROCESS_ABBR", length = 50)
	public String getProcessAbbr() {
		return processAbbr;
	}
	public void setProcessAbbr(String processAbbr) {
		this.processAbbr = processAbbr;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processMst")
	public Set<LicBranchHubPicMapping> getLicBranchHubPicMappings() {
		return licBranchHubPicMappings;
	}
	public void setLicBranchHubPicMappings(
			Set<LicBranchHubPicMapping> licBranchHubPicMappings) {
		this.licBranchHubPicMappings = licBranchHubPicMappings;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processMst")
	public List<LicBranchHubMap> getLicBranchHubMaps() {
		return licBranchHubMaps;
	}
	public void setLicBranchHubMaps(List<LicBranchHubMap> licBranchHubMaps) {
		this.licBranchHubMaps = licBranchHubMaps;
	}
	@Override
	public boolean equals(Object obj) {
		ProcessMst processMst=(ProcessMst) obj;
		return this.getId().equals(processMst.getId());
	}
}
