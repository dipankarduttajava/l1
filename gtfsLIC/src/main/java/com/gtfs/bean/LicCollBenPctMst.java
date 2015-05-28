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
@Table(name = "LIC_COLL_BEN_PCT_MST")
public class LicCollBenPctMst implements Serializable{
	private Long id;
	private Long termFrom;
	private Long termTo;
	private Double collBenPct;
	private LicProductMst licProductMst;
	private SchemeMst schemeMst;
	private TieupCompyMst tieupCompyMst;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "TERM_FROM", precision = 22, scale = 0)
	public Long getTermFrom() {
		return termFrom;
	}

	public void setTermFrom(Long termFrom) {
		this.termFrom = termFrom;
	}
	
	
	@Column(name = "TERM_TO", precision = 22, scale = 0)
	public Long getTermTo() {
		return termTo;
	}

	public void setTermTo(Long termTo) {
		this.termTo = termTo;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIE_COMPY_ID")
	public TieupCompyMst getTieupCompyMst() {
		return tieupCompyMst;
	}

	public void setTieupCompyMst(TieupCompyMst tieupCompyMst) {
		this.tieupCompyMst = tieupCompyMst;
	}

	
	@Column(name = "COLL_BEN_PCT", precision = 22, scale = 0)
	public Double getCollBenPct() {
		return collBenPct;
	}

	public void setCollBenPct(Double collBenPct) {
		this.collBenPct = collBenPct;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_PRODUCT_MST_ID")
	public LicProductMst getLicProductMst() {
		return licProductMst;
	}

	public void setLicProductMst(LicProductMst licProductMst) {
		this.licProductMst = licProductMst;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHEME_MST_ID")
	public SchemeMst getSchemeMst() {
		return schemeMst;
	}

	public void setSchemeMst(SchemeMst schemeMst) {
		this.schemeMst = schemeMst;
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
	
}
