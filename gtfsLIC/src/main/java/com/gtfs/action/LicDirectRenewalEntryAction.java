package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.AgentMst;
import com.gtfs.bean.AgentRlns;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyPaymentMapping;
import com.gtfs.bean.LicRnlBusinessTxn;
import com.gtfs.bean.LicRnlPaymentDtls;
import com.gtfs.bean.LicRnlPaymentMst;
import com.gtfs.bean.PhaseMst;
import com.gtfs.service.interfaces.AgentMstService;
import com.gtfs.service.interfaces.AgentRlnsService;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.PhaseMstService;

@Component
@Scope("session")
public class LicDirectRenewalEntryAction implements Serializable{
	private Logger log = Logger.getLogger(LicDirectRenewalEntryAction.class);
	
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private PhaseMstService phaseMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicBranchHubPicMappingService licBranchHubPicMappingService;
	@Autowired
	private AgentMstService agentMstService;
	@Autowired
	private LicHubMstService licHubMstService;
	@Autowired
	private AgentRlnsService agentRlnsService;
	
	private String policyNo;
	private String product;
	private String insuredName;
	private Double modalPremium;
	private String remarks;
	private String status;
	private Date lastPaidDueDate;
	private Boolean renderedDetails;
	private Double receiptAmt;
	private Date receiptDate;
	private String premDueRemarks;
	private Long agCode;
	private Long phaseId;
	private Double totalModalPrem;
	private Double sumAssured;
	private Double totalServiceTax;
	private String payNature;
	private Double totalAmountReceivable;
	private Date riskStartDate;
	private Boolean renderedSave;
	private Boolean disabledSave;
	private Boolean matchFlag;
	
	private LicPolicyDtls licPolicyDtls;
	private List<LicPolicyDtls> licPolicyDtlsList = new ArrayList<LicPolicyDtls>();
	private List<LicPolicyDtls> selectedList =  new ArrayList<LicPolicyDtls>();
	private List<PhaseMst> phaseMstList = new ArrayList<PhaseMst>();
	
	
	@PostConstruct
	public void init(){
		phaseMstList = phaseMstService.findBusinessPhasesForCurrentDate();
	}
	
	public void refresh(){
		if(licPolicyDtlsList!=null){
			licPolicyDtlsList.clear();
		}
		
		if(selectedList!=null){
			selectedList.clear();
		}
		
		renderedDetails = false;
		policyNo = null;
		renderedSave = false;
		disabledSave = false;
		matchFlag = false;
	}
	
	public String onLoad(){
		refresh();
		return "/licHubRenewalActivity/licDirectRenewalEntry.xhtml";
	}

	public void search(){
		try{
			Date now = new Date();
			LicPolicyDtls lastPaidPolicyDtls = licPolicyDtlsService.findLastPaidPolicyDtlsByPolicyNo(policyNo).get(0);
			this.modalPremium = lastPaidPolicyDtls.getLicPolicyMst().getModalPrem();
			this.product = lastPaidPolicyDtls.getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getLicProductMst().getProdDesc();
			this.totalModalPrem = 0.0;
			this.insuredName = lastPaidPolicyDtls.getLicPolicyMst().getLicOblApplicationMst().getLicInsuredDtls().getName();
			this.sumAssured = lastPaidPolicyDtls.getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getSumAssured();
			this.totalServiceTax = 0.0;
			this.payNature = lastPaidPolicyDtls.getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getPayNature();
			this.totalAmountReceivable = 0.0;
			this.riskStartDate = lastPaidPolicyDtls.getLicPolicyMst().getRiskStartDate();
			this.lastPaidDueDate = lastPaidPolicyDtls.getDueDate();		
			licPolicyDtlsList = licPolicyDtlsService.findPolicyDtlsByPolicyNoForDirectRenewal(policyNo,now);
			
			for(int i=0; i<licPolicyDtlsList.size(); i++){
				licPolicyDtlsList.get(i).setFineAmt(0.0);
				licPolicyDtlsList.get(i).setPremAmt(licPolicyDtlsList.get(i).getLicPolicyMst().getModalPrem());
				licPolicyDtlsList.get(i).setServiceTax(Math.ceil(((licPolicyDtlsList.get(i).getLicPolicyMst().getModalPrem() * 1.545)/100)));
				licPolicyDtlsList.get(i).setHlthReqdFlag("N");
			}
			renderedDetails = true;
		}catch(Exception e){
			log.info("LicDirectRenewalEntryAction search Error : ", e);
		}
	}
	
