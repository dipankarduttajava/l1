package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gtfs.util.DateJsonDeserializer;
import com.gtfs.util.DateJsonSerializer;



public class ReceiptDtlsDto implements Serializable {
	private Long id;
	private Long receiptMst;
	private String rcptMode;
	private String chdNo;
	private Date chddDate;
	private String chddBank;
	private String chddBankBranch;
	private Double chddAmount;
	private String favourOf;
	private String remarks;
	private Long createdBy;
	private Long modifiedBy;
	private Long deletedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String deleteFlag;
	private Long flatPaySchdId;
	private Long flatInvoiceComboId;
	
	
	/* GETTER SETTER */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReceiptMst() {
		return receiptMst;
	}
	public void setReceiptMst(Long receiptMst) {
		this.receiptMst = receiptMst;
	}
	public String getRcptMode() {
		return rcptMode;
	}
	public void setRcptMode(String rcptMode) {
		this.rcptMode = rcptMode;
	}
	public String getChdNo() {
		return chdNo;
	}
	public void setChdNo(String chdNo) {
		this.chdNo = chdNo;
	}
	@JsonSerialize(using=DateJsonSerializer.class)
	public Date getChddDate() {
		return chddDate;
	}
	@JsonDeserialize(using = DateJsonDeserializer.class)
	public void setChddDate(Date chddDate) {
		this.chddDate = chddDate;
	}
	public String getChddBank() {
		return chddBank;
	}
	public void setChddBank(String chddBank) {
		this.chddBank = chddBank;
	}
	public String getChddBankBranch() {
		return chddBankBranch;
	}
	public void setChddBankBranch(String chddBankBranch) {
		this.chddBankBranch = chddBankBranch;
	}
	public Double getChddAmount() {
		return chddAmount;
	}
	public void setChddAmount(Double chddAmount) {
		this.chddAmount = chddAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Long getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Long getFlatPaySchdId() {
		return flatPaySchdId;
	}
	public void setFlatPaySchdId(Long flatPaySchdId) {
		this.flatPaySchdId = flatPaySchdId;
	}
	public Long getFlatInvoiceComboId() {
		return flatInvoiceComboId;
	}
	public void setFlatInvoiceComboId(Long flatInvoiceComboId) {
		this.flatInvoiceComboId = flatInvoiceComboId;
	}
	public String getFavourOf() {
		return favourOf;
	}
	public void setFavourOf(String favourOf) {
		this.favourOf = favourOf;
	}	
}
