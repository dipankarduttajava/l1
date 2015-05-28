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
@Table(name = "RECEIPT_MST")
public class ReceiptMst implements Serializable {

	private Long id;
	private FlatPaySchd flatPaySchd;
	private String paymantRcvd;
	private String rcptMode;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private FlatInvoiceCombo flatInvoiceCombo;
	
	private List<ReceiptDtls> ReceiptDtlss = new ArrayList<ReceiptDtls>();
	
	
	/* GETTER SETTER */
	@Id
	@SequenceGenerator(name="RECEIPT_MST_SEQ",sequenceName="RECEIPT_MST_SEQ")
	@GeneratedValue(generator="RECEIPT_MST_SEQ",strategy=GenerationType.AUTO)
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
	
	@Column(name="PAYMENT_RCVD" , length=1)
	public String getPaymantRcvd() {
		return paymantRcvd;
	}
	public void setPaymantRcvd(String paymantRcvd) {
		this.paymantRcvd = paymantRcvd;
	}
	
	@Column(name="RCPT_MODE" , length=10)
	public String getRcptMode() {
		return rcptMode;
	}
	public void setRcptMode(String rcptMode) {
		this.rcptMode = rcptMode;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receiptMst")
	public List<ReceiptDtls> getReceiptDtlss() {
		return ReceiptDtlss;
	}
	public void setReceiptDtlss(List<ReceiptDtls> receiptDtlss) {
		ReceiptDtlss = receiptDtlss;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLAT_INVOICE_COMBO_ID")
	public FlatInvoiceCombo getFlatInvoiceCombo() {
		return flatInvoiceCombo;
	}
	public void setFlatInvoiceCombo(FlatInvoiceCombo flatInvoiceCombo) {
		this.flatInvoiceCombo = flatInvoiceCombo;
	}
	
	
}
