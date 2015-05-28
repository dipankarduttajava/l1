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
public class PicDeliveryListAction implements Serializable{
	private Logger log = Logger.getLogger(PicDeliveryListAction.class);
	
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBrnhHubPicMapDtlsService licBrnhHubPicMapDtlsService;
	@Autowired
	private LicApplBocMappingService licApplBocMappingService;
	
	private Long premListNo;
	private String applNo;
	private Boolean premiumSearchRendered;
	private Boolean searchRendered;
	private Boolean listRendered;
	
	private List<Long> premList = new ArrayList<Long>();
	private List<LicOblApplicationMst> licOblApplicationMsts = new ArrayList<LicOblApplicationMst>();
	private List<LicOblApplicationMst> searchLicOblApplicationMsts = new ArrayList<LicOblApplicationMst>();
	private List<LicOblApplicationMst> addedLicOblApplicationMsts = new ArrayList<LicOblApplicationMst>();
	
	public void refresh(){
		try{
			listRendered = false;
			searchRendered = false;
			premiumSearchRendered = true;
			
			if(licOblApplicationMsts != null){
				licOblApplicationMsts.clear();
			}
			
			if(searchLicOblApplicationMsts != null){
				searchLicOblApplicationMsts.clear();
			}
			
			if(addedLicOblApplicationMsts != null){
				addedLicOblApplicationMsts.clear();
			}
			
			premListNo = null;
			applNo = null;
			premList = licPremiumListService.findPremiumListForPicDelivery(loginAction.findHubForProcess("OBL"));
		}catch(Exception e){
			log.info("PicDeliveryListAction refresh Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/picDeliveryList.xhtml";
	}
	
	public void save(){
		try{
			if(addedLicOblApplicationMsts == null || addedLicOblApplicationMsts.size() == 0 || addedLicOblApplicationMsts.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Pleses Add an Application"));
				return;
			}
			
			Date now = new Date();
			LicBrnhHubPicMapDtls licBrnhHubPicMapDtls = new LicBrnhHubPicMapDtls();
			licBrnhHubPicMapDtls.setBranchHubPicFlag("H2P");
			licBrnhHubPicMapDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setCreatedDate(now);
			licBrnhHubPicMapDtls.setModifiedDate(now);
			licBrnhHubPicMapDtls.setDeleteFlag("N");

			for(LicOblApplicationMst obj : addedLicOblApplicationMsts){
				obj.setHubPicMapDtls(licBrnhHubPicMapDtls);
			}
			
			licBrnhHubPicMapDtls.setHubPicApplicationMsts(addedLicOblApplicationMsts);
			
			Long id = licBrnhHubPicMapDtlsService.saveForHubPicDispatchList(licBrnhHubPicMapDtls);
			
			if(id > 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "PIC List Generated Successfully, Your PIC Dispatch No. is : " + licBrnhHubPicMapDtls.getId()));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "PIC List Generation UnSuccessful"));
			}
		}catch(Exception e){
			log.info("PicDeliveryListAction save Error : ", e);
		}
	}
	
	public void search(){
		try{
			licOblApplicationMsts = licPremiumListService.findApplicationForDeliveryListOfPic(premListNo, loginAction.getUserList().get(0).getBranchMst());
			
			for(LicOblApplicationMst obj : licOblApplicationMsts){
				List<LicApplBocMapping> licApplBocMappingList = licApplBocMappingService.findBocMappingByApplication(obj.getId());
				obj.setLicApplBocMappings(licApplBocMappingList);
				
			}
			
			if(licOblApplicationMsts == null || licOblApplicationMsts.size() == 0 || licOblApplicationMsts.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}else{
				searchLicOblApplicationMsts.addAll(licOblApplicationMsts);
				listRendered = true;
				searchRendered = true;
				premiumSearchRendered = false;
			}
		}catch(Exception e){
			log.info("PicDeliveryListAction search Error : ", e);
		}
	}
	
	public void searchToAdd(){
		searchLicOblApplicationMsts.clear();
		
		if(applNo != null ){
			for(LicOblApplicationMst obj : licOblApplicationMsts){
				if(obj.getOblApplNo().equals(applNo)){
					searchLicOblApplicationMsts.add(obj);
				}
			}
		}else{
			searchLicOblApplicationMsts.addAll(licOblApplicationMsts);
		}
		
	}
	
	public void add(LicOblApplicationMst licOblApplicationMst){
		addedLicOblApplicationMsts.add(licOblApplicationMst);
		searchLicOblApplicationMsts.remove(licOblApplicationMst);
		licOblApplicationMsts.remove(licOblApplicationMst);
	}
	
	
	/* GETTER SETTER */
	public Long getPremListNo() {
		return premListNo;
	}

	public void setPremListNo(Long premListNo) {
		this.premListNo = premListNo;
	}
	public List<Long> getPremList() {
		return premList;
	}
	public void setPremList(List<Long> premList) {
		this.premList = premList;
	}

	public List<LicOblApplicationMst> getLicOblApplicationMsts() {
		return licOblApplicationMsts;
	}

	public void setLicOblApplicationMsts(
			List<LicOblApplicationMst> licOblApplicationMsts) {
		this.licOblApplicationMsts = licOblApplicationMsts;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public List<LicOblApplicationMst> getSearchLicOblApplicationMsts() {
		return searchLicOblApplicationMsts;
	}

	public void setSearchLicOblApplicationMsts(
			List<LicOblApplicationMst> searchLicOblApplicationMsts) {
		this.searchLicOblApplicationMsts = searchLicOblApplicationMsts;
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

	public List<LicOblApplicationMst> getAddedLicOblApplicationMsts() {
		return addedLicOblApplicationMsts;
	}

	public void setAddedLicOblApplicationMsts(
			List<LicOblApplicationMst> addedLicOblApplicationMsts) {
		this.addedLicOblApplicationMsts = addedLicOblApplicationMsts;
	}
	
	
}
