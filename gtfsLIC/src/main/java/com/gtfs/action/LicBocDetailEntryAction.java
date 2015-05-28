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
import com.gtfs.service.interfaces.LicBocDetailEntryService;
import com.gtfs.service.interfaces.LicPremiumListService;

@Component
@Scope("session")
public class LicBocDetailEntryAction implements Serializable{
	private Logger log = Logger.getLogger(LicBocDetailEntryAction.class);
	
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LicBocDetailEntryService licBocDetailEntryService;
	@Autowired
	private LoginAction loginAction;
	
	private Long premiumListNo;
	private Boolean renderedBOCEntryList;
	private Date bocDate;
	
	private List<Long> premList = new ArrayList<Long>();
	private List<LicOblApplicationMst> licOblApplicationMsts = new ArrayList<LicOblApplicationMst>();
	
	
	public void refresh(){
		try{
			premiumListNo = null;
			renderedBOCEntryList = false;
			bocDate = null;
			
			if(premList != null){
				premList.clear();
			}
			
			premList = licPremiumListService.findPremiumListNoForBocEntry(loginAction.findHubForProcess("OBL"));
			
			if(premList == null || premList.size() == 0 || premList.contains(null)){
				return;
			}
			
			if(licOblApplicationMsts != null){
				licOblApplicationMsts.clear();
			}
		}catch(Exception e){
			log.info("LicBocDetailEntryAction refresh Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/bocDetailEntry.xhtml";
	}
	
	public void add(LicOblApplicationMst licOblApplicationMst){
		Date now = new Date();		
		List<LicApplBocMapping> list = new ArrayList<LicApplBocMapping>();	
		
		LicBocDetailEntry licBocDetailEntry = new LicBocDetailEntry();
		licBocDetailEntry.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licBocDetailEntry.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licBocDetailEntry.setCreatedDate(now);
		licBocDetailEntry.setModifiedDate(now);
		licBocDetailEntry.setDeleteFlag("N");
		licBocDetailEntry.setBocType("O");
		
		LicApplBocMapping licApplBocMapping = new LicApplBocMapping();			
		licApplBocMapping.setLicBocDetailEntry(licBocDetailEntry);
		licApplBocMapping.setLicOblApplicationMst(licOblApplicationMst);
		licApplBocMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licApplBocMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licApplBocMapping.setCreatedDate(now);
		licApplBocMapping.setModifiedDate(now);
		licApplBocMapping.setDeleteFlag("N");
		
		list.add(licApplBocMapping);
		licBocDetailEntry.setLicApplBocMappings(list);
		
		licOblApplicationMst.getLicApplBocMappings().add(licApplBocMapping);
	}
	
	
	public void save(){
		try{
			for(LicOblApplicationMst obj:licOblApplicationMsts){
				for(LicApplBocMapping licApplBocMapping : obj.getLicApplBocMappings()){
					licApplBocMapping.getLicBocDetailEntry().setBocDate(bocDate);
				}
			}
			
			Boolean status = licBocDetailEntryService.saveBoc(licOblApplicationMsts);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "BOC Detailed Entry Successfully"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "BOC Detailed Entry UnSuccessful"));
			}
		}catch(Exception e){
			log.info("LicBocDetailEntryAction save Error : ", e);
		}
	}
	
	
	public void search(){
		try{
			Date now = new Date();
			licOblApplicationMsts = licBocDetailEntryService.findApplicationForBocEntry(premiumListNo, loginAction.getUserList().get(0).getBranchMst());
			
			for(LicOblApplicationMst obj : licOblApplicationMsts){
				List<LicApplBocMapping> list = new ArrayList<LicApplBocMapping>();
				LicBocDetailEntry licBocDetailEntry = new LicBocDetailEntry();
				licBocDetailEntry.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licBocDetailEntry.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licBocDetailEntry.setCreatedDate(now);
				licBocDetailEntry.setModifiedDate(now);
				licBocDetailEntry.setDeleteFlag("N");
				licBocDetailEntry.setBocType("O"); //
				
				LicApplBocMapping licApplBocMapping = new LicApplBocMapping();			
				licApplBocMapping.setLicBocDetailEntry(licBocDetailEntry);
				licApplBocMapping.setLicOblApplicationMst(obj);
				licApplBocMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licApplBocMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licApplBocMapping.setCreatedDate(now);
				licApplBocMapping.setModifiedDate(now);
				licApplBocMapping.setDeleteFlag("N");
				
				list.add(licApplBocMapping);
				licBocDetailEntry.setLicApplBocMappings(list);
				
				obj.setLicApplBocMappings(list);
			}
			renderedBOCEntryList = true;
		}catch(Exception e){
			log.info("LicBocDetailEntryAction search Error : ", e);
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

	public List<LicOblApplicationMst> getLicOblApplicationMsts() {
		return licOblApplicationMsts;
	}

	public void setLicOblApplicationMsts(
			List<LicOblApplicationMst> licOblApplicationMsts) {
		this.licOblApplicationMsts = licOblApplicationMsts;
	}

	public Boolean getRenderedBOCEntryList() {
		return renderedBOCEntryList;
	}

	public void setRenderedBOCEntryList(Boolean renderedBOCEntryList) {
		this.renderedBOCEntryList = renderedBOCEntryList;
	}
	public Date getBocDate() {
		return bocDate;
	}
	public void setBocDate(Date bocDate) {
		this.bocDate = bocDate;
	}

}
