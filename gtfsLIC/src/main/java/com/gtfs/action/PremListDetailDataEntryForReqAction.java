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

import com.gtfs.bean.LicPremPaymentDtls;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.service.interfaces.LicPaymentDtlsService;
import com.gtfs.service.interfaces.LicPremiumListService;

@Component
@Scope("session")
public class PremListDetailDataEntryForReqAction implements Serializable{
	private Logger log = Logger.getLogger(PremListDetailDataEntryForReqAction.class);
	
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LicPaymentDtlsService licPaymentDtlsService;
	@Autowired
	private LoginAction loginAction;
	private Long premiumListNo;
	private Double totalAmount;
	private Boolean renderedPremiumList;
	
	private List<LicPremiumListDtls> premList = new ArrayList<LicPremiumListDtls>();	
	private List<LicRequirementDtls> licRequirementDtlsList = new ArrayList<LicRequirementDtls>();	
	private List<LicPremPaymentDtls> licPremPaymentDtlses = new ArrayList<LicPremPaymentDtls>();
	
	
	public void save(){
		try{
			Double totalDraftAmount=0.0;
			for(LicPremPaymentDtls obj:licPremPaymentDtlses){
				totalDraftAmount = totalDraftAmount + obj.getSentPayAmount();
			}
			if(!totalAmount.equals(totalDraftAmount)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Total Cheque/DD amount is not equal to Total Premium Amount"));
				return;
			}
			
			LicPremiumListDtls licPremiumListDtls = licPremiumListService.findById(premiumListNo);
			
			for(LicPremPaymentDtls obj:licPremPaymentDtlses ){
				obj.setLicPremiumListDtls(licPremiumListDtls);
			}
			licPremiumListDtls.setLicPremPaymentDtlses(licPremPaymentDtlses);
			
			Boolean status = licPremiumListService.saveForPremiumDetailDataEntryList(licPremiumListDtls);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Premium Detailed Entry Successfully"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Premium Detailed Entry UnSuccessful"));
			}
		}catch(Exception e){
			log.info("PremListDetailDataEntryForReqAction save Error : ", e);
		}
	}

	public void add(){
		Date now = new Date();
		LicPremPaymentDtls licPremPaymentDtls= new LicPremPaymentDtls();
		licPremPaymentDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licPremPaymentDtls.setCreatedDate(now);
		licPremPaymentDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licPremPaymentDtls.setModifiedDate(now);
		licPremPaymentDtls.setDeleteFlag("N");
		licPremPaymentDtlses.add(licPremPaymentDtls);
	}
	
	public void search(){
		try{
			totalAmount = 0.0;
			licRequirementDtlsList = licPremiumListService.findLicRequirementDtlsByPremListNo(premiumListNo, loginAction.getUserList().get(0).getBranchMst());
			renderedPremiumList = true;
			
			for(LicRequirementDtls obj : licRequirementDtlsList){
				totalAmount = totalAmount + obj.getAmount();
			}
			
			/*for(LicRequirementDtls obj : licRequirementDtlsList){
				List<LicPaymentDtls> licPaymentDtlses = licPaymentDtlsService.findLicPaymentDtlsByPayIdAndLicRequirment(obj.getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst(), obj.getId());
				for(LicPaymentDtls ob:licPaymentDtlses){
					if((ob.getPayeeName() == null || ob.getPayeeName().equals("SARADA INSURANCE CONSULTANCY LTD"))){
						totalAmount = totalAmount + ob.getAmount();
					}
					
				}
				obj.getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setLicPaymentDtlses(licPaymentDtlses);
			}*/
		}catch(Exception e){
			log.info("PremListDetailDataEntryForReqAction search Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/premListDetailDataEntryForReq.xhtml";
	}
	
	public void refresh(){
		try{
			if(premList!=null){
				premList.clear();
			}
			
			premList = licPremiumListService.findPremiumListForDetailEntryReq(loginAction.findHubForProcess("POS"));
			if(licRequirementDtlsList !=null){
				licRequirementDtlsList.clear();
			}
			if(licPremPaymentDtlses!=null){
				licPremPaymentDtlses.clear();
			}
			
			renderedPremiumList = false;
			premiumListNo = null;
			totalAmount = null;
		}catch(Exception e){
			log.info("PremListDetailDataEntryForReqAction refresh Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public Long getPremiumListNo() {
		return premiumListNo;
	}

	public void setPremiumListNo(Long premiumListNo) {
		this.premiumListNo = premiumListNo;
	}

	public List<LicPremiumListDtls> getPremList() {
		return premList;
	}

	public void setPremList(List<LicPremiumListDtls> premList) {
		this.premList = premList;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	

	public List<LicPremPaymentDtls> getLicPremPaymentDtlses() {
		return licPremPaymentDtlses;
	}

	public void setLicPremPaymentDtlses(
			List<LicPremPaymentDtls> licPremPaymentDtlses) {
		this.licPremPaymentDtlses = licPremPaymentDtlses;
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

}
