package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

import com.gtfs.bean.LicOblChecklist;
import com.gtfs.bean.PhaseMst;

public class LicPreliminaryDataEntryDto implements Serializable{
	private String insuredName;
	private Date insuredDob;
	private String proposerName;
	private Date proposerDob;
	private String applicationNo;
	private Long productId;
	private String productName;
	private Long policyTerm;
	private Double premiumAmount;
	private Double serviceTax;
	private Double loading;
	private Double sumAssured;
	private String payMode;
	private Long agCode;
	private String phaseName;
	private Long phaseId;
	private PhaseMst phaseMst;
	private Date businessDate;
	private String companyName;
	private String branchName;
	private String operatorName;
	private Double ddMakingCharges;
	private Double otherCharges;
	private String remarks;
	private LicOblChecklist licOblChecklist;
	private Double totalReceivable;
	private Double totalDdChqAmt;
	private Double balanceInCash;
	private Double amtToReturn;
	private Double basicPrem;
	private Double tabPrem;
	private Double totalAmount;
	
	
	public Double getTotalReceivable() {
		return totalReceivable;
	}
	public void setTotalReceivable(Double totalReceivable) {
		this.totalReceivable = totalReceivable;
	}
	public Double getTotalDdChqAmt() {
		return totalDdChqAmt;
	}
	public void setTotalDdChqAmt(Double totalDdChqAmt) {
		this.totalDdChqAmt = totalDdChqAmt;
	}
	public Double getBalanceInCash() {
		return balanceInCash;
	}
	public void setBalanceInCash(Double balanceInCash) {
		this.balanceInCash = balanceInCash;
	}
	public Double getAmtToReturn() {
		return amtToReturn;
	}
	public void setAmtToReturn(Double amtToReturn) {
		this.amtToReturn = amtToReturn;
	}
	public LicOblChecklist getLicOblChecklist() {
		return licOblChecklist;
	}
	public void setLicOblChecklist(LicOblChecklist licOblChecklist) {
		this.licOblChecklist = licOblChecklist;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getDdMakingCharges() {
		return ddMakingCharges;
	}
	public void setDdMakingCharges(Double ddMakingCharges) {
		this.ddMakingCharges = ddMakingCharges;
	}
	public Double getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(Double otherCharges) {
		this.otherCharges = otherCharges;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getAgCode() {
		return agCode;
	}
	public void setAgCode(Long agCode) {
		this.agCode = agCode;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public Long getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}
	public Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(Double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	public Double getLoading() {
		return loading;
	}
	public void setLoading(Double loading) {
		this.loading = loading;
	}
	public Double getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public Date getInsuredDob() {
		return insuredDob;
	}
	public void setInsuredDob(Date insuredDob) {
		this.insuredDob = insuredDob;
	}
	public String getProposerName() {
		return proposerName;
	}
	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}
	public Date getProposerDob() {
		return proposerDob;
	}
	public void setProposerDob(Date proposerDob) {
		this.proposerDob = proposerDob;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public PhaseMst getPhaseMst() {
		return phaseMst;
	}
	public void setPhaseMst(PhaseMst phaseMst) {
		this.phaseMst = phaseMst;
	}
	public Double getBasicPrem() {
		return basicPrem;
	}
	public void setBasicPrem(Double basicPrem) {
		this.basicPrem = basicPrem;
	}
	public Double getTabPrem() {
		return tabPrem;
	}
	public void setTabPrem(Double tabPrem) {
		this.tabPrem = tabPrem;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(Long policyTerm) {
		this.policyTerm = policyTerm;
	}
	
	
}
