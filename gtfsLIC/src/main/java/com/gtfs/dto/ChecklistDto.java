package com.gtfs.dto;

import java.io.Serializable;
import java.util.Date;

public class ChecklistDto implements Serializable{
	private Long agCode;
	private String oblApplNo;
	private Long productId;
	private Long phaseId;
	private Long policyTerm;
	private Double sumAssured;
	private String payMode;
	private String proposerName;
	private Date proposerDob;
	private Boolean insuredPropSameFlag;
	private String insuredName;
	private Date insuredDob;
	private Integer year;
	private Integer month;
	private Integer day;
	private Boolean dobAndAgeApplicable;
	private Boolean panApplicable;
	private Boolean bankAcNoApplicable;
	private Boolean photoApplicable;
	private Boolean proposalFormSigned;
	private Boolean biSigned;
	private Boolean proposalFormProperlyFillup;
	private Boolean ageProof;
	private Boolean loadingNeeded;
	private Boolean identityProof;
	private Boolean addressProof;
	private Boolean incomeProof;
	private Boolean arRiderFlag;
	private Boolean wvrRiderFlag;
	private Boolean termRiderFlag;
	private Double arRiderAmt;
	private Double wvrRiderAmt;
	private Double termRiderAmt;
	private Double termRiderNsapAmt;
	private Long nsapDocId;
	private Long premiumPayingTerm;
	private String businessDateOf;
	private Integer propYear;
	private Integer propMonth;
	private Integer propDay;
	private String arCategory;
	
	
	/* GETTER SETTER */
	public Boolean getInsuredPropSameFlag() {
		return insuredPropSameFlag;
	}
	public void setInsuredPropSameFlag(Boolean insuredPropSameFlag) {
		this.insuredPropSameFlag = insuredPropSameFlag;
	}
	public Long getAgCode() {
		return agCode;
	}
	public void setAgCode(Long agCode) {
		this.agCode = agCode;
	}
	public String getOblApplNo() {
		return oblApplNo;
	}
	public void setOblApplNo(String oblApplNo) {
		this.oblApplNo = oblApplNo;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Boolean getDobAndAgeApplicable() {
		return dobAndAgeApplicable;
	}
	public void setDobAndAgeApplicable(Boolean dobAndAgeApplicable) {
		this.dobAndAgeApplicable = dobAndAgeApplicable;
	}
	public Boolean getPanApplicable() {
		return panApplicable;
	}
	public void setPanApplicable(Boolean panApplicable) {
		this.panApplicable = panApplicable;
	}
	public Boolean getBankAcNoApplicable() {
		return bankAcNoApplicable;
	}
	public void setBankAcNoApplicable(Boolean bankAcNoApplicable) {
		this.bankAcNoApplicable = bankAcNoApplicable;
	}
	public Boolean getPhotoApplicable() {
		return photoApplicable;
	}
	public void setPhotoApplicable(Boolean photoApplicable) {
		this.photoApplicable = photoApplicable;
	}
	public Boolean getProposalFormSigned() {
		return proposalFormSigned;
	}
	public void setProposalFormSigned(Boolean proposalFormSigned) {
		this.proposalFormSigned = proposalFormSigned;
	}
	public Boolean getBiSigned() {
		return biSigned;
	}
	public void setBiSigned(Boolean biSigned) {
		this.biSigned = biSigned;
	}
	public Boolean getProposalFormProperlyFillup() {
		return proposalFormProperlyFillup;
	}
	public void setProposalFormProperlyFillup(Boolean proposalFormProperlyFillup) {
		this.proposalFormProperlyFillup = proposalFormProperlyFillup;
	}
	public Boolean getAgeProof() {
		return ageProof;
	}
	public void setAgeProof(Boolean ageProof) {
		this.ageProof = ageProof;
	}
	public Boolean getLoadingNeeded() {
		return loadingNeeded;
	}
	public void setLoadingNeeded(Boolean loadingNeeded) {
		this.loadingNeeded = loadingNeeded;
	}
	public Boolean getIdentityProof() {
		return identityProof;
	}
	public void setIdentityProof(Boolean identityProof) {
		this.identityProof = identityProof;
	}
	public Boolean getAddressProof() {
		return addressProof;
	}
	public void setAddressProof(Boolean addressProof) {
		this.addressProof = addressProof;
	}
	public Boolean getIncomeProof() {
		return incomeProof;
	}
	public void setIncomeProof(Boolean incomeProof) {
		this.incomeProof = incomeProof;
	}
	public Boolean getArRiderFlag() {
		return arRiderFlag;
	}
	public void setArRiderFlag(Boolean arRiderFlag) {
		this.arRiderFlag = arRiderFlag;
	}
	public Double getArRiderAmt() {
		return arRiderAmt;
	}
	public void setArRiderAmt(Double arRiderAmt) {
		this.arRiderAmt = arRiderAmt;
	}
	public Long getNsapDocId() {
		return nsapDocId;
	}
	public void setNsapDocId(Long nsapDocId) {
		this.nsapDocId = nsapDocId;
	}
	public Long getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(Long policyTerm) {
		this.policyTerm = policyTerm;
	}
	public Long getPremiumPayingTerm() {
		return premiumPayingTerm;
	}
	public void setPremiumPayingTerm(Long premiumPayingTerm) {
		this.premiumPayingTerm = premiumPayingTerm;
	}
	public String getBusinessDateOf() {
		return businessDateOf;
	}
	public void setBusinessDateOf(String businessDateOf) {
		this.businessDateOf = businessDateOf;
	}
	public Boolean getWvrRiderFlag() {
		return wvrRiderFlag;
	}
	public void setWvrRiderFlag(Boolean wvrRiderFlag) {
		this.wvrRiderFlag = wvrRiderFlag;
	}
	public Integer getPropYear() {
		return propYear;
	}
	public void setPropYear(Integer propYear) {
		this.propYear = propYear;
	}
	public Integer getPropMonth() {
		return propMonth;
	}
	public void setPropMonth(Integer propMonth) {
		this.propMonth = propMonth;
	}
	public Integer getPropDay() {
		return propDay;
	}
	public void setPropDay(Integer propDay) {
		this.propDay = propDay;
	}
	public Double getWvrRiderAmt() {
		return wvrRiderAmt;
	}
	public void setWvrRiderAmt(Double wvrRiderAmt) {
		this.wvrRiderAmt = wvrRiderAmt;
	}
	public String getArCategory() {
		return arCategory;
	}
	public void setArCategory(String arCategory) {
		this.arCategory = arCategory;
	}
	public Boolean getTermRiderFlag() {
		return termRiderFlag;
	}
	public void setTermRiderFlag(Boolean termRiderFlag) {
		this.termRiderFlag = termRiderFlag;
	}
	public Double getTermRiderAmt() {
		return termRiderAmt;
	}
	public void setTermRiderAmt(Double termRiderAmt) {
		this.termRiderAmt = termRiderAmt;
	}
	public Double getTermRiderNsapAmt() {
		return termRiderNsapAmt;
	}
	public void setTermRiderNsapAmt(Double termRiderNsapAmt) {
		this.termRiderNsapAmt = termRiderNsapAmt;
	}
	
}
