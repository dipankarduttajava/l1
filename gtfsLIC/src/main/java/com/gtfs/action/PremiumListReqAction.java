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

import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicPremReqMapping;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.bean.PicBranchMst;
import com.gtfs.service.interfaces.LicPremiumListService;
import com.gtfs.service.interfaces.PicBranchMstService;
@Component
@Scope("session")
public class PremiumListReqAction implements Serializable{
	private Logger log = Logger.getLogger(PremiumListReqAction.class);
	
	@Autowired
	private PicBranchMstService picBranchMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPremiumListService licPremiumListService;
	
	private Long picBranchId;
	private String payMode;
	private Boolean renderedPremiumList;

	private List<PicBranchMst> picBranchMsts = new ArrayList<PicBranchMst>();	
	private List<LicRequirementDtls> licRequirementDtlsList = new ArrayList<LicRequirementDtls>();
	private List<LicRequirementDtls> selectedList =new ArrayList<LicRequirementDtls>();
	
	@PostConstruct
	public void loadingPicForHub(){
		List<Long> licHubMsts = new ArrayList<Long>();
		for(LicHubMst hub:loginAction.findHubForProcess("POS")){
			licHubMsts.add(hub.getId());
		}
		
		List<Long> pics = picBranchMstService.findPicIdsForHubsByProcessName(licHubMsts, "POS");		
		picBranchMsts = picBranchMstService.findPicsByPicIds(pics);
		
	}
	
	
	public void search(){
		try{
			renderedPremiumList = true;
			licRequirementDtlsList = licPremiumListService.findRequirementForPremList(loginAction.findHubForProcess("POS"), payMode, loginAction.getUserList().get(0).getBranchMst());
		}catch(Exception e){
			log.info("PremiumListReqAction search Error : ", e);
		}
	}
	
	
	public void save(){
		try{
			PicBranchMst picBranchMst = picBranchMstService.findbyId(picBranchId);
			Date now = new Date();
			List<LicPremReqMapping>	licPremReqMappingList = new ArrayList<LicPremReqMapping>();

			LicPremiumListDtls licPremiumListDtls = new LicPremiumListDtls();
			licPremiumListDtls.setPremiumListDate(now);
			licPremiumListDtls.setPremuumType("S");
			licPremiumListDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licPremiumListDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPremiumListDtls.setCreatedDate(now);
			licPremiumListDtls.setModifiedDate(now);
			licPremiumListDtls.setDeleteFlag("N");
			
			for(LicRequirementDtls obj : selectedList){
				LicPremReqMapping licPremReqMapping = new LicPremReqMapping();
				licPremReqMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licPremReqMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licPremReqMapping.setCreatedDate(now);
				licPremReqMapping.setModifiedDate(now);
				licPremReqMapping.setDeleteFlag("N");
				licPremReqMapping.setLicRequirementDtls(obj);
				licPremReqMapping.setLicPremiumListDtls(licPremiumListDtls);
				
				licPremReqMappingList.add(licPremReqMapping);
			}
			
			for(LicRequirementDtls obj:selectedList){
				obj.setDispatchReadyFlag("Y");
				obj.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				obj.setModifiedDate(now);
				obj.setLicPremReqMappings(licPremReqMappingList);
				obj.setPicBranchMst(picBranchMst);
			}
			
			licPremiumListDtls.setLicPremReqMappings(licPremReqMappingList);
			
			Boolean status = licPremiumListService.saveForPremiumListForReq(licPremReqMappingList);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Premium List Generated Successfully"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Premium List Generation UnSuccessful"));
			}
		}catch(Exception e){
			log.info("PremiumListReqAction save Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/premiumListForReq.xhtml";
	}

	public void refresh(){
		renderedPremiumList = false;
		
		if(licRequirementDtlsList!=null){
			licRequirementDtlsList.clear();
		}
		
		selectedList.clear();
		picBranchId = null;
		payMode = null;
	}
	
	
	/* GETTER SETTER */
	public Long getPicBranchId() {
		return picBranchId;
	}

	public void setPicBranchId(Long picBranchId) {
		this.picBranchId = picBranchId;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public List<PicBranchMst> getPicBranchMsts() {
		return picBranchMsts;
	}

	public void setPicBranchMsts(List<PicBranchMst> picBranchMsts) {
		this.picBranchMsts = picBranchMsts;
	}

	public Boolean getRenderedPremiumList() {
		return renderedPremiumList;
	}

	public void setRenderedPremiumList(Boolean renderedPremiumList) {
		this.renderedPremiumList = renderedPremiumList;
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
