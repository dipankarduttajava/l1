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
import com.gtfs.bean.LicBocDetailEntry;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicReqBocMapping;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.service.interfaces.LicBocDetailEntryService;
import com.gtfs.service.interfaces.LicPremiumListService;

@Component
@Scope("session")
public class LicBocDetailEntryForReqAction implements Serializable {
	private Logger log = Logger.getLogger(LicBocDetailEntryForReqAction.class);
	
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LicBocDetailEntryService licBocDetailEntryService;
	@Autowired
	private LoginAction loginAction;
	
	
	private Long premiumListNo;
	private Boolean renderedBOCEntryList;	
	private List<Long> premList = new ArrayList<Long>();	
	private List<LicRequirementDtls> licRequirementDtlsList = new ArrayList<LicRequirementDtls>();
	
	public void refresh(){
		try{
			premiumListNo = null;
			renderedBOCEntryList = false;
			premList = licPremiumListService.findPremiumListNoForReqBocEntry(loginAction.findHubForProcess("POS"));
			
			if(licRequirementDtlsList!=null){
				licRequirementDtlsList.clear();
			}
		}catch(Exception e){
			log.info("LicBocDetailEntryForReqAction refresh Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/bocDetailEntryForReq.xhtml";
	}
	
	public void add(LicRequirementDtls licRequirementDtls){
		Date now = new Date();
		
		List<LicReqBocMapping> list = new ArrayList<LicReqBocMapping>();
		LicBocDetailEntry licBocDetailEntry = new LicBocDetailEntry();
		licBocDetailEntry.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licBocDetailEntry.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licBocDetailEntry.setCreatedDate(now);
		licBocDetailEntry.setModifiedDate(now);
		licBocDetailEntry.setDeleteFlag("N");
		licBocDetailEntry.setBocType("P");
		
		LicReqBocMapping licReqBocMapping = new LicReqBocMapping();
		licReqBocMapping.setLicBocDetailEntry(licBocDetailEntry);
		licReqBocMapping.setLicRequirementDtls(licRequirementDtls);
		licReqBocMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licReqBocMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licReqBocMapping.setCreatedDate(now);
		licReqBocMapping.setModifiedDate(now);
		licReqBocMapping.setDeleteFlag("N");
		
		list.add(licReqBocMapping);
		licBocDetailEntry.setLicReqBocMappings(list);
		
		licRequirementDtls.getLicReqBocMappings().add(licReqBocMapping);
	}	
	
	public void search(){
		try{
			Date now = new Date();
			licRequirementDtlsList = licBocDetailEntryService.findRequirmentDtlsForBocEntry(premiumListNo, loginAction.getUserList().get(0).getBranchMst());
			
			for(LicRequirementDtls obj : licRequirementDtlsList){
				
				List<LicReqBocMapping> list = new ArrayList<LicReqBocMapping>();
				
				LicBocDetailEntry licBocDetailEntry = new LicBocDetailEntry();
				licBocDetailEntry.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licBocDetailEntry.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licBocDetailEntry.setCreatedDate(now);
				licBocDetailEntry.setModifiedDate(now);
				licBocDetailEntry.setDeleteFlag("N");
				licBocDetailEntry.setBocType("O"); //
				
				LicReqBocMapping licReqBocMapping = new LicReqBocMapping();
				licReqBocMapping.setLicBocDetailEntry(licBocDetailEntry);
				licReqBocMapping.setLicRequirementDtls(obj);
				licReqBocMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licReqBocMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licReqBocMapping.setCreatedDate(now);
				licReqBocMapping.setModifiedDate(now);
				licReqBocMapping.setDeleteFlag("N");
				
				list.add(licReqBocMapping);
				licBocDetailEntry.setLicReqBocMappings(list);
				
				obj.setLicReqBocMappings(list);
			}
			renderedBOCEntryList = true;
		}catch(Exception e){
			log.info("LicBocDetailEntryForReqAction search Error : ", e);
		}
	}
	
	public void save(){
		try{
			
			for(LicRequirementDtls obj : licRequirementDtlsList){
				Double shortAmt = obj.getLicOblApplicationMst().getLicProductValueMst().getShortAmount() == null ? 0.0 : obj.getLicOblApplicationMst().getLicProductValueMst().getShortAmount();
				shortAmt = shortAmt + obj.getAmount();
				obj.getLicOblApplicationMst().getLicProductValueMst().setShortAmount(shortAmt);
			}
			
			Boolean status = licBocDetailEntryService.saveBocForReq(licRequirementDtlsList);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "BOC Detailed Entry Successfully"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "BOC Detailed Entry UnSuccessful"));
			}
		}catch(Exception e){
			log.info("LicBocDetailEntryForReqAction save Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public Long getPremiumListNo() {
		return premiumListNo;
	}

	public void setPremiumListNo(Long premiumListNo) {
		this.premiumListNo = premiumListNo;
	}

	public List<Long> getPremList() {
		return premList;
	}

	public void setPremList(List<Long> premList) {
		this.premList = premList;
	}

	public Boolean getRenderedBOCEntryList() {
		return renderedBOCEntryList;
	}

	public void setRenderedBOCEntryList(Boolean renderedBOCEntryList) {
		this.renderedBOCEntryList = renderedBOCEntryList;
	}

	public List<LicRequirementDtls> getLicRequirementDtlsList() {
		return licRequirementDtlsList;
	}

	public void setLicRequirementDtlsList(
			List<LicRequirementDtls> licRequirementDtlsList) {
		this.licRequirementDtlsList = licRequirementDtlsList;
	}
}
