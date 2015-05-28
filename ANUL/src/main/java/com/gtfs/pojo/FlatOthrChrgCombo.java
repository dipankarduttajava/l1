package com.gtfs.pojo;

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
@Table(name = "FLAT_OTHR_CHRG_COMBO")
public class FlatOthrChrgCombo implements Serializable {

	private Long id;
	private FlatMst flatMst;
	private OtherChargesMst otherChargesMst;
	private String chargeOnItem;
	private Double chargeValue;
	private Long patWithMilestoneId;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<FlatTaxCombo> flatTaxCombos = new ArrayList<FlatTaxCombo>();
	
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OTHER_CHARGES_MST_ID")
	public OtherChargesMst getOtherChargesMst() {
		return otherChargesMst;
	}
	public void setOtherChargesMst(OtherChargesMst otherChargesMst) {
		this.otherChargesMst = otherChargesMst;
	}
	
	@Column(name="CHARGE_ON_ITEM" , length=20)
	public String getChargeOnItem() {
		return chargeOnItem;
	}
	public void setChargeOnItem(String chargeOnItem) {
		this.chargeOnItem = chargeOnItem;
	}
	
	@Column(name = "CHARGE_VALUE", precision = 22, scale = 0)
	public Double getChargeValue() {
		return chargeValue;
	}
	public void setChargeValue(Double chargeValue) {
		this.chargeValue = chargeValue;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatOthrChrgCombo")
	public List<FlatTaxCombo> getFlatTaxCombos() {
		return flatTaxCombos;
	}
	public void setFlatTaxCombos(List<FlatTaxCombo> flatTaxCombos) {
		this.flatTaxCombos = flatTaxCombos;
	}
	
	@Column(name = "PAY_WITH_MILESTONE_ID", precision = 22, scale = 0)
	public Long getPatWithMilestoneId() {
		return patWithMilestoneId;
	}
	public void setPatWithMilestoneId(Long patWithMilestoneId) {
		this.patWithMilestoneId = patWithMilestoneId;
	}
	
	
}
