package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.AgentMst;
import com.gtfs.bean.LicBusinessTxn;
import com.gtfs.bean.LicInsuredDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicOblChecklist;
import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicPaymentMst;
import com.gtfs.bean.LicProposerDtls;
import com.gtfs.dto.LicPreliminaryDataEntryDto;
import com.gtfs.service.interfaces.AgentMstService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.LicOblChecklistService;
import com.gtfs.service.interfaces.LicProductMstService;
import com.gtfs.service.interfaces.LicProductValueMstService;
import com.gtfs.service.interfaces.PhaseMstService;
import com.gtfs.util.FinYearCalculation;
@Component
@Scope("session")
public class LicPreliminaryDataEntrySearchAction implements Serializable{
	private Logger log = Logger.getLogger(LicPreliminaryDataEntrySearchAction.class);
	
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private PhaseMstService phaseMstService;
	@Autowired
	private LicProductMstService licProductMstService;
	@Autowired
	private LicOblChecklistService licOblChecklistService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicProductValueMstService licProductValueMstService;
	@Autowired
	private AgentMstService agentMstService;
	@Autowired
	private LicHubMstService licHubMstService;
	
	private Date applicationDate;
	private String applicationNo;
	private Boolean renderedDetailForm;
	private Boolean renderedListPanel;
	private Boolean renderedsearchPanel;
	
	private LicPreliminaryDataEntryDto licPreliminaryDataEntryDto;	
	private List<LicOblChecklist> licOblChecklists = new ArrayList<LicOblChecklist>();
	private List<LicPaymentDtls> licPaymentDtlsList=new ArrayList<LicPaymentDtls>();
	
	public void refresh(){
		licPreliminaryDataEntryDto = new LicPreliminaryDataEntryDto();
		licOblChecklists.clear();
		licPaymentDtlsList.clear();
		renderedDetailForm = false;
		renderedListPanel = false;
		renderedsearchPanel = true;
		applicationDate = null;
		applicationNo = null;
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/licPreliminaryDataEntrySearch.xhtml";
	}
	
	public void calculate(ActionEvent actionEvent){
		Double totalReceivable = (checkDouble(licPreliminaryDataEntryDto.getTotalAmount()) - (checkDouble(licPreliminaryDataEntryDto.getDdMakingCharges())+checkDouble(licPreliminaryDataEntryDto.getOtherCharges())));
		Double totalDdChqAmt = 0.0;		 
		 
		for(LicPaymentDtls obj:licPaymentDtlsList){
			 totalDdChqAmt = totalDdChqAmt+checkDouble(obj.getAmount());
		 }
		 
		 licPreliminaryDataEntryDto.setTotalReceivable(totalReceivable);
		 licPreliminaryDataEntryDto.setTotalDdChqAmt(totalDdChqAmt);
		 
		 if(totalReceivable >= totalDdChqAmt){
			 licPreliminaryDataEntryDto.setBalanceInCash(totalReceivable - totalDdChqAmt);
		 }else{
			 licPreliminaryDataEntryDto.setBalanceInCash(0.0);
		 }
		 
		 if(totalReceivable < totalDdChqAmt){
			 //licPreliminaryDataEntryDto.setAmtToReturn(totalDdChqAmt - totalReceivable);
			 licPreliminaryDataEntryDto.setAmtToReturn(0.0);
		 }else{
			 licPreliminaryDataEntryDto.setAmtToReturn(0.0);
		 }
	}
	
