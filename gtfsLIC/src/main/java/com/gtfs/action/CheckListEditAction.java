package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.AgentRlns;
import com.gtfs.bean.LicCollBenPctMst;
import com.gtfs.bean.LicHighSaDiscMst;
import com.gtfs.bean.LicModeRebateMst;
import com.gtfs.bean.LicNsapDocMst;
import com.gtfs.bean.LicNsapMst;
import com.gtfs.bean.LicOblChecklist;
import com.gtfs.bean.LicPremFreqAllowARMst;
import com.gtfs.bean.LicProductMst;
import com.gtfs.bean.LicProductValueMst;
import com.gtfs.bean.LicTabPremMst;
import com.gtfs.bean.PhaseMst;
import com.gtfs.dto.ChecklistDto;
import com.gtfs.dto.PremiumDto;
import com.gtfs.service.interfaces.AgentRlnsService;
import com.gtfs.service.interfaces.LicCollBenPctMstService;
import com.gtfs.service.interfaces.LicHighSaDiscMstService;
import com.gtfs.service.interfaces.LicModeRebateMstService;
import com.gtfs.service.interfaces.LicNsapDocMstService;
import com.gtfs.service.interfaces.LicNsapMstService;
import com.gtfs.service.interfaces.LicOblChecklistService;
import com.gtfs.service.interfaces.LicPremFreqAllowARMstService;
import com.gtfs.service.interfaces.LicProductMstService;
import com.gtfs.service.interfaces.LicProductValueMstService;
import com.gtfs.service.interfaces.LicTabPremMstService;
import com.gtfs.service.interfaces.PhaseMstService;
import com.gtfs.util.Age;
import com.gtfs.util.AgeCalculation;

@Component
@Scope("session")
public class CheckListEditAction implements Serializable{
	private Logger log = Logger.getLogger(CheckListEditAction.class);
	
	@Autowired
	private LicProductMstService licProductMstService;
	@Autowired
	private LicProductValueMstService licProductValueMstService;
	@Autowired
	private PhaseMstService phaseMstService;
	@Autowired
	private LicOblChecklistService licOblChecklistService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private AgentRlnsService agentRlnsService;
	@Autowired
	private LicTabPremMstService licTabPremMstService;
	@Autowired
	private LicPremFreqAllowARMstService licPremFreqAllowARMstService;
	@Autowired
	private LicNsapDocMstService licNsapDocMstService;
	@Autowired
	private LicNsapMstService licNsapMstService;
	@Autowired
	private LicModeRebateMstService licModeRebateMstService;
	@Autowired
	private LicHighSaDiscMstService licHighSaDiscMstService;
	@Autowired
	private LicCollBenPctMstService licCollBenPctMstService;
	
	private String applicationNo;
	private Boolean renderedSave;
	private Boolean disabledSave;
	private Boolean renderedArRider;
	private Integer age;
	private Boolean renderedSearchPanel;
	private Boolean renderedCheckListEditPanel;
	
	private ChecklistDto checklistDto;
	private Set<Long> termList = new TreeSet<Long>();
	private List<LicProductValueMst> ProductValueMstList = new ArrayList<LicProductValueMst>();	
	private List<SelectItem> payModeList = new ArrayList<SelectItem>();
	private List<LicNsapDocMst> licNsapDocMsts = new ArrayList<LicNsapDocMst>();	
	private List<PhaseMst> phases = new ArrayList<PhaseMst>();
	private List<LicProductMst> licProductMsts = new ArrayList<LicProductMst>();
	private List<LicOblChecklist> licOblChecklists = new ArrayList<LicOblChecklist>();
	
	
	// global important variables
	private PremiumDto premiumDto;	
	
	@PostConstruct
	public void init(){
		phases = phaseMstService.findBusinessPhasesForCurrentDate();
		licProductMsts = licProductMstService.findActiveLicProductMstForChecklist();
	}
	
