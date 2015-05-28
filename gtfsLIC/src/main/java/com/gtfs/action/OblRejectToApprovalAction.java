package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.AgentRlns;
import com.gtfs.bean.GenericBusinessTxn;
import com.gtfs.bean.LicApplBocMapping;
import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicReqBocMapping;
import com.gtfs.service.interfaces.LicApplBocMappingService;
import com.gtfs.service.interfaces.LicInsuredAddressMappingService;
import com.gtfs.service.interfaces.LicNomineeDtlsService;
import com.gtfs.service.interfaces.LicPolicyMstService;
import com.gtfs.service.interfaces.LicReqBocMappingService;

@Component
@Scope("session")
public class OblRejectToApprovalAction implements Serializable {
	private Logger log = Logger.getLogger(OblRejectToApprovalAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyMstService licPolicyMstService;
	@Autowired
	private LicReqBocMappingService licReqBocMappingService;
	@Autowired
	private LicApplBocMappingService licApplBocMappingService;
	@Autowired
	private LicInsuredAddressMappingService licInsuredAddressMappingService;
	@Autowired
	private LicNomineeDtlsService licNomineeDtlsService;
	
	private Boolean renderedList;
	private Boolean renderedRejectedList;
	private Date fromDate;
	private Date toDate;
	private String applicantName;
	private Double premium;
	private Double sumAssured;
	private Long term;
	private String applicationNo;
	private String policyNo;
	private String proposalNo;
	private List<LicPolicyMst> licPolicyMsts = new ArrayList<LicPolicyMst>();
	private List<LicPolicyMst> selectedLicPolicyMsts = new ArrayList<LicPolicyMst>();
	private List<LicReqBocMapping> licReqBocMappings = new ArrayList<LicReqBocMapping>();
	
	private LicPolicyMst licPolicyMst;
	
	private LicPolicyMst licPolicyMstDialog;
	
	
	
	
	public void save(){
		try{
			Date now = new Date();
			
			licPolicyMst.setAcceptDate(null);
			licPolicyMst.setDeleteFlag("N");
			licPolicyMst.setModalPrem(null);
			licPolicyMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPolicyMst.setModifiedDate(now);
			licPolicyMst.setPolicyNo(null);
			licPolicyMst.setPolicyStatus(null);
			licPolicyMst.setProposalNo(null);
			licPolicyMst.setRiskStartDate(null);
			licPolicyMst.setBondFlag("N");
			licPolicyMst.setDispatchDate(null);
			licPolicyMst.setDispatchNo(null);
			licPolicyMst.setFprFlag("N");
			licPolicyMst.setRemarks(null);
			licPolicyMst.setSentHub(null);
			licPolicyMst.setPicBranchMst(null);
			licPolicyMst.setAgentMst(null);
			
			licPolicyMst.getLicOblApplicationMst().setDeleteFlag("N");
			licPolicyMst.getLicOblApplicationMst().setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPolicyMst.getLicOblApplicationMst().setModifiedDate(now);
			
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setDeleteFlag("N");
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setModifiedDate(now);
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransStatus("D");
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransDate(now);
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransferFlag("N");
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransferFlag(null);
			
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setDeleteFlag("N");
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setModifiedDate(now);
			
			
			
			Boolean status = licPolicyMstService.update(licPolicyMst);
					
			if (status) {
				refresh();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Success : ", "Policy Updatation Successful"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error : ", "Policy Updatation Unsuccessful"));
			}
					
			
		}catch(Exception e){
			log.info("OblApprovalAction goToWelcomePage Error : ", e);
		}
	}
	
	
	
	
	
	
	
	public void search(){
		try{
			if(licPolicyMsts!=null){
				licPolicyMsts.clear();
			}
			if(selectedLicPolicyMsts != null){
				selectedLicPolicyMsts.clear();	
			}
			
			licPolicyMsts = licPolicyMstService.findApplicationForRejectedEntry(fromDate, toDate, applicantName, premium, sumAssured, term, applicationNo, policyNo, proposalNo, loginAction.findHubForProcess("OBL"));
			renderedList = true;
			
		}catch(Exception e){
			log.info("OblApprovalAction search Error : ", e);
		}
		
	}
	
	
	public void showDetail(LicPolicyMst licPolicyMst){
		licReqBocMappings = licReqBocMappingService.findReqBocMappingByApplication(licPolicyMst.getLicOblApplicationMst().getId());
		
		List<LicApplBocMapping> licApplBocMappings = licApplBocMappingService.findBocMappingByApplication(licPolicyMst.getLicOblApplicationMst().getId());
		List<LicInsuredAddressMapping> licInsuredAddressMappings = licInsuredAddressMappingService.findAddressDtlsByInsuredDtls(licPolicyMst.getLicOblApplicationMst().getLicInsuredDtls());
		List<LicNomineeDtls> LicNomineeDtlses = licNomineeDtlsService.findNomineeDtlsByApplication(licPolicyMst.getLicOblApplicationMst());
		
		licPolicyMst.getLicOblApplicationMst().setLicApplBocMappings(licApplBocMappings);
		licPolicyMst.getLicOblApplicationMst().getLicInsuredDtls().setLicInsuredAddressMappings(licInsuredAddressMappings);
		licPolicyMst.getLicOblApplicationMst().setLicNomineeDtlses(LicNomineeDtlses);
		licPolicyMstDialog = licPolicyMst;
		
		RequestContext.getCurrentInstance().openDialog("oblRejectToApprovalDialog");
	}
	
	
	public void select(LicPolicyMst licPolicyMst){
		try{
			selectedLicPolicyMsts.add(licPolicyMst);
			this.licPolicyMst = licPolicyMst;
			renderedList = false;
			renderedRejectedList = true;
		}catch(Exception e){
			log.info("OblApprovalAction select Error : ", e);
		}
	}
	
	
	
	
	public void refresh(){
		if(licPolicyMsts!=null){
			licPolicyMsts.clear();
		}
		if(selectedLicPolicyMsts!=null){
			selectedLicPolicyMsts.clear();
		}	
		renderedList = false;
		renderedRejectedList = false;
	}

	
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/oblRejectToApproval.xhtml";
	}

	public Boolean getRenderedRejectedList() {
		return renderedRejectedList;
	}

	public void setRenderedRejectedList(Boolean renderedRejectedList) {
		this.renderedRejectedList = renderedRejectedList;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}

	public Long getTerm() {
		return term;
	}

	public void setTerm(Long term) {
		this.term = term;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public Boolean getRenderedList() {
		return renderedList;
	}

	public void setRenderedList(Boolean renderedList) {
		this.renderedList = renderedList;
	}

	public List<LicPolicyMst> getLicPolicyMsts() {
		return licPolicyMsts;
	}

	public void setLicPolicyMsts(List<LicPolicyMst> licPolicyMsts) {
		this.licPolicyMsts = licPolicyMsts;
	}
	public List<LicPolicyMst> getSelectedLicPolicyMsts() {
		return selectedLicPolicyMsts;
	}
	public void setSelectedLicPolicyMsts(List<LicPolicyMst> selectedLicPolicyMsts) {
		this.selectedLicPolicyMsts = selectedLicPolicyMsts;
	}


	public List<LicReqBocMapping> getLicReqBocMappings() {
		return licReqBocMappings;
	}


	public void setLicReqBocMappings(List<LicReqBocMapping> licReqBocMappings) {
		this.licReqBocMappings = licReqBocMappings;
	}


	public LicPolicyMst getLicPolicyMstDialog() {
		return licPolicyMstDialog;
	}


	public void setLicPolicyMstDialog(LicPolicyMst licPolicyMstDialog) {
		this.licPolicyMstDialog = licPolicyMstDialog;
	}
	public LicPolicyMst getLicPolicyMst() {
		return licPolicyMst;
	}

	public void setLicPolicyMst(LicPolicyMst licPolicyMst) {
		this.licPolicyMst = licPolicyMst;
	}
	
	
	
}
