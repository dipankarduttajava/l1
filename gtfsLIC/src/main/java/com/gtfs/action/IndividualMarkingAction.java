package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicAddressDtls;
import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.StateMst;
import com.gtfs.service.interfaces.AgentMstService;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.LicInsuredAddressMappingService;
import com.gtfs.service.interfaces.LicMarkingToQcDtlsService;
import com.gtfs.service.interfaces.LicNomineeDtlsService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.LicPaymentDtlsService;
import com.gtfs.service.interfaces.StateMstService;

@Component
@Scope("session")
public class IndividualMarkingAction implements Serializable{
	private Logger log = Logger.getLogger(IndividualMarkingAction.class);
	
	@Autowired
	private LicMarkingToQcDtlsService licMarkingToQcDtlsService;
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private BranchMstService branchMstService;
	@Autowired
	private AgentMstService agentMstService;
	@Autowired
	private StateMstService stateMstService;
	@Autowired
	private LicInsuredAddressMappingService licInsuredAddressMappingService;
	@Autowired
	private LicNomineeDtlsService licNomineeDtlsService;
	@Autowired
	private LicPaymentDtlsService licPaymentDtlsService;
	
	private Date bnsFromDate;
	private Date bnsToDate;
	private Boolean renderedindMark;
	private Long branchId;
	private String applicationNo;
	private Double totalAmount;
	
	private List<LicOblApplicationMst> licOblApplicationMstList = new ArrayList<LicOblApplicationMst>();
	private List<LicOblApplicationMst> selectedList =  new ArrayList<LicOblApplicationMst>();
	private List<BranchMst> branchList = new ArrayList<BranchMst>();
	private List<StateMst> stateMsts = new ArrayList<StateMst>();
	private LicAddressDtls licAddressDtls;
	private LicNomineeDtls licNomineeDtls;
	
	private LicOblApplicationMst licOblApplicationMst;
	
	@PostConstruct
	public void init(){
		stateMsts = stateMstService.findAllActiveStateMSt();
	}
	
	
	
	public void refresh(){
		renderedindMark = false;
		bnsFromDate = null;
		bnsToDate = null;
		applicationNo = null;
		licOblApplicationMstList.clear();
		selectedList.clear();
		
		if(branchList != null){
			branchList.clear();
		}
		
		branchList = branchMstService.findAll();
	}
	
	public String onLoad(){
		refresh();
		return "/licHubActivity/individualMarking.xhtml";
	}
	
