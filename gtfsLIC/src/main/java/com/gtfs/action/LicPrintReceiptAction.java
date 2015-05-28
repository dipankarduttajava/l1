package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
@Component
@Scope("session")
public class LicPrintReceiptAction implements Serializable{	
	private Logger log = Logger.getLogger(LicPrintReceiptAction.class);
	
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private LoginAction loginAction;
	
	private Date businessDate;
	private String applicationNo;
	private Boolean renderedListPanel;
	private String receiptNo;
	
	private LicOblApplicationMstDto licOblApplicationMstDto;
	
	private List<LicOblApplicationMstDto> licOblApplicationMstDtoList = new ArrayList<LicOblApplicationMstDto>();

	public void refresh(){
		businessDate = null;
		applicationNo = null;
		renderedListPanel = false;
		if(licOblApplicationMstDtoList !=null){
			licOblApplicationMstDtoList.clear();
		}
		
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/licPrintReceipt.xhtml";
	}
	
	public void search(){
		try{
			if(businessDate != null){
				licOblApplicationMstDtoList = licOblApplicationMstService.findApplicationForPrintReceiptByDate(businessDate, loginAction.getUserList().get(0).getBranchMst());
				
				renderedListPanel = true;
				
			}else if(applicationNo != null){
				licOblApplicationMstDtoList = licOblApplicationMstService.findApplicationForPrintReceiptByApplicationNo(applicationNo, loginAction.getUserList().get(0).getBranchMst());
				renderedListPanel = true;
				
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_ERROR,"Error :", "Please Enter Date or Application Number"));
				renderedListPanel = false;
			}
		}catch(Exception e){
			log.info("LicPrintReceiptAction search Error : ", e);
		}
	}
	
	public String printPage(LicOblApplicationMstDto licOblApplicationMstDto){
		try{
			this.licOblApplicationMstDto = licOblApplicationMstDto;
			if(licOblApplicationMstDto.getPrintFlag().equals("N")){
				Boolean status = licOblApplicationMstService.updatePrintReceiptFlagInLicOblApplMst(licOblApplicationMstDto.getId());
				
				if(status == false){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error :", "Please Try Again"));
					return null;
				}
			}
			
			return "/licBranchActivity/licProvitionalPrintReceipt.xhtml?faces-redirect=true";
		}catch(Exception e){
			log.info("LicPrintReceiptAction printPage Error : ", e);
			return null;
		}
	}

	
	/* GETTER SETTER */
	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	
	
	public List<LicOblApplicationMstDto> getLicOblApplicationMstDtoList() {
		return licOblApplicationMstDtoList;
	}

	public void setLicOblApplicationMstDtoList(
			List<LicOblApplicationMstDto> licOblApplicationMstDtoList) {
		this.licOblApplicationMstDtoList = licOblApplicationMstDtoList;
	}

	public LicOblApplicationMstDto getLicOblApplicationMstDto() {
		return licOblApplicationMstDto;
	}

	public void setLicOblApplicationMstDto(
			LicOblApplicationMstDto licOblApplicationMstDto) {
		this.licOblApplicationMstDto = licOblApplicationMstDto;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
}