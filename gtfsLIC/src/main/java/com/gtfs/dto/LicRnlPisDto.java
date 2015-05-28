package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class LicRnlPisDto implements Serializable{
	private Long policyDtlsId;
	private Long requirementId;
	private String policyNo;
	private Date payDate;
	private String draftChqNo;
	private Double draftChqInsAmt;
	private Double draftChqTieAmt;
	private Double cashAmt;
	private Double totalAmt;
	private Long paymentMstId;
	public Long getPolicyDtlsId() {
		return policyDtlsId;
	}
	public void setPolicyDtlsId(Long policyDtlsId) {
		this.policyDtlsId = policyDtlsId;
	}
	public Long getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(Long requirementId) {
		this.requirementId = requirementId;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getDraftChqNo() {
		return draftChqNo;
	}
	public void setDraftChqNo(String draftChqNo) {
		this.draftChqNo = draftChqNo;
	}
	public Double getDraftChqInsAmt() {
		return draftChqInsAmt;
	}
	public void setDraftChqInsAmt(Double draftChqInsAmt) {
		this.draftChqInsAmt = draftChqInsAmt;
	}
	public Double getDraftChqTieAmt() {
		return draftChqTieAmt;
	}
	public void setDraftChqTieAmt(Double draftChqTieAmt) {
		this.draftChqTieAmt = draftChqTieAmt;
	}
	public Double getCashAmt() {
		return cashAmt;
	}
	public void setCashAmt(Double cashAmt) {
		this.cashAmt = cashAmt;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Long getPaymentMstId() {
		return paymentMstId;
	}
	public void setPaymentMstId(Long paymentMstId) {
		this.paymentMstId = paymentMstId;
	}
	
	
	
}
