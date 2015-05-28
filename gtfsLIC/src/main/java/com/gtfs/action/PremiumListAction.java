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
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicPremApplMapping;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.bean.PicBranchMst;
import com.gtfs.service.interfaces.LicPaymentDtlsService;
import com.gtfs.service.interfaces.LicPremiumListService;
import com.gtfs.service.interfaces.PicBranchMstService;
@Component
@Scope("session")
public class PremiumListAction implements Serializable{
	private Logger log = Logger.getLogger(PremiumListAction.class);
	
	@Autowired
	private PicBranchMstService picBranchMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LicPaymentDtlsService licPaymentDtlsService;
	
	private Long picBranchId;
	private String payMode;
	private Boolean renderedPremiumList;
	private Double totalAmount;

	private List<PicBranchMst> picBranchMsts = new ArrayList<PicBranchMst>();	
	private List<LicOblApplicationMst> licOblApplicationMsts =new ArrayList<LicOblApplicationMst>();
	private List<LicOblApplicationMst> selectedList =new ArrayList<LicOblApplicationMst>();
	
	
	
	@PostConstruct
	public void loadingPicForHub(){
		List<Long> licHubMsts = new ArrayList<Long>();
		for(LicHubMst hub:loginAction.findHubForProcess("OBL")){
			licHubMsts.add(hub.getId());
		}
		
		List<Long> pics = picBranchMstService.findPicIdsForHubsByProcessName(licHubMsts, "OBL");
		picBranchMsts = picBranchMstService.findPicsByPicIds(pics);
	}
	
	public void search(){
		try{
			totalAmount = 0.0;
			renderedPremiumList = true;
			licOblApplicationMsts = licPremiumListService.findApplicationForPremList(loginAction.findHubForProcess("OBL"), payMode, loginAction.getUserList().get(0).getBranchMst());

			for(LicOblApplicationMst obj : licOblApplicationMsts){

				List<LicPaymentDtls> licPaymentDtlses = licPaymentDtlsService.findLicPaymentDtlsByPayId(obj.getLicBusinessTxn().getLicPaymentMst());

				for(LicPaymentDtls ob : licPaymentDtlses){
					if((ob.getPayeeName() == null || ob.getPayeeName().equals("SARADA INSURANCE CONSULTANCY LTD"))){
						totalAmount = totalAmount + ob.getAmount();
					}
				}
				obj.getLicBusinessTxn().getLicPaymentMst().setLicPaymentDtlses(licPaymentDtlses);
			}
		}catch(Exception e){
			log.info("PremiumListAction search Error : ", e);
		}
	}
	
	
	public void save(){
		try{
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select a Policy to Save"));
				return;
			}
			
			PicBranchMst picBranchMst = picBranchMstService.findbyId(picBranchId);
			Date now=new Date();
			List<LicPremApplMapping> licPremApplMappings = new ArrayList<LicPremApplMapping>();

			LicPremiumListDtls licPremiumListDtls =new LicPremiumListDtls();
			licPremiumListDtls.setPremiumListDate(now);
			licPremiumListDtls.setPremuumType("D");
			licPremiumListDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licPremiumListDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPremiumListDtls.setCreatedDate(now);
			licPremiumListDtls.setModifiedDate(now);
			licPremiumListDtls.setDeleteFlag("N");
			
			for(LicOblApplicationMst obj:selectedList){
				LicPremApplMapping licPremApplMapping = new LicPremApplMapping();
				licPremApplMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licPremApplMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licPremApplMapping.setCreatedDate(now);
				licPremApplMapping.setModifiedDate(now);
				licPremApplMapping.setDeleteFlag("N");
				licPremApplMapping.setLicOblApplicationMst(obj);
				licPremApplMapping.setLicPremiumListDtls(licPremiumListDtls);
				
				licPremApplMappings.add(licPremApplMapping);
			}
			
			for(LicOblApplicationMst obj:selectedList){
				obj.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				obj.setModifiedDate(now);
				obj.setLicPremApplMappings(licPremApplMappings);
				obj.setPicBranchMstId(picBranchMst);
			}
			licPremiumListDtls.setLicPremApplMappings(licPremApplMappings);
			Boolean status = licPremiumListService.saveForPremiumList(licPremApplMappings);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Premium List Generated Successfully"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Premium List Generation UnSuccessful"));
			}
		}catch(Exception e){
			log.info("PremiumListAction save Error : ", e);
		}
	}
	
	public void refresh(){
		renderedPremiumList = false;
		if(licOblApplicationMsts != null){
			licOblApplicationMsts.clear();
		}
		if(selectedList != null){
			selectedList.clear();
		}
		picBranchId = null;
		payMode = null;
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/premiumList.xhtml";
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

	public List<LicOblApplicationMst> getLicOblApplicationMsts() {
		return licOblApplicationMsts;
	}

	public void setLicOblApplicationMsts(
			List<LicOblApplicationMst> licOblApplicationMsts) {
		this.licOblApplicationMsts = licOblApplicationMsts;
	}

	public Boolean getRenderedPremiumList() {
		return renderedPremiumList;
	}

	public void setRenderedPremiumList(Boolean renderedPremiumList) {
		this.renderedPremiumList = renderedPremiumList;
	}

	public List<LicOblApplicationMst> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicOblApplicationMst> selectedList) {
		this.selectedList = selectedList;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