	public void processEntry(ActionEvent actionEvent){
		 //RequestContext.getCurrentInstance().openDialog("preliminaryDialog");
		try{
			Date now = new Date();
			Double totalReceived = 0.0;
		 	Long hubId = licHubMstService.findActiveHubIdByBranchId(loginAction.getUserList().get(0).getBranchMst().getBranchId()).get(0);
			
		 	if(hubId == null){
		 		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save UnSuccessful : ", "Data Not Valid"));
		 		return;
		 	}
		 	
			AgentMst agentMst = agentMstService.findByAgCode(licPreliminaryDataEntryDto.getAgCode());
			
			LicOblChecklist licOblChecklist = licPreliminaryDataEntryDto.getLicOblChecklist();
			licOblChecklist.setPreDataEntryFlag("Y");
			
			LicPaymentMst licPaymentMst = new LicPaymentMst();
			licPaymentMst.setPayDate(now);
			licPaymentMst.setProcessName("OBL");
			licPaymentMst.setTotalReceivable(licPreliminaryDataEntryDto.getTotalReceivable());
			//licPaymentMst.setTotalReceived(licPreliminaryDataEntryDto.getTotalReceivable());
			licPaymentMst.setSalesTax(0.0);
			licPaymentMst.setServiceTax(0.0);
			licPaymentMst.setServiceCharge(0.0);
			licPaymentMst.setDdCharges(licPreliminaryDataEntryDto.getDdMakingCharges());
			licPaymentMst.setOtherCharges(licPreliminaryDataEntryDto.getOtherCharges());
			licPaymentMst.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licPaymentMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPaymentMst.setCreatedDate(now);
			licPaymentMst.setModifiedDate(now);
			licPaymentMst.setDeleteFlag("N");
			
			
			LicBusinessTxn licBusinessTxn=new LicBusinessTxn();
			licBusinessTxn.setTransDate(now);
			
			licBusinessTxn.setPhaseMst(phaseMstService.findByPhaseId(licPreliminaryDataEntryDto.getPhaseId()));
			
			licBusinessTxn.setAgentMst(agentMst);
			licBusinessTxn.setLicProductMst(licProductMstService.findByProductId(licPreliminaryDataEntryDto.getProductId()));
			licBusinessTxn.setBusinessValue(licPreliminaryDataEntryDto.getBasicPrem());
			licBusinessTxn.setFrozenFlag("N");
			licBusinessTxn.setTransferFlag("N");
			licBusinessTxn.setTransStatus("D");
			//licBusinessTxn.setAgentRlns(agentRlns);
			licBusinessTxn.setFreeQtyFlag("N");
			licBusinessTxn.setReceivable(licPreliminaryDataEntryDto.getTotalReceivable());
			//licBusinessTxn.setReceived(licPreliminaryDataEntryDto.getTotalReceivable());
			licBusinessTxn.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBusinessTxn.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBusinessTxn.setCreatedDate(now);
			licBusinessTxn.setModifiedDate(now);
			licBusinessTxn.setDeleteFlag("N");
			
			//payment for cash 
			if(!licPreliminaryDataEntryDto.getBalanceInCash().equals(0.0)){
				LicPaymentDtls licPaymentDtls = new LicPaymentDtls();
				licPaymentDtls.setAmount(licPreliminaryDataEntryDto.getBalanceInCash());
				licPaymentDtls.setPayMode("C");
				licPaymentDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licPaymentDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licPaymentDtls.setCreatedDate(now);
				licPaymentDtls.setModifiedDate(now);
				licPaymentDtls.setDeleteFlag("N");
				licPaymentDtlsList.add(licPaymentDtls);
			}
			
			if(licPaymentDtlsList.size()>1){
				licPaymentMst.setPayMode("B");
			}else{
				licPaymentMst.setPayMode(licPaymentDtlsList.get(0).getPayMode());
			}
			
			for (LicPaymentDtls obj : licPaymentDtlsList) {
				obj.setLicPaymentMst(licPaymentMst);
				totalReceived = totalReceived + obj.getAmount();
			}
			
			licBusinessTxn.setReceived(totalReceived);
			licPaymentMst.setTotalReceived(totalReceived);
			licPaymentMst.setLicPaymentDtlses(licPaymentDtlsList);
				
			licBusinessTxn.setLicPaymentMst(licPaymentMst);
			//licPaymentMst.getLicBusinessTxns().add(licBusinessTxn);
			
			LicProposerDtls licProposerDtls=new LicProposerDtls();
			licProposerDtls.setName(licPreliminaryDataEntryDto.getProposerName());
			licProposerDtls.setDob(licPreliminaryDataEntryDto.getProposerDob());
			licProposerDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licProposerDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licProposerDtls.setCreatedDate(now);
			licProposerDtls.setModifiedDate(now);
			licProposerDtls.setDeleteFlag("N");
			LicInsuredDtls licInsuredDtls = new LicInsuredDtls();
			licInsuredDtls.setName(licPreliminaryDataEntryDto.getInsuredName());
			licInsuredDtls.setDob(licPreliminaryDataEntryDto.getInsuredDob());
			licInsuredDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licInsuredDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licInsuredDtls.setCreatedDate(now);
			licInsuredDtls.setModifiedDate(now);
			licInsuredDtls.setDeleteFlag("N");
			
			LicOblApplicationMst licOblApplicationMst = new LicOblApplicationMst();
			licOblApplicationMst.setAgentMst(agentMst);					
			licOblApplicationMst.setTieupCompyMst(licPreliminaryDataEntryDto.getLicOblChecklist().getLicProductValueMst().getTieupCompyMst());
			licOblApplicationMst.setDesignationMst(loginAction.getUserList().get(0).getDesignationMst());
			licOblApplicationMst.setBranchMst(loginAction.getUserList().get(0).getBranchMst());
			licOblApplicationMst.setLicProductValueMst(licPreliminaryDataEntryDto.getLicOblChecklist().getLicProductValueMst());
			licOblApplicationMst.setOblApplNo(licPreliminaryDataEntryDto.getApplicationNo());
			licOblApplicationMst.setFinYr(FinYearCalculation.getFiscalYear());
			licOblApplicationMst.setBusinessDate(now);
			licOblApplicationMst.setPrintFlag("N");
			licOblApplicationMst.setSecondaryEntryFlag("N");
			//licOblApplicationMst.setPicBranchId(picBranchId);
			licOblApplicationMst.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licOblApplicationMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licOblApplicationMst.setCreatedDate(now);
			licOblApplicationMst.setModifiedDate(now);
			licOblApplicationMst.setDeleteFlag("N");
			licOblApplicationMst.setOblHubMst(licHubMstService.findById(hubId));
			licOblApplicationMst.setLicProposerDtls(licProposerDtls);
			licOblApplicationMst.setLicInsuredDtls(licInsuredDtls);
			licOblApplicationMst.setLicBusinessTxn(licBusinessTxn);
			licOblApplicationMst.setLicOblChecklist(licOblChecklist);
			licOblApplicationMst.setMigrationFlag("N");
			licOblApplicationMst.setReceiptNo(licOblChecklist.getId()+"/"+new Random().nextInt(100)+"/"+new Date().getTime());
						
			licProposerDtls.setLicOblApplicationMst(licOblApplicationMst);
			licInsuredDtls.setLicOblApplicationMst(licOblApplicationMst);
			
			
			licBusinessTxn.setLicOblApplicationMst(licOblApplicationMst);
			licPaymentMst.setLicBusinessTxn(licBusinessTxn);
			
			

			Long id = licOblApplicationMstService.insertDataForPreliminaryDataEntry(licOblApplicationMst);
			
			if(id > 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Data Saved Successfully"));
				renderedsearchPanel = true;
				renderedListPanel = false;
				renderedDetailForm = false;
				search(actionEvent);
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Data Not Saved"));
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Save UnSuccessful : ", "Internal Error"));
			log.info("LicDirectRenewalEntryAction save Error : ", e);
		}
	}

	
	public Double checkDouble(Double value){
		if(value!=null){
			return value;
		}else{
			return 0.0;
		}
	}
	
	public void addPaymentDtls(){
		Date now = new Date();
		LicPaymentDtls licPaymentDtls = new LicPaymentDtls();
		licPaymentDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licPaymentDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licPaymentDtls.setCreatedDate(now);
		licPaymentDtls.setModifiedDate(now);
		licPaymentDtls.setDeleteFlag("N");
		licPaymentDtlsList.add(licPaymentDtls);
	}
	
	public void deletePaymentDtls(LicPaymentDtls licPaymentDtls){
		licPaymentDtlsList.remove(licPaymentDtls);
	}
	
	public void editDetail(LicOblChecklist licOblChecklist){
		if(licPaymentDtlsList!=null){
			licPaymentDtlsList.clear();
		}
		
		licPreliminaryDataEntryDto.setLicOblChecklist(licOblChecklist);
		licPreliminaryDataEntryDto.setInsuredName(licOblChecklist.getInsuredName());
		licPreliminaryDataEntryDto.setInsuredDob(licOblChecklist.getInsuredDob());
		licPreliminaryDataEntryDto.setProposerName(licOblChecklist.getProposerName());
		licPreliminaryDataEntryDto.setProposerDob(licOblChecklist.getProposerDob());
		licPreliminaryDataEntryDto.setApplicationNo(licOblChecklist.getOblApplNo());
		licPreliminaryDataEntryDto.setProductId(licOblChecklist.getLicProductValueMst().getLicProductMst().getId());
		licPreliminaryDataEntryDto.setProductName(licOblChecklist.getLicProductValueMst().getLicProductMst().getProdDesc());
		licPreliminaryDataEntryDto.setPolicyTerm(licOblChecklist.getLicProductValueMst().getPolicyTerm());
		//licPreliminaryDataEntryDto.setPremiumAmount(licOblChecklist.getLicProductValueMst().getPremAmt()); you will do this work
		//licPreliminaryDataEntryDto.setServiceTax(licOblChecklist.getLicProductValueMst().getTaxSvcChrgs());
		licPreliminaryDataEntryDto.setTabPrem(licOblChecklist.getLicProductValueMst().getTabPrem());
		licPreliminaryDataEntryDto.setBasicPrem(licOblChecklist.getLicProductValueMst().getBasicPrem());
		licPreliminaryDataEntryDto.setTotalAmount(licOblChecklist.getLicProductValueMst().getTotalAmt());
		licPreliminaryDataEntryDto.setSumAssured(licOblChecklist.getLicProductValueMst().getSumAssured());
		licPreliminaryDataEntryDto.setPayMode(licOblChecklist.getLicProductValueMst().getPayNature());
		licPreliminaryDataEntryDto.setAgCode(licOblChecklist.getAgCode());
		licPreliminaryDataEntryDto.setPhaseId(licOblChecklist.getPhaseMst().getPhaseId());
		licPreliminaryDataEntryDto.setPhaseName(licOblChecklist.getPhaseMst().getPhaseName());
		licPreliminaryDataEntryDto.setBusinessDate(new Date());
		licPreliminaryDataEntryDto.setCompanyName("LIFE INSURANCE CORPORATION OF INDIA (LICI)");
		licPreliminaryDataEntryDto.setBranchName(loginAction.getUserList().get(0).getBranchMst().getBranchName());
		licPreliminaryDataEntryDto.setOperatorName(loginAction.getUserList().get(0).getUserName());
		
		renderedDetailForm = true;
		renderedListPanel = false;
		renderedsearchPanel = false;
	}
	
	public void search(ActionEvent actionEvent) {
		try{
			if(applicationDate != null){
				licOblChecklists = licOblChecklistService.findApplicationForPremDataEntryByDate(applicationDate, loginAction.getUserList().get(0).getBranchMst());
				renderedListPanel = true;
				
			}else if(applicationNo != null){
				licOblChecklists = licOblChecklistService.findApplicationForPremDataEntryByApplicationNo(applicationNo, loginAction.getUserList().get(0).getBranchMst());
				renderedListPanel = true;
				
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_ERROR,"Error :", "Please Enter Date or Application Number"));
				renderedListPanel = false;
			}
		}catch(Exception e){
			log.info("LicDirectRenewalEntryAction search Error : ", e);
		}
    }
	
	
	/* GETTER SETTER */
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public List<LicOblChecklist> getLicOblChecklists() {
		return licOblChecklists;
	}

	public void setLicOblChecklists(List<LicOblChecklist> licOblChecklists) {
		this.licOblChecklists = licOblChecklists;
	}

	public LicPreliminaryDataEntryDto getLicPreliminaryDataEntryDto() {
		return licPreliminaryDataEntryDto;
	}

	public void setLicPreliminaryDataEntryDto(
			LicPreliminaryDataEntryDto licPreliminaryDataEntryDto) {
		this.licPreliminaryDataEntryDto = licPreliminaryDataEntryDto;
	}
	public List<LicPaymentDtls> getLicPaymentDtlsList() {
		return licPaymentDtlsList;
	}
	public void setLicPaymentDtlsList(List<LicPaymentDtls> licPaymentDtlsList) {
		this.licPaymentDtlsList = licPaymentDtlsList;
	}
	public Boolean getRenderedDetailForm() {
		return renderedDetailForm;
	}
	public void setRenderedDetailForm(Boolean renderedDetailForm) {
		this.renderedDetailForm = renderedDetailForm;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public Boolean getRenderedsearchPanel() {
		return renderedsearchPanel;
	}

	public void setRenderedsearchPanel(Boolean renderedsearchPanel) {
		this.renderedsearchPanel = renderedsearchPanel;
	}

}