	public void refresh(){
		try{
			applicationNo = null;
			age = 0;
			checklistDto = new ChecklistDto();
			checklistDto.setIdentityProof(true);
			renderedSave = false;
			renderedArRider = true;
			termList.clear();
			payModeList.clear();
			premiumDto = new PremiumDto();
			disabledSave = false;
			renderedSearchPanel = true;
			renderedCheckListEditPanel = false;
			phases = phaseMstService.findBusinessPhasesForCurrentDate();
			licProductMsts = licProductMstService.findActiveLicProductMstForChecklist();
		}catch(Exception e){
			log.info("CheckListAction refresh Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/checklistEdit.xhtml";
	}
	
	public void search(ActionEvent actionEvent) {
		try{
			
			if(applicationNo != null){
				if(licOblChecklists != null){
					licOblChecklists.clear();
				}
				
				licOblChecklists = licOblChecklistService.findApplicationForCheckListEditByApplicationNo(applicationNo, loginAction.getUserList().get(0).getBranchMst());
				
				if(licOblChecklists == null || licOblChecklists.size() == 0 || licOblChecklists.contains(null)){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error :", "Application Number Not Found or Priliminary Data Entry Completed"));
					renderedCheckListEditPanel = false;
					renderedSearchPanel = true;
					return;
				}
				
				checklistDto.setAgCode(licOblChecklists.get(0).getAgCode());
				checklistDto.setOblApplNo(licOblChecklists.get(0).getOblApplNo());
				checklistDto.setPhaseId(licOblChecklists.get(0).getPhaseMst().getPhaseId());
				checklistDto.setProposerName(licOblChecklists.get(0).getProposerName());
				checklistDto.setProposerDob(licOblChecklists.get(0).getProposerDob());
				
				if(licOblChecklists.get(0).getProposerName().equals(licOblChecklists.get(0).getInsuredName()) && licOblChecklists.get(0).getProposerDob().equals(licOblChecklists.get(0).getInsuredDob())){
					checklistDto.setInsuredPropSameFlag(true);
				}
				
				checklistDto.setInsuredName(licOblChecklists.get(0).getInsuredName());
				checklistDto.setInsuredDob(licOblChecklists.get(0).getInsuredDob());
				calculateAge(checklistDto.getInsuredDob());
				checklistDto.setProductId(licOblChecklists.get(0).getLicProductValueMst().getLicProductMst().getId());
				productChangeListener();
				checklistDto.setPolicyTerm(licOblChecklists.get(0).getLicProductValueMst().getPolicyTerm());
				checklistDto.setPremiumPayingTerm(licOblChecklists.get(0).getLicProductValueMst().getPremiumPayingTerm());
				checklistDto.setPayMode(licOblChecklists.get(0).getLicProductValueMst().getPayNature());
				checklistDto.setSumAssured(licOblChecklists.get(0).getLicProductValueMst().getSumAssured());
				checklistDto.setBusinessDateOf(licOblChecklists.get(0).getBnsDateOf());
				sumAssuredChangeListener();
				checklistDto.setNsapDocId(licOblChecklists.get(0).getLicProductValueMst().getLicNsapDocMst().getId());				
				
				if(licOblChecklists.get(0).getLicProductValueMst().getArFlag().equals("Y")){
					checklistDto.setArRiderFlag(true);
					checklistDto.setArRiderAmt(licOblChecklists.get(0).getLicProductValueMst().getArAmt());
				}else{
					checklistDto.setArRiderFlag(false);
					checklistDto.setArRiderAmt(0.0);
				}
				
				if(licOblChecklists.get(0).getDobAgeFlag().endsWith("Y")){
					checklistDto.setDobAndAgeApplicable(true);
				}
				
				if(licOblChecklists.get(0).getPanNoFlag().equals("Y")){
					checklistDto.setPanApplicable(true);
				}
				
				if(licOblChecklists.get(0).getBankAccnoFlag().equals("Y")){
					checklistDto.setBankAcNoApplicable(true);
				}
				
				if(licOblChecklists.get(0).getPhotoFlag().equals("Y")){
					checklistDto.setPhotoApplicable(true);
				}
				
				if(licOblChecklists.get(0).getPrpslSignedFlag().equals("Y")){
					checklistDto.setProposalFormSigned(true);
				}
				
				if(licOblChecklists.get(0).getBiFlag().equals("Y")){
					checklistDto.setBiSigned(true);
				}
				
				if(licOblChecklists.get(0).getProperlyFilledFlag().equals("Y")){
					checklistDto.setProposalFormProperlyFillup(true);
				}
				
				if(licOblChecklists.get(0).getLoadFlag().equals("Y")){
					checklistDto.setLoadingNeeded(true);
				}
				
				if(licOblChecklists.get(0).getAddrProofFlag().equals("Y")){
					checklistDto.setAddressProof(true);
				}
				
				if(licOblChecklists.get(0).getIncomeProofFlag().equals("Y")){
					checklistDto.setIncomeProof(true);
				}
				
				renderedCheckListEditPanel = true;
				renderedSearchPanel = false;
				
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_ERROR,"Error :", "Please Enter an Application Number"));
				renderedCheckListEditPanel = false;
				return;
			}
		}catch(Exception e){
			log.info("CheckListEditAction search Error : ", e);
		}
    }
	
