package com.gtfs.pojo;

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
@Table(name = "RECEIPT_DTLS")
public class ReceiptDtls implements Serializable {

	private Long id;
	private ReceiptMst receiptMst;
	private String rcptMode;
	private String chdNo;
	private Date chddDate;
	private String chddBank;
	private String chddBankBranch;
	private Double chddAmount;
	private String remarks;
	private String favourOf;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	/* GETTER SETTER */
	@Id
	@SequenceGenerator(name="RECEIPT_DTLS_SEQ",sequenceName="RECEIPT_DTLS_SEQ")
	@GeneratedValue(generator="RECEIPT_DTLS_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIPT_MST_ID")
	public ReceiptMst getReceiptMst() {
		return receiptMst;
	}
	public void setReceiptMst(ReceiptMst receiptMst) {
		this.receiptMst = receiptMst;
	}
	
	@Column(name="RCPT_MODE" , length=10)
	public String getRcptMode() {
		return rcptMode;
	}
	public void setRcptMode(String rcptMode) {
		this.rcptMode = rcptMode;
	}
	
	@Column(name="CHDDNO" , length=10)
	public String getChdNo() {
		return chdNo;
	}
	public void setChdNo(String chdNo) {
		this.chdNo = chdNo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CHDD_DATE", length = 7)
	public Date getChddDate() {
		return chddDate;
	}
	public void setChddDate(Date chddDate) {
		this.chddDate = chddDate;
	}
	
	@Column(name="CHDD_BANK" , length=100)
	public String getChddBank() {
		return chddBank;
	}
	public void setChddBank(String chddBank) {
		this.chddBank = chddBank;
	}
	
	@Column(name="CHDD_BANK_BRANCH" , length=100)
	public String getChddBankBranch() {
		return chddBankBranch;
	}
	public void setChddBankBranch(String chddBankBranch) {
		this.chddBankBranch = chddBankBranch;
	}
	
	@Column(name = "CHDD_AMOUNT", precision = 22, scale = 0)
	public Double getChddAmount() {
		return chddAmount;
	}
	public void setChddAmount(Double chddAmount) {
		this.chddAmount = chddAmount;
	}
	
	@Column(name="REMARKS" , length=500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	@Column(name = "FAVOUR_OF", length = 100)
	public String getFavourOf() {
		return favourOf;
	}
	public void setFavourOf(String favourOf) {
		this.favourOf = favourOf;
	}
}
