package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.AgentMst;
import com.gtfs.bean.AgentRlns;
import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicBrnhHubPicPodDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicMarkingToQcDtls;
import com.gtfs.bean.LicPolicyBankDtls;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.bean.LicPolicyPaymentMapping;
import com.gtfs.bean.LicProdFineMst;
import com.gtfs.bean.LicRnlBusinessTxn;
import com.gtfs.bean.LicRnlPaymentDtls;
import com.gtfs.bean.LicRnlPaymentMst;
import com.gtfs.bean.PhaseMst;
import com.gtfs.bean.RbiBankDtls;
import com.gtfs.service.interfaces.AgentMstService;
import com.gtfs.service.interfaces.AgentRlnsService;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.LicPolicyMstService;
import com.gtfs.service.interfaces.LicProdFineMstService;
import com.gtfs.service.interfaces.PhaseMstService;
import com.gtfs.service.interfaces.RbiBankDtlsService;
import com.gtfs.service.interfaces.UserMstService;
import com.gtfs.util.BankValidation;
import com.gtfs.util.CalenderUtil;

@Component
@Scope("session")
public class RenewalBranchMasterAction implements Serializable{
	private Logger log = Logger.getLogger(RenewalBranchMasterAction.class);
	
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private PhaseMstService phaseMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private AgentRlnsService agentRlnsService;
	@Autowired
	private AgentMstService agentMstService;
	@Autowired
	private LicProdFineMstService licProdFineMstService;
	@Autowired
	private LicBranchHubPicMappingService licBranchHubPicMappingService;
	@Autowired
	private LicHubMstService licHubMstService;
	@Autowired
	private UserMstService userMstService;
	@Autowired
	private RbiBankDtlsService rbiBankDtlsService;
	@Autowired
	private LicPolicyMstService licPolicyMstService;
	
	Date sysdate = new Date();
	private String policyNo;
	private String product;
	private String insuredName;
	private Double sumAssured;
	private String payNature;
	private Date riskStartDate;
	private Double modalPremium;
	private String remarks;
	private String status;
	private Date lastPaidDueDate;
	private Boolean renderedDetails;	
	private Long agCode;
	private Long phaseId;	
	private Double ddMakingCharges;
	private Double otherCharges;
	private String paymentRemarks;	
	private Double totalAmount;
	private Double totalReceivable;
	private Double totalDdChequeAmt;
	private Double balanceRcvInCash;
	private Double amtToReturn;	
	private Boolean renderedSave;
	private Double totalAmt;
	private Double serviceTax;
	private Boolean renderedAdvPolicy;
	private Boolean renderedBankDtls;
	private Double fineAmount;
	private Double totalModalPrem;
	private Double totalServiceTax;
	private String healthGivenFlag;
	private Double totalAmountReceivable;	
	private String ifsCode;
	private String bankName;
	private String branchName;
	private String branchAdd;
	private String micrCode;
	private String acctNo;
	private String acctType;
	private DataTable licPolicyDtlsListBinding;

	private List<LicPolicyDtls> licPolicyDtlsList = new ArrayList<LicPolicyDtls>();
	private List<LicPolicyDtls> licAdvPolicyDtlsList = new ArrayList<LicPolicyDtls>();
	private List<LicRnlPaymentDtls> licRnlPaymentDtlsList = new ArrayList<LicRnlPaymentDtls>();
	private List<LicPolicyDtls> selectedList =  new ArrayList<LicPolicyDtls>();
	private LicPolicyDtls licPolicyDtls;
	private List<PhaseMst> phaseMsts = new ArrayList<PhaseMst>();
	private LicPolicyMst licPolicyMst;
	
	
	@PostConstruct
	public void init(){
		phaseMsts = phaseMstService.findBusinessPhasesForCurrentDate();
	}
	
	public void refresh(){
		licPolicyMst = null;
		if(licPolicyDtlsList != null){
			licPolicyDtlsList.clear();
		}
		
		if(licAdvPolicyDtlsList != null){
			licAdvPolicyDtlsList.clear();
		}
		
		if(licRnlPaymentDtlsList != null){
			licRnlPaymentDtlsList.clear();
		}
		
		if(selectedList != null){
			selectedList.clear();
		}
		renderedDetails = false;
		renderedSave = false;
		renderedAdvPolicy = false;
		renderedBankDtls = false;
		healthGivenFlag = "N";
		policyNo = null;
		agCode = null;
		phaseId = null;
		ifsCode = null;
		bankName = null;
		branchName = null;
		branchAdd = null;
		micrCode = null;
		acctNo = null;
		acctType = null;
		fineAmount = 0.0;
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchRenewalActivity/renewalBranchMaster.xhtml";
	}
	
