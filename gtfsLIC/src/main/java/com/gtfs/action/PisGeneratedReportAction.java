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
import com.gtfs.service.interfaces.LicPisMstService;

@Component
@Scope("session")
public class PisGeneratedReportAction implements Serializable {
	private Logger log = Logger.getLogger(PisGeneratedReportAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPisMstService licPisMstService;
	
	private Date businessFromDate;
	private Date businessToDate;
	private Boolean renderedPanel;
	private String pisCms;
	private String applNo;
	
	private List<LicOblApplicationMst> licOblApplicationMstList = new ArrayList<LicOblApplicationMst>();
	
	public void refresh(){
		businessFromDate = null;
		businessToDate = null;
		renderedPanel = false;
		pisCms = null;
		applNo = null; 
		
		if(licOblApplicationMstList != null){
			licOblApplicationMstList.clear();
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/pisGeneratedReport.xhtml";
	}
	
	public void onSearch(){
		try{
			if(businessFromDate == null && businessToDate == null && applNo == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select Proper Search Criteria"));
				return;
			}
			
			licOblApplicationMstList = licPisMstService.findPisGeneratedReport(businessFromDate, businessToDate, pisCms, applNo, loginAction.findHubForProcess("OBL"));
			
			if(licOblApplicationMstList == null || licOblApplicationMstList.size() == 0 || licOblApplicationMstList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
			renderedPanel = true;
		}catch(Exception e){
			log.info("PisGeneratedReportAction onSearch Error : ", e);
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

	public String getPisCms() {
		return pisCms;
	}

	public void setPisCms(String pisCms) {
		this.pisCms = pisCms;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public List<LicOblApplicationMst> getLicOblApplicationMstList() {
		return licOblApplicationMstList;
	}

	public void setLicOblApplicationMstList(
			List<LicOblApplicationMst> licOblApplicationMstList) {
		this.licOblApplicationMstList = licOblApplicationMstList;
	}	
}
