package com.gtfs.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name = "LIC_PAYMENT_DTLS")
public class LicPaymentDtls implements Serializable{
	private Long id;
	private String payMode; 
	private Double amount;
	private String prntTieupFlag;
	private TieupCompyMst tieupCompyMst;
	private String payeeName;
	private String draftChqNo;
	private String draftChqBank;
	private String draftCgqBranch;
	private Date draftChqDate;
	private Long creditNoteNo;
	private Date creditNoteDate;
	private String shortPremFlag;
	private LicRequirementDtls licRequirementDtls;
	private LicPaymentMst licPaymentMst;
	
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	
	@Id
	@SequenceGenerator(name="LIC_PAYMENT_DTLS_SEQ",sequenceName="LIC_PAYMENT_DTLS_SEQ")
	@GeneratedValue(generator="LIC_PAYMENT_DTLS_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "PAY_MODE", length = 1)
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	
	
	@Column(name = "AMOUNT", precision = 22, scale = 0)
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	@Column(name = "PRNT_TIEUP_FLAG", length = 1)
	public String getPrntTieupFlag() {
		return prntTieupFlag;
	}
	public void setPrntTieupFlag(String prntTieupFlag) {
		this.prntTieupFlag = prntTieupFlag;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIEUP_COMPY_MST_ID")
	public TieupCompyMst getTieupCompyMst() {
		return tieupCompyMst;
	}
	public void setTieupCompyMst(TieupCompyMst tieupCompyMst) {
		this.tieupCompyMst = tieupCompyMst;
	}
	
	
	@Column(name = "PAYEE_NAME", length = 100)
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	
	
	@Column(name = "DRAFT_CHQ_NO", length = 100)
	public String getDraftChqNo() {
		return draftChqNo;
	}
	public void setDraftChqNo(String draftChqNo) {
		this.draftChqNo = draftChqNo;
	}
	
	
	@Column(name = "DRAFT_CHQ_BANK", length = 100)
	public String getDraftChqBank() {
		return draftChqBank;
	}
	public void setDraftChqBank(String draftChqBank) {
		this.draftChqBank = draftChqBank;
	}
	
	
	@Column(name = "DRAFT_CHQ_BRANCH", length = 100)
	public String getDraftCgqBranch() {
		return draftCgqBranch;
	}
	public void setDraftCgqBranch(String draftCgqBranch) {
		this.draftCgqBranch = draftCgqBranch;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DRAFT_CHQ_DATE", length = 7)
	public Date getDraftChqDate() {
		return draftChqDate;
	}
	public void setDraftChqDate(Date draftChqDate) {
		this.draftChqDate = draftChqDate;
	}
	
	
	@Column(name = "CREDIT_NOTE_NO", precision = 22, scale = 0)
	public Long getCreditNoteNo() {
		return creditNoteNo;
	}
	public void setCreditNoteNo(Long creditNoteNo) {
		this.creditNoteNo = creditNoteNo;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREDIT_NOTE_DATE", length = 7)
	public Date getCreditNoteDate() {
		return creditNoteDate;
	}
	public void setCreditNoteDate(Date creditNoteDate) {
		this.creditNoteDate = creditNoteDate;
	}
	
	
	@Column(name="SHORT_PREM_FLAG",length=1)
	public String getShortPremFlag() {
		return shortPremFlag;
	}
	public void setShortPremFlag(String shortPremFlag) {
		this.shortPremFlag = shortPremFlag;
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
	@JoinColumn(name = "LIC_PAYMENT_MST_ID")
	public LicPaymentMst getLicPaymentMst() {
		return licPaymentMst;
	}
	public void setLicPaymentMst(LicPaymentMst licPaymentMst) {
		this.licPaymentMst = licPaymentMst;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_REQ_DTLS_ID")
	public LicRequirementDtls getLicRequirementDtls() {
		return licRequirementDtls;
	}
	public void setLicRequirementDtls(LicRequirementDtls licRequirementDtls) {
		this.licRequirementDtls = licRequirementDtls;
	}
}
