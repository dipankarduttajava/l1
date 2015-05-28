package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
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
import com.gtfs.bean.LicPremFreqAllowARMst2;
import com.gtfs.bean.LicPremWvr;
import com.gtfs.bean.LicProductMst;
import com.gtfs.bean.LicProductValueMst;
import com.gtfs.bean.LicTabPremMst;
import com.gtfs.bean.PhaseMst;
import com.gtfs.dto.ChecklistDto;
import com.gtfs.dto.LicTermRiderDto;
import com.gtfs.dto.PremiumDto;
import com.gtfs.service.interfaces.AgentRlnsService;
import com.gtfs.service.interfaces.LicCollBenPctMstService;
import com.gtfs.service.interfaces.LicHighSaDiscMstService;
import com.gtfs.service.interfaces.LicModeRebateMstService;
import com.gtfs.service.interfaces.LicNsapDocMstService;
import com.gtfs.service.interfaces.LicNsapMstService;
import com.gtfs.service.interfaces.LicOblChecklistService;
import com.gtfs.service.interfaces.LicPremFreqAllowARMst2Service;
import com.gtfs.service.interfaces.LicPremFreqAllowARMstService;
import com.gtfs.service.interfaces.LicPremWvrService;
import com.gtfs.service.interfaces.LicProductMstService;
import com.gtfs.service.interfaces.LicProductValueMstService;
import com.gtfs.service.interfaces.LicTabPremMstService;
import com.gtfs.service.interfaces.LicTermRiderService;
import com.gtfs.service.interfaces.PhaseMstService;
import com.gtfs.util.Age;
import com.gtfs.util.AgeCalculation;

@Component
@Scope("session")
public class CheckListAction implements Serializable{
	private Logger log = Logger.getLogger(CheckListAction.class);
	
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
	private LicPremFreqAllowARMst2Service licPremFreqAllowARMst2Service;
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
	@Autowired
	private LicPremWvrService licPremWvrService;
	@Autowired
	private LicTermRiderService licTermRiderService;
	
	private ChecklistDto checklistDto;
	private List<LicProductValueMst> ProductValueMstList = new ArrayList<LicProductValueMst>();	
	private Set<Long> termList = new TreeSet<Long>();
	private List<SelectItem> payModeList = new ArrayList<SelectItem>();
	private List<LicNsapDocMst> licNsapDocMsts = new ArrayList<LicNsapDocMst>();	
	private List<PhaseMst> phases = new ArrayList<PhaseMst>();
	private List<LicProductMst> licProductMsts = new ArrayList<LicProductMst>();
	private List<Long> premumPayingTermList = new ArrayList<Long>();
	private List<String> categoryList = new ArrayList<String>();
	
	private Boolean disabledValidate;
	private Boolean renderedSave;
	private Boolean disabledSave;
	private Boolean renderedArRider;
	private Boolean renderedWvrRider;
	private Boolean renderedTermRider;
	private Boolean renderedBusinessDateOf;
	private Boolean renderedCategory;
	private Integer age;
	
	// global important variables
	private PremiumDto premiumDto;	
	
	@PostConstruct
	public void init(){
		phases = phaseMstService.findBusinessPhasesForCurrentDate();
		licProductMsts = licProductMstService.findActiveLicProductMstForChecklist();
	}
	
	public void refresh(){
		try{
			age = 0;
			checklistDto = new ChecklistDto();
			checklistDto.setIdentityProof(true);
			renderedSave = false;
			renderedArRider = true;
			renderedWvrRider = true;
			renderedTermRider = true;
			renderedBusinessDateOf = false;
			renderedCategory = false;
			termList.clear();
			payModeList.clear();
			premumPayingTermList.clear();
			categoryList.clear();
			
			if(licNsapDocMsts != null){
				licNsapDocMsts.clear();
			}
			
			premiumDto = new PremiumDto();
			disabledSave = false;
			disabledValidate = true;
		}catch(Exception e){
			log.info("CheckListAction refresh Error : ", e);
		}
	}
	
	
	public String onLoad(){ 
		refresh();
		return "/licBranchActivity/checklist.xhtml";
	}
	
