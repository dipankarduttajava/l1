package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.dto.LicPolicyDtlsDto;

@Component
@Scope("session")
public class PremiumListRnlAction implements Serializable{
	private Logger log = Logger.getLogger(PremiumListRnlAction.class);
	
	private Long picBranchId;
	private String payMode;
	
	private Boolean renderedPremiumList;
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> selectedList =  new ArrayList<LicPolicyDtlsDto>();
	
	public void refresh(){
		renderedPremiumList = false;
		if(licPolicyDtlsDtoList!=null){
			licPolicyDtlsDtoList.clear();
		}
		
		if(selectedList!=null){
			selectedList.clear();
		}
		
		picBranchId = null;
		payMode = null;
	}
	
	
	public String onLoad(){
		refresh();
		return "/licHubRenewalActivity/premiumListRnl.xhtml";
	}

	
	/* GETTER SETTER */
	public Boolean getRenderedPremiumList() {
		return renderedPremiumList;
	}
	public void setRenderedPremiumList(Boolean renderedPremiumList) {
		this.renderedPremiumList = renderedPremiumList;
	}

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtoList() {
		return licPolicyDtlsDtoList;
	}

	public void setLicPolicyDtlsDtoList(List<LicPolicyDtlsDto> licPolicyDtlsDtoList) {
		this.licPolicyDtlsDtoList = licPolicyDtlsDtoList;
	}

	public List<LicPolicyDtlsDto> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicPolicyDtlsDto> selectedList) {
		this.selectedList = selectedList;
	}

	public Long getPicBranchId() {
		return picBranchId;
	}

	public void setPicBranchId(Long picBranchId) {
		this.picBranchId = picBranchId;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
}