	public void onRowSelect(SelectEvent event) {
		LicPolicyDtls obj = (LicPolicyDtls) event.getObject();
		selectedList.clear();
		
		for(int i=0; i<licPolicyDtlsList.size(); i++){
			selectedList.add(licPolicyDtlsList.get(i));
			if(licPolicyDtlsList.get(i) == obj){
				matchFlag = true;
				break;
			}
			
		}
		if(matchFlag == false){
			selectedList.clear();
		}
		
		this.totalModalPrem = Math.ceil(modalPremium * selectedList.size());
		this.totalServiceTax = Math.ceil((totalModalPrem * 1.545)/100);
		this.totalAmountReceivable = totalModalPrem + totalServiceTax ;
    }

	public void onRowUnselect(UnselectEvent event) {
		this.totalModalPrem = 0.0;
		this.totalServiceTax =0.0;
		this.totalAmountReceivable = 0.0;
		selectedList.clear();
    }

	public void validate(){
		try{
			if(matchFlag == false){
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error Occured : ", "No Premium Due List(s) Row Selected"));
				return;
			}
			
			List<AgentRlns> list = agentRlnsService.findValidAgentByPhase(phaseId, agCode);
			
			if(list == null || list.size() == 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Agent Not Valid, Please Try Again With Valid Agent"));
				return;
			}
			renderedSave = true;
		}catch(Exception e){
			log.info("LicDirectRenewalEntryAction generateReport Error : ", e);
		}
	}
	
	public void save(){
		try{
			Date now  = new Date();
			List<Long> hubIds = licBranchHubPicMappingService.findHubIdForBranchIdByProcessName(loginAction.getUserList().get(0).getBranchMst().getBranchId(), "RNL");
			
			if(hubIds==null || hubIds.size()==0 || hubIds.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Hub For that Branch not Defined"));
				return;
			}
			
			LicHubMst licHubMst = licHubMstService.findById(hubIds.get(0));
			AgentMst agentMst = agentMstService.findByAgCode(agCode);
			PhaseMst phaseMst = phaseMstService.findByPhaseId(phaseId);	
			
			for(LicPolicyDtls dtls: selectedList){
				//LicPolicyDtls
				dtls.setRenewalType("DIRECT");
				dtls.setPaidFlag("Y");
				dtls.setPayDate(receiptDate);
				dtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				dtls.setModifiedDate(now);
				dtls.setHlthReqdFlag("N");
				dtls.setBranchMst(loginAction.getUserList().get(0).getBranchMst());
				dtls.setOblHubMst(licHubMst);
				
				//LicRnlBusinessTxn
				LicRnlBusinessTxn licRnlBusinessTxn = new LicRnlBusinessTxn();
				licRnlBusinessTxn.setBusinessValue(dtls.getPremAmt());
				licRnlBusinessTxn.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licRnlBusinessTxn.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licRnlBusinessTxn.setCreatedDate(now);
				licRnlBusinessTxn.setModifiedDate(now);
				licRnlBusinessTxn.setDeleteFlag("N");
				licRnlBusinessTxn.setFreeQtyFlag("N");
				licRnlBusinessTxn.setFrozenFlag("N");
				licRnlBusinessTxn.setReceivable(dtls.getPremAmt());
				licRnlBusinessTxn.setReceived(dtls.getPremAmt());
				licRnlBusinessTxn.setTransStatus("D");
				licRnlBusinessTxn.setTransDate(now);
				licRnlBusinessTxn.setTransferFlag("N");
				licRnlBusinessTxn.setAgentMst(agentMst);
				licRnlBusinessTxn.setPhaseMst(phaseMst);
				licRnlBusinessTxn.setLicProductMst(dtls.getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getLicProductMst());
				
				dtls.setLicRnlBusinessTxn(licRnlBusinessTxn);
				licRnlBusinessTxn.setLicPolicyDtls(dtls);
			}
			
			Boolean status = licPolicyDtlsService.saveRenewalBranchEntry(selectedList);
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Success : ", "Renewal At Branch Successful"));
				disabledSave = true;
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Renewal At Branch Unsuccessful"));
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error : ", "Renewal At Branch Unsuccessful"));
			log.info("LicDirectRenewalEntryAction save Error : ", e);
		}
	}
	
	
	