	public Boolean check(){
		try{
			List<AgentRlns> list = agentRlnsService.findValidAgentByPhase(checklistDto.getPhaseId(), checklistDto.getAgCode());
			List<LicOblChecklist> checkList = licOblChecklistService.findApplicationByApplicationNo(checklistDto.getOblApplNo());

			if(list == null || list.size() == 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : ", "Agent Not Valid, Please Try Again With Valid Agent"));
				return false;
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
			
			if(checklistDto.getProductId().equals(113l)){
				LicPremFreqAllowARMst2 licPremFreqAllowARMst2 = licPremFreqAllowARMst2Service.checkForSumAssuredByProdIdAndTermFromLicPremFreqAllowARMst2(checklistDto.getPolicyTerm(), checklistDto.getProductId()).get(0);
				
				if(licPremFreqAllowARMst2.equals(null) || licPremFreqAllowARMst2 == null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error : ", "Sum Assured Not Found As Per Product Term"));
					return false;
				}
				
				if(licPremFreqAllowARMst2.getMinSa() > checklistDto.getSumAssured()){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
							"Please Enter Correct Sum Assured (Sum Assured Range : " + licPremFreqAllowARMst2.getMinSa()+" - "+licPremFreqAllowARMst2.getMaxSa()+")"));
					return false;
				}
				
				if(licPremFreqAllowARMst2.getMaxSa() < checklistDto.getSumAssured()){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
							"Please Enter Correct Sum Assured (Sum Assured Range : " + licPremFreqAllowARMst2.getMinSa()+" - "+licPremFreqAllowARMst2.getMaxSa()+")"));
					return false;
				}
				
