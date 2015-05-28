package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicDocReqMst;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicRequirementDtlsDto;
import com.gtfs.service.interfaces.LicDocReqMstService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.LicRequirementDtlsService;

@Component
@Scope("session")
public class LicRequirementDtlsAction implements Serializable{
	private Logger log = Logger.getLogger(LicRequirementDtlsAction.class);
	
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicRequirementDtlsService licRequirementDtlsService;
	@Autowired
	private LicDocReqMstService licDocReqMstService;
	
	private String applicationNo;
	private Boolean renderedPanel;
	private Double shortageAmt;
	private String reqType;
	private String document;
	private List<LicPolicyMst> licPolicyMsts = new ArrayList<LicPolicyMst>();
	private List<LicRequirementDtlsDto> licRequirementDtlsDtos = new ArrayList<LicRequirementDtlsDto>();
	private List<LicDocReqMst> documentList = new ArrayList<LicDocReqMst>();
	
	@PostConstruct
	public void init(){
		documentList = licDocReqMstService.findAll();
	}
	
	public void refresh(){
		applicationNo=null;
		shortageAmt=null;
		document=null;
		reqType=null;
		renderedPanel=false;
		if(licPolicyMsts !=null){
			licPolicyMsts.clear();
		}
		
		if(licRequirementDtlsDtos != null){
			licRequirementDtlsDtos.clear();
		}
		
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/licRequirementDtls.xhtml";
	}	
	
	public void save(){
		try{
			Date now = new Date();	
			LicHubMst licHubMst =null;
			
			if(loginAction.findHubForProcess("POS").size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Requirement need to be entered in Hub"));
				return;
			}
			
			LicRequirementDtls licRequirementDtls = new LicRequirementDtls();
			licRequirementDtls.setReqType(reqType);
			licRequirementDtls.setDocument(document);
			licRequirementDtls.setAmount(shortageAmt);
			licRequirementDtls.setProcessType("POS");
			
			if(reqType.equals("D")){
				licRequirementDtls.setActionType("CFSL");
				licRequirementDtls.setActionBy(loginAction.getUserList().get(0).getUserid());
				licRequirementDtls.setActionDate(now);
			}
			
			licRequirementDtls.setLicHubMst(loginAction.findHubForProcess("POS").get(0));
			licRequirementDtls.setLicOblApplicationMst(licPolicyMsts.get(0).getLicOblApplicationMst());
			licRequirementDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licRequirementDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licRequirementDtls.setCreatedDate(now);
			licRequirementDtls.setModifiedDate(now);
			licRequirementDtls.setDeleteFlag("N");
			licRequirementDtls.setPrintFlag("N");
			licRequirementDtls.setReceiptNo(licPolicyMsts.get(0).getLicOblApplicationMst().getId()+"/"+new Random().nextInt(100)+"/"+new Date().getTime());
			
			Boolean status = licRequirementDtlsService.saveForRequirementDtls(licRequirementDtls);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Requirement Entry Successful"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Requirement Entry Unsuccessful"));
			}
		}catch(Exception e){
			log.info("LicRequirementDtlsAction save Error : ", e);
		}
	}
	
	public void search(){
		try{
			if(reqType.equals("S")){			
				List<LicRequirementDtls> list = licRequirementDtlsService.findPendingRequirementDtlsByApplicationNoAndReqType(applicationNo, reqType);
				
				if(!(list==null || list.size()==0 || list.contains(null))){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error : ", "Short Premium for Application No. already entered"));
					return;
				}
			}
			licPolicyMsts = licOblApplicationMstService.findApplicationByApplicationNoForReqirement(applicationNo);
			
			if(!(licPolicyMsts == null || licPolicyMsts.size()==0)){
				for(LicRequirementDtls licRequirementDtls:licRequirementDtlsService.findReqForReqEntryByApplNo(applicationNo)){
					LicRequirementDtlsDto licRequirementDtlsDto = new LicRequirementDtlsDto();
					licRequirementDtlsDto.setApplicationNo(applicationNo);
					licRequirementDtlsDto.setShortAmt(licRequirementDtls.getAmount());
					licRequirementDtlsDto.setDocument(licRequirementDtls.getDocument());
					licRequirementDtlsDto.setEntryBy(licRequirementDtls.getCreatedBy());
					licRequirementDtlsDto.setEntryDate(licRequirementDtls.getCreatedDate());
					licRequirementDtlsDtos.add(licRequirementDtlsDto);
				}
				renderedPanel = true;
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Application No. not found"));
			}
		}catch(Exception e){
			log.info("LicRequirementDtlsAction search Error : ", e);
		}
	}

	
	
	/* GETTER SETTER */
	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Boolean getRenderedPanel() {
		return renderedPanel;
	}

	public void setRenderedPanel(Boolean renderedPanel) {
		this.renderedPanel = renderedPanel;
	}

	

	public List<LicPolicyMst> getLicPolicyMsts() {
		return licPolicyMsts;
	}

	public void setLicPolicyMsts(List<LicPolicyMst> licPolicyMsts) {
		this.licPolicyMsts = licPolicyMsts;
	}

	public Double getShortageAmt() {
		return shortageAmt;
	}

	public void setShortageAmt(Double shortageAmt) {
		this.shortageAmt = shortageAmt;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public List<LicRequirementDtlsDto> getLicRequirementDtlsDtos() {
		return licRequirementDtlsDtos;
	}

	public void setLicRequirementDtlsDtos(
			List<LicRequirementDtlsDto> licRequirementDtlsDtos) {
		this.licRequirementDtlsDtos = licRequirementDtlsDtos;
	}

	public List<LicDocReqMst> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<LicDocReqMst> documentList) {
		this.documentList = documentList;
	}
	
}
