package com.gtfs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LIC_NSAP_DOC_MST")
public class LicNsapDocMst implements Serializable{
	private Long id;
	private String docName;
	private Integer docAgeFrom;
	private Integer docAgeTo;
	private String docType;
	private Double sumAssuredFrom;
	private Double sumAssuredTo;
	private LicProductMst licProductMst;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<LicProductValueMst> licProductValueMsts = new ArrayList<LicProductValueMst>();
	
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "DOC_NAME", length=100)
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	
	@Column(name = "DOC_AGE_FROM", precision = 22, scale = 0)
	public Integer getDocAgeFrom() {
		return docAgeFrom;
	}
	public void setDocAgeFrom(Integer docAgeFrom) {
		this.docAgeFrom = docAgeFrom;
	}
	
	
	@Column(name = "DOC_AGE_TO", precision = 22, scale = 0)
	public Integer getDocAgeTo() {
		return docAgeTo;
	}
	public void setDocAgeTo(Integer docAgeTo) {
		this.docAgeTo = docAgeTo;
	}
	
	
	@Column(name = "DOC_Type", length=1)
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
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
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licNsapDocMst")
	public List<LicProductValueMst> getLicProductValueMsts() {
		return licProductValueMsts;
	}
	public void setLicProductValueMsts(List<LicProductValueMst> licProductValueMsts) {
		this.licProductValueMsts = licProductValueMsts;
	}
	
	
	@Column(name = "SUM_ASSURED_FROM", precision = 22, scale = 0)
	public Double getSumAssuredFrom() {
		return sumAssuredFrom;
	}
	public void setSumAssuredFrom(Double sumAssuredFrom) {
		this.sumAssuredFrom = sumAssuredFrom;
	}
	
	
	@Column(name = "SUM_ASSURED_TO", precision = 22, scale = 0)
	public Double getSumAssuredTo() {
		return sumAssuredTo;
	}
	public void setSumAssuredTo(Double sumAssuredTo) {
		this.sumAssuredTo = sumAssuredTo;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LIC_PRODUCT_MST_ID")
	public LicProductMst getLicProductMst() {
		return licProductMst;
	}
	public void setLicProductMst(LicProductMst licProductMst) {
		this.licProductMst = licProductMst;
	}
	
	
}