				if((licPremFreqAllowARMst2.getMinSa() % licPremFreqAllowARMst2.getPremSlab()) != (checklistDto.getSumAssured() % licPremFreqAllowARMst2.getPremSlab())){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
							"Please Enter Correct Sum Assured (Not In Slab)"));
					return false;
				}
			}else if(checklistDto.getProductId().equals(115l)){
				LicPremFreqAllowARMst2 licPremFreqAllowARMst2 = licPremFreqAllowARMst2Service.checkForSumAssuredByProdIdAndTermFromLicPremFreqAllowARMst2(checklistDto.getPolicyTerm(), checklistDto.getProductId()).get(0);
				
				if(licPremFreqAllowARMst2.equals(null) || licPremFreqAllowARMst2 == null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error : ", "Sum Assured Not Found As Per Product Term"));
					return false;
				}
				
				if(licPremFreqAllowARMst2.getMinSa() > checklistDto.getSumAssured()){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
							"Please Enter Correct Sum Assured (Sum Assured Range : " + licPremFreqAllowARMst2.getMinSa()+" - "+licPremFreqAllowARMst2.getMaxSa()+")"));
					return false;
				}
				
				if(licPremFreqAllowARMst2.getMaxSa() < checklistDto.getSumAssured()){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
							"Please Enter Correct Sum Assured (Sum Assured Range : " + licPremFreqAllowARMst2.getMinSa()+" - "+licPremFreqAllowARMst2.getMaxSa()+")"));
					return false;
				}
				
				if((licPremFreqAllowARMst2.getMinSa() % licPremFreqAllowARMst2.getPremSlab()) != (checklistDto.getSumAssured() % licPremFreqAllowARMst2.getPremSlab())){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error : ",
							"Please Enter Correct Sum Assured (Not In Slab)"));
					return false;
				}
				
			}else{
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
			premiumDto.setWvrRiderAmt(0.0);
			premiumDto.setTermRiderAmt(0.0);
			premiumDto.setTermRiderNsapAmt(0.0);
			
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
					
					if(checklistDto.getProductId().equals(114l)){
						premiumDto.setWvrRiderAmt((checklistDto.getWvrRiderAmt()*(100-licModeRebateMst.getMrValue()))/100);
					}else{
						premiumDto.setWvrRiderAmt(0.0);
					}
				}else if(licModeRebateMst.getMrType().equals("AMT")){
					premiumDto.setRebateAmt(licModeRebateMst.getMrValue());
					if(checklistDto.getProductId().equals(114l)){
						premiumDto.setWvrRiderAmt(checklistDto.getWvrRiderAmt() - licModeRebateMst.getMrValue());
					}else{
						premiumDto.setWvrRiderAmt(0.0);
					}
				}
			}
			
			
			
			
			if(checklistDto.getArRiderAmt() != null){
				if(checklistDto.getArRiderFlag() == true){
					premiumDto.setRiderAmt(checklistDto.getArRiderAmt());
				}			
			}	
			
			
			if(checklistDto.getTermRiderAmt() != null){
				if(checklistDto.getTermRiderFlag() == true){
					
					if(licNsapDocMst.getDocType().equals("N")){
						premiumDto.setTermRiderNsapAmt(checklistDto.getTermRiderNsapAmt());
					}
					
					if(licModeRebateMst!=null){
						if(licModeRebateMst.getMrType().equals("PCT")){
							premiumDto.setTermRiderAmt(checklistDto.getTermRiderAmt() - ((checklistDto.getTermRiderAmt() * licModeRebateMst.getMrValue())/100));
						}else if(licModeRebateMst.getMrType().equals("AMT")){
							
						}
					}
					
					
				}			
			}	
			
			
			
			premiumDto.setTabBasicPrem(premiumDto.getTabPrem() +  premiumDto.getNsapAmt() + (-premiumDto.getHighSaDiscAmt()) + premiumDto.getTermRiderAmt()+ premiumDto.getTermRiderNsapAmt() + premiumDto.getRiderAmt() + (-premiumDto.getRebateAmt()));
			premiumDto.setFinalBasicPrem(Math.ceil((checklistDto.getSumAssured() * premiumDto.getTabBasicPrem()) / 1000));
			
			if(checklistDto.getWvrRiderFlag()!=null){				
				if(checklistDto.getWvrRiderFlag()){
					Double totalWvrRiderAmt = (premiumDto.getFinalBasicPrem()*premiumDto.getWvrRiderAmt())/100;
					premiumDto.setFinalBasicPrem(Math.ceil(premiumDto.getFinalBasicPrem()+totalWvrRiderAmt));
				}	
			}
			
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
			log.info("CheckListAction check Error : ", e);
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
				disabledValidate = false;
			}else{
				disabledValidate = true;
			}
			
		}catch(Exception e){
			log.info("CheckListAction show Error : ", e);
		}
	}
	
	public void save(ActionEvent actionEvent){
		try{			
			if(checklistDto.getAgCode() == null || checklistDto.getOblApplNo() == null || checklistDto.getPhaseId() == null || checklistDto.getProposerName() == null || checklistDto.getProposerDob() == null || checklistDto.getInsuredName() == null || checklistDto.getInsuredDob() == null || checklistDto.getProductId() == null || checklistDto.getPolicyTerm() == null || checklistDto.getPayMode() == null || checklistDto.getNsapDocId() == null || checklistDto.getSumAssured() == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful: ", "Data Not Saved, Please Input Correct Values"));
				return;
			}
			
			Date now = new Date();
			Date applDate = new Date();
			
			if(renderedBusinessDateOf == true){
				if(checklistDto.getBusinessDateOf().equals("B")){
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, -1);
					applDate = cal.getTime();
				}
			}
			
			//insert start
			LicNsapDocMst licNsapDocMst = licNsapDocMstService.findById(checklistDto.getNsapDocId());		
			LicCollBenPctMst licCollBenPctMst = licCollBenPctMstService.findLicCollBenPctMstByProdIdTerm(checklistDto.getProductId(), checklistDto.getPolicyTerm()).get(0);
			LicProductValueMst licProductValueMst = new LicProductValueMst();
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
			licProductValueMst.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licProductValueMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licProductValueMst.setCreatedDate(now);
			licProductValueMst.setModifiedDate(now);
			licProductValueMst.setDeleteFlag("N");
			licProductValueMst.setShortAmount(0.0);
			licProductValueMst.setOblApplNo(checklistDto.getOblApplNo());
			
			LicOblChecklist licOblChecklist = new LicOblChecklist();
			licOblChecklist.setDesignationMst(loginAction.getUserList().get(0).getDesignationMst());
			licOblChecklist.setBranchMst(loginAction.getUserList().get(0).getBranchMst());
			licOblChecklist.setLicProductValueMst(licProductValueMst);
			licOblChecklist.setPhaseMst(phaseMstService.findByPhaseId(checklistDto.getPhaseId()));	
			licOblChecklist.setOblApplNo(checklistDto.getOblApplNo());
			licOblChecklist.setOblApplDate(applDate);
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
			licOblChecklist.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licOblChecklist.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licOblChecklist.setCreatedDate(now);
			licOblChecklist.setModifiedDate(now);
			licOblChecklist.setPreDataEntryFlag("N");
			licOblChecklist.setDeleteFlag("N");
			licOblChecklist.setBnsDateOf(checklistDto.getBusinessDateOf());
			
			Boolean status = licOblChecklistService.insertIntoLicChecklist(licOblChecklist);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful: ", "Data Saved Successfully"));
				renderedSave = false;
				disabledSave = true;
				disabledValidate = true;
				renderedCategory = false;
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful: ", "Data Not Saved"));
				renderedSave = false;
				disabledValidate = false;
			}
		}catch(Exception e){
			log.info("CheckListAction save Error : ", e);
		}
	}
	
	public void proposerDobChangeListener(){		
		Long code = checklistDto.getAgCode();
		String applicationNo = checklistDto.getOblApplNo();
		Long phaseId =  checklistDto.getPhaseId();
		String proposerName = checklistDto.getProposerName();
		Date proposerDob = checklistDto.getProposerDob();

		Age age = AgeCalculation.calculateAge(proposerDob);

		checklistDto = new ChecklistDto();
		checklistDto.setAgCode(code);
		checklistDto.setOblApplNo(applicationNo);
		checklistDto.setPhaseId(phaseId);
		checklistDto.setProposerName(proposerName);
		checklistDto.setProposerDob(proposerDob);
		checklistDto.setPropYear(age.getYear());
		checklistDto.setPropMonth(age.getMonth());
		checklistDto.setPropDay(age.getDay());	
		checklistDto.setIdentityProof(true);
		renderedSave = false;
		renderedArRider = true;
		renderedWvrRider = true;
		renderedTermRider = true;
		renderedBusinessDateOf = false;
		termList.clear();
		premumPayingTermList.clear();
		payModeList.clear();
		
		if(licNsapDocMsts!=null){
			licNsapDocMsts.clear();
		}
		
		premiumDto = new PremiumDto();
		disabledSave = false;
		disabledValidate = true;
	}
	
	public void calculateProposerAge(Date dob){
		if(dob != null){
			Age age = AgeCalculation.calculateAge(dob);
			checklistDto.setPropYear(age.getYear());
			checklistDto.setPropMonth(age.getMonth());
			checklistDto.setPropDay(age.getDay());
		}
	}
	
	public void insuredDobChangeListener(){
		Long code = checklistDto.getAgCode();
		String applicationNo = checklistDto.getOblApplNo();
		Long phaseId =  checklistDto.getPhaseId();
		String proposerName = checklistDto.getProposerName();
		Date proposerDob = checklistDto.getProposerDob();
		String insuredName = checklistDto.getInsuredName();
		Date insuredDob = checklistDto.getInsuredDob();
		Age proposerAge = AgeCalculation.calculateAge(proposerDob);
		Age InsuredAge = AgeCalculation.calculateAge(insuredDob);
		Boolean sameFlag = checklistDto.getInsuredPropSameFlag();
		
		checklistDto = new ChecklistDto();
		checklistDto.setAgCode(code);
		checklistDto.setOblApplNo(applicationNo);
		checklistDto.setPhaseId(phaseId);
		checklistDto.setProposerName(proposerName);
		checklistDto.setProposerDob(proposerDob);
		checklistDto.setInsuredName(insuredName);
		checklistDto.setInsuredDob(insuredDob);
		checklistDto.setPropYear(proposerAge.getYear());
		checklistDto.setPropMonth(proposerAge.getMonth());
		checklistDto.setPropDay(proposerAge.getDay());	
		checklistDto.setYear(InsuredAge.getYear());
		checklistDto.setMonth(InsuredAge.getMonth());
		checklistDto.setDay(InsuredAge.getDay());
		checklistDto.setIdentityProof(true);
		checklistDto.setInsuredPropSameFlag(sameFlag);
		renderedSave = false;
		renderedArRider = true;
		renderedWvrRider = true;
		renderedTermRider = true;
		renderedBusinessDateOf = false;
		termList.clear();
		premumPayingTermList.clear();
		payModeList.clear();
		
		if(licNsapDocMsts!=null){
			licNsapDocMsts.clear();
		}
		
		premiumDto = new PremiumDto();
		disabledSave = false;
		disabledValidate = true;
	}
	
	public void calculateInsuredAge(Date dob){
		if(dob != null){
			Age age = AgeCalculation.calculateAge(dob);
			checklistDto.setYear(age.getYear());
			checklistDto.setMonth(age.getMonth());
			checklistDto.setDay(age.getDay());
		}
	}
	
	public void insuredProposerSameFlagListener(){
		if(checklistDto.getInsuredPropSameFlag() == true){
			checklistDto.setInsuredName(checklistDto.getProposerName());
			checklistDto.setInsuredDob(checklistDto.getProposerDob());
			insuredDobChangeListener();
		}else{
			checklistDto.setInsuredName(null);
			checklistDto.setInsuredDob(null);
			insuredDobChangeListener();
		}
	}
	
	public void policyTermChangeListener(){
		premumPayingTermList = licTabPremMstService.findPremiumPayingTermByPolicyTerm(checklistDto.getPolicyTerm(), age, checklistDto.getProductId());
		
		if(premumPayingTermList == null || premumPayingTermList.size() == 0 || premumPayingTermList.contains(null)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error : ", "Premium Paying Term Not Found"));
			return;
		}/*else{
			for(Long obj : premumPayingTermList){
				checklistDto.setPremiumPayingTerm(obj);
			}
		}*/
	}
	
	
	public void sumAssuredChangeListener(){
		try{
			if(checklistDto.getProductId().equals(113l)){
				List<Object> list = licPremFreqAllowARMst2Service.checkForAddbRiderByAgeProdIdCategoryTermPptFromLicPremFreqAllowARMst2(age, checklistDto.getProductId(), checklistDto.getArCategory(), checklistDto.getPolicyTerm(), checklistDto.getPremiumPayingTerm());
				
				if(list == null || list.size() == 0 || list.contains(null)){
					renderedArRider = true;
				}else{
					renderedArRider = false;
					Object[] arr = (Object[]) list.get(0);
					checklistDto.setArRiderAmt((Double) arr[1]); // PCT WORK Done Later
				}
				
				//calculate Term Rider
				List<LicTermRiderDto> termList = licTermRiderService.findRiderAmtAndRiderTypeFromLicTermRider(age, checklistDto.getPolicyTerm(), checklistDto.getPremiumPayingTerm(), checklistDto.getProductId());
				if(termList == null || termList.size() == 0 || termList.contains(null)){
					renderedTermRider = true;
				}else{
					renderedTermRider = false;
					checklistDto.setTermRiderAmt(termList.get(0).getRiderAmt()); // PCT WORK Done Later
					checklistDto.setTermRiderNsapAmt(termList.get(0).getRiderNsapAmt());
				}				
				//calculate Term Rider
			}else if(checklistDto.getProductId().equals(115l)){
				List<Object> list = licPremFreqAllowARMst2Service.checkForAddbRiderByAgeProdIdCategoryTermPptFromLicPremFreqAllowARMst2(age, checklistDto.getProductId(), checklistDto.getArCategory(), checklistDto.getPolicyTerm(), checklistDto.getPremiumPayingTerm());
				
				if(list == null || list.size() == 0 || list.contains(null)){
					renderedArRider = true;
				}else{
					renderedArRider = false;
					Object[] arr = (Object[]) list.get(0);
					checklistDto.setArRiderAmt((Double) arr[1]); // PCT WORK Done Later
				}
				
				//calculate Term Rider
				List<LicTermRiderDto> termList = licTermRiderService.findRiderAmtAndRiderTypeFromLicTermRider(age, checklistDto.getPolicyTerm(), checklistDto.getPremiumPayingTerm(), checklistDto.getProductId());
				if(termList == null || termList.size() == 0 || termList.contains(null)){
					renderedTermRider = true;
				}else{
					renderedTermRider = false;
					checklistDto.setTermRiderAmt(termList.get(0).getRiderAmt()); // PCT WORK Done Later
					checklistDto.setTermRiderNsapAmt(termList.get(0).getRiderNsapAmt());
				}		
				
			}else if(checklistDto.getProductId().equals(114l)){
					Long term = 0l;
					for(long val:termList){
						term = val;
					}
					
					if (checklistDto.getPropMonth() >= 6) {
						checklistDto.setPropYear(checklistDto.getPropYear() + 1);
					}
					
					List<LicPremWvr> licPremWvrList= licPremWvrService.findLoadAmountByPropAgeTermProdId(checklistDto.getPropYear(), term , checklistDto.getProductId());
					
					if(licPremWvrList == null || licPremWvrList.size()==0){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
			                    "Error : ", "Policy Not valid"));
						return;
					}else{
						checklistDto.setWvrRiderAmt(licPremWvrList.get(0).getLoadAmt());
						renderedWvrRider = false;
					}
			}else{
				List<Object> list= licPremFreqAllowARMstService.checkForAddbRiderByAgeAndProdId(age, checklistDto.getProductId());
				
				if(list == null || list.size() == 0 || list.contains(null)){
					renderedArRider=true;
				}else{
					renderedArRider=false;
					Object[] arr=(Object[]) list.get(0);
					checklistDto.setArRiderAmt((Double) arr[1]); // PCT WORK Done Later
				}
			}
			licNsapDocMsts = licNsapDocMstService.findLicNsapDocMstByAge(age, checklistDto.getSumAssured(), checklistDto.getProductId());
		}catch(Exception e){
			log.info("CheckListAction sumAssuredChangeListener Error : ", e);
		}
	}
	
	public void productChangeListener(){
		try{
			termList.clear();
			payModeList.clear();
			premumPayingTermList.clear();
			
			if(licNsapDocMsts!=null){
				licNsapDocMsts.clear();
			}
			
			checklistDto.setWvrRiderAmt(0.0);
			LicProductMst product = licProductMstService.findByProductId(checklistDto.getProductId());
			
			if(product.getBackdatingFlag().equals("Y")){
				renderedBusinessDateOf = true;
			}
			
			Integer minAge = licTabPremMstService.findMinAgeByProdId(checklistDto.getProductId()).get(0);
			
			if(minAge == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Policy Term Not Valid For Entered Age"));
				return;
			}
			
			if(minAge != null){
				if(minAge <= checklistDto.getYear()){
					if(checklistDto.getProductId().equals(114l)){
						age = checklistDto.getYear();
					}else{
						if (checklistDto.getMonth() >= 6) {
							age = checklistDto.getYear() + 1;
						} else {
							age = checklistDto.getYear();
						}
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

			/* Category List*/
			if(checklistDto.getProductId().equals(113l)){
				categoryList = licPremFreqAllowARMst2Service.findDistinctCategoryFromLicPremFreqAllowARMst2();
				
				// Fetch Distinct Prem_FREQ_ALLOW by ProductId FROM licPremFreqAllowARMst2
				String paymode = licPremFreqAllowARMst2Service.findDistinctPremFreqAllowByProductIdFromLicPremFreqAllowARMst2(checklistDto.getProductId()).get(0);
				
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
				renderedCategory = true;
			}else if(checklistDto.getProductId().equals(115l)){
				categoryList = licPremFreqAllowARMst2Service.findDistinctCategoryFromLicPremFreqAllowARMst2();
				
				// Fetch Distinct Prem_FREQ_ALLOW by ProductId FROM licPremFreqAllowARMst2
				String paymode = licPremFreqAllowARMst2Service.findDistinctPremFreqAllowByProductIdFromLicPremFreqAllowARMst2(checklistDto.getProductId()).get(0);
				
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
				renderedCategory = true;
			}else{
				
				// to do
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
				renderedCategory = false;
			}
			
		}catch(Exception e){
			log.info("CheckListAction productChangeListener Error : ", e);
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
	
	
	public Boolean getRenderedBusinessDateOf() {
		return renderedBusinessDateOf;
	}
	public void setRenderedBusinessDateOf(Boolean renderedBusinessDateOf) {
		this.renderedBusinessDateOf = renderedBusinessDateOf;
	}
	
	
	public Boolean getRenderedWvrRider() {
		return renderedWvrRider;
	}
	public void setRenderedWvrRider(Boolean renderedWvrRider) {
		this.renderedWvrRider = renderedWvrRider;
	}

	
	public Boolean getDisabledValidate() {
		return disabledValidate;
	}
	public void setDisabledValidate(Boolean disabledValidate) {
		this.disabledValidate = disabledValidate;
	}

	
	public List<Long> getPremumPayingTermList() {
		return premumPayingTermList;
	}
	public void setPremumPayingTermList(List<Long> premumPayingTermList) {
		this.premumPayingTermList = premumPayingTermList;
	}

	
	public Boolean getRenderedCategory() {
		return renderedCategory;
	}
	public void setRenderedCategory(Boolean renderedCategory) {
		this.renderedCategory = renderedCategory;
	}

	
	public List<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	
	public Boolean getRenderedTermRider() {
		return renderedTermRider;
	}
	public void setRenderedTermRider(Boolean renderedTermRider) {
		this.renderedTermRider = renderedTermRider;
	}
}
