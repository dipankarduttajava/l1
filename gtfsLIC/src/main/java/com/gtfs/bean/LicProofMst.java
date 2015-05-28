package com.gtfs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LIC_PROOF_MST")
public class LicProofMst implements Serializable{
	private Long id;
	private String proofName;
	private String proofFlag;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<LicInsuredDtls> addrProofList = new ArrayList<LicInsuredDtls>();
	private List<LicInsuredDtls> incomeProofList = new ArrayList<LicInsuredDtls>();
	private List<LicInsuredDtls> ageProofList = new ArrayList<LicInsuredDtls>();
	private List<LicInsuredDtls> identityProofList = new ArrayList<LicInsuredDtls>();
	
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "PROOF_NAME",length = 250)
	public String getProofName() {
		return proofName;
	}	
	public void setProofName(String proofName) {
		this.proofName = proofName;
	}
	
	
	@Column(name = "PROOF_FLAG",length = 2)
	public String getProofFlag() {
		return proofFlag;
	}
	public void setProofFlag(String proofFlag) {
		this.proofFlag = proofFlag;
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
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addrProof")
	public List<LicInsuredDtls> getAddrProofList() {
		return addrProofList;
	}
	public void setAddrProofList(List<LicInsuredDtls> addrProofList) {
		this.addrProofList = addrProofList;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "incomeProof")
	public List<LicInsuredDtls> getIncomeProofList() {
		return incomeProofList;
	}
	public void setIncomeProofList(List<LicInsuredDtls> incomeProofList) {
		this.incomeProofList = incomeProofList;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ageProofNature")
	public List<LicInsuredDtls> getAgeProofList() {
		return ageProofList;
	}
	public void setAgeProofList(List<LicInsuredDtls> ageProofList) {
		this.ageProofList = ageProofList;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "identityProof")
	public List<LicInsuredDtls> getIdentityProofList() {
		return identityProofList;
	}
	public void setIdentityProofList(List<LicInsuredDtls> identityProofList) {
		this.identityProofList = identityProofList;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LicProofMst)) {
			return false;
			}
		LicProofMst licProofMst = (LicProofMst) obj;
		return (this.id.equals(licProofMst.id));
	}
}