	public void search(){
		try{
			renderedindMark = true;
			totalAmount = 0.0;
			licOblApplicationMstList = licMarkingToQcDtlsService.findIndividualMarkingDtlsByDate(applicationNo,bnsFromDate, bnsToDate, loginAction.findHubForProcess("OBL"), loginAction.getUserList().get(0).getBranchMst(), branchId);
			
			if(licOblApplicationMstList == null || licOblApplicationMstList.size() == 0 || licOblApplicationMstList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
			
			
			for(LicOblApplicationMst obj : licOblApplicationMstList){

				List<LicPaymentDtls> licPaymentDtlses = licPaymentDtlsService.findLicPaymentDtlsByPayId(obj.getLicBusinessTxn().getLicPaymentMst());

				for(LicPaymentDtls ob : licPaymentDtlses){
					if((ob.getPayeeName() == null || ob.getPayeeName().equals("SARADA INSURANCE CONSULTANCY LTD"))){
						totalAmount = totalAmount + ob.getAmount();
					}
				}
				obj.getLicBusinessTxn().getLicPaymentMst().setLicPaymentDtlses(licPaymentDtlses);
			}
			
			
			
			
		}catch(Exception e){
			log.info("IndividualMarkingAction search Error : ", e);
		}
	}
	
	
	
	public void modify(LicOblApplicationMst licOblApplicationMst){	
		
		List<LicInsuredAddressMapping> licInsuredAddressMappings = licInsuredAddressMappingService.findAddressDtlsByInsuredDtls(licOblApplicationMst.getLicInsuredDtls());
		List<LicNomineeDtls> LicNomineeDtlses = licNomineeDtlsService.findNomineeDtlsByApplication(licOblApplicationMst);
		
		licOblApplicationMst.getLicInsuredDtls().setLicInsuredAddressMappings(licInsuredAddressMappings);
		licOblApplicationMst.setLicNomineeDtlses(LicNomineeDtlses);
		
		licAddressDtls = licOblApplicationMst.getLicInsuredDtls().getLicInsuredAddressMappings().get(0).getLicAddressDtls();
		licNomineeDtls = licOblApplicationMst.getLicNomineeDtlses().get(0);
		setLicOblApplicationMst(licOblApplicationMst);
		RequestContext.getCurrentInstance().openDialog("individualMarkingDialog");
		
	}
	
	
	public void updateDetails(){
		Boolean status = licOblApplicationMstService.update(licOblApplicationMst);
		if(status){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Update Successful : ", "Update Completed Successfully."));
			licOblApplicationMstList = licMarkingToQcDtlsService.findIndividualMarkingDtlsByDate(applicationNo,bnsFromDate, bnsToDate, loginAction.findHubForProcess("OBL"), loginAction.getUserList().get(0).getBranchMst(), branchId);
			for(LicOblApplicationMst obj : licOblApplicationMstList){

				List<LicPaymentDtls> licPaymentDtlses = licPaymentDtlsService.findLicPaymentDtlsByPayId(obj.getLicBusinessTxn().getLicPaymentMst());

				for(LicPaymentDtls ob : licPaymentDtlses){
					if((ob.getPayeeName() == null || ob.getPayeeName().equals("SARADA INSURANCE CONSULTANCY LTD"))){
						totalAmount = totalAmount + ob.getAmount();
					}
				}
				obj.getLicBusinessTxn().getLicPaymentMst().setLicPaymentDtlses(licPaymentDtlses);
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Update UnSuccessful : ", "Update Unsuccessful"));
		}
	}
	
	
	public void save(){
		try{
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select an Application to Save"));
				return;
			}
			
			Date now = new Date();			
			for(LicOblApplicationMst obj : selectedList){
				obj.setAgentMst(agentMstService.findByAgCode(obj.getAgentMst().getAgCode()));
				obj.getLicMarkingToQcDtls().setIndMrkBy(loginAction.getUserList().get(0).getUserid());
				obj.getLicMarkingToQcDtls().setIndMrkDate(now);
				obj.getLicMarkingToQcDtls().setIndMrkFlag("Y");
				obj.getLicMarkingToQcDtls().setModifiedBy(loginAction.getUserList().get(0).getUserid());
				obj.getLicMarkingToQcDtls().setModifiedDate(now);
			}
			
			Boolean status = licMarkingToQcDtlsService.updateForIndividualMarking(selectedList);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Pod Tagging Successfully Completed."));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Pod Tagging Unsuccessful"));
			}
		}catch(Exception e){
			log.info("IndividualMarkingAction save Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public Date getBnsFromDate() {
		return bnsFromDate;
	}
	public void setBnsFromDate(Date bnsFromDate) {
		this.bnsFromDate = bnsFromDate;
	}
	public Date getBnsToDate() {
		return bnsToDate;
	}
	public void setBnsToDate(Date bnsToDate) {
		this.bnsToDate = bnsToDate;
	}
	public List<LicOblApplicationMst> getLicOblApplicationMstList() {
		return licOblApplicationMstList;
	}
	public void setLicOblApplicationMstList(
			List<LicOblApplicationMst> licOblApplicationMstList) {
		this.licOblApplicationMstList = licOblApplicationMstList;
	}
	public Boolean getRenderedindMark() {
		return renderedindMark;
	}
	public void setRenderedindMark(Boolean renderedindMark) {
		this.renderedindMark = renderedindMark;
	}
	public List<LicOblApplicationMst> getSelectedList() {
		return selectedList;
	}
	public void setSelectedList(List<LicOblApplicationMst> selectedList) {
		this.selectedList = selectedList;
	}
	public List<BranchMst> getBranchList() {
		return branchList;
	}
	public void setBranchList(List<BranchMst> branchList) {
		this.branchList = branchList;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public LicOblApplicationMst getLicOblApplicationMst() {
		return licOblApplicationMst;
	}

	public void setLicOblApplicationMst(LicOblApplicationMst licOblApplicationMst) {
		this.licOblApplicationMst = licOblApplicationMst;
	}
	public List<StateMst> getStateMsts() {
		return stateMsts;
	}
	public void setStateMsts(List<StateMst> stateMsts) {
		this.stateMsts = stateMsts;
	}
	public LicAddressDtls getLicAddressDtls() {
		return licAddressDtls;
	}
	public void setLicAddressDtls(LicAddressDtls licAddressDtls) {
		this.licAddressDtls = licAddressDtls;
	}
	public LicNomineeDtls getLicNomineeDtls() {
		return licNomineeDtls;
	}
	public void setLicNomineeDtls(LicNomineeDtls licNomineeDtls) {
		this.licNomineeDtls = licNomineeDtls;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
