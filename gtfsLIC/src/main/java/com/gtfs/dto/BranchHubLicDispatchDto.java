package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class BranchHubLicDispatchDto implements Serializable{
	private String applicationNo;
	private Date businessDate;
	private String insuredName;
	private String proposerName;
	private String branchName;
	private String productName;
	private Double basePremium;
	private Double serviceTax;
	private Double total;
	private String ddChequeNo;
	private String chqBankName;
	private String chqBranchName;
	private Date ddChequeDate;
	private Double chequeAmount;
	private Double cashAmount;
	private Double totalReceived;
	
	
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getBasePremium() {
		return basePremium;
	}
	public void setBasePremium(Double basePremium) {
		this.basePremium = basePremium;
	}
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getDdChequeNo() {
		return ddChequeNo;
	}
	public void setDdChequeNo(String ddChequeNo) {
		this.ddChequeNo = ddChequeNo;
	}
	public String getChqBankName() {
		return chqBankName;
	}
	public void setChqBankName(String chqBankName) {
		this.chqBankName = chqBankName;
	}
	public String getChqBranchName() {
		return chqBranchName;
	}
	public void setChqBranchName(String chqBranchName) {
		this.chqBranchName = chqBranchName;
	}
	public Date getDdChequeDate() {
		return ddChequeDate;
	}
	public void setDdChequeDate(Date ddChequeDate) {
		this.ddChequeDate = ddChequeDate;
	}
	public Double getChequeAmount() {
		return chequeAmount;
	}
	public void setChequeAmount(Double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}
	public Double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public Double getTotalReceived() {
		return totalReceived;
	}
	public void setTotalReceived(Double totalReceived) {
		this.totalReceived = totalReceived;
	}
}
