package com.gtfs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "LIC_PROD_FINE_MST")
public class LicProdFineMst implements Serializable{
	private Long id;
	private LicProductMst licProductMst;
	private Integer fineMnth;
	private Double yFinePct;
	private Double hFinePct;
	private Double qFinePct;
	private Double mFinePct;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	@Id
	@SequenceGenerator(name="LIC_PROD_FINE_MST_SEQ",sequenceName="LIC_PROD_FINE_MST_SEQ")
	@GeneratedValue(generator="LIC_PROD_FINE_MST_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROD_ID")
	public LicProductMst getLicProductMst() {
		return licProductMst;
	}
	public void setLicProductMst(LicProductMst licProductMst) {
		this.licProductMst = licProductMst;
	}
	
	
	@Column(name = "FINE_MNTH", precision = 22, scale = 0)
	public Integer getFineMnth() {
		return fineMnth;
	}
	public void setFineMnth(Integer fineMnth) {
		this.fineMnth = fineMnth;
	}
	
	
	@Column(name = "Y_FINE_PCT", precision = 22, scale = 0)
	public Double getyFinePct() {
		return yFinePct;
	}
	public void setyFinePct(Double yFinePct) {
		this.yFinePct = yFinePct;
	}
	
	
	@Column(name = "H_FINE_PCT", precision = 22, scale = 0)
	public Double gethFinePct() {
		return hFinePct;
	}
	public void sethFinePct(Double hFinePct) {
		this.hFinePct = hFinePct;
	}
	
	
	@Column(name = "Q_FINE_PCT", precision = 22, scale = 0)
	public Double getqFinePct() {
		return qFinePct;
	}
	public void setqFinePct(Double qFinePct) {
		this.qFinePct = qFinePct;
	}
	
	
	@Column(name = "M_FINE_PCT", precision = 22, scale = 0)
	public Double getmFinePct() {
		return mFinePct;
	}
	public void setmFinePct(Double mFinePct) {
		this.mFinePct = mFinePct;
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
