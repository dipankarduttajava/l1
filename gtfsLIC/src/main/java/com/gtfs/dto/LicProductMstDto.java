package com.gtfs.dto;

import java.io.Serializable;


public class LicProductMstDto implements Serializable{
	private String productName;
	private Long parentCompanyId;
	private Long parentCompanyName;
	private Double colLBenPct;
	private String productCategory;
	private Long tieupCompyId;
	private String tieupCompyName;
	private Long schemeId;
	private String schemeName;
	private Long normalRenewalPeriod;
	private Long extendedRenewalPeriod;
	private Double serviceTax;
	private Long durationForFirstHealth;
	private Double rnlCollBenPct;
	private Boolean healthDecRequiredFlag;
	private Long roundMonths;
	private Long roundDays;
	private Long healthDecValidityDays;
	
	
	public Long getHealthDecValidityDays() {
		return healthDecValidityDays;
	}
	public void setHealthDecValidityDays(Long healthDecValidityDays) {
		this.healthDecValidityDays = healthDecValidityDays;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getParentCompanyId() {
		return parentCompanyId;
	}
	public void setParentCompanyId(Long parentCompanyId) {
		this.parentCompanyId = parentCompanyId;
	}
	public Long getParentCompanyName() {
		return parentCompanyName;
	}
	public void setParentCompanyName(Long parentCompanyName) {
		this.parentCompanyName = parentCompanyName;
	}
	public Double getColLBenPct() {
		return colLBenPct;
	}
	public void setColLBenPct(Double colLBenPct) {
		this.colLBenPct = colLBenPct;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public Long getTieupCompyId() {
		return tieupCompyId;
	}
	public void setTieupCompyId(Long tieupCompyId) {
		this.tieupCompyId = tieupCompyId;
	}
	public String getTieupCompyName() {
		return tieupCompyName;
	}
	public void setTieupCompyName(String tieupCompyName) {
		this.tieupCompyName = tieupCompyName;
	}
	public Long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public Long getNormalRenewalPeriod() {
		return normalRenewalPeriod;
	}
	public void setNormalRenewalPeriod(Long normalRenewalPeriod) {
		this.normalRenewalPeriod = normalRenewalPeriod;
	}
	public Long getExtendedRenewalPeriod() {
		return extendedRenewalPeriod;
	}
	public void setExtendedRenewalPeriod(Long extendedRenewalPeriod) {
		this.extendedRenewalPeriod = extendedRenewalPeriod;
	}
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	public Long getDurationForFirstHealth() {
		return durationForFirstHealth;
	}
	public void setDurationForFirstHealth(Long durationForFirstHealth) {
		this.durationForFirstHealth = durationForFirstHealth;
	}
	public Double getRnlCollBenPct() {
		return rnlCollBenPct;
	}
	public void setRnlCollBenPct(Double rnlCollBenPct) {
		this.rnlCollBenPct = rnlCollBenPct;
	}
	public Boolean getHealthDecRequiredFlag() {
		return healthDecRequiredFlag;
	}
	public void setHealthDecRequiredFlag(Boolean healthDecRequiredFlag) {
		this.healthDecRequiredFlag = healthDecRequiredFlag;
	}
	public Long getRoundMonths() {
		return roundMonths;
	}
	public void setRoundMonths(Long roundMonths) {
		this.roundMonths = roundMonths;
	}
	public Long getRoundDays() {
		return roundDays;
	}
	public void setRoundDays(Long roundDays) {
		this.roundDays = roundDays;
	}
	
	
	
	
}
