package com.gtfs.pojo;

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
@Table(name = "FLAT_TAX_COMBO")
public class FlatTaxCombo implements Serializable {

	private Long id;
	private FlatMst flatMst;
	private String flatChargeFlag;
	private TaxHeadMst taxHeadMst;
	private Double flatValue;
	private Double taxOnFlat;
	private FlatOthrChrgCombo flatOthrChrgCombo;
	private Double otherChargeValue;
	private Double taxOnOtherCharge;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	/* GETTER SETTER */
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLAT_MST_ID")
	public FlatMst getFlatMst() {
		return flatMst;
	}
	public void setFlatMst(FlatMst flatMst) {
		this.flatMst = flatMst;
	}
	
	@Column(name="FLAT_CHARGE_FLAG" , length=1)
	public String getFlatChargeFlag() {
		return flatChargeFlag;
	}
	public void setFlatChargeFlag(String flatChargeFlag) {
		this.flatChargeFlag = flatChargeFlag;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TAX_HEAD_MST_ID")
	public TaxHeadMst getTaxHeadMst() {
		return taxHeadMst;
	}
	public void setTaxHeadMst(TaxHeadMst taxHeadMst) {
		this.taxHeadMst = taxHeadMst;
	}
	
	@Column(name = "FLAT_VALUE", precision = 22, scale = 0)
	public Double getFlatValue() {
		return flatValue;
	}
	public void setFlatValue(Double flatValue) {
		this.flatValue = flatValue;
	}
	
	@Column(name = "TAX_ON_FLAT", precision = 22, scale = 0)
	public Double getTaxOnFlat() {
		return taxOnFlat;
	}
	public void setTaxOnFlat(Double taxOnFlat) {
		this.taxOnFlat = taxOnFlat;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLAT_OTHER_CHRG_COMBO_ID")
	public FlatOthrChrgCombo getFlatOthrChrgCombo() {
		return flatOthrChrgCombo;
	}
	public void setFlatOthrChrgCombo(FlatOthrChrgCombo flatOthrChrgCombo) {
		this.flatOthrChrgCombo = flatOthrChrgCombo;
	}
	
	@Column(name = "OTHER_CHRARGE_VALUE", precision = 22, scale = 0)
	public Double getOtherChargeValue() {
		return otherChargeValue;
	}
	public void setOtherChargeValue(Double otherChargeValue) {
		this.otherChargeValue = otherChargeValue;
	}
	
	@Column(name = "TAX_ON_OTHER_CHARGE", precision = 22, scale = 0)
	public Double getTaxOnOtherCharge() {
		return taxOnOtherCharge;
	}
	public void setTaxOnOtherCharge(Double taxOnOtherCharge) {
		this.taxOnOtherCharge = taxOnOtherCharge;
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
