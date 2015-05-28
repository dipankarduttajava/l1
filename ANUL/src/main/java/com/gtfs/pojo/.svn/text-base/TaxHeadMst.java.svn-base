package com.gtfs.pojo;

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
@Table(name = "TAX_HEAD_MST")
public class TaxHeadMst implements Serializable {

	private Long id;
	private String taxHeadName;
	private Double percentage;
	private Date fromDate;
	private Date toDate;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<FlatTaxCombo> flatTaxCombos = new ArrayList<FlatTaxCombo>();
	private List<FlatTaxApplicableCombo> flatTaxApplicableCombos = new ArrayList<FlatTaxApplicableCombo>();
	
	
	/* GETTER SETTER */
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="TAX_HEAD_NAME" , length=25)
	public String getTaxHeadName() {
		return taxHeadName;
	}
	public void setTaxHeadName(String taxHeadName) {
		this.taxHeadName = taxHeadName;
	}
	
	@Column(name = "PERCENTAGE", precision = 22, scale = 0)
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FROM_DATE", length = 7)
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "TO_DATE", length = 7)
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxHeadMst")
	public List<FlatTaxCombo> getFlatTaxCombos() {
		return flatTaxCombos;
	}
	public void setFlatTaxCombos(List<FlatTaxCombo> flatTaxCombos) {
		this.flatTaxCombos = flatTaxCombos;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxHeadMst")
	public List<FlatTaxApplicableCombo> getFlatTaxApplicableCombos() {
		return flatTaxApplicableCombos;
	}
	public void setFlatTaxApplicableCombos(
			List<FlatTaxApplicableCombo> flatTaxApplicableCombos) {
		this.flatTaxApplicableCombos = flatTaxApplicableCombos;
	}
}
