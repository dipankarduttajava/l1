package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;
import com.gtfs.service.interfaces.LicBrnhHubPicMapDtlsService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;


@Component
@Scope("session")
public class BranchHubLicDispatchAction implements Serializable{
	private Logger log = Logger.getLogger(BranchHubLicDispatchAction.class);
	
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBrnhHubPicMapDtlsService licBrnhHubPicMapDtlsService;
	@Autowired
	private LicBranchHubPicMappingService licBranchHubPicMappingService;
	@Autowired
	private LicHubMstService licHubMstService;
	
	private Date businessFromDate;
	private Date businessToDate;
	private Long hubId;
	private Boolean renderedListPanel;
	
	private List<LicHubMst> hubMstList = new ArrayList<LicHubMst>();
	private List<LicOblApplicationMstDto> licOblApplicationMstDtoList =  new ArrayList<LicOblApplicationMstDto>();
	private List<LicOblApplicationMstDto> selectedList =  new ArrayList<LicOblApplicationMstDto>();
	
	@PostConstruct
	public void loadingHubsAndPicForBranch(){
		hubMstList.clear();
		List<Long> destinationlist = licBranchHubPicMappingService.findHubIdForBranchIdByProcessName(loginAction.getUserList().get(0).getBranchMst().getBranchId(), "OBL");
		
		for(LicHubMst obj:licHubMstService.findActiveHubMst()){
			if(destinationlist.contains(obj.getId())){
				hubMstList.add(obj);
			}		
		}
	}
	
	public void refresh(){
		businessFromDate = null;
		businessToDate = null;
		
		if(licOblApplicationMstDtoList != null){
			licOblApplicationMstDtoList.clear();
		}
		renderedListPanel = false;
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/branchHubLicDispatch.xhtml";
	}
	
	public void searchForDispatch(){
		try{
			licOblApplicationMstDtoList = licOblApplicationMstService.findDispatchApplicationsByBusinessDate(businessFromDate, businessToDate, loginAction.getUserList().get(0).getBranchMst());
			
			if(licOblApplicationMstDtoList == null || licOblApplicationMstDtoList.size() == 0 || licOblApplicationMstDtoList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				renderedListPanel = false;
				return;
			}			
			renderedListPanel = true;
		}catch(Exception e){
			log.info("BranchHubLicDispatchAction searchForDispatch Error : ", e);
		}
	}
	
	
	public void save(){
		try{
			Date now = new Date();
			Double total = 0.0;
			LicHubMst hub = licHubMstService.findById(hubId);
			
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select an Application to Save"));
				return;
			}
			
			LicBrnhHubPicMapDtls licBrnhHubPicMapDtls = new LicBrnhHubPicMapDtls();
			licBrnhHubPicMapDtls.setBranchHubPicFlag("B2H");
			licBrnhHubPicMapDtls.setDispatchListDate(now);
			licBrnhHubPicMapDtls.setTotalAmount(total);
			licBrnhHubPicMapDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setCreatedDate(now);
			licBrnhHubPicMapDtls.setModifiedDate(now);
			licBrnhHubPicMapDtls.setDeleteFlag("N");
			
			for(LicOblApplicationMstDto obj : selectedList){
				LicOblApplicationMst applicationMst = licOblApplicationMstService.findById(obj.getId());
				applicationMst.setId(obj.getId());
				licBrnhHubPicMapDtls.getLicOblApplicationMsts().add(applicationMst);
				applicationMst.setBrnhHubMapDtls(licBrnhHubPicMapDtls);
			}
					
			Long id = licBrnhHubPicMapDtlsService.saveForBranchHubDispatchList(licBrnhHubPicMapDtls);
			
			if(id > 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Branch To HUB Despatched Successfully, Your Despatch ID is : " + licBrnhHubPicMapDtls.getId()));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unsuccessful : ", "Branch To HUB Despatch Unsuccessful"));
			}
		}catch(Exception e){
			log.info("BranchHubLicDispatchAction Save Error : ", e);
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
	
	
	
	
	
	public List<LicOblApplicationMstDto> getLicOblApplicationMstDtoList() {
		return licOblApplicationMstDtoList;
	}

	public void setLicOblApplicationMstDtoList(
			List<LicOblApplicationMstDto> licOblApplicationMstDtoList) {
		this.licOblApplicationMstDtoList = licOblApplicationMstDtoList;
	}

	public Long getHubId() {
		return hubId;
	}


	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}


	public List<LicHubMst> getHubMstList() {
		return hubMstList;
	}


	public void setHubMstList(List<LicHubMst> hubMstList) {
		this.hubMstList = hubMstList;
	}

	

	public List<LicOblApplicationMstDto> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicOblApplicationMstDto> selectedList) {
		this.selectedList = selectedList;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

}
