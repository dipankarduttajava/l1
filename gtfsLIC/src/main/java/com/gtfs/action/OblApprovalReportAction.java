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

import com.gtfs.bean.LicApplBocMapping;
import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicReqBocMapping;
import com.gtfs.service.interfaces.LicApplBocMappingService;
import com.gtfs.service.interfaces.LicInsuredAddressMappingService;
import com.gtfs.service.interfaces.LicNomineeDtlsService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.LicPolicyMstService;
import com.gtfs.service.interfaces.LicReqBocMappingService;

@Component
@Scope("session")
public class OblApprovalReportAction implements Serializable{
	private Logger log = Logger.getLogger(OblApprovalReportAction.class);
	
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private LicPolicyMstService licPolicyMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicApplBocMappingService licApplBocMappingService;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private LicInsuredAddressMappingService licInsuredAddressMappingService;
	@Autowired
	private LicNomineeDtlsService licNomineeDtlsService;
	@Autowired
	private LicReqBocMappingService licReqBocMappingService;
	
	private String policyNo;
	private Date fromDate;
	private Date toDate;
	private String applicantName;
	private Double premium;
	private Double sumAssured;
	private Long term;
	private String applicationNo;
	private Boolean renderedList;
	private String message;
	private String proposalNo;
	private Date currentDate;
	
	private LicPolicyMst licPolicyMst;
	private List<LicPolicyMst> licPolicyMsts = new ArrayList<LicPolicyMst>();

	
	private LicPolicyMst licPolicyMstDialog;
	private List<LicReqBocMapping> licReqBocMappings = new ArrayList<LicReqBocMapping>();
	
	public void refresh(){
		licPolicyMsts.clear();
		renderedList = false;
		currentDate = new Date();
		proposalNo = null;
	}
	
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/oblApprovalReport.xhtml";
	}
	
	public void onSearchApplStatusReport(){
		try{
			licPolicyMsts.clear();
			
			licPolicyMsts = licOblApplicationMstService.findStatusEntryReport(fromDate, toDate, applicantName, premium, sumAssured, term, applicationNo, policyNo, proposalNo, loginAction.findHubForProcess("OBL"));
			renderedList = true;
						
			if(licPolicyMsts == null || licPolicyMsts.size() == 0 || licPolicyMsts.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
		}catch(Exception e){
			log.info("OblApprovalReportAction search Error : ", e);
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
		
		RequestContext.getCurrentInstance().openDialog("oblApprovalReportDialog");
	}

		

	/* GETTER SETTER */
	public String getPolicyNo() {
		return policyNo;
	}


	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
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


	public Boolean getRenderedList() {
		return renderedList;
	}


	public void setRenderedList(Boolean renderedList) {
		this.renderedList = renderedList;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getProposalNo() {
		return proposalNo;
	}


	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}


	public Date getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}


	public LicPolicyMst getLicPolicyMst() {
		return licPolicyMst;
	}


	public void setLicPolicyMst(LicPolicyMst licPolicyMst) {
		this.licPolicyMst = licPolicyMst;
	}


	public List<LicPolicyMst> getLicPolicyMsts() {
		return licPolicyMsts;
	}


	public void setLicPolicyMsts(List<LicPolicyMst> licPolicyMsts) {
		this.licPolicyMsts = licPolicyMsts;
	}


	public LicPolicyMst getLicPolicyMstDialog() {
		return licPolicyMstDialog;
	}


	public void setLicPolicyMstDialog(LicPolicyMst licPolicyMstDialog) {
		this.licPolicyMstDialog = licPolicyMstDialog;
	}


	public List<LicReqBocMapping> getLicReqBocMappings() {
		return licReqBocMappings;
	}


	public void setLicReqBocMappings(List<LicReqBocMapping> licReqBocMappings) {
		this.licReqBocMappings = licReqBocMappings;
	}
	
}