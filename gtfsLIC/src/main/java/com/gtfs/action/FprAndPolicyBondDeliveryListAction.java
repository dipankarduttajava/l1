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
import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.AgentRlnsService;
import com.gtfs.service.interfaces.LicApplBocMappingService;
import com.gtfs.service.interfaces.LicInsuredAddressMappingService;
import com.gtfs.service.interfaces.LicNomineeDtlsService;
import com.gtfs.service.interfaces.LicPolicyMstService;
import com.gtfs.service.interfaces.LicReqBocMappingService;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

@Component
@Scope("session")
public class FprAndPolicyBondDeliveryListAction implements Serializable {
	private Logger log = Logger.getLogger(FprAndPolicyBondDeliveryListAction.class);
	
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
	@Autowired
	private AgentRlnsService agentRlnsService;
	
	private Date fromDate;
	private Date toDate;
	private String applicantName;
	private Double premium;
	private Double sumAssured;
	private Long term;
	private String applicationNo;
	private String policyNo;
	private String proposalNo;
	private Double agentRank;
	
	private List<LicPolicyMst> licPolicyMsts = new ArrayList<LicPolicyMst>();
	private List<LicPolicyMst> selectedLicPolicyMsts = new ArrayList<LicPolicyMst>();
	private List<LicReqBocMapping> licReqBocMappings = new ArrayList<LicReqBocMapping>();
	
	private LicPolicyMst licPolicyMst;	
	private LicPolicyMst licPolicyMstDialog;
	
	
	public void refresh(){
		if(licPolicyMsts!=null){
			licPolicyMsts.clear();
		}
		if(selectedLicPolicyMsts!=null){
			selectedLicPolicyMsts.clear();
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/fprAndPolicyBondDeliveryList.xhtml";
	}
	

	public void search(){
		try{
			if(licPolicyMsts!=null){
				licPolicyMsts.clear();
			}
			if(selectedLicPolicyMsts != null){
				selectedLicPolicyMsts.clear();	
			}
			
			licPolicyMsts = licPolicyMstService.findApplicationForFprAndPolicyBondDelivery(fromDate, toDate, applicantName, premium, sumAssured, term, applicationNo, policyNo, proposalNo, loginAction.findHubForProcess("OBL"));			
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
		
		RequestContext.getCurrentInstance().openDialog("fprAndPolicyBondDeliveryListDialog");
	}
	
	
	public String printPage(LicPolicyMst licPolicyMst){
		try{
			this.licPolicyMst = licPolicyMst;
			
			List<AgentRlns> list = agentRlnsService.findValidAgentByPhase(licPolicyMst.getLicOblApplicationMst().getAgentMst().getPhaseId(), licPolicyMst.getLicOblApplicationMst().getAgentMst().getAgCode());
			
			if(list == null || list.size() == 0 || list.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error: ", "Agent Not Valid"));
				return null;
			}
			
			agentRank = list.get(0).getRankMst().getRankId();
			
			return "/licHubActivity/fprAndPolicyBondDeliveryListPrintReceipt.xhtml?faces-redirect=true";
		}catch(Exception e){
			log.info("LicPrintReceiptAction printPage Error : ", e);
			return null;
		}
	}


	
	/* GETTER SETTER */	
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

	public Double getAgentRank() {
		return agentRank;
	}

	public void setAgentRank(Double agentRank) {
		this.agentRank = agentRank;
	}
}