	/* GETTER SETTER */
	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public Double getModalPremium() {
		return modalPremium;
	}

	public void setModalPremium(Double modalPremium) {
		this.modalPremium = modalPremium;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastPaidDueDate() {
		return lastPaidDueDate;
	}

	public void setLastPaidDueDate(Date lastPaidDueDate) {
		this.lastPaidDueDate = lastPaidDueDate;
	}

	public Boolean getRenderedDetails() {
		return renderedDetails;
	}

	public void setRenderedDetails(Boolean renderedDetails) {
		this.renderedDetails = renderedDetails;
	}

	public Double getReceiptAmt() {
		return receiptAmt;
	}

	public void setReceiptAmt(Double receiptAmt) {
		this.receiptAmt = receiptAmt;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getPremDueRemarks() {
		return premDueRemarks;
	}

	public void setPremDueRemarks(String premDueRemarks) {
		this.premDueRemarks = premDueRemarks;
	}

	public Long getAgCode() {
		return agCode;
	}

	public void setAgCode(Long agCode) {
		this.agCode = agCode;
	}

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public LicPolicyDtls getLicPolicyDtls() {
		return licPolicyDtls;
	}

	public void setLicPolicyDtls(LicPolicyDtls licPolicyDtls) {
		this.licPolicyDtls = licPolicyDtls;
	}

	public List<LicPolicyDtls> getLicPolicyDtlsList() {
		return licPolicyDtlsList;
	}

	public void setLicPolicyDtlsList(List<LicPolicyDtls> licPolicyDtlsList) {
		this.licPolicyDtlsList = licPolicyDtlsList;
	}

	public List<LicPolicyDtls> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicPolicyDtls> selectedList) {
		this.selectedList = selectedList;
	}

	public List<PhaseMst> getPhaseMstList() {
		return phaseMstList;
	}

	public void setPhaseMstList(List<PhaseMst> phaseMstList) {
		this.phaseMstList = phaseMstList;
	}

	public Double getTotalModalPrem() {
		return totalModalPrem;
	}

	public void setTotalModalPrem(Double totalModalPrem) {
		this.totalModalPrem = totalModalPrem;
	}

	public Double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}

	public Double getTotalServiceTax() {
		return totalServiceTax;
	}

	public void setTotalServiceTax(Double totalServiceTax) {
		this.totalServiceTax = totalServiceTax;
	}

	public String getPayNature() {
		return payNature;
	}

	public void setPayNature(String payNature) {
		this.payNature = payNature;
	}

	public Double getTotalAmountReceivable() {
		return totalAmountReceivable;
	}

	public void setTotalAmountReceivable(Double totalAmountReceivable) {
		this.totalAmountReceivable = totalAmountReceivable;
	}

	public Date getRiskStartDate() {
		return riskStartDate;
	}

	public void setRiskStartDate(Date riskStartDate) {
		this.riskStartDate = riskStartDate;
	}

	public Boolean getRenderedSave() {
		return renderedSave;
	}

	public void setRenderedSave(Boolean renderedSave) {
		this.renderedSave = renderedSave;
	}

	public Boolean getDisabledSave() {
		return disabledSave;
	}

	public void setDisabledSave(Boolean disabledSave) {
		this.disabledSave = disabledSave;
	}
	
}