	public void insuredDobChangeListener(){
		calculateAge(checklistDto.getInsuredDob());
	}
	
	public void calculateAge(Date dob){
		if(dob != null){
			Age age = AgeCalculation.calculateAge(dob);
			checklistDto.setYear(age.getYear());
			checklistDto.setMonth(age.getMonth());
			checklistDto.setDay(age.getDay());
		}
	}
	
	public void productChangeListener(){
		try{
			termList.clear();
			payModeList.clear();
			
			Integer minAge = licTabPremMstService.findMinAgeByProdId(checklistDto.getProductId()).get(0);
			
			if(minAge == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Policy Term Not Valid For Entered Age"));
				return;
			}
			
			if(minAge != null){
				if(minAge <= checklistDto.getYear()){
					
					if (checklistDto.getMonth() >= 6) {
						age = checklistDto.getYear() + 1;
					} else {
						age = checklistDto.getYear();
					}
					
					for(Long obj : licTabPremMstService.findTermsByAgeProdId(age, checklistDto.getProductId())){
						if(obj == null){
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                    "Error : ", "Policy Term Not Valid For Entered Age"));
							return;
						}else{
							termList.add(obj);
						}
					}
				}
			}
			
			String paymode = licPremFreqAllowARMstService.findPayModeByProdId(checklistDto.getProductId()).get(0);
			
			if(paymode != null){
				if(paymode.contains("Y")){
					payModeList.add(new SelectItem("Y","Yearly"));
				}
				if(paymode.contains("H")){
					payModeList.add(new SelectItem("H","Half-Yearly"));
				}
				/*if(paymode.contains("Q")){
					payModeList.add(new SelectItem("Q","Quaterly"));
				}
				if(paymode.contains("M")){
					payModeList.add(new SelectItem("M","Monthly"));
				}*/
				if(paymode.contains("S")){
					payModeList.add(new SelectItem("S","Single"));
				}
			}
			
			List<Object> list = licPremFreqAllowARMstService.checkForAddbRiderByAgeAndProdId(age, checklistDto.getProductId());
			
