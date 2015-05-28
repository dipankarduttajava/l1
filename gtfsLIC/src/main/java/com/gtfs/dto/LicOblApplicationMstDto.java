package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

import com.gtfs.bean.LicOblApplicationMst;

public class LicOblApplicationMstDto implements Serializable {
	
	private Long id;
	private String oblApplNo;
	private Date businessDate;
	private String insuredName;
	private String proposerName;
	private String prodDesc;
	private Double totalReceived;
	private String printFlag;
	private Double cashAmount;
	private String payNature;
	private Double insAmount;
	private Double tieAmount;
	private String branchName;
	private Long policyTerm;
	private Long agCode;
	private String chequeNo;
	private Double basicPrem;
	private Double taxOnPrem;
	private String payMode; //cash or cheque
	private Double totalAmt;
	private String dispatchListNo;
	private String podListNo;
	private String hubName;
	private String receiptNo;
	private String fiscalYear;
	private String agName;
	private Date proposerDob;
	private Date insuredDob;
	private Double sumAssured;
	
	
	private LicOblApplicationMst licOblApplicationMst;

	
	/* GETTER SETTER */
	public LicOblApplicationMst getLicOblApplicationMst() {
		return licOblApplicationMst;
	}
	public void setLicOblApplicationMst(LicOblApplicationMst licOblApplicationMst) {
		this.licOblApplicationMst = licOblApplicationMst;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getOblApplNo() {
		return oblApplNo;
	}
	public void setOblApplNo(String oblApplNo) {
		this.oblApplNo = oblApplNo;
	}

	public Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
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

	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public Double getTotalReceived() {
		return totalReceived;
	}
	public void setTotalReceived(Double totalReceived) {
		this.totalReceived = totalReceived;
	}

	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public Double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Double getInsAmount() {
		return insAmount;
	}
	public void setInsAmount(Double insAmount) {
		this.insAmount = insAmount;
	}

	public Double getTieAmount() {
		return tieAmount;
	}
	public void setTieAmount(Double tieAmount) {
		this.tieAmount = tieAmount;
	}

	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPayNature() {
		return payNature;
	}
	public void setPayNature(String payNature) {
		this.payNature = payNature;
	}

	public Long getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(Long policyTerm) {
		this.policyTerm = policyTerm;
	}

	public Long getAgCode() {
		return agCode;
	}
	public String getHubName() {
		return hubName;
	}

	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

	public void setAgCode(Long agCode) {
		this.agCode = agCode;
	}

	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Double getBasicPrem() {
		return basicPrem;
	}
	public void setBasicPrem(Double basicPrem) {
		this.basicPrem = basicPrem;
	}

	public Double getTaxOnPrem() {
		return taxOnPrem;
	}
	public void setTaxOnPrem(Double taxOnPrem) {
		this.taxOnPrem = taxOnPrem;
	}

	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getDispatchListNo() {
		return dispatchListNo;
	}
	public void setDispatchListNo(String dispatchListNo) {
		this.dispatchListNo = dispatchListNo;
	}
	
	public String getPodListNo() {
		return podListNo;
	}
	public void setPodListNo(String podListNo) {
		this.podListNo = podListNo;
	}
	
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getAgName() {
		return agName;
	}
	public void setAgName(String agName) {
		this.agName = agName;
	}
	public Date getProposerDob() {
		return proposerDob;
	}
	public void setProposerDob(Date proposerDob) {
		this.proposerDob = proposerDob;
	}
	public Date getInsuredDob() {
		return insuredDob;
	}
	public void setInsuredDob(Date insuredDob) {
		this.insuredDob = insuredDob;
	}
	public Double getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}
	
	
}
