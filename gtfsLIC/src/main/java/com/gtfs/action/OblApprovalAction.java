package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicReqBocMapping;
import com.gtfs.service.interfaces.AgentRlnsService;
import com.gtfs.service.interfaces.LicApplBocMappingService;
import com.gtfs.service.interfaces.LicInsuredAddressMappingService;
import com.gtfs.service.interfaces.LicNomineeDtlsService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.LicPolicyMstService;
import com.gtfs.service.interfaces.LicReqBocMappingService;

@Component
@Scope("session")
public class OblApprovalAction implements Serializable{
	private Logger log = Logger.getLogger(OblApprovalAction.class);
	
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
	@Autowired
	private AgentRlnsService agentRlnsService;
	
	private String policyNo;
	private Date fromDate;
	private Date toDate;
	private String applicantName;
	private Double premium;
	private Double sumAssured;
	private Long term;
	private String applicationNo;
	private Boolean renderedList;
	private Boolean statusUpdate;
	private Boolean fprUpdate;
	private Boolean policyBondUpdate;
	private Boolean renderedApprovalList;
	private LicPolicyMst licPolicyMst;
	private Date currentDate;
	private LicOblApplicationMst licOblApplicationMst;	
	private List<LicPolicyMst> licPolicyMsts = new ArrayList<LicPolicyMst>();
	private List<LicPolicyMst> approvalLicPolicyMsts = new ArrayList<LicPolicyMst>();
	private String message;
	private String proposalNo;
	
