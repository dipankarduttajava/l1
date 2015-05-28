package com.gtfs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "LIC_CMS_DENOMINATION_DTLS")
public class LicCmsDenominationDtls implements Serializable {
	private Long id;
	private Long inr1000;
	private Long inr500;
	private Long inr100;
	private Long inr50;
	private Long inr20;
	private Long inr10;
	private Long inr5;
	private Long inr2;
	private Long inr1;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private LicCmsMst licCmsMst;
	
	@Id
	@GenericGenerator(name = "cmsGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "licCmsMst"))
	@GeneratedValue(generator = "cmsGenerator")
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "INR_1000", precision = 22, scale = 0)
	public Long getInr1000() {
		return inr1000;
	}
	public void setInr1000(Long inr1000) {
		this.inr1000 = inr1000;
	}
	
	
	@Column(name = "INR_500", precision = 22, scale = 0)
	public Long getInr500() {
		return inr500;
	}
	public void setInr500(Long inr500) {
		this.inr500 = inr500;
	}
	
	
	@Column(name = "INR_100", precision = 22, scale = 0)
	public Long getInr100() {
		return inr100;
	}
	public void setInr100(Long inr100) {
		this.inr100 = inr100;
	}
	
	
	@Column(name = "INR_50", precision = 22, scale = 0)
	public Long getInr50() {
		return inr50;
	}
	public void setInr50(Long inr50) {
		this.inr50 = inr50;
	}
	
	
	@Column(name = "INR_20", precision = 22, scale = 0)
	public Long getInr20() {
		return inr20;
	}
	public void setInr20(Long inr20) {
		this.inr20 = inr20;
	}
	
	
	@Column(name = "INR_10", precision = 22, scale = 0)
	public Long getInr10() {
		return inr10;
	}
	public void setInr10(Long inr10) {
		this.inr10 = inr10;
	}
	
	
	@Column(name = "INR_5", precision = 22, scale = 0)
	public Long getInr5() {
		return inr5;
	}
	public void setInr5(Long inr5) {
		this.inr5 = inr5;
	}
	
	
	@Column(name = "INR_1", precision = 22, scale = 0)
	public Long getInr1() {
		return inr1;
	}
	public void setInr1(Long inr1) {
		this.inr1 = inr1;
	}
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@Cascade({CascadeType.SAVE_UPDATE})
	public LicCmsMst getLicCmsMst() {
		return licCmsMst;
	}
	public void setLicCmsMst(LicCmsMst licCmsMst) {
		this.licCmsMst = licCmsMst;
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
	@Column(name = "INR_2", precision = 22, scale = 0)
	public Long getInr2() {
		return inr2;
	}
	public void setInr2(Long inr2) {
		this.inr2 = inr2;
	}
	
}