	/*public void addList(LicPolicyDtls licPolicyDtls){
		if(licPolicyDtlsList.get(0).equals(licPolicyDtls)){
			selectedList.add(licPolicyDtls);
			licPolicyDtlsList.remove(licPolicyDtls);
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, "Error :", "Please Add Chronologically"));
		}
	}*/
	
	public void onRowSelect(SelectEvent event) {
		if(licPolicyDtlsList == null || licPolicyDtlsList.size()==0){
			totalModalPrem = 0.0 + modalPremium;
		}else{
			totalModalPrem = (licPolicyDtlsList.get(0).getLicPolicyMst().getModalPrem() * licPolicyDtlsList.size()) + modalPremium;
		}
		totalServiceTax = Math.ceil((totalModalPrem * 1.545)/100);
		totalAmountReceivable = totalModalPrem + totalServiceTax + fineAmount;
    }
	
	public void onRowUnselect(UnselectEvent event) {
		if(licPolicyDtlsList == null || licPolicyDtlsList.size()==0){
			totalModalPrem = 0.0;
		}else{
			totalModalPrem = (licPolicyDtlsList.get(0).getLicPolicyMst().getModalPrem() * licPolicyDtlsList.size());
		}
		totalServiceTax = Math.ceil((totalModalPrem * 1.545)/100);
		totalAmountReceivable = totalModalPrem + totalServiceTax + fineAmount;
    }
	
