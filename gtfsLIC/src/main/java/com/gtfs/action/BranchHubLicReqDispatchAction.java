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

import com.gtfs.bean.LicBranchReqRcvMst;
import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;
import com.gtfs.service.interfaces.LicBranchReqRcvMstService;
import com.gtfs.service.interfaces.LicBrnhHubPicMapDtlsService;
import com.gtfs.service.interfaces.LicHubMstService;
@Component
@Scope("session")
public class BranchHubLicReqDispatchAction implements Serializable {
	private Logger log = Logger.getLogger(BranchHubLicReqDispatchAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicHubMstService licHubMstService;
	@Autowired
	private LicBranchHubPicMappingService licBranchHubPicMappingService;
	@Autowired
	private LicBranchReqRcvMstService licBranchReqRcvMstService;
	@Autowired
	private LicBrnhHubPicMapDtlsService licBrnhHubPicMapDtlsService;
	
	private Date businessFromDate;
	private Date businessToDate;
	private Long hubId;
	private Boolean renderedListPanel;
	private List<LicHubMst> hubMstList = new ArrayList<LicHubMst>();
	private List<LicBranchReqRcvMst> licBranchReqRcvMsts = new ArrayList<LicBranchReqRcvMst>();
	private List<LicBranchReqRcvMst> selectedList = new ArrayList<LicBranchReqRcvMst>();
	
	@PostConstruct
	public void loadingHubsAndPicForBranch(){
		hubMstList.clear();
		List<Long> destinationlist = licBranchHubPicMappingService.findHubIdForBranchIdByProcessName(loginAction.getUserList().get(0).getBranchMst().getBranchId(), "POS");
		
		for(LicHubMst obj:licHubMstService.findActiveHubMst()){
			if(destinationlist.contains(obj.getId())){
				hubMstList.add(obj);
			}		
		}
	}
	
	public void save(){
		try{
			Date now = new Date();
			Double total = 0.0;
			//LicHubMst hub = licHubMstService.findById(hubId);
			
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unsuccessful : ", "Please Select an Application to Save"));
				return;
			}
			
//			for(LicBranchReqRcvMst obj : selectedList){
//				total = total+(obj.getLicProductValueMst().getPremAmt()+obj.getLicProductValueMst().getTaxOnPrem()); // you will do this work
//			}
			
			LicBrnhHubPicMapDtls licBrnhHubPicMapDtls = new LicBrnhHubPicMapDtls();
			licBrnhHubPicMapDtls.setBranchHubPicFlag("B2H");
			licBrnhHubPicMapDtls.setDispatchListDate(now);
			licBrnhHubPicMapDtls.setTotalAmount(total);
			licBrnhHubPicMapDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setCreatedDate(now);
			licBrnhHubPicMapDtls.setModifiedDate(now);
			licBrnhHubPicMapDtls.setDeleteFlag("N");
			
			for(LicBranchReqRcvMst obj : selectedList){
				obj.setLicBrnhHubPicMapDtls(licBrnhHubPicMapDtls);
				//obj.setLicHubMst(hub);
			}
			
			licBrnhHubPicMapDtls.setLicBranchReqRcvMsts(selectedList);		
			Boolean status = licBrnhHubPicMapDtlsService.saveForBranchHubReqDispatchList(licBrnhHubPicMapDtls);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Branch To HUB Despatched Successfully"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unsuccessful : ", "Branch To HUB Despatch Unsuccessful"));
			}
		}catch(Exception e){
			log.info("BranchHubLicReqDispatchAction Save Error : ", e);
		}
	}
	
	public void searchForDispatch(){
		try{
			licBranchReqRcvMsts = licBranchReqRcvMstService.findRequirementForDispatch(businessFromDate, businessToDate, loginAction.getUserList().get(0).getBranchMst());
			renderedListPanel = true;
		}catch(Exception e){
			log.info("BranchHubLicReqDispatchAction searchForDispatch Error : ", e);
		}
	}
	
	public void refresh(){
		businessFromDate = null;
		businessToDate = null;
		
		if(licBranchReqRcvMsts!=null){
			licBranchReqRcvMsts.clear();
		}
		renderedListPanel = false;
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/branchHubLicReqDispatch.xhtml";
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

	public List<LicBranchReqRcvMst> getLicBranchReqRcvMsts() {
		return licBranchReqRcvMsts;
	}

	public void setLicBranchReqRcvMsts(List<LicBranchReqRcvMst> licBranchReqRcvMsts) {
		this.licBranchReqRcvMsts = licBranchReqRcvMsts;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public List<LicBranchReqRcvMst> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicBranchReqRcvMst> selectedList) {
		this.selectedList = selectedList;
	}
}
