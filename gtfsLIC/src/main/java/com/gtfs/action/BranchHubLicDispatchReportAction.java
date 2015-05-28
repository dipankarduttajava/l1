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

import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.LicBrnhHubPicMapDtlsService;


@Component
@Scope("session")
public class BranchHubLicDispatchReportAction implements Serializable{
	private Logger log = Logger.getLogger(BranchHubLicDispatchReportAction.class);

	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBrnhHubPicMapDtlsService licBrnhHubPicMapDtlsService;
	
	private Date businessFromDate;
	private Date businessToDate;
	private Boolean renderedPanel;
	
	private List<LicOblApplicationMstDto> licOblApplicationMstDtoList =  new ArrayList<LicOblApplicationMstDto>();
	
	public void refresh(){
		businessFromDate = null;
		businessToDate = null;
		renderedPanel = false;
		
		if(licOblApplicationMstDtoList != null){
			licOblApplicationMstDtoList.clear();
		}
	}
	
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/branchHubLicDispatchReport.xhtml";
	}
		
	public void searchForDispatchReport(){
		try{
			licOblApplicationMstDtoList = licBrnhHubPicMapDtlsService.findBranchHubDispatchReport(businessFromDate, businessToDate, loginAction.getUserList().get(0).getBranchMst());
			
			if(licOblApplicationMstDtoList == null || licOblApplicationMstDtoList.size() == 0 || licOblApplicationMstDtoList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				renderedPanel = false;
				return;
			}
			renderedPanel = true;
		}catch(Exception e){
			log.info("BranchHubLicDispatchReportAction onSearch Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public Date getBusinessFromDate() {
		return businessFromDate;
	}
	public void setBusinessFromDate(Date businessFromDate) {
		this.businessFromDate = businessFromDate;
	}

	
	public Date getBusinessToDate() {
		return businessToDate;
	}
	public void setBusinessToDate(Date businessToDate) {
		this.businessToDate = businessToDate;
	}

	
	public Boolean getRenderedPanel() {
		return renderedPanel;
	}
	public void setRenderedPanel(Boolean renderedPanel) {
		this.renderedPanel = renderedPanel;
	}


	public List<LicOblApplicationMstDto> getLicOblApplicationMstDtoList() {
		return licOblApplicationMstDtoList;
	}
	public void setLicOblApplicationMstDtoList(
			List<LicOblApplicationMstDto> licOblApplicationMstDtoList) {
		this.licOblApplicationMstDtoList = licOblApplicationMstDtoList;
	}
	
}
	