package com.gtfs.dto;

import java.io.Serializable;

public class PremiumDto implements Serializable{
	private Double finalBasicPrem;
	private Double tabBasicPrem;
	private Double serviceTax;
	private Double totalAmt;
	private Double tabPrem;
	private Double nsapAmt;
	private String nsapFlag;
	private String modeRebateFlag;
	private Double highSaDiscAmt;
	private Double riderAmt;
	private Double rebateAmt;
	private Double wvrRiderAmt;
	private Double termRiderAmt;
	private Double termRiderNsapAmt;
	
	public Double getFinalBasicPrem() {
		return finalBasicPrem;
	}
	public void setFinalBasicPrem(Double finalBasicPrem) {
		this.finalBasicPrem = finalBasicPrem;
	}
	public Double getTabBasicPrem() {
		return tabBasicPrem;
	}
	public void setTabBasicPrem(Double tabBasicPrem) {
		this.tabBasicPrem = tabBasicPrem;
	}
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Double getTabPrem() {
		return tabPrem;
	}
	public void setTabPrem(Double tabPrem) {
		this.tabPrem = tabPrem;
	}
	public Double getNsapAmt() {
		return nsapAmt;
	}
	public void setNsapAmt(Double nsapAmt) {
		this.nsapAmt = nsapAmt;
	}
	public String getNsapFlag() {
		return nsapFlag;
	}
	public void setNsapFlag(String nsapFlag) {
		this.nsapFlag = nsapFlag;
	}
	public String getModeRebateFlag() {
		return modeRebateFlag;
	}
	public void setModeRebateFlag(String modeRebateFlag) {
		this.modeRebateFlag = modeRebateFlag;
	}
	public Double getHighSaDiscAmt() {
		return highSaDiscAmt;
	}
	public void setHighSaDiscAmt(Double highSaDiscAmt) {
		this.highSaDiscAmt = highSaDiscAmt;
	}
	public Double getRiderAmt() {
		return riderAmt;
	}
	public void setRiderAmt(Double riderAmt) {
		this.riderAmt = riderAmt;
	}
	public Double getRebateAmt() {
		return rebateAmt;
	}
	public void setRebateAmt(Double rebateAmt) {
		this.rebateAmt = rebateAmt;
	}
	public Double getWvrRiderAmt() {
		return wvrRiderAmt;
	}
	public void setWvrRiderAmt(Double wvrRiderAmt) {
		this.wvrRiderAmt = wvrRiderAmt;
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
