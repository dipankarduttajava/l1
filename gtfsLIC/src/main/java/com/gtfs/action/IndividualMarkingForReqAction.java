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
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.service.interfaces.LicMarkingToQcDtlsService;


@Component
@Scope("session")
public class IndividualMarkingForReqAction implements Serializable{
	private Logger log = Logger.getLogger(IndividualMarkingForReqAction.class);
	
	@Autowired
	private LicMarkingToQcDtlsService licMarkingToQcDtlsService;
	@Autowired
	private LoginAction loginAction;
	private Date bnsFromDate;
	private Date bnsToDate;
	private Boolean renderedindMark;
	
	private List<LicRequirementDtls> licRequirementDtlsList = new ArrayList<LicRequirementDtls>();
	private List<LicRequirementDtls> selectedList =  new ArrayList<LicRequirementDtls>();
	
	public void refresh(){
		renderedindMark = false;
		bnsFromDate = null;
		bnsToDate = null;
		
		if(licRequirementDtlsList!=null){
			licRequirementDtlsList.clear();
		}
		if(selectedList!=null){
			selectedList.clear();
		}
	}
	
	public String onLoad(){
		refresh();		
		return "/licHubActivity/individualMarkingForReq.xhtml";
	}
	
	
	public void save(){
		try{
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select an Application to Save"));
				return;
			}
			
			Date now = new Date();			
			for(LicRequirementDtls obj : selectedList){
				obj.setDispatchReadyFlag("Y");
				obj.getLicMarkingToQcDtls().setIndMrkBy(loginAction.getUserList().get(0).getUserid());
				obj.getLicMarkingToQcDtls().setIndMrkDate(now);
				obj.getLicMarkingToQcDtls().setIndMrkFlag("Y");
				obj.getLicMarkingToQcDtls().setModifiedBy(loginAction.getUserList().get(0).getUserid());
				obj.getLicMarkingToQcDtls().setModifiedDate(now);
			}
			
			Boolean status = licMarkingToQcDtlsService.updateForIndividualMarkingForReq(selectedList);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "REQ POD Tagging Successfully Completed."));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "REQ POD Tagging Unsuccessful"));
			}
		}catch(Exception e){
			log.info("IndividualMarkingForReqAction save Error : ", e);
		}
	}
	
	public void search(){
		try{
			renderedindMark = true;
			licRequirementDtlsList = licMarkingToQcDtlsService.findIndividualMArkingDtlsByDateForReq(bnsFromDate, bnsToDate, loginAction.findHubForProcess("POS"), loginAction.getUserList().get(0).getBranchMst());
			
			if(licRequirementDtlsList == null || licRequirementDtlsList.size() == 0 || licRequirementDtlsList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
		}catch(Exception e){
			log.info("IndividualMarkingAction search Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public Date getBnsFromDate() {
		return bnsFromDate;
	}
	public void setBnsFromDate(Date bnsFromDate) {
		this.bnsFromDate = bnsFromDate;
	}
	public Date getBnsToDate() {
		return bnsToDate;
	}
	public void setBnsToDate(Date bnsToDate) {
		this.bnsToDate = bnsToDate;
	}

	public Boolean getRenderedindMark() {
		return renderedindMark;
	}

	public void setRenderedindMark(Boolean renderedindMark) {
		this.renderedindMark = renderedindMark;
	}

	public List<LicRequirementDtls> getLicRequirementDtlsList() {
		return licRequirementDtlsList;
	}

	public void setLicRequirementDtlsList(
			List<LicRequirementDtls> licRequirementDtlsList) {
		this.licRequirementDtlsList = licRequirementDtlsList;
	}

	public List<LicRequirementDtls> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicRequirementDtls> selectedList) {
		this.selectedList = selectedList;
	}

}
