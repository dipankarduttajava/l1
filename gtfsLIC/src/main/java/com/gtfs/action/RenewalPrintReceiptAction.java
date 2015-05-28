package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicPolicyDtlsService;

@Component
@Scope("session")
public class RenewalPrintReceiptAction implements Serializable {
	private Logger log = Logger.getLogger(RenewalPrintReceiptAction.class);
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	
	private LicPolicyDtlsDto licPolicyDtlsDto;
	private List<LicPolicyDtlsDto> licPolicyDtlsDtos = new ArrayList<LicPolicyDtlsDto>();
	private String policyNo;
	
	private Boolean renderedPanel;
	
	public void refresh() {
		renderedPanel = false;
	}

	public String onLoad() {
		refresh();
		return "/licBranchRenewalActivity/renewalPrintReceipt.xhtml";
	}
	
	public void search() {
		licPolicyDtlsDtos = licPolicyDtlsService.findPolicyDtlsForRnlPrintReceipt(policyNo);		
		renderedPanel = true;
	}
	
	public String printPage(LicPolicyDtlsDto licPolicyDtlsDto){
		try{
			this.licPolicyDtlsDto = licPolicyDtlsDto;
			licPolicyDtlsDto.setPolicyNo(policyNo);
			
			if(licPolicyDtlsDto.getTieupCompyName().equals("SARADA INSURANCE CONSULTANCY LTD")){
				return "/licBranchRenewalActivity/renewalProvitionalSICLPrintReceipt.xhtml?faces-redirect=true";
				
			}else if(licPolicyDtlsDto.getTieupCompyName().equals("GOLDEN TRUST FINANCIAL SERVICES")){
				return "/licBranchRenewalActivity/renewalProvitionalGTFSPrintReceipt.xhtml?faces-redirect=true";
			}else{
				return null;
			}
		}catch(Exception e){
			log.info("Print Error ", e);
			return null;
		}
	}

	
	/* GETTER SETTER */
	public Boolean getRenderedPanel() {
		return renderedPanel;
	}
	public void setRenderedPanel(Boolean renderedPanel) {
		this.renderedPanel = renderedPanel;
	}

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtos() {
		return licPolicyDtlsDtos;
	}
	public void setLicPolicyDtlsDtos(List<LicPolicyDtlsDto> licPolicyDtlsDtos) {
		this.licPolicyDtlsDtos = licPolicyDtlsDtos;
	}

	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public LicPolicyDtlsDto getLicPolicyDtlsDto() {
		return licPolicyDtlsDto;
	}
	public void setLicPolicyDtlsDto(LicPolicyDtlsDto licPolicyDtlsDto) {
		this.licPolicyDtlsDto = licPolicyDtlsDto;
	}	
}
