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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
@Table(name = "LIC_BOC_DETAIL_ENTRY")
public class LicBocDetailEntry implements Serializable{
	private Long id;
	private String boc;
	private Date bocDate;
	private String bocType;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private String bocCategory;
	private Double bocAmount;
	private Double tieupBalance;
	private Double picBalance;
	private List<LicApplBocMapping> licApplBocMappings = new ArrayList<LicApplBocMapping>();
	private List<LicReqBocMapping> licReqBocMappings = new ArrayList<LicReqBocMapping>();
	
	@Id
	@SequenceGenerator(name="LIC_BOC_DETAIL_ENTRY_SEQ",sequenceName="LIC_BOC_DETAIL_ENTRY_SEQ")
	@GeneratedValue(generator="LIC_BOC_DETAIL_ENTRY_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="BOC", length=20)
	public String getBoc() {
		return boc;
	}
	public void setBoc(String boc) {
		this.boc = boc;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BOC_DATE", length = 7)
	public Date getBocDate() {
		return bocDate;
	}
	public void setBocDate(Date bocDate) {
		this.bocDate = bocDate;
	}
	
	
	@Column(name="BOC_TYPE",length=1)
	public String getBocType() {
		return bocType;
	}
	public void setBocType(String bocType) {
		this.bocType = bocType;
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
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licBocDetailEntry")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicApplBocMapping> getLicApplBocMappings() {
		return licApplBocMappings;
	}
	public void setLicApplBocMappings(List<LicApplBocMapping> licApplBocMappings) {
		this.licApplBocMappings = licApplBocMappings;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "licBocDetailEntry")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<LicReqBocMapping> getLicReqBocMappings() {
		return licReqBocMappings;
	}
	public void setLicReqBocMappings(List<LicReqBocMapping> licReqBocMappings) {
		this.licReqBocMappings = licReqBocMappings;
	}
	@Column(name="BOC_CATEGORY",length=1)
	public String getBocCategory() {
		return bocCategory;
	}
	public void setBocCategory(String bocCategory) {
		this.bocCategory = bocCategory;
	}
	@Column(name = "BOC_AMOUNT", precision = 22, scale = 0)
	public Double getBocAmount() {
		return bocAmount;
	}
	public void setBocAmount(Double bocAmount) {
		this.bocAmount = bocAmount;
	}
	@Column(name = "TIEUP_BALANCE", precision = 22, scale = 0)
	public Double getTieupBalance() {
		return tieupBalance;
	}
	public void setTieupBalance(Double tieupBalance) {
		this.tieupBalance = tieupBalance;
	}
	@Column(name = "PIC_BALANCE", precision = 22, scale = 0)
	public Double getPicBalance() {
		return picBalance;
	}
	public void setPicBalance(Double picBalance) {
		this.picBalance = picBalance;
	}
	
	
	
}
