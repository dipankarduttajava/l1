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
@Table(name = "LIC_MARKING_TO_QC_DTLS")
public class LicMarkingToQcDtls implements Serializable{
	private Long id;
	private String consldMrkFlag;
	private Date consldDate;
	private Long consldBy;
	private String indMrkFlag;
	private Date indMrkDate;
	private Long indMrkBy;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<LicRequirementDtls> licRequirementDtls = new ArrayList<LicRequirementDtls>();
	private List<LicOblApplicationMst> licOblApplicationMsts = new ArrayList<LicOblApplicationMst>();
	private List<LicPolicyDtls> licPolicyDtlses = new ArrayList<LicPolicyDtls>();
		
	
	@Id
	@SequenceGenerator(name="LIC_MARKING_TO_QC_DTLS_SEQ",sequenceName="LIC_MARKING_TO_QC_DTLS_SEQ")
	@GeneratedValue(generator="LIC_MARKING_TO_QC_DTLS_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="CONSLD_MRK_FLAG",length=1)
	public String getConsldMrkFlag() {
		return consldMrkFlag;
	}
	public void setConsldMrkFlag(String consldMrkFlag) {
		this.consldMrkFlag = consldMrkFlag;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CONSLD_DATE", length = 7)
	public Date getConsldDate() {
		return consldDate;
	}
	public void setConsldDate(Date consldDate) {
		this.consldDate = consldDate;
	}
	
	
	@Column(name = "CONSLD_BY", precision = 22, scale = 0)
	public Long getConsldBy() {
		return consldBy;
	}
	public void setConsldBy(Long consldBy) {
		this.consldBy = consldBy;
	}
	
	
	@Column(name="IND_MRK_FLAG",length=1)
	public String getIndMrkFlag() {
		return indMrkFlag;
	}
	public void setIndMrkFlag(String indMrkFlag) {
		this.indMrkFlag = indMrkFlag;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "IND_MRK_DATE", length = 7)
	public Date getIndMrkDate() {
		return indMrkDate;
	}
	public void setIndMrkDate(Date indMrkDate) {
		this.indMrkDate = indMrkDate;
	}
	
	
	@Column(name = "IND_MRK_BY", precision = 22, scale = 0)
	public Long getIndMrkBy() {
		return indMrkBy;
	}
	public void setIndMrkBy(Long indMrkBy) {
		this.indMrkBy = indMrkBy;
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
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licMarkingToQcDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicOblApplicationMst> getLicOblApplicationMsts() {
		return licOblApplicationMsts;
	}
	public void setLicOblApplicationMsts(
			List<LicOblApplicationMst> licOblApplicationMsts) {
		this.licOblApplicationMsts = licOblApplicationMsts;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licMarkingToQcDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicRequirementDtls> getLicRequirementDtls() {
		return licRequirementDtls;
	}
	public void setLicRequirementDtls(List<LicRequirementDtls> licRequirementDtls) {
		this.licRequirementDtls = licRequirementDtls;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licMarkingToQcDtls")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicPolicyDtls> getLicPolicyDtlses() {
		return licPolicyDtlses;
	}
	public void setLicPolicyDtlses(List<LicPolicyDtls> licPolicyDtlses) {
		this.licPolicyDtlses = licPolicyDtlses;
	}
	
	
}
