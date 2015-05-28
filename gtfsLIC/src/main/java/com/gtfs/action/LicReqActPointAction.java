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

import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.service.interfaces.LicRequirementDtlsService;

@Component
@Scope("session")
public class LicReqActPointAction implements Serializable{
	private Logger log = Logger.getLogger(LicReqActPointAction.class);
	
	@Autowired
	private LicRequirementDtlsService licRequirementDtlsService;
	@Autowired
	private LoginAction loginAction;
	
	private String applicationNo;
	private Boolean renderedList;
	private List<LicRequirementDtls>  licRequirementDtlses= new ArrayList<LicRequirementDtls>();
	
	
	public void refresh(){
		applicationNo = null;
		renderedList = false;
	}
	
	public void save(){
		try{
			Date now = new Date();
			for(LicRequirementDtls obj:licRequirementDtlses){
				obj.setActionBy(loginAction.getUserList().get(0).getUserid());
				obj.setActionDate(now);
				
				if(obj.getActionType().equals("SFCL") || obj.getActionType().equals("IR")){
					obj.setDispatchReadyFlag("Y");
				}else{
					obj.setDispatchReadyFlag("N");
				}
			}
			
			Boolean status=licRequirementDtlsService.saveForActionPoint(licRequirementDtlses);
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Actions are taken"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unsuccessful : ", "Error Occurred"));
			}
		}catch(Exception e){
			log.info("LicReqActPointAction save Error : ", e);
		}
	}
	
	
	public void search(){
		try{
			licRequirementDtlses = licRequirementDtlsService.findReqForActionTakenByApplNo(applicationNo,loginAction.findHubForProcess("POS"));
			
			if(!(licRequirementDtlses == null || licRequirementDtlses.size() == 0)){
				renderedList=true;
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "No Record(s) found : ", "Please Enter Requirement"));
			}
		}catch(Exception e){
			log.info("LicReqActPointAction save Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/licReqActPoint.xhtml";
	}

	
	/* GETTER SETTER */
	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Boolean getRenderedList() {
		return renderedList;
	}

	public void setRenderedList(Boolean renderedList) {
		this.renderedList = renderedList;
	}

	public List<LicRequirementDtls> getLicRequirementDtlses() {
		return licRequirementDtlses;
	}

	public void setLicRequirementDtlses(
			List<LicRequirementDtls> licRequirementDtlses) {
		this.licRequirementDtlses = licRequirementDtlses;
	}
	
	
}