			if(list == null || list.size() == 0 || list.contains(null)){
				renderedArRider=true;
			}else{
				renderedArRider=false;
				Object[] arr=(Object[]) list.get(0);
				checklistDto.setArRiderAmt((Double) arr[1]); // need to be done later
			}	
		}catch(Exception e){
			log.info("CheckListAction productChangeListener Error : ", e);
		}
	}
	
	public void sumAssuredChangeListener(){
		try{
			licNsapDocMsts = licNsapDocMstService.findLicNsapDocMstByAge(age, checklistDto.getSumAssured(), checklistDto.getProductId());
		}catch(Exception e){
			log.info("CheckListAction sumAssuredChangeListener Error : ", e);
		}
	}
	
	public void insuredProposerSameFlagListener(){
		if(checklistDto.getInsuredPropSameFlag() == true){
			checklistDto.setInsuredName(checklistDto.getProposerName());
			checklistDto.setInsuredDob(checklistDto.getProposerDob());
			calculateAge(checklistDto.getProposerDob());
		}else{
			checklistDto.setInsuredName(null);
			checklistDto.setInsuredDob(null);
			checklistDto.setYear(null);
			checklistDto.setDay(null);
			checklistDto.setMonth(null);
		}
	}
	
	public Boolean check(){
		try{
			List<AgentRlns> list = agentRlnsService.findValidAgentByPhase(checklistDto.getPhaseId(), checklistDto.getAgCode());
			List<LicOblChecklist> checkList = new ArrayList<LicOblChecklist>();
			
			if(list == null || list.size() == 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Agent Not Valid, Please Try Again With Valid Agent"));
				return false;
			}
			
			if(!(applicationNo.equals(checklistDto.getOblApplNo()))){
				checkList = licOblChecklistService.findApplicationByApplicationNo(checklistDto.getOblApplNo());
			}
			
			for(LicOblChecklist licOblChecklist : checkList){
				if(licOblChecklist.getOblApplNo().equals(checklistDto.getOblApplNo())){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error : ", "Application Number Already Exits"));
					return false;
				}
			}
			
			if(checklistDto.getIdentityProof().equals(false)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Please Select Identity Proof"));
				return false;
			}
			
			LicPremFreqAllowARMst licPremFreqAllowARMst = licPremFreqAllowARMstService.checkForSumAssuredByProdIdTerm(checklistDto.getPolicyTerm(), checklistDto.getProductId()).get(0);
			
			if(licPremFreqAllowARMst.equals(null) || licPremFreqAllowARMst == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Sum Assured Not Found As Per Product Term"));
				return false;
			}
			
			if(licPremFreqAllowARMst.getMinSa() > checklistDto.getSumAssured()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
						"Please Enter Correct Sum Assured (Sum Assured Range : " + licPremFreqAllowARMst.getMinSa()+" - "+licPremFreqAllowARMst.getMaxSa()+")"));
				return false;
			}
			
			if(licPremFreqAllowARMst.getMaxSa() < checklistDto.getSumAssured()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
						"Please Enter Correct Sum Assured (Sum Assured Range : " + licPremFreqAllowARMst.getMinSa()+" - "+licPremFreqAllowARMst.getMaxSa()+")"));
				return false;
			}
			
			if((licPremFreqAllowARMst.getMinSa() % licPremFreqAllowARMst.getPremSlab()) != (checklistDto.getSumAssured() % licPremFreqAllowARMst.getPremSlab())){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
						"Please Enter Correct Sum Assured (Not In Slab)"));
				return false;
			}
			
			LicNsapDocMst licNsapDocMst = licNsapDocMstService.findById(checklistDto.getNsapDocId());
			LicTabPremMst  licTabPremMst = licTabPremMstService.findLicTabPremMstByProdIdAgeTerm(checklistDto.getProductId(), age, checklistDto.getPolicyTerm()).get(0);

			if(licNsapDocMst.equals(null) || licNsapDocMst == null){
				return false;
			}
			
			if(licTabPremMst.equals(null) || licTabPremMst == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Tabular Premium Not Found As Per Product And Age"));
				return false;
			}
			
			LicModeRebateMst licModeRebateMst = null;
			LicNsapMst licNsapMst = null;
			LicHighSaDiscMst licHighSaDiscMst= null;
			List<LicNsapMst> licNsapMstList = licNsapMstService.findNsapMstByProdIdAgeTerm(checklistDto.getProductId(), age, checklistDto.getPolicyTerm(),checklistDto.getPremiumPayingTerm());
			List<LicModeRebateMst> licModeRebateMstList=licModeRebateMstService.findModeRebateByProdIdPayMode(checklistDto.getProductId(), checklistDto.getPayMode());
			List<LicHighSaDiscMst> licHighSaDiscMstList = licHighSaDiscMstService.findHighSaDiscByProdIdTermSumAssured(checklistDto.getProductId(), checklistDto.getPolicyTerm(), checklistDto.getSumAssured());
			
			if(!(licNsapMstList==null || licNsapMstList.size()==0)){
				licNsapMst = licNsapMstList.get(0);
			}
			
			if(!(licModeRebateMstList==null || licModeRebateMstList.size()==0)){
				licModeRebateMst = licModeRebateMstList.get(0);
			}

			if(!(licHighSaDiscMstList==null || licHighSaDiscMstList.size()==0)){
				licHighSaDiscMst = licHighSaDiscMstList.get(0);
			}
			
			premiumDto.setTabPrem(licTabPremMst.getTabPrem());
			premiumDto.setNsapAmt(0.0);
			premiumDto.setNsapFlag("N");
			premiumDto.setModeRebateFlag("N");
			premiumDto.setHighSaDiscAmt(0.0);
			premiumDto.setRiderAmt(0.0);
			premiumDto.setRebateAmt(0.0);
			
			if(licNsapMst!=null){
				if(licNsapDocMst.getDocType().equals("N")){
					premiumDto.setNsapAmt(licNsapMst.getLoadAmt());
					premiumDto.setNsapFlag("Y");
				}
			}
			
			if(licHighSaDiscMst!=null){
				if(licHighSaDiscMst.getHsdType().equals("PCT")){
					premiumDto.setHighSaDiscAmt((premiumDto.getTabPrem() * licHighSaDiscMst.getHsdValue())/100);
				}else if(licHighSaDiscMst.getHsdType().equals("AMT")){
					premiumDto.setHighSaDiscAmt(licHighSaDiscMst.getHsdValue());
				}
			}
			
			if(licModeRebateMst!=null){
				premiumDto.setModeRebateFlag("Y");
				if(licModeRebateMst.getMrType().equals("PCT")){
					premiumDto.setRebateAmt((premiumDto.getTabPrem() * licModeRebateMst.getMrValue())/100);
				}else if(licModeRebateMst.getMrType().equals("AMT")){
					premiumDto.setRebateAmt(licModeRebateMst.getMrValue());
				}
			}
			
			if(checklistDto.getArRiderAmt() != null){
				if(checklistDto.getArRiderFlag() == true){
					premiumDto.setRiderAmt(checklistDto.getArRiderAmt());
				}			
			}
			
			premiumDto.setTabBasicPrem(premiumDto.getTabPrem() +  premiumDto.getNsapAmt() + (-premiumDto.getHighSaDiscAmt()) + premiumDto.getRiderAmt() + (-premiumDto.getRebateAmt()));
			premiumDto.setFinalBasicPrem(Math.ceil((checklistDto.getSumAssured() * premiumDto.getTabBasicPrem()) / 1000));
			
			if(checklistDto.getPayMode().equals("Y") || checklistDto.getPayMode().equals("S")){
				premiumDto.setFinalBasicPrem(premiumDto.getFinalBasicPrem()/1);
			}else if(checklistDto.getPayMode().equals("H")){
				premiumDto.setFinalBasicPrem(premiumDto.getFinalBasicPrem()/2);
			}else if(checklistDto.getPayMode().equals("Q")){
				premiumDto.setFinalBasicPrem(premiumDto.getFinalBasicPrem()/4);
			}else if(checklistDto.getPayMode().equals("M")){
				premiumDto.setFinalBasicPrem(premiumDto.getFinalBasicPrem()/12);
			}
			
			LicProductMst licProductMst = licProductMstService.findByProductId(checklistDto.getProductId());

			premiumDto.setServiceTax(Math.ceil((premiumDto.getFinalBasicPrem() * licProductMst.getServiceTax()) / 100));
			premiumDto.setTotalAmt(Math.ceil(premiumDto.getFinalBasicPrem() + premiumDto.getServiceTax()));
			
			return true;
		}catch(Exception e){
			log.info("CheckListEditAction check Error : ", e);
			return false;
		}
	}
	
	public void validate(ActionEvent actionEvent){
		try{
			Boolean status = check();
			if(status){
				renderedSave = true;
			}
		}catch(Exception e){
			log.info("CheckListAction validate Error : ", e);
		}
	}
	
	public void show(ActionEvent actionEvent){
		try{
			Boolean status = check();
			if(status){
				renderedSave = false;
			}
		}catch(Exception e){
			log.info("CheckListAction show Error : ", e);
		}
	}
	
	public void update(ActionEvent actionEvent){
		try{
			
			if(checklistDto.getAgCode() == null || checklistDto.getOblApplNo() == null || checklistDto.getPhaseId() == null || checklistDto.getProposerName() == null || checklistDto.getProposerDob() == null || checklistDto.getInsuredName() == null || checklistDto.getInsuredDob() == null || checklistDto.getProductId() == null || checklistDto.getPolicyTerm() == null || checklistDto.getPayMode() == null || checklistDto.getNsapDocId() == null || checklistDto.getSumAssured() == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful: ", "Data Not Saved, Please Input Correct Values"));
				return;
			}
			
			Date now = new Date();
			Date applDate = new Date();
			
			if(checklistDto.getBusinessDateOf() != null){
				if(checklistDto.getBusinessDateOf().equals("B")){
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, -1);
					applDate = cal.getTime();
				}
			}
			
			//insert start
			LicNsapDocMst licNsapDocMst = licNsapDocMstService.findById(checklistDto.getNsapDocId());		
			LicCollBenPctMst licCollBenPctMst = licCollBenPctMstService.findLicCollBenPctMstByProdIdTerm(checklistDto.getProductId(), checklistDto.getPolicyTerm()).get(0);
			
			List<LicProductValueMst> licProductValueMstList = licProductValueMstService.findProductValueMstByProductId(checklistDto.getProductId());
			
			
			LicProductValueMst licProductValueMst = licProductValueMstList.get(0);			
			licProductValueMst.setLicProductMst(licProductMstService.findByProductId(checklistDto.getProductId()));
			licProductValueMst.setTieupCompyMst(licCollBenPctMst.getTieupCompyMst());
			licProductValueMst.setSchemeMst(licCollBenPctMst.getSchemeMst());
			licProductValueMst.setCollBenPct(licCollBenPctMst.getCollBenPct());
			licProductValueMst.setPolicyTerm(checklistDto.getPolicyTerm());
			licProductValueMst.setPremiumPayingTerm(checklistDto.getPremiumPayingTerm());
			licProductValueMst.setAge(age);
			licProductValueMst.setBasicPrem(premiumDto.getFinalBasicPrem());
			licProductValueMst.setTabPrem(premiumDto.getTabBasicPrem());
			licProductValueMst.setSumAssured(checklistDto.getSumAssured());
			licProductValueMst.setPayNature(checklistDto.getPayMode());
			licProductValueMst.setTaxOnPrem(premiumDto.getServiceTax());
			licProductValueMst.setNsapFlag(premiumDto.getNsapFlag());
			licProductValueMst.setNsapAmt(premiumDto.getNsapAmt());
			licProductValueMst.setLicNsapDocMst(licNsapDocMst);
			licProductValueMst.setArFlag(checklistDto.getArRiderFlag() == true ? "Y" : "N");
			licProductValueMst.setArAmt(checklistDto.getArRiderAmt());
			licProductValueMst.setTotalAmt(premiumDto.getTotalAmt());
			licProductValueMst.setModeRebateFlag(premiumDto.getModeRebateFlag());
			licProductValueMst.setModeRebateAmt(premiumDto.getRebateAmt());
			licProductValueMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licProductValueMst.setModifiedDate(now);
			licProductValueMst.setDeleteFlag("N");
			licProductValueMst.setShortAmount(0.0);
			licProductValueMst.setOblApplNo(checklistDto.getOblApplNo());
			
			LicOblChecklist licOblChecklist = licOblChecklists.get(0);
			licOblChecklist.setDesignationMst(loginAction.getUserList().get(0).getDesignationMst());
			licOblChecklist.setBranchMst(loginAction.getUserList().get(0).getBranchMst());
			licOblChecklist.setLicProductValueMst(licProductValueMst);
			licOblChecklist.setPhaseMst(phaseMstService.findByPhaseId(checklistDto.getPhaseId()));	
			licOblChecklist.setOblApplNo(checklistDto.getOblApplNo());
			if(checklistDto.getBusinessDateOf() != null){
				licOblChecklist.setOblApplDate(applDate);
			}
			
			licOblChecklist.setPrntCompyId(3l);
			licOblChecklist.setAgCode(checklistDto.getAgCode());
			licOblChecklist.setDobAgeFlag(checklistDto.getDobAndAgeApplicable() ? "Y" : "N");
			licOblChecklist.setPanNoFlag(checklistDto.getPanApplicable() ? "Y" : "N");
			licOblChecklist.setBankAccnoFlag(checklistDto.getBankAcNoApplicable() ? "Y" : "N");
			licOblChecklist.setPhotoFlag(checklistDto.getPhotoApplicable() ? "Y" : "N");
			licOblChecklist.setPrpslSignedFlag(checklistDto.getProposalFormSigned() ? "Y" : "N");
			licOblChecklist.setBiFlag(checklistDto.getBiSigned() ? "Y" : "N");
			licOblChecklist.setProperlyFilledFlag(checklistDto.getProposalFormProperlyFillup() ? "Y" : "N");
			licOblChecklist.setLoadFlag(checklistDto.getLoadingNeeded() ? "Y" : "N");
			licOblChecklist.setIdProofFlag(checklistDto.getIdentityProof() ? "Y" : "N");
			licOblChecklist.setAddrProofFlag(checklistDto.getAddressProof() ? "Y" : "N");
			licOblChecklist.setIncomeProofFlag(checklistDto.getIncomeProof() ? "Y" : "N");
			licOblChecklist.setApprvRejectFlag("Y");
			licOblChecklist.setApprvRejectDate(now);
			licOblChecklist.setInsuredDob(checklistDto.getInsuredDob());
			licOblChecklist.setInsuredName(checklistDto.getInsuredName());
			licOblChecklist.setProposerName(checklistDto.getProposerName());
			licOblChecklist.setProposerDob(checklistDto.getProposerDob());
			licOblChecklist.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licOblChecklist.setModifiedDate(now);
			licOblChecklist.setPreDataEntryFlag("N");
			licOblChecklist.setDeleteFlag("N");
			licOblChecklist.setBnsDateOf(checklistDto.getBusinessDateOf());
			
			Boolean status = licOblChecklistService.updateLicChecklist(licOblChecklist);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful: ", "Data Updated Successfully"));
				renderedSave = false;
				disabledSave = true;
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful: ", "Data Not Updated"));
				renderedSave = false;
			}
		}catch(Exception e){
			log.info("CheckListAction update Error : ", e);
		}
	}
	
	public void policyTermChangeListener(){
		List<Long> premumPayingTermList = licTabPremMstService.findPremiumPayingTermByPolicyTerm(checklistDto.getPolicyTerm(), age, checklistDto.getProductId());
		
		if(premumPayingTermList == null || premumPayingTermList.size() == 0 || premumPayingTermList.contains(null)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error : ", "Premium Paying Term Not Found"));
			return;
		}else{
			for(Long obj : premumPayingTermList){
				checklistDto.setPremiumPayingTerm(obj);
			}
		}
	}
	
	/* GETTER SETTER */
	public ChecklistDto getChecklistDto() {
		return checklistDto;
	}

	public void setChecklistDto(ChecklistDto checklistDto) {
		this.checklistDto = checklistDto;
	}

	public List<LicProductValueMst> getProductValueMstList() {
		return ProductValueMstList;
	}

	public void setProductValueMstList(List<LicProductValueMst> productValueMstList) {
		ProductValueMstList = productValueMstList;
	}

	public Set<Long> getTermList() {
		return termList;
	}

	public void setTermList(Set<Long> termList) {
		this.termList = termList;
	}

	public List<SelectItem> getPayModeList() {
		return payModeList;
	}

	public void setPayModeList(List<SelectItem> payModeList) {
		this.payModeList = payModeList;
	}

	public Boolean getRenderedArRider() {
		return renderedArRider;
	}

	public void setRenderedArRider(Boolean renderedArRider) {
		this.renderedArRider = renderedArRider;
	}

	public List<LicNsapDocMst> getLicNsapDocMsts() {
		return licNsapDocMsts;
	}

	public void setLicNsapDocMsts(List<LicNsapDocMst> licNsapDocMsts) {
		this.licNsapDocMsts = licNsapDocMsts;
	}

	public PremiumDto getPremiumDto() {
		return premiumDto;
	}

	public void setPremiumDto(PremiumDto premiumDto) {
		this.premiumDto = premiumDto;
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

	public List<PhaseMst> getPhases() {
		return phases;
	}

	public void setPhases(List<PhaseMst> phases) {
		this.phases = phases;
	}
	public List<LicProductMst> getLicProductMsts() {
		return licProductMsts;
	}
	public void setLicProductMsts(List<LicProductMst> licProductMsts) {
		this.licProductMsts = licProductMsts;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Boolean getRenderedSearchPanel() {
		return renderedSearchPanel;
	}

	public void setRenderedSearchPanel(Boolean renderedSearchPanel) {
		this.renderedSearchPanel = renderedSearchPanel;
	}

	public Boolean getRenderedCheckListEditPanel() {
		return renderedCheckListEditPanel;
	}

	public void setRenderedCheckListEditPanel(Boolean renderedCheckListEditPanel) {
		this.renderedCheckListEditPanel = renderedCheckListEditPanel;
	}
	
}