	private LicPolicyMst licPolicyMstDialog;
	private List<LicReqBocMapping> licReqBocMappings = new ArrayList<LicReqBocMapping>();
	
	
	public void openDialog(){
		for(LicPolicyMst obj : approvalLicPolicyMsts){
			Double totalPrem = obj.getLicOblApplicationMst().getLicProductValueMst().getBasicPrem() + obj.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem() + obj.getLicOblApplicationMst().getLicProductValueMst().getShortAmount();
			if(licPolicyMst.getModalPrem() != null){
				if(!(licPolicyMst.getModalPrem().equals(totalPrem))){
					message = "Total Premium is different from Model Premium, Do you want to Continue..";
				}
			}
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
		
		RequestContext.getCurrentInstance().openDialog("oblApprovalDialog");
	}
	
	
	public void save(){
		try{
			Date now = new Date();
			if (licPolicyMst.getPolicyStatus().equals("A")) {
				
				LicPolicyMst selectPolicyMst = licPolicyMstService.findById(licPolicyMst.getId());
				
				if(!(selectPolicyMst.getPolicyStatus() == null || selectPolicyMst.getPolicyStatus().equals(""))){
					if(selectPolicyMst.getPolicyStatus().equals("A")){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error : ", "Policy Already Approved."));
						return;
					}
				}
				
				List<LicPolicyMst> policyCheck = licPolicyMstService.checkPolicyNo(licPolicyMst.getPolicyNo());
				
				if(!(policyCheck == null || policyCheck.size() == 0 || policyCheck.contains(null))){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error : ", "Policy Number Already Exists, Please enter Different Policy Number"));
					return;
				}
				
				List<AgentRlns> agentRlns = agentRlnsService.findValidAgentByPhase(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getPhaseMst().getPhaseId(), licPolicyMst.getLicOblApplicationMst().getAgentMst().getAgCode());
				
				if(agentRlns== null || agentRlns.size() == 0){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error : ", "Trainee is not valid"));
					return;
				}
				
				licPolicyMst = calculatePolicy(licPolicyMst);
				licPolicyMst.setPicBranchMst(licPolicyMst.getLicOblApplicationMst().getPicBranchMstId());
				licPolicyMst.setSentHub(licPolicyMst.getLicOblApplicationMst().getOblHubMst());
				licPolicyMst.setAgentMst(licOblApplicationMst.getAgentMst());
				licPolicyMst.setLastPaidDueDate(licPolicyMst.getRiskStartDate());							
				
				// GenericBusinessTxn 
				GenericBusinessTxn genericBusinessTxn = new GenericBusinessTxn();
				genericBusinessTxn.setPhaseMst(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getPhaseMst());
				genericBusinessTxn.setAgentMst(licPolicyMst.getLicOblApplicationMst().getAgentMst());
				genericBusinessTxn.setBusinessValue(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getBusinessValue());
				genericBusinessTxn.setTransStatus(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getTransStatus());
				genericBusinessTxn.setTransDate(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getTransDate());
				genericBusinessTxn.setTableName("LIC_OBL_APPLICATION_MST");
				genericBusinessTxn.setTableId(licPolicyMst.getLicOblApplicationMst().getId());
				genericBusinessTxn.setProcessName("OBL");
				genericBusinessTxn.setSchemeId(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getSchemeMst());
				genericBusinessTxn.setAgentRank(agentRlns.get(0).getRankMst().getRankId());
				genericBusinessTxn.setProcessFlag("N");
				genericBusinessTxn.setName(licPolicyMst.getLicOblApplicationMst().getLicInsuredDtls().getName());
				genericBusinessTxn.setBranchMst(licPolicyMst.getLicOblApplicationMst().getBranchMst());
				genericBusinessTxn.setBusinessDate(licPolicyMst.getLicOblApplicationMst().getBusinessDate());
				genericBusinessTxn.setTieupCompyMst(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTieupCompyMst());
				genericBusinessTxn.setCollBenPct(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getCollBenPct());
				genericBusinessTxn.setModalPremium(licPolicyMst.getModalPrem());
				genericBusinessTxn.setBasicPremium(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getBasicPrem());
				genericBusinessTxn.setShortPremium(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getShortAmount());
				genericBusinessTxn.setServiceTax(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem());
				genericBusinessTxn.setTotalAmount(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTotalAmt());
				genericBusinessTxn.setPolicyTerm(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPolicyTerm());
				genericBusinessTxn.setPremiumPayingTerm(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPremiumPayingTerm());
				genericBusinessTxn.setSumAssured(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getSumAssured());
				genericBusinessTxn.setPayNature(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPayNature());
				genericBusinessTxn.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				genericBusinessTxn.setCreatedDate(now);
				genericBusinessTxn.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				genericBusinessTxn.setModifiedDate(now);
				genericBusinessTxn.setDeleteFlag("N");
				// GenericBusinessTxn
				
				
				Boolean status = licPolicyMstService.updateForStatusEntry(licPolicyMst, genericBusinessTxn);
				
				if (status) {
					refresh();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Success : ", "Policy Updatation Successful"));
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error : ", "Policy Updatation Unsuccessful"));
				}
			}

			if(licPolicyMst.getPolicyStatus().equals("R")){
				
				licPolicyMst.setDeleteFlag("Y");
				licPolicyMst.setDeletedBy(loginAction.getUserList().get(0).getUserid());
				licPolicyMst.setDeletedDate(now);
				
				licPolicyMst.getLicOblApplicationMst().setDeleteFlag("Y");
				licPolicyMst.getLicOblApplicationMst().setDeletedBy(loginAction.getUserList().get(0).getUserid());
				licPolicyMst.getLicOblApplicationMst().setDeletedDate(now);
				
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setDeleteFlag("Y");
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setDeletedBy(loginAction.getUserList().get(0).getUserid());
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setDeletedDate(now);
				
				//business txn update
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setDeleteFlag("Y");
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setDeletedBy(loginAction.getUserList().get(0).getUserid());
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setDeletedDate(now);
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransStatus("R");
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransDate(now);
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransferFlag("Y");
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransferDate(now);
				//business txn update
				
				
				List<LicPolicyDtls> policyList = licPolicyDtlsService.findPolicyDtlsByPolicyMst(licPolicyMst.getId());
				
				int count = 0;
				
				if (!(policyList == null || policyList.size() == 0 || policyList.contains(null))) {
					for (LicPolicyDtls obj : policyList) {
						
						if(obj.getPaidFlag().equals("Y")){
							count = count+1;
						}
						
						obj.setDeleteFlag("Y");
						obj.setDeletedDate(now);
						obj.setDeletedBy(loginAction.getUserList().get(0).getUserid());
					}
					licPolicyMst.setLicPolicyDtlses(policyList);
				}
				
				
				if(count>1){ // for multiple payment dtls
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error : ", "Policy Can not be Rejected."));
					return;
				}
								
				LicPolicyMst selectPolicyMst = licPolicyMstService.findById(licPolicyMst.getId());
				
				if(selectPolicyMst.getPolicyStatus() == null || selectPolicyMst.getPolicyStatus().equals("Q")){
					Boolean status = licPolicyMstService.update(licPolicyMst);					
					if (status) {
						refresh();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Success : ", "Policy Updatation Successful"));
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Error : ", "Policy Updatation Unsuccessful"));
					}
				}else if(selectPolicyMst.getPolicyStatus().equals("A")){
					
					List<AgentRlns> agentRlns = agentRlnsService.findValidAgentByPhase(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getPhaseMst().getPhaseId(), licPolicyMst.getLicOblApplicationMst().getAgentMst().getAgCode());
					
					if(agentRlns== null || agentRlns.size() == 0){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
			                    "Error : ", "Trainee is not valid"));
						return;
					}
					
					// GenericBusinessTxn					
					GenericBusinessTxn genericBusinessTxn = new GenericBusinessTxn();
					genericBusinessTxn.setPhaseMst(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getPhaseMst());
					genericBusinessTxn.setAgentMst(licPolicyMst.getLicOblApplicationMst().getAgentMst());
					genericBusinessTxn.setBusinessValue(-(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getBusinessValue()));
					genericBusinessTxn.setTransStatus(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getTransStatus());
					genericBusinessTxn.setTransDate(licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().getTransDate());
					genericBusinessTxn.setTableName("LIC_OBL_APPLICATION_MST");
					genericBusinessTxn.setTableId(licPolicyMst.getLicOblApplicationMst().getId());
					genericBusinessTxn.setProcessName("OBL");
					genericBusinessTxn.setSchemeId(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getSchemeMst());
					genericBusinessTxn.setAgentRank(agentRlns.get(0).getRankMst().getRankId());
					genericBusinessTxn.setProcessFlag("N");
					genericBusinessTxn.setName(licPolicyMst.getLicOblApplicationMst().getLicInsuredDtls().getName());
					genericBusinessTxn.setBranchMst(licPolicyMst.getLicOblApplicationMst().getBranchMst());
					genericBusinessTxn.setBusinessDate(licPolicyMst.getLicOblApplicationMst().getBusinessDate());
					genericBusinessTxn.setTieupCompyMst(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTieupCompyMst());
					genericBusinessTxn.setCollBenPct(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getCollBenPct());
					genericBusinessTxn.setModalPremium(licPolicyMst.getModalPrem());
					genericBusinessTxn.setBasicPremium(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getBasicPrem());
					genericBusinessTxn.setShortPremium(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getShortAmount());
					genericBusinessTxn.setServiceTax(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem());
					genericBusinessTxn.setTotalAmount(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTotalAmt());
					genericBusinessTxn.setPolicyTerm(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPolicyTerm());
					genericBusinessTxn.setPremiumPayingTerm(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPremiumPayingTerm());
					genericBusinessTxn.setSumAssured(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getSumAssured());
					genericBusinessTxn.setPayNature(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPayNature());
					genericBusinessTxn.setCreatedBy(loginAction.getUserList().get(0).getUserid());
					genericBusinessTxn.setCreatedDate(now);
					genericBusinessTxn.setModifiedBy(loginAction.getUserList().get(0).getUserid());
					genericBusinessTxn.setModifiedDate(now);
					genericBusinessTxn.setDeleteFlag("N");
					// GenericBusinessTxn
					
					Boolean status = licPolicyMstService.updateForStatusEntry(licPolicyMst,genericBusinessTxn);
					
					if (status) {
						refresh();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Success : ", "Policy Updatation Successful"));
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Error : ", "Policy Updatation Unsuccessful"));
					}
				}else if(selectPolicyMst.getPolicyStatus().equals("R")){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error : ", "Policy Already Rejected"));
					return;
				}
			}
		}catch(Exception e){
			log.info("OblApprovalAction goToWelcomePage Error : ", e);
		}
	}
	
	
	public LicPolicyMst calculatePolicy(LicPolicyMst licPolicyMst){
		try{
			Date now = new Date();
			
			if(licPolicyMst.getPolicyStatus().equals("A")){				
				List<LicPolicyDtls> licPolicyDtlses = new ArrayList<LicPolicyDtls>();
				
				Long term = licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPremiumPayingTerm();
				String payNature = licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPayNature();
				LicHubMst licHubMst = licPolicyMst.getLicOblApplicationMst().getOblHubMst();
				
				licPolicyMst.setSentHub(licHubMst);
				
				//business txn update				
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setAcceptedFlag("Y");
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setAcceptedOn(now);
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransStatus("A");
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransDate(now);
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransferFlag("Y");
				licPolicyMst.getLicOblApplicationMst().getLicBusinessTxn().setTransferDate(now);
				
				
				//business txn update
				Date riskDate = licPolicyMst.getRiskStartDate();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(riskDate);
				
				if(payNature.equals("Y")){
					for(int i=0; i<term; i++){
						LicPolicyDtls licPolicyDtls = new LicPolicyDtls();
						licPolicyDtls.setLicPolicyMst(licPolicyMst);
						licPolicyDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
						licPolicyDtls.setCreatedDate(now);
						licPolicyDtls.setDeleteFlag("N");
						licPolicyDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
						licPolicyDtls.setModifiedDate(now);
						licPolicyDtls.setDueDate(calendar.getTime());
						licPolicyDtls.setRenewalMonth(12l * i);
						licPolicyDtls.setMigrationFlag("N");
						
						if(licPolicyDtls.getDueDate().equals(licPolicyMst.getRiskStartDate())){
							licPolicyDtls.setPaidFlag("Y");
							licPolicyDtls.setRenewalType("NORMAL");
							licPolicyDtls.setPayDate(licPolicyMst.getRiskStartDate());
							licPolicyDtls.setOblHubMst(licHubMst);
							licPolicyDtls.setProcessHubMst(licHubMst);
							licPolicyDtls.setOblPicBranchMst(licPolicyMst.getLicOblApplicationMst().getPicBranchMstId());
							licPolicyDtls.setProcessPicBranchMst(licPolicyMst.getLicOblApplicationMst().getPicBranchMstId());
							licPolicyDtls.setPremAmt(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTotalAmt());
							licPolicyDtls.setServiceTax(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem());
							licPolicyDtls.setBranchMst(loginAction.getUserList().get(0).getBranchMst());
							licPolicyDtls.setAgentMst(licOblApplicationMst.getAgentMst());

						}else{
							licPolicyDtls.setPaidFlag("N");
						}
						
						licPolicyDtlses.add(licPolicyDtls);
						calendar.add(Calendar.YEAR, 1);
					}
				}else if(payNature.equals("H")){
					for(int i=0; i<(term * 2) ; i++){
						LicPolicyDtls licPolicyDtls = new LicPolicyDtls();
						licPolicyDtls.setLicPolicyMst(licPolicyMst);
						licPolicyDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
						licPolicyDtls.setCreatedDate(now);
						licPolicyDtls.setDeleteFlag("N");
						licPolicyDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
						licPolicyDtls.setModifiedDate(now);
						licPolicyDtls.setDueDate(calendar.getTime());
						licPolicyDtls.setRenewalMonth(6l*i);
						licPolicyDtls.setMigrationFlag("N");
						
						if(licPolicyDtls.getDueDate().equals(licPolicyMst.getRiskStartDate())){
							licPolicyDtls.setPaidFlag("Y");
							licPolicyDtls.setPayDate(licPolicyMst.getRiskStartDate());
							licPolicyDtls.setOblHubMst(licHubMst);
							licPolicyDtls.setOblHubMst(licPolicyMst.getLicOblApplicationMst().getOblHubMst());
							licPolicyDtls.setPremAmt(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTotalAmt());
							licPolicyDtls.setPremAmt(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem());
							licPolicyDtls.setAgentMst(licOblApplicationMst.getAgentMst());

						}else{
							licPolicyDtls.setPaidFlag("N");
						}
						
						licPolicyDtlses.add(licPolicyDtls);
						calendar.add(Calendar.MONTH, 6);
					}				
				}else if(payNature.equals("Q")){
					for(int i=0; i<(term * 4) ; i++){
						LicPolicyDtls licPolicyDtls = new LicPolicyDtls();
						licPolicyDtls.setLicPolicyMst(licPolicyMst);
						licPolicyDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
						licPolicyDtls.setCreatedDate(now);
						licPolicyDtls.setDeleteFlag("N");
						licPolicyDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
						licPolicyDtls.setModifiedDate(now);
						licPolicyDtls.setDueDate(calendar.getTime());
						licPolicyDtls.setRenewalMonth(3l*i);
						licPolicyDtls.setMigrationFlag("N");
						
						if(licPolicyDtls.getDueDate().equals(licPolicyMst.getRiskStartDate())){
							licPolicyDtls.setPaidFlag("Y");
							licPolicyDtls.setPayDate(licPolicyMst.getRiskStartDate());
							licPolicyDtls.setOblHubMst(licHubMst);
							licPolicyDtls.setOblHubMst(licPolicyMst.getLicOblApplicationMst().getOblHubMst());
							licPolicyDtls.setPremAmt(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTotalAmt());
							licPolicyDtls.setPremAmt(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem());
							licPolicyDtls.setAgentMst(licOblApplicationMst.getAgentMst());

						}else{
							licPolicyDtls.setPaidFlag("N");
						}
						
						licPolicyDtlses.add(licPolicyDtls);
						calendar.add(Calendar.MONTH, 4);
					}	
					
				}else if(payNature.equals("M")){				
					for(int i=0; i<(term * 12) ; i++){
						LicPolicyDtls licPolicyDtls = new LicPolicyDtls();
						licPolicyDtls.setLicPolicyMst(licPolicyMst);
						licPolicyDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
						licPolicyDtls.setCreatedDate(now);
						licPolicyDtls.setDeleteFlag("N");
						licPolicyDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
						licPolicyDtls.setModifiedDate(now);
						licPolicyDtls.setDueDate(calendar.getTime());
						licPolicyDtls.setRenewalMonth(1l*i);
						licPolicyDtls.setMigrationFlag("N");
						
						if(licPolicyDtls.getDueDate().equals(licPolicyMst.getRiskStartDate())){
							licPolicyDtls.setPaidFlag("Y");
							licPolicyDtls.setPayDate(licPolicyMst.getRiskStartDate());
							licPolicyDtls.setOblHubMst(licHubMst);
							licPolicyDtls.setOblHubMst(licPolicyMst.getLicOblApplicationMst().getOblHubMst());
							licPolicyDtls.setPremAmt(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTotalAmt());
							licPolicyDtls.setPremAmt(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem());
							licPolicyDtls.setAgentMst(licOblApplicationMst.getAgentMst());

						}else{
							licPolicyDtls.setPaidFlag("N");
						}
						
						licPolicyDtlses.add(licPolicyDtls);
						calendar.add(Calendar.MONTH, 1);
					}
				}
				licPolicyMst.setLicPolicyDtlses(licPolicyDtlses);
			}
			
			return licPolicyMst;
		}catch(Exception e){
			log.info("OblApprovalAction calculate Error : ", e);
			return null;
		}
	}
	
	public void search(){
		try{
			licPolicyMsts.clear();
			approvalLicPolicyMsts.clear();
						
			if(statusUpdate || fprUpdate || policyBondUpdate){
				licPolicyMsts = licOblApplicationMstService.findApplicationForStatusEntry(fromDate, toDate, applicantName, premium, sumAssured, term, applicationNo, policyNo, proposalNo, loginAction.findHubForProcess("OBL"));
				renderedList = true;
				renderedApprovalList = false;
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please select at least one checkbox"));
				return;
			}
		}catch(Exception e){
			log.info("OblApprovalAction search Error : ", e);
		}
		
	}
	
	public void select(LicPolicyMst licPolicyMst){
		try{
			approvalLicPolicyMsts.add(licPolicyMst);		
			licOblApplicationMst = licPolicyMstService.findPolicyDtls(licPolicyMst.getLicOblApplicationMst().getId());
			
			if(policyBondUpdate != true){
				if(licPolicyMst.getBondFlag().equals("N")){
					licPolicyMst.setBondFlag(licPolicyMst.getBondFlag());
				}else{
					licPolicyMst.setBondFlag("Y");
				}
			}else{
				licPolicyMst.setBondFlag("Y");
			}
			
			if(fprUpdate != true){
				if(licPolicyMst.getFprFlag().equals("N")){
					licPolicyMst.setFprFlag(licPolicyMst.getFprFlag());
				}else{
					licPolicyMst.setFprFlag("Y");
				}
			}else{
				licPolicyMst.setFprFlag("Y");
			}
			
			this.licPolicyMst = licPolicyMst;
			renderedList = false;
			renderedApprovalList = true;
		}catch(Exception e){
			log.info("OblApprovalAction select Error : ", e);
		}
	}
	
	public void refresh(){
		if(licPolicyMsts!=null){
			licPolicyMsts.clear();
		}
		if(approvalLicPolicyMsts!=null){
			approvalLicPolicyMsts.clear();
		}	
		renderedList = false;
		renderedApprovalList = false;
		currentDate = new Date();
		statusUpdate = false;
		fprUpdate = false;
		policyBondUpdate = false;
		proposalNo = null;
	}
	
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/oblApproval.xhtml";
	}
	
	public void fprChange(){
		if(fprUpdate == true){
			statusUpdate = true;
		}else{
			policyBondUpdate =false;
			statusUpdate = false;
		}
	}
	
	
	public void policyBondChange(){
		if(policyBondUpdate == true){
			fprUpdate = true;
			statusUpdate = true;
		}else{
			statusUpdate = false;
		}
	}
	
	public void statusChange(){
		if(statusUpdate == true){
			// do nothing
		}else{
			policyBondUpdate = false;
			fprUpdate = false;
		}
	}
	

	/* GETTER SETTER*/
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

	

	public List<LicPolicyMst> getLicPolicyMsts() {
		return licPolicyMsts;
	}

	public void setLicPolicyMsts(List<LicPolicyMst> licPolicyMsts) {
		this.licPolicyMsts = licPolicyMsts;
	}

	public Boolean getRenderedList() {
		return renderedList;
	}

	public void setRenderedList(Boolean renderedList) {
		this.renderedList = renderedList;
	}

	public Boolean getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Boolean statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

	public Boolean getFprUpdate() {
		return fprUpdate;
	}

	public void setFprUpdate(Boolean fprUpdate) {
		this.fprUpdate = fprUpdate;
	}

	public Boolean getPolicyBondUpdate() {
		return policyBondUpdate;
	}

	public void setPolicyBondUpdate(Boolean policyBondUpdate) {
		this.policyBondUpdate = policyBondUpdate;
	}

	public List<LicPolicyMst> getApprovalLicPolicyMsts() {
		return approvalLicPolicyMsts;
	}

	public void setApprovalLicPolicyMsts(List<LicPolicyMst> approvalLicPolicyMsts) {
		this.approvalLicPolicyMsts = approvalLicPolicyMsts;
	}

	public Boolean getRenderedApprovalList() {
		return renderedApprovalList;
	}

	public void setRenderedApprovalList(Boolean renderedApprovalList) {
		this.renderedApprovalList = renderedApprovalList;
	}

	public LicPolicyMst getLicPolicyMst() {
		return licPolicyMst;
	}

	public void setLicPolicyMst(LicPolicyMst licPolicyMst) {
		this.licPolicyMst = licPolicyMst;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LicPolicyMst getLicPolicyMstDialog() {
		return licPolicyMstDialog;
	}
	public void setLicPolicyMstDialog(LicPolicyMst licPolicyMstDialog) {
		this.licPolicyMstDialog = licPolicyMstDialog;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public List<LicReqBocMapping> getLicReqBocMappings() {
		return licReqBocMappings;
	}
	public void setLicReqBocMappings(List<LicReqBocMapping> licReqBocMappings) {
		this.licReqBocMappings = licReqBocMappings;
	}
	
}
