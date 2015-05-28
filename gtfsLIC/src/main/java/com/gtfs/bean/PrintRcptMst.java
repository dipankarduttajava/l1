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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PRINT_RCPT_MST",uniqueConstraints = { @UniqueConstraint( columnNames = { "PREFIX", "TIE_COMPY_MST_ID","RECEIPT_FROM","RECEIPT_TO" } ) })
public class PrintRcptMst implements Serializable{
	private Long id;
	private Long receiptFrom;
	private Long receiptTo;
	private String prefix;
	private BranchMst branchMst;
	private TieupCompyMst tieupCompyMst;
	private ParentCompyMst parentCompyMst;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private String remarks;
	private List<LicPrintRcptDtls> licPrintRcptDtlses = new ArrayList<LicPrintRcptDtls>();
	
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name="PRINT_RCPT_MST_SEQ",sequenceName="PRINT_RCPT_MST_SEQ")
	@GeneratedValue(generator="PRINT_RCPT_MST_SEQ",strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "RECEIPT_FROM", precision = 22, scale = 0)
	public Long getReceiptFrom() {
		return receiptFrom;
	}
	public void setReceiptFrom(Long receiptFrom) {
		this.receiptFrom = receiptFrom;
	}
	@Column(name = "RECEIPT_TO", precision = 22, scale = 0)
	public Long getReceiptTo() {
		return receiptTo;
	}
	public void setReceiptTo(Long receiptTo) {
		this.receiptTo = receiptTo;
	}
	@Column(name = "PREFIX", length=20)
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH_MST_ID")
	public BranchMst getBranchMst() {
		return branchMst;
	}
	public void setBranchMst(BranchMst branchMst) {
		this.branchMst = branchMst;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIE_COMPY_MST_ID")
	public TieupCompyMst getTieupCompyMst() {
		return tieupCompyMst;
	}
	public void setTieupCompyMst(TieupCompyMst tieupCompyMst) {
		this.tieupCompyMst = tieupCompyMst;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRNT_COMPY_MST_ID")
	public ParentCompyMst getParentCompyMst() {
		return parentCompyMst;
	}
	public void setParentCompyMst(ParentCompyMst parentCompyMst) {
		this.parentCompyMst = parentCompyMst;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "printRcptMst")
	public List<LicPrintRcptDtls> getLicPrintRcptDtlses() {
		return licPrintRcptDtlses;
	}
	public void setLicPrintRcptDtlses(List<LicPrintRcptDtls> licPrintRcptDtlses) {
		this.licPrintRcptDtlses = licPrintRcptDtlses;
	}
	@Column(name="REMARKS",length=500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
