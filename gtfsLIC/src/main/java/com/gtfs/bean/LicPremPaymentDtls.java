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
@Table(name = "LIC_PREM_PAYMENT_DTLS")
public class LicPremPaymentDtls implements Serializable{
	private Long id;
	private String payMode;
	private String sentPayNo;
	private Date sentPayDate;
	private Double sentPayAmount;
	private String bankName;
	private String branchName;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private LicPremiumListDtls licPremiumListDtls;
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name="LIC_PREM_PAYMENT_DTLS_SEQ",sequenceName="LIC_PREM_PAYMENT_DTLS_SEQ")
	@GeneratedValue(generator="LIC_PREM_PAYMENT_DTLS_SEQ",strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="PAY_MODE",length=1)
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	@Column(name="SENT_PAY_NO",length=50)
	public String getSentPayNo() {
		return sentPayNo;
	}
	public void setSentPayNo(String sentPayNo) {
		this.sentPayNo = sentPayNo;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "SENT_PAY_DATE", length = 7)
	public Date getSentPayDate() {
		return sentPayDate;
	}
	public void setSentPayDate(Date sentPayDate) {
		this.sentPayDate = sentPayDate;
	}
	@Column(name = "SENT_PAY_AMOUNT",precision = 22, scale = 0)
	public Double getSentPayAmount() {
		return sentPayAmount;
	}
	public void setSentPayAmount(Double sentPayAmount) {
		this.sentPayAmount = sentPayAmount;
	}
	@Column(name="BANK_NAME",length=50)
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(name="BRANCH_NAME",length=50)
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_PREM_ID")
	public LicPremiumListDtls getLicPremiumListDtls() {
		return licPremiumListDtls;
	}
	public void setLicPremiumListDtls(LicPremiumListDtls licPremiumListDtls) {
		this.licPremiumListDtls = licPremiumListDtls;
	}
	
	
}
