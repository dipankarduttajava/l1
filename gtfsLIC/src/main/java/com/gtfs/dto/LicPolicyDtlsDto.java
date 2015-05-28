package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicMarkingToQcDtls;
import com.gtfs.bean.LicPisMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicRnlBusinessTxn;
import com.gtfs.bean.PicBranchMst;

public class LicPolicyDtlsDto implements Serializable{

	private String policyNo;
	private Date payDate;
	private String insuredName;
	private String proposerName;
	private String product;
	private String healthFlag;
	private String payMode;
	private Long dueYears;
	private Date fromDueDate;
	private Date toDueDate;
	private String payNature;
	private String branchName;
	private String sendingHub;
	private String healthValidated;
	private String paymentPayMode;
	private Double cashAmt;
	private Double chqDDAmt;
	private Double totalAmt;
	private Long licRnlPayId;
	private String hubName;
	private String printFlag;
	private String paidFlag;
	private Double fineAmount;
	private Double totalReceivable;
	private Double totalReceived;
	private String tieupCompyName;
	private String prodName;
	
	
	/* GETTER SETTER */
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
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getProposerName() {
		return proposerName;
	}
	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getHealthFlag() {
		return healthFlag;
	}
	public void setHealthFlag(String healthFlag) {
		this.healthFlag = healthFlag;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public Long getDueYears() {
		return dueYears;
	}
	public void setDueYears(Long dueYears) {
		this.dueYears = dueYears;
	}
	public Date getFromDueDate() {
		return fromDueDate;
	}
	public void setFromDueDate(Date fromDueDate) {
		this.fromDueDate = fromDueDate;
	}
	public Date getToDueDate() {
		return toDueDate;
	}
	public void setToDueDate(Date toDueDate) {
		this.toDueDate = toDueDate;
	}
	public String getPayNature() {
		return payNature;
	}
	public void setPayNature(String payNature) {
		this.payNature = payNature;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getSendingHub() {
		return sendingHub;
	}
	public void setSendingHub(String sendingHub) {
		this.sendingHub = sendingHub;
	}
	public String getHealthValidated() {
		return healthValidated;
	}
	public void setHealthValidated(String healthValidated) {
		this.healthValidated = healthValidated;
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
	public String getPaymentPayMode() {
		return paymentPayMode;
	}
	public void setPaymentPayMode(String paymentPayMode) {
		this.paymentPayMode = paymentPayMode;
	}
	public Double getChqDDAmt() {
		return chqDDAmt;
	}
	public void setChqDDAmt(Double chqDDAmt) {
		this.chqDDAmt = chqDDAmt;
	}
	public Long getLicRnlPayId() {
		return licRnlPayId;
	}
	public void setLicRnlPayId(Long licRnlPayId) {
		this.licRnlPayId = licRnlPayId;
	}
	public String getHubName() {
		return hubName;
	}
	public void setHubName(String hubName) {
		this.hubName = hubName;
	}
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	public String getPaidFlag() {
		return paidFlag;
	}
	public void setPaidFlag(String paidFlag) {
		this.paidFlag = paidFlag;
	}
	public Double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(Double fineAmount) {
		this.fineAmount = fineAmount;
	}
	public Double getTotalReceivable() {
		return totalReceivable;
	}
	public void setTotalReceivable(Double totalReceivable) {
		this.totalReceivable = totalReceivable;
	}
	public Double getTotalReceived() {
		return totalReceived;
	}
	public void setTotalReceived(Double totalReceived) {
		this.totalReceived = totalReceived;
	}
	public String getTieupCompyName() {
		return tieupCompyName;
	}
	public void setTieupCompyName(String tieupCompyName) {
		this.tieupCompyName = tieupCompyName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
}