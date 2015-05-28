package com.gtfs.pojo;

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

@Entity
@Table(name = "FLAT_INVOICE_COMBO")
public class FlatInvoiceCombo implements Serializable {

	private Long id;
	private FlatPaySchd flatPaySchd;
	private String invoiceNo;
	private Date invoiceDate;
	private Double invoiceAmt;
	private String fullAmtPaidFlag;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	
	private List<ReceiptMst> receiptMsts = new ArrayList<ReceiptMst>();
	private List<PaymentFollowupCombo> paymentFollowupCombos = new ArrayList<PaymentFollowupCombo>();
	
	
	/* GETTER SETTER */
	@Id
	@SequenceGenerator(name="FLAT_INVOICE_COMBO_SEQ",sequenceName="FLAT_INVOICE_COMBO_SEQ")
	@GeneratedValue(generator="FLAT_INVOICE_COMBO_SEQ",strategy=GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLAT_PAY_SCHD_ID")
	public FlatPaySchd getFlatPaySchd() {
		return flatPaySchd;
	}
	public void setFlatPaySchd(FlatPaySchd flatPaySchd) {
		this.flatPaySchd = flatPaySchd;
	}
	
	@Column(name="INVOICE_NO" , length=25)
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "INVOICE_DATE", length = 7)
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	@Column(name = "INVOICE_AMT", precision = 22, scale = 0)
	public Double getInvoiceAmt() {
		return invoiceAmt;
	}
	public void setInvoiceAmt(Double invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatInvoiceCombo")
	public List<ReceiptMst> getReceiptMsts() {
		return receiptMsts;
	}
	public void setReceiptMsts(List<ReceiptMst> receiptMsts) {
		this.receiptMsts = receiptMsts;
	}
	
	@Column(name="FULL_AMT_PAID_FLAG",length=1)
	public String getFullAmtPaidFlag() {
		return fullAmtPaidFlag;
	}
	public void setFullAmtPaidFlag(String fullAmtPaidFlag) {
		this.fullAmtPaidFlag = fullAmtPaidFlag;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flatInvoiceCombo")
	public List<PaymentFollowupCombo> getPaymentFollowupCombos() {
		return paymentFollowupCombos;
	}
	public void setPaymentFollowupCombos(
			List<PaymentFollowupCombo> paymentFollowupCombos) {
		this.paymentFollowupCombos = paymentFollowupCombos;
	}
	
	
}
