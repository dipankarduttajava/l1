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

import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.service.interfaces.LicBrnhHubPicMapDtlsService;
import com.gtfs.service.interfaces.LicPremiumListService;
@Component
@Scope("session")
public class PicDeliveryListForReqAction implements Serializable{
	private Logger log = Logger.getLogger(PicDeliveryListForReqAction.class);
	
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBrnhHubPicMapDtlsService licBrnhHubPicMapDtlsService;
	
	
	private List<Long> premList = new ArrayList<Long>();	
	private List<LicRequirementDtls> licRequirementDtlsList = new ArrayList<LicRequirementDtls>();
	private List<LicRequirementDtls> licRequirementDtlses = new ArrayList<LicRequirementDtls>();
	private List<LicRequirementDtls> addedLicRequirementDtls = new ArrayList<LicRequirementDtls>();
	
	private String reqType;
	private Boolean premiumSearchRendered;
	private Boolean searchRendered;
	private Boolean listRendered;
	private Date businessFromDate;
	private Date businessToDate;
	
	
	
	public void refresh(){
		listRendered = false;
		searchRendered = false;
		premiumSearchRendered = true;
		businessFromDate = null;
		businessToDate = null;
		
		if(licRequirementDtlsList!=null){
			licRequirementDtlsList.clear();
		}
		
		if(licRequirementDtlses!=null){
			licRequirementDtlses.clear();
		}
		
		if(addedLicRequirementDtls!=null){
			addedLicRequirementDtls.clear();
		}
		
		reqType = null;
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/picDeliveryListForReq.xhtml";
	}
	
	
	public void searchToAdd(){
		if(licRequirementDtlsList!=null){
			licRequirementDtlsList.clear();
		}
		
		if(reqType!=null ){
			for(LicRequirementDtls obj:licRequirementDtlses){
				if(obj.getReqType().equals(reqType)){
					licRequirementDtlsList.add(obj);
				}
			}
		}else{
			licRequirementDtlsList.addAll(licRequirementDtlses);
		}
		
	}
	
	public void save(){
		try{
			Date now =new Date();
			LicBrnhHubPicMapDtls licBrnhHubPicMapDtls = new LicBrnhHubPicMapDtls();
			licBrnhHubPicMapDtls.setBranchHubPicFlag("H2P");
			licBrnhHubPicMapDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setCreatedDate(now);
			licBrnhHubPicMapDtls.setModifiedDate(now);
			licBrnhHubPicMapDtls.setDeleteFlag("N");

			for(LicRequirementDtls obj : addedLicRequirementDtls){
				obj.setLicBrnhHubPicMapDtls(licBrnhHubPicMapDtls);
			}
			
			licBrnhHubPicMapDtls.setLicRequirementDtlses(addedLicRequirementDtls);
			
			Long id = licBrnhHubPicMapDtlsService.saveForHubPicDispatchListForReq(licBrnhHubPicMapDtls);
			
			if(id > 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "PIC List Generated Successfully"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "PIC List Generation UnSuccessful"));
			}
		}catch(Exception e){
			log.info("PicDeliveryListForReqAction save Error : ", e);
		}
	}
	
	
	
	public void search(){
		try{
			licRequirementDtlses = licBrnhHubPicMapDtlsService.findRequirementsForHubPicDispatch(businessFromDate,businessToDate,loginAction.findHubForProcess("POS"));
			
			if(licRequirementDtlses == null || licRequirementDtlses.size() == 0 || licRequirementDtlses.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
			
			licRequirementDtlsList.addAll(licRequirementDtlses);
			listRendered = true;
			searchRendered = true;
			premiumSearchRendered = false;
		}catch(Exception e){
			log.info("PicDeliveryListForReqAction search Error : ", e);
		}
	}
	
	public void add(LicRequirementDtls licRequirementDtls){
		addedLicRequirementDtls.add(licRequirementDtls);
		licRequirementDtlsList.remove(licRequirementDtls);
		licRequirementDtlses.remove(licRequirementDtls);
	}
	
	
	/* GETTER SETTER */
	public List<Long> getPremList() {
		return premList;
	}
	public void setPremList(List<Long> premList) {
		this.premList = premList;
	}
	
	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public Boolean getPremiumSearchRendered() {
		return premiumSearchRendered;
	}

	public void setPremiumSearchRendered(Boolean premiumSearchRendered) {
		this.premiumSearchRendered = premiumSearchRendered;
	}

	public Boolean getSearchRendered() {
		return searchRendered;
	}

	public void setSearchRendered(Boolean searchRendered) {
		this.searchRendered = searchRendered;
	}

	public Boolean getListRendered() {
		return listRendered;
	}

	public void setListRendered(Boolean listRendered) {
		this.listRendered = listRendered;
	}

	public List<LicRequirementDtls> getLicRequirementDtlsList() {
		return licRequirementDtlsList;
	}

	public void setLicRequirementDtlsList(
			List<LicRequirementDtls> licRequirementDtlsList) {
		this.licRequirementDtlsList = licRequirementDtlsList;
	}

	public List<LicRequirementDtls> getAddedLicRequirementDtls() {
		return addedLicRequirementDtls;
	}

	public void setAddedLicRequirementDtls(
			List<LicRequirementDtls> addedLicRequirementDtls) {
		this.addedLicRequirementDtls = addedLicRequirementDtls;
	}

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

	public List<LicRequirementDtls> getLicRequirementDtlses() {
		return licRequirementDtlses;
	}

	public void setLicRequirementDtlses(List<LicRequirementDtls> licRequirementDtlses) {
		this.licRequirementDtlses = licRequirementDtlses;
	}
	
}