	public void search(){
		try{
			serviceTax = 0.0;
			Double totalPremAmount=0.0;
			Date now = new Date();
			Date sysdate = new Date();
			
			licPolicyDtlsList = licPolicyDtlsService.findPolicyDtlsByPolicyNo(policyNo, now);
			licAdvPolicyDtlsList = licPolicyDtlsService.findAdvPolicyDtlsByPolicyNo(policyNo, now, CalenderUtil.incrementDate(sysdate, 30));
			
			if((licPolicyDtlsList.size() == 0 || licPolicyDtlsList.contains(null) || licPolicyDtlsList == null) && (licAdvPolicyDtlsList.size() == 0 || licAdvPolicyDtlsList.contains(null) || licAdvPolicyDtlsList == null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Renewal is Not Pending"));
				return;
			}
			
			licPolicyMst = licPolicyMstService.findPolicyInfoByPolicyNo(policyNo).get(0);
			
			setProduct(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getLicProductMst().getProdDesc());
			setInsuredName(licPolicyMst.getLicOblApplicationMst().getLicInsuredDtls().getName());
			setModalPremium(licPolicyMst.getModalPrem());
			setServiceTax(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem());
			setTotalAmt(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getBasicPrem() + licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getTaxOnPrem());
			setSumAssured(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getSumAssured());
			setPayNature(licPolicyMst.getLicOblApplicationMst().getLicProductValueMst().getPayNature());
			setRiskStartDate(licPolicyMst.getRiskStartDate());
			setLastPaidDueDate(licPolicyMst.getLastPaidDueDate());
			
			setStatus(licPolicyMst.getPolicyStatus());
			//setLastPaidDueDate(licPolicyDtls.getDueDate()); to do later
			
			
			// startDate :: Latest/Current/Bigger Date
			// endDate :: Other Date
			
			
			totalModalPrem = 0.0;
			totalServiceTax = 0.0;
			totalAmountReceivable = 0.0;
			
			
			totalAmt = 0.0;
			if(!(licPolicyDtlsList == null || licPolicyDtlsList.size()==0)){
				
				totalAmount = 0.0;
				for(int i=0; i<licPolicyDtlsList.size(); i++){
					
					Date dueDate = licPolicyDtlsList.get(i).getDueDate();
					Long healthDuration = licPolicyDtlsList.get(i).getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getLicProductMst().getHlthDeclDuration();
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(dueDate);
					cal.add(Calendar.DATE, healthDuration.intValue());
					
					if(Calendar.getInstance().compareTo(cal) > 0){
						licPolicyDtlsList.get(i).setHlthReqdFlag("Y");
						renderedBankDtls = true;
						
						// for health add lead time
						if(healthGivenFlag.equals("N")){
							healthGivenFlag = "Y";
							// add 10 days lead time
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(now);
							calendar.add(Calendar.DATE, 10);
							now = calendar.getTime();
						}
					}else{
						licPolicyDtlsList.get(i).setHlthReqdFlag("N");
					}
					licPolicyDtlsList.get(i).setFineAmt(0.0);
					licPolicyDtlsList.get(i).setPremAmt(licPolicyDtlsList.get(i).getLicPolicyMst().getModalPrem());
					
					if(licPolicyDtlsList.get(i).getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getLicProductMst().getId()>=101){
						if(licPolicyDtlsList.get(i).getRenewalMonth()<12){
							licPolicyDtlsList.get(i).setServiceTax(Math.ceil(((licPolicyDtlsList.get(i).getLicPolicyMst().getModalPrem() * 3.09)/100)));
						}else{
							licPolicyDtlsList.get(i).setServiceTax(Math.ceil(((licPolicyDtlsList.get(i).getLicPolicyMst().getModalPrem() * 1.545)/100)));
						}
					}else{
						licPolicyDtlsList.get(i).setServiceTax(0.0);
					}
					totalAmount = totalAmount + (licPolicyDtlsList.get(i).getFineAmt() + licPolicyDtlsList.get(i).getPremAmt()+ licPolicyDtlsList.get(i).getServiceTax());
					totalPremAmount = totalPremAmount + licPolicyDtlsList.get(i).getPremAmt();
				}
				
				Integer fineMnth = CalenderUtil.monthDiff(licPolicyDtlsList.get(0).getDueDate(),now);
				
				List<LicProdFineMst> fineMsts = licProdFineMstService.findLicProdFineMstByProdIdAndFineMnth(licPolicyDtlsList.get(0).getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getLicProductMst().getId(), fineMnth);
				
				if(fineMsts==null || fineMsts.size()==0 || fineMsts.contains(null)){
					renderedBankDtls = false;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error : ", "Policy is Lapsed and Closed"));
					return;
				}
				
				
				String paynature = licPolicyDtlsList.get(0).getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getPayNature();
				Double finePct = 0.0;
				if(paynature.equals("Y")){
					finePct = fineMsts.get(0).getyFinePct();
				}else if(paynature.equals("H")){
					finePct = fineMsts.get(0).gethFinePct();
				}else if(paynature.equals("Q")){
					finePct = fineMsts.get(0).getqFinePct();
				}else if(paynature.equals("M")){
					finePct = fineMsts.get(0).getmFinePct();
				}
				
				fineAmount  = Math.ceil((finePct * licPolicyDtlsList.get(0).getLicPolicyMst().getModalPrem())-(licPolicyDtlsList.get(0).getLicPolicyMst().getModalPrem()*licPolicyDtlsList.size()));
				totalModalPrem = licPolicyDtlsList.get(0).getLicPolicyMst().getModalPrem()*licPolicyDtlsList.size();
				
				if(fineAmount>totalModalPrem){
					List<LicProdFineMst> fineMstsRepeat = licProdFineMstService.findLicProdFineMstByProdIdAndFineMnth(licPolicyDtlsList.get(0).getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getLicProductMst().getId(), fineMnth - 1);
					String paynatureRepeat = licPolicyDtlsList.get(0).getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getPayNature();
					Double finePctRepreat = 0.0;
					if(paynatureRepeat.equals("Y")){
						finePctRepreat = fineMstsRepeat.get(0).getyFinePct();
					}else if(paynatureRepeat.equals("H")){
						finePctRepreat = fineMstsRepeat.get(0).gethFinePct();
					}else if(paynatureRepeat.equals("Q")){
						finePctRepreat = fineMstsRepeat.get(0).getqFinePct();
					}else if(paynatureRepeat.equals("M")){
						finePctRepreat = fineMstsRepeat.get(0).getmFinePct();
					}
					fineAmount  = Math.ceil((finePctRepreat * licPolicyDtlsList.get(0).getLicPolicyMst().getModalPrem()) - (licPolicyDtlsList.get(0).getLicPolicyMst().getModalPrem() * licPolicyDtlsList.size()));
				}
				totalServiceTax = 0.0;
				for(LicPolicyDtls dtls:licPolicyDtlsList){
					if(dtls.getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getLicProductMst().getId()>=101){
						if(dtls.getRenewalMonth()<12){
							totalServiceTax = totalServiceTax + ((dtls.getLicPolicyMst().getModalPrem() * 3.09)/100);
						}else{
							totalServiceTax = totalServiceTax + ((dtls.getLicPolicyMst().getModalPrem() * 1.545)/100);
						}
					}else{
						totalServiceTax = totalServiceTax + 0.0;
					}
				}
				
				totalServiceTax = Math.ceil(totalServiceTax);
				totalAmountReceivable = totalModalPrem + totalServiceTax + fineAmount;
			}
			renderedDetails = true;
		}catch(Exception e){
			log.info("RenewalBranchMasterAction search Error : ", e);
		}
	}
	
	
	public void validate(ActionEvent actionEvent){
		try{
			if(checkDouble(totalAmountReceivable)<=0.0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Please Select the Check box"));
				renderedSave = false;
				return;
			}
			
			totalReceivable = (checkDouble(totalAmountReceivable)-(checkDouble(ddMakingCharges)+checkDouble(otherCharges)));
			totalDdChequeAmt = 0.0;
			
			for(LicRnlPaymentDtls obj:licRnlPaymentDtlsList){
				totalDdChequeAmt = totalDdChequeAmt+checkDouble(obj.getAmount());
			 }		
			 
			 if(totalReceivable>=totalDdChequeAmt){
				 setBalanceRcvInCash(totalReceivable-totalDdChequeAmt);
			 }else{
				 setBalanceRcvInCash(0.0);
			 }
			 
			 if(totalReceivable<totalDdChequeAmt){
				 setAmtToReturn(totalDdChequeAmt-totalReceivable);
			 }else{
				 setAmtToReturn(0.0);
			 }
			 
			 if(!(licPolicyDtlsList== null || licPolicyDtlsList.size()==0)){
				 if(licPolicyDtlsList.get(0).getHlthReqdFlag().equals("Y")){
					 if(!(BankValidation.bankAccountValidation(bankName, acctNo))){
						 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error : ", "Account Number Not Valid"));
						 renderedSave = false;
						 return;
					 } 
				 } 
			 }
			 
			List<AgentRlns> list = agentRlnsService.findValidAgentByPhase(phaseId, agCode);
				
			if(list == null || list.size() == 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Agent Not Valid, Please Try Again With Valid Agent"));
				renderedSave = false;
				return;
			}
			 			 
			 renderedSave = true;
		}catch(Exception e){
			log.info("RenewalBranchMasterAction validate Error : ", e);
		}
	}
	
	
	public void save(){
		try{
			Date maxDate = null;
			/*List<AgentRlns> list = agentRlnsService.findValidAgentByPhase(phaseId, agCode);
			
			if(list == null || list.size() == 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Agent Not Valid, Please Try Again With Valid Agent"));
				renderedSave = false;
				return;
			}*/
			
			Date now  = new Date();
			
			//LicPolicyBankDtls
			LicPolicyBankDtls licPolicyBankDtls = new LicPolicyBankDtls();
			licPolicyBankDtls.setAccountNo(acctNo);
			licPolicyBankDtls.setAccountType(acctType);
			licPolicyBankDtls.setAddress(branchAdd);
			licPolicyBankDtls.setBankName(bankName);
			licPolicyBankDtls.setBranchName(branchName);
			licPolicyBankDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licPolicyBankDtls.setCreatedDate(now);
			licPolicyBankDtls.setDeleteFlag("N");
			licPolicyBankDtls.setIfscCode(ifsCode);
			licPolicyBankDtls.setMicrCode(micrCode);
			licPolicyBankDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPolicyBankDtls.setModifiedDate(now);
			
			
			List<Long> hubIds = licBranchHubPicMappingService.findHubIdForBranchIdByProcessName(loginAction.getUserList().get(0).getBranchMst().getBranchId(), "RNL");
			
			if(hubIds == null || hubIds.size() == 0 || hubIds.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Hub For that branch not Defined"));
				return;
			}
			
			LicHubMst licHubMst = licHubMstService.findById(hubIds.get(0));
			AgentMst agentMst = agentMstService.findByAgCode(agCode);
			PhaseMst phaseMst = phaseMstService.findByPhaseId(phaseId);	
			
			if(!(selectedList == null || selectedList.size() == 0)){
				licPolicyDtlsList.add(selectedList.get(0));
			}
			
			//LicRnlPaymentMst
			LicRnlPaymentMst licRnlPaymentMst = new LicRnlPaymentMst();
			licRnlPaymentMst.setDdCharges(ddMakingCharges);
			licRnlPaymentMst.setOtherCharges(otherCharges);
			licRnlPaymentMst.setPayDate(now);
			licRnlPaymentMst.setProcessName("RNL");
			licRnlPaymentMst.setTotalReceivable(totalReceivable);
			licRnlPaymentMst.setTotalReceived(totalReceivable);
			licRnlPaymentMst.setSalesTax(0.0);
			licRnlPaymentMst.setServiceTax(0.0);
			licRnlPaymentMst.setServiceCharge(0.0);
			licRnlPaymentMst.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licRnlPaymentMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licRnlPaymentMst.setCreatedDate(now);
			licRnlPaymentMst.setModifiedDate(now);
			licRnlPaymentMst.setDeleteFlag("N");
			licRnlPaymentMst.setFineAmt(fineAmount);
			
			//payment for cash 
			if(!getBalanceRcvInCash().equals(0.0)){
				LicRnlPaymentDtls licRnlPaymentDtls = new LicRnlPaymentDtls();
				licRnlPaymentDtls.setAmount(getBalanceRcvInCash());
				licRnlPaymentDtls.setPayMode("C");
				licRnlPaymentDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licRnlPaymentDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licRnlPaymentDtls.setCreatedDate(now);
				licRnlPaymentDtls.setModifiedDate(now);
				licRnlPaymentDtls.setDeleteFlag("N");
				licRnlPaymentDtlsList.add(licRnlPaymentDtls);
			}
			
			if(licRnlPaymentDtlsList.size() > 1){
				licRnlPaymentMst.setPayMode("B");
			}else{
				licRnlPaymentMst.setPayMode(licRnlPaymentDtlsList.get(0).getPayMode());
			}
			
			licRnlPaymentMst.setFineAmt(fineAmount);
			for (LicRnlPaymentDtls obj : licRnlPaymentDtlsList) {
				obj.setLicRnlPaymentMst(licRnlPaymentMst);
			}
			licRnlPaymentMst.setLicRnlPaymentDtlses(licRnlPaymentDtlsList);
			
			List<LicPolicyPaymentMapping> licPolicyPaymentMappings = new ArrayList<LicPolicyPaymentMapping>();
			
			//For cash work
			LicBrnhHubPicMapDtls licBrnhHubPicMapDtls = new LicBrnhHubPicMapDtls();
			licBrnhHubPicMapDtls.setBranchHubPicFlag("B2H");
			licBrnhHubPicMapDtls.setDispatchListDate(now);
			licBrnhHubPicMapDtls.setTotalAmount(totalReceivable);
			licBrnhHubPicMapDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setCreatedDate(now);
			licBrnhHubPicMapDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setModifiedDate(now);
			licBrnhHubPicMapDtls.setDeleteFlag("N");
			
			LicBrnhHubPicPodDtls licBrnhHubPicPodDtls = new LicBrnhHubPicPodDtls();
			licBrnhHubPicPodDtls.setBranchHubPicFlag("B2H");
			licBrnhHubPicPodDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicPodDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicPodDtls.setCreatedDate(now);
			licBrnhHubPicPodDtls.setModifiedDate(now);
			licBrnhHubPicPodDtls.setDeleteFlag("N");
			licBrnhHubPicPodDtls.setEmployee(userMstService.findById(loginAction.getUserList().get(0).getUserid()));
			licBrnhHubPicPodDtls.setPodNo(now.getTime()+"");
			licBrnhHubPicPodDtls.setPodDate(now);
			
			LicMarkingToQcDtls licMarkingToQcDtls = new LicMarkingToQcDtls();
			licMarkingToQcDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licMarkingToQcDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licMarkingToQcDtls.setCreatedDate(now);
			licMarkingToQcDtls.setModifiedDate(now);
			licMarkingToQcDtls.setDeleteFlag("N");
			licMarkingToQcDtls.setConsldBy(loginAction.getUserList().get(0).getUserid());
			licMarkingToQcDtls.setConsldDate(now);
			licMarkingToQcDtls.setConsldMrkFlag("Y");
			licMarkingToQcDtls.setIndMrkBy(loginAction.getUserList().get(0).getUserid());
			licMarkingToQcDtls.setIndMrkDate(now);
			licMarkingToQcDtls.setIndMrkFlag("Y");
			
			//For cash work End			
			for(LicPolicyDtls dtls: licPolicyDtlsList){
				
				//LicPolicyDtls
				dtls.setAgentMst(agentMst);
				dtls.setRenewalType("NORMAL");
				dtls.setPaidFlag("Y");
				dtls.setPayDate(now);
				dtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				dtls.setModifiedDate(now);
				dtls.setHlthReqdFlag(healthGivenFlag);
				dtls.setBranchMst(loginAction.getUserList().get(0).getBranchMst());
				dtls.setPayMode(licRnlPaymentMst.getPayMode());
				
				if(dtls.getPayMode().equals("C")){
					dtls.setOblHubMst(licHubMst);
					dtls.setBrnhHubMapDtls(licBrnhHubPicMapDtls);
					dtls.setBrnhHubPodDtls(licBrnhHubPicPodDtls);
					dtls.setLicMarkingToQcDtls(licMarkingToQcDtls);
					dtls.setBrnhHubDespFlag("Y");
					dtls.setPremListReadyFlag("Y");
					dtls.setHealthValidated("Y");
				}
				
				if(!dtls.getPayMode().equals("C")){
					dtls.setOblHubMst(licHubMst);
					dtls.setBrnhHubDespFlag("N");
					dtls.setPremListReadyFlag("N");
					dtls.setHealthValidated("Y");
					//dtls.setOblPicBranchMst(); // sent pic not understood
				}
							
				if(healthGivenFlag.equals("Y")){
					dtls.setOblHubMst(dtls.getLicPolicyMst().getSentHub());
					dtls.setOblPicBranchMst(dtls.getLicPolicyMst().getPicBranchMst());
					dtls.setBrnhHubDespFlag("N");
					dtls.setPremListReadyFlag("N");
					dtls.setBrnhHubMapDtls(null);
					dtls.setBrnhHubPodDtls(null);
					dtls.setLicMarkingToQcDtls(null);
					dtls.setHealthValidated(null);				
				}
				
				//LicRnlBusinessTxn
				LicRnlBusinessTxn licRnlBusinessTxn = new LicRnlBusinessTxn();
				licRnlBusinessTxn.setBusinessValue(dtls.getLicPolicyMst().getModalPrem());
				licRnlBusinessTxn.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licRnlBusinessTxn.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licRnlBusinessTxn.setCreatedDate(now);
				licRnlBusinessTxn.setModifiedDate(now);
				licRnlBusinessTxn.setDeleteFlag("N");
				licRnlBusinessTxn.setFreeQtyFlag("N");
				licRnlBusinessTxn.setFrozenFlag("N");
				licRnlBusinessTxn.setReceivable(dtls.getLicPolicyMst().getModalPrem());
				licRnlBusinessTxn.setReceived(dtls.getLicPolicyMst().getModalPrem());
				licRnlBusinessTxn.setTransStatus("D");
				licRnlBusinessTxn.setTransDate(now);
				licRnlBusinessTxn.setTransferFlag("N");
				licRnlBusinessTxn.setAgentMst(agentMst);
				licRnlBusinessTxn.setPhaseMst(phaseMst);
				licRnlBusinessTxn.setLicProductMst(dtls.getLicPolicyMst().getLicOblApplicationMst().getLicProductValueMst().getLicProductMst());
				dtls.setLicRnlBusinessTxn(licRnlBusinessTxn);
				licRnlBusinessTxn.setLicPolicyDtls(dtls);
				
				
				//LicPolicyPaymentMapping			
				LicPolicyPaymentMapping licPolicyPaymentMapping = new LicPolicyPaymentMapping();
				licPolicyPaymentMapping.setLicPolicyDtls(dtls);
				licPolicyPaymentMapping.setLicRnlPaymentMst(licRnlPaymentMst);
				licPolicyPaymentMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licPolicyPaymentMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licPolicyPaymentMapping.setCreatedDate(now);
				licPolicyPaymentMapping.setModifiedDate(now);
				licPolicyPaymentMapping.setDeleteFlag("N");
				
				licPolicyPaymentMappings.add(licPolicyPaymentMapping);				
				//licPolicyBankDtlsList.add(licPolicyBankDtls);
			}
			
			for (LicPolicyDtls dtls : licPolicyDtlsList) {
				dtls.setLicPolicyPaymentMappings(licPolicyPaymentMappings);
				if(dtls.getHlthReqdFlag().equals("Y")){
					dtls.setLicPolicyBankDtls(licPolicyBankDtls);
				}
				
				/*  Last Paid Due Date Update Start */
				maxDate = dtls.getDueDate();
				/*  Last Paid Due Date Update End */
				
				log.info("Policy "+dtls.getId() + " "+dtls.getDueDate());
			}
			
			licPolicyMst.setLastPaidDueDate(maxDate);
			
			Boolean status = licPolicyDtlsService.saveNormalRenewalBranchEntry(licPolicyDtlsList,licPolicyMst);

			if (status){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Success : ", "Renewal At Branch Successful"));
					refresh();
			} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error : ", "Renewal At Branch Unsuccessful"));
			}
		}catch(Exception e){
			log.info("RenewalBranchMasterAction save Error : ", e);
		}
	}
	
	public void back(){
		renderedSave = false;
	}
	
	
	public void addPaymentDtls(){
		Date now = new Date();
		LicRnlPaymentDtls licRnlPaymentDtls = new LicRnlPaymentDtls();
		licRnlPaymentDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licRnlPaymentDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licRnlPaymentDtls.setCreatedDate(now);
		licRnlPaymentDtls.setModifiedDate(now);
		licRnlPaymentDtls.setDeleteFlag("N");
		licRnlPaymentDtlsList.add(licRnlPaymentDtls);
	}

	public void deletePaymentDtls(LicRnlPaymentDtls licRnlPaymentDtls){
		licRnlPaymentDtlsList.remove(licRnlPaymentDtls);
	}
	
	public Double checkDouble(Double value){
		if(value != null){
			return value;
		}else{
			return 0.0;
		}
	}
	
	public void searchByIfscCode(){
		try{
			List<RbiBankDtls> list = rbiBankDtlsService.findRbiBankDtlsByIfscCode(ifsCode);
			
			if(!(list == null || list.size() == 0)){
				bankName = list.get(0).getBankName();
				branchName = list.get(0).getBranchName();
				branchAdd = list.get(0).getAddress();
				micrCode = list.get(0).getMicrCode();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "IFSC Code Not Valid"));
				ifsCode = null;
				bankName = null;
				branchName = null;
				branchAdd = null;
				micrCode = null;
				acctNo = null;
				acctType = null;
				return;
			}
		}catch(Exception e){
			log.info("RenewalBranchMasterAction searchByIfscCode Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public Boolean getRenderedDetails() {
		return renderedDetails;
	}

	public void setRenderedDetails(Boolean renderedDetails) {
		this.renderedDetails = renderedDetails;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public List<LicPolicyDtls> getLicPolicyDtlsList() {
		return licPolicyDtlsList;
	}

	public void setLicPolicyDtlsList(List<LicPolicyDtls> licPolicyDtlsList) {
		this.licPolicyDtlsList = licPolicyDtlsList;
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

	public LicPolicyDtls getLicPolicyDtls() {
		return licPolicyDtls;
	}

	public void setLicPolicyDtls(LicPolicyDtls licPolicyDtls) {
		this.licPolicyDtls = licPolicyDtls;
	}

	public List<LicPolicyDtls> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicPolicyDtls> selectedList) {
		this.selectedList = selectedList;
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

	public List<PhaseMst> getPhaseMsts() {
		return phaseMsts;
	}

	public void setPhaseMsts(List<PhaseMst> phaseMsts) {
		this.phaseMsts = phaseMsts;
	}

	public List<LicRnlPaymentDtls> getLicRnlPaymentDtlsList() {
		return licRnlPaymentDtlsList;
	}

	public void setLicRnlPaymentDtlsList(
			List<LicRnlPaymentDtls> licRnlPaymentDtlsList) {
		this.licRnlPaymentDtlsList = licRnlPaymentDtlsList;
	}

	public Double getDdMakingCharges() {
		return ddMakingCharges;
	}

	public void setDdMakingCharges(Double ddMakingCharges) {
		this.ddMakingCharges = ddMakingCharges;
	}

	public Double getOtherCharges() {
		return otherCharges;
	}

	public void setOtherCharges(Double otherCharges) {
		this.otherCharges = otherCharges;
	}

	public String getPaymentRemarks() {
		return paymentRemarks;
	}

	public void setPaymentRemarks(String paymentRemarks) {
		this.paymentRemarks = paymentRemarks;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalReceivable() {
		return totalReceivable;
	}

	public void setTotalReceivable(Double totalReceivable) {
		this.totalReceivable = totalReceivable;
	}

	public Double getTotalDdChequeAmt() {
		return totalDdChequeAmt;
	}

	public void setTotalDdChequeAmt(Double totalDdChequeAmt) {
		this.totalDdChequeAmt = totalDdChequeAmt;
	}

	public Double getBalanceRcvInCash() {
		return balanceRcvInCash;
	}

	public void setBalanceRcvInCash(Double balanceRcvInCash) {
		this.balanceRcvInCash = balanceRcvInCash;
	}

	public Double getAmtToReturn() {
		return amtToReturn;
	}

	public void setAmtToReturn(Double amtToReturn) {
		this.amtToReturn = amtToReturn;
	}

	public Boolean getRenderedSave() {
		return renderedSave;
	}

	public void setRenderedSave(Boolean renderedSave) {
		this.renderedSave = renderedSave;
	}

	public Double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public Double getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}

	public Boolean getRenderedAdvPolicy() {
		return renderedAdvPolicy;
	}

	public void setRenderedAdvPolicy(Boolean renderedAdvPolicy) {
		this.renderedAdvPolicy = renderedAdvPolicy;
	}

	public DataTable getLicPolicyDtlsListBinding() {
		return licPolicyDtlsListBinding;
	}

	public void setLicPolicyDtlsListBinding(DataTable licPolicyDtlsListBinding) {
		this.licPolicyDtlsListBinding = licPolicyDtlsListBinding;
	}

	public Date getSysdate() {
		return sysdate;
	}

	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}

	public Double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Double fineAmount) {
		this.fineAmount = fineAmount;
	}

	public Double getTotalModalPrem() {
		return totalModalPrem;
	}

	public void setTotalModalPrem(Double totalModalPrem) {
		this.totalModalPrem = totalModalPrem;
	}

	public Double getTotalServiceTax() {
		return totalServiceTax;
	}

	public void setTotalServiceTax(Double totalServiceTax) {
		this.totalServiceTax = totalServiceTax;
	}

	public Double getTotalAmountReceivable() {
		return totalAmountReceivable;
	}

	public void setTotalAmountReceivable(Double totalAmountReceivable) {
		this.totalAmountReceivable = totalAmountReceivable;
	}

	public Double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}

	public String getPayNature() {
		return payNature;
	}

	public void setPayNature(String payNature) {
		this.payNature = payNature;
	}

	public Date getRiskStartDate() {
		return riskStartDate;
	}

	public void setRiskStartDate(Date riskStartDate) {
		this.riskStartDate = riskStartDate;
	}

	public List<LicPolicyDtls> getLicAdvPolicyDtlsList() {
		return licAdvPolicyDtlsList;
	}

	public void setLicAdvPolicyDtlsList(List<LicPolicyDtls> licAdvPolicyDtlsList) {
		this.licAdvPolicyDtlsList = licAdvPolicyDtlsList;
	}

	public Boolean getRenderedBankDtls() {
		return renderedBankDtls;
	}

	public void setRenderedBankDtls(Boolean renderedBankDtls) {
		this.renderedBankDtls = renderedBankDtls;
	}

	public String getIfsCode() {
		return ifsCode;
	}

	public void setIfsCode(String ifsCode) {
		this.ifsCode = ifsCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAdd() {
		return branchAdd;
	}

	public void setBranchAdd(String branchAdd) {
		this.branchAdd = branchAdd;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public LicPolicyMst getLicPolicyMst() {
		return licPolicyMst;
	}

	public void setLicPolicyMst(LicPolicyMst licPolicyMst) {
		this.licPolicyMst = licPolicyMst;
	}

}
