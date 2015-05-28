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
import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.service.interfaces.LicApplBocMappingService;
import com.gtfs.service.interfaces.LicBrnhHubPicMapDtlsService;
import com.gtfs.service.interfaces.LicPremiumListService;
@Component
@Scope("session")
public class PicDeliveryListReportAction implements Serializable{
	private Logger log = Logger.getLogger(PicDeliveryListReportAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBrnhHubPicMapDtlsService licBrnhHubPicMapDtlsService;
	@Autowired
	private LicApplBocMappingService licApplBocMappingService;
	
	private Date businessFromDate;
	private Date businessToDate;
	private Boolean renderedPanel;
	
	private List<LicOblApplicationMst> licOblApplicationMstList = new ArrayList<LicOblApplicationMst>();
	
	public void refresh(){
		businessFromDate = null;
		businessToDate = null;
		renderedPanel = false;
		
		if(licOblApplicationMstList != null){
			licOblApplicationMstList.clear();
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/picDeliveryListReport.xhtml";
	}
	
	public void onSearch(){
		try{
			licOblApplicationMstList = licBrnhHubPicMapDtlsService.findPicDispatchReport(businessFromDate, businessToDate, loginAction.getUserList().get(0).getBranchMst());

			if(licOblApplicationMstList == null || licOblApplicationMstList.size() == 0 || licOblApplicationMstList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				renderedPanel = false;
				return;
			}
			renderedPanel = true;
		}catch(Exception e){
			log.info("PicDeliveryListAction search Error : ", e);
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

	public List<LicOblApplicationMst> getLicOblApplicationMstList() {
		return licOblApplicationMstList;
	}

	public void setLicOblApplicationMstList(
			List<LicOblApplicationMst> licOblApplicationMstList) {
		this.licOblApplicationMstList = licOblApplicationMstList;
	}

	public Boolean getRenderedPanel() {
		return renderedPanel;
	}

	public void setRenderedPanel(Boolean renderedPanel) {
		this.renderedPanel = renderedPanel;
	}
	
}
