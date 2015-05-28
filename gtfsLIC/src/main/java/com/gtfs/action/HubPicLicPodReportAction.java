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

import com.gtfs.bean.LicApplBocMapping;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.UserMst;
import com.gtfs.service.interfaces.LicApplBocMappingService;
import com.gtfs.service.interfaces.LicBrnhHubPicPodDtlsService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.UserMstService;

@Component
@Scope("session")
public class HubPicLicPodReportAction implements Serializable{
	private Logger log = Logger.getLogger(HubPicLicPodReportAction.class);
	
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private UserMstService userMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBrnhHubPicPodDtlsService licBrnhHubPicPodDtlsService;

	private Date businessFromDate;
	private Date businessToDate;
	private Boolean renderedPanel;

	private List<LicOblApplicationMst> licOblApplicationMstList = new ArrayList<LicOblApplicationMst>();
	
	
	public void refresh(){
		try{
			businessFromDate = null;
			businessToDate = null;
			renderedPanel = false;
		}catch(Exception e){
			log.info("HubPicLicPodReportAction refresh Error : ", e);
		}
	}
	
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/hubPicLicPodReport.xhtml";
	}

	
	public void onSearch(){
		try{
			licOblApplicationMstList = licBrnhHubPicPodDtlsService.findPicPodReport(businessFromDate, businessToDate, loginAction.getUserList().get(0).getBranchMst());		
			
			if(licOblApplicationMstList == null || licOblApplicationMstList.size() == 0 || licOblApplicationMstList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				renderedPanel = false;
				return;
			}
			renderedPanel = true;
		}catch(Exception e){
			log.info("HubPicLicPodReportAction searchForPod Error : ", e);
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

	public List<LicOblApplicationMst> getLicOblApplicationMstList() {
		return licOblApplicationMstList;
	}

	public void setLicOblApplicationMstList(
			List<LicOblApplicationMst> licOblApplicationMstList) {
		this.licOblApplicationMstList = licOblApplicationMstList;
	}
	
}
