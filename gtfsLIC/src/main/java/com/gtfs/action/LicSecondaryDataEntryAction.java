package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicAddressDtls;
import com.gtfs.bean.LicInsuredAddressMapping;
import com.gtfs.bean.LicInsuredBankDtls;
import com.gtfs.bean.LicNomineeDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPrintRcptDtls;
import com.gtfs.bean.LicProofMst;
import com.gtfs.bean.PrintRcptMst;
import com.gtfs.bean.RbiBankDtls;
import com.gtfs.bean.StateMst;
import com.gtfs.service.interfaces.LicInsuredAddressMappingService;
import com.gtfs.service.interfaces.LicNomineeDtlsService;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.LicPrintRcptDtlsService;
import com.gtfs.service.interfaces.LicProofMstService;
import com.gtfs.service.interfaces.PrintRcptMstService;
import com.gtfs.service.interfaces.RbiBankDtlsService;
import com.gtfs.service.interfaces.StateMstService;
import com.gtfs.util.Age;
import com.gtfs.util.AgeCalculation;
import com.gtfs.util.BankValidation;

@Component
@Scope("session")
public class LicSecondaryDataEntryAction implements Serializable{
	private Logger log = Logger.getLogger(LicSecondaryDataEntryAction.class);
	
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	@Autowired
	private StateMstService stateMstService;
	@Autowired
	private RbiBankDtlsService rbiBankDtlsService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicProofMstService licProofMstService;
	@Autowired
	private PrintRcptMstService printRcptMstService;
	@Autowired
	private LicInsuredAddressMappingService licInsuredAddressMappingService;
	@Autowired
	private LicPrintRcptDtlsService licPrintRcptDtlsService;
	@Autowired
	private LicNomineeDtlsService licNomineeDtlsService;
	
	private Date applicationDate;
	private String applicationNo;
	private Boolean renderedDetailForm;
	private Boolean renderedListPanel;
	private Boolean renderedsearchPanel;
	private Boolean requiredApointee;
	private Boolean insuredNomineeAddressSameFlag;
	private Boolean requiredHusband;
	private Boolean requiredAccountNo;
	
//	private List<LicProofMst> ageProofList = new ArrayList<LicProofMst>();
	private List<LicProofMst> addrProofList = new ArrayList<LicProofMst>();
	private List<LicProofMst> identityProofList = new ArrayList<LicProofMst>();
	private List<LicProofMst> incomeProofList = new ArrayList<LicProofMst>();
	private List<StateMst> stateMsts = new ArrayList<StateMst>();
	private List<String> prefixs = new ArrayList<String>();
	private List<LicOblApplicationMst> licOblApplicationMstList = new ArrayList<LicOblApplicationMst>();
	
	// temp variable
	private LicOblApplicationMst licOblApplicationMst;
	private LicAddressDtls licAddressDtls;
	private LicNomineeDtls licNomineeDtls;
	private LicPrintRcptDtls licPrintRcptDtls;
	

	
	@PostConstruct
	public void findAllActiveStateMSt(){
//		ageProofList.clear();
		addrProofList.clear();
		identityProofList.clear();
		incomeProofList.clear();
		List<LicProofMst> list = licProofMstService.findAll();
		
		for(LicProofMst obj : list){
			if(obj.getProofFlag().equals("AD")){
				addrProofList.add(obj);
//			}else if(obj.getProofFlag().equals("AG")){
//				ageProofList.add(obj);
			}else if(obj.getProofFlag().equals("ID")){
				identityProofList.add(obj);
			}else if(obj.getProofFlag().equals("IN")){
				incomeProofList.add(obj);
			}
		}
		stateMsts = stateMstService.findAllActiveStateMSt();
	}
	
	public void refresh(){
		Date now = new Date();
		licOblApplicationMstList.clear();
		licOblApplicationMst = new LicOblApplicationMst();
		licAddressDtls = new LicAddressDtls();
		licAddressDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licAddressDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licAddressDtls.setCreatedDate(now);
		licAddressDtls.setModifiedDate(now);
		licAddressDtls.setDeleteFlag("N");
		
		licNomineeDtls = new LicNomineeDtls();
		licNomineeDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licNomineeDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licNomineeDtls.setCreatedDate(now);
		licNomineeDtls.setModifiedDate(now);
		licNomineeDtls.setDeleteFlag("N");
		
		requiredHusband = false;
		requiredAccountNo = false;
		renderedDetailForm = false;
		renderedListPanel = false;
		renderedsearchPanel = true;
		requiredApointee = false;
		insuredNomineeAddressSameFlag = false;
		licPrintRcptDtls = new LicPrintRcptDtls();
	}
	
	public String onLoad(){
		refresh();
		applicationDate = null;
		applicationNo = null;
		return "/licBranchActivity/licSecondaryDataEntry.xhtml"; 
	}
	
	public void maritalStatusChangeListener(){
		if(licOblApplicationMst.getLicInsuredDtls().getMaritalStatus() != null){
			if(licOblApplicationMst.getLicInsuredDtls().getMaritalStatus().equals("Married") && !(licOblApplicationMst.getLicInsuredDtls().getSalutation().equals("Mr."))){
				requiredHusband = true;
			}else{
				requiredHusband = false;
			}
		}else{
			requiredHusband = false;
		}
	}
	
	public void insuredNomineeAddressSameFlagListener(){
		try{			
			String special = "";
			
			if(insuredNomineeAddressSameFlag == true){
				if(licAddressDtls.getAddress1() != null){
					special = " || ";
				}else{
					licAddressDtls.setAddress1(" ");
				}
				
				if(licAddressDtls.getAddress2() != null){
					special = " || ";
				}else{
					licAddressDtls.setAddress2(" ");
				}
				
					licNomineeDtls.setNomineeAddress(licAddressDtls.getAddress1() + " " + special + " " + licAddressDtls.getAddress2());
			}else{
				licNomineeDtls.setNomineeAddress(null);
			}
		}catch(Exception e){
			log.info("Error : ", e);
		}
	}
	
	public void searchByIfscCode(){
		try{
			List<RbiBankDtls> list = rbiBankDtlsService.findRbiBankDtlsByIfscCode(licOblApplicationMst.getLicInsuredBankDtls().getIfsCode());
			
			if(!(list == null || list.size() == 0 || list.contains(null))){
				licOblApplicationMst.getLicInsuredBankDtls().setBankName(list.get(0).getBankName());
				licOblApplicationMst.getLicInsuredBankDtls().setBankBranch(list.get(0).getBranchName());
				licOblApplicationMst.getLicInsuredBankDtls().setMicrCode(list.get(0).getMicrCode());
				requiredAccountNo = true;
			}else{
				requiredAccountNo = false;
				licOblApplicationMst.getLicInsuredBankDtls().setAcctNo(null);
				licOblApplicationMst.getLicInsuredBankDtls().setBankName(null);
				licOblApplicationMst.getLicInsuredBankDtls().setBankBranch(null);
				licOblApplicationMst.getLicInsuredBankDtls().setMicrCode(null);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "IFSC Code Not Valid"));
				return;
			}
		}catch(Exception e){
			log.info("LicSecondaryDataEntryAction process Error : ", e);
		}
	}
	
	public void validateApointee(){
		try{
			Date now = new Date();		
			int age = now.getYear() - licNomineeDtls.getNomineeDob().getYear();
			
			if(age < 18){
				requiredApointee = true;
			}else{
				requiredApointee = false;
			}
		}catch(Exception e){
			log.info("Error : ", e);
		}
	}
	
	public void search(){
		try{
			requiredAccountNo = false;
			
			if(applicationDate != null){
				licOblApplicationMstList = licOblApplicationMstService.findApplicationForSecondaryDataEntryByDate(applicationDate, loginAction.getUserList().get(0).getBranchMst());
				
				renderedListPanel = true;
				
			}else if(applicationNo != null && !applicationNo.equals("")){
				licOblApplicationMstList = licOblApplicationMstService.findApplicationForSecondaryDataEntryByApplicationNo(applicationNo, loginAction.getUserList().get(0).getBranchMst());
				renderedListPanel = true;
				
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_ERROR,"Error :", "Please Enter Date or Application Number"));
				renderedListPanel = false;
				return;
			}
		}catch(Exception e){
			log.info("LicSecondaryDataEntryAction search Error : ", e);
		}
	}
	
	
	public void editDetail(LicOblApplicationMst licOblApplicationMst){
		try{
			Date now = new Date();
			
			this.licOblApplicationMst = licOblApplicationMst;
			prefixs = printRcptMstService.findPrintRcptPrefixByBranchParentTieupCoId(loginAction.getUserList().get(0).getBranchMst().getBranchId(), licOblApplicationMst.getLicProductValueMst().getTieupCompyMst().getTieCompyId(), 3l);
			List<LicNomineeDtls> nomineeList = licNomineeDtlsService.findNomineeDtlsByApplication(this.licOblApplicationMst);
			List<LicInsuredAddressMapping> addressList = licInsuredAddressMappingService.findAddressDtlsByInsuredDtls(licOblApplicationMst.getLicInsuredDtls());

			if(addressList == null || addressList.size() == 0){
				licAddressDtls = new LicAddressDtls();
				licAddressDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licAddressDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licAddressDtls.setCreatedDate(now);
				licAddressDtls.setModifiedDate(now);
				licAddressDtls.setDeleteFlag("N");
			}else{
				licAddressDtls = addressList.get(0).getLicAddressDtls();
				licAddressDtls.setId(null);
			}
			
			if(nomineeList == null || nomineeList.size() == 0){
				licNomineeDtls = new LicNomineeDtls();
				licNomineeDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licNomineeDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licNomineeDtls.setCreatedDate(now);
				licNomineeDtls.setModifiedDate(now);
				licNomineeDtls.setDeleteFlag("N");
				licNomineeDtls.setLicOblApplicationMst(this.licOblApplicationMst);
			}else{
				licNomineeDtls = nomineeList.get(0);
				licNomineeDtls.setId(null);
			}

			if(this.licOblApplicationMst.getSecondaryEntryFlag().equals("Y")){
				this.licPrintRcptDtls = licPrintRcptDtlsService.findLicPrintRcptDtlsByApplication(this.licOblApplicationMst).get(0);			
			}else{
				licPrintRcptDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licPrintRcptDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licPrintRcptDtls.setCreatedDate(now);
				licPrintRcptDtls.setModifiedDate(now);
				licPrintRcptDtls.setDeleteFlag("N");
			}
		
			if(licOblApplicationMst.getLicInsuredBankDtls() == null){
				LicInsuredBankDtls licInsuredBankDtls = new LicInsuredBankDtls();
				licInsuredBankDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licInsuredBankDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licInsuredBankDtls.setDeleteFlag("N");
				licInsuredBankDtls.setCreatedDate(now);
				licInsuredBankDtls.setModifiedDate(now);
				this.licOblApplicationMst.setLicInsuredBankDtls(licInsuredBankDtls);
				licInsuredBankDtls.setLicOblApplicationMst(this.licOblApplicationMst);
			}
			
			if(licOblApplicationMst.getSecondaryEntryFlag().equals("Y")){
				validateApointee();
				maritalStatusChangeListener();
			}
			
			if(licOblApplicationMst.getLicInsuredBankDtls().getIfsCode() != null){
				requiredAccountNo = true;
			}

			renderedDetailForm = true;
			renderedListPanel = false;
			renderedsearchPanel = false;
		}catch(Exception e){
			log.info("LicSecondaryDataEntryAction editDetail Error : ", e);
		}
	}
	
	
	public void process(){
		try{
			Date now = new Date();

			//print receipt work start
			if(!(licOblApplicationMst.getSecondaryEntryFlag().equals("Y"))){ // check for secondary entry flag
				Boolean successFlag = false;

				List<PrintRcptMst> printRcptMsts = printRcptMstService.findPrintRcptMstByPrefixBranchIdTieupCoIdParentCoId(licPrintRcptDtls.getPrefix(), loginAction.getUserList().get(0).getBranchMst().getBranchId(), licOblApplicationMst.getLicProductValueMst().getTieupCompyMst().getTieCompyId(), 3l);

				if(printRcptMsts == null || printRcptMsts.size() == 0 || printRcptMsts.contains(null)){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Save UnSuccessful: ", "Master Data not Saved"));
					return;
				}

				for(PrintRcptMst printRcptMst : printRcptMsts){
					if(printRcptMst.getReceiptFrom() <= licPrintRcptDtls.getReceiptNo() && printRcptMst.getReceiptTo() >= licPrintRcptDtls.getReceiptNo()){
						
						List<Long> list = licPrintRcptDtlsService.findAllLicPrintRcptDtlsByPrintRcptMst(printRcptMst);
						
						if(!(list == null || list.size() == 0 || list.contains(null))){
							if(list.contains(licPrintRcptDtls.getReceiptNo())){
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                    "Error : ", "Receipt No. Already Used."));
								return;
								
							}
						}
						successFlag = true;
						licPrintRcptDtls.setTableId(licOblApplicationMst.getId());
						licPrintRcptDtls.setTableName("LIC_OBL_APPLICATION_MST");
						licPrintRcptDtls.setProcessName("OBL");					
						licPrintRcptDtls.setPrintRcptMst(printRcptMst);
						break;
						/*if(list == null || list.size() == 0 || list.contains(null)){
							if(printRcptMst.getReceiptFrom().equals(licPrintRcptDtls.getReceiptNo())){
								successFlag = true;
								licPrintRcptDtls.setTableId(licOblApplicationMst.getId());
								licPrintRcptDtls.setTableName("LIC_OBL_APPLICATION_MST");
								licPrintRcptDtls.setProcessName("OBL");					
								licPrintRcptDtls.setPrintRcptMst(printRcptMst);
								break;
							}else{
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                    "Error : ", "Enter Receipt No. Not In Order"));
								return;
							}
						}else{
							if(licPrintRcptDtls.getReceiptNo().equals((list.get(0) + 1))){
								successFlag = true;
								licPrintRcptDtls.setTableId(licOblApplicationMst.getId());
								licPrintRcptDtls.setTableName("LIC_OBL_APPLICATION_MST");
								licPrintRcptDtls.setProcessName("OBL");
								licPrintRcptDtls.setPrintRcptMst(printRcptMst);
								break;
							}else{
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                    "Error : ", "Enter Receipt No. Not In Order"));
								return;
							}
						}*/
						
						
						
						
						
						
						
					}
				}

				if(successFlag == false){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error : ", "Your Receipt No. Has Not Been Allocated To Your Branch"));
					return;
				}
				//print receipt work end
				
				
			}

			List<LicNomineeDtls> nomineeList = licNomineeDtlsService.findNomineeDtlsByApplication(this.licOblApplicationMst);		
			List<LicInsuredAddressMapping> addressList = licInsuredAddressMappingService.findAddressDtlsByInsuredDtls(licOblApplicationMst.getLicInsuredDtls());

			if(nomineeList == null || nomineeList.size()==0){
				nomineeList = new ArrayList<LicNomineeDtls>();
				nomineeList.add(licNomineeDtls);
				this.licOblApplicationMst.setLicNomineeDtlses(nomineeList);
			}else{	
				for(LicNomineeDtls obj:nomineeList){
					obj.setDeleteFlag("Y");
				}
				nomineeList.add(licNomineeDtls);
				this.licOblApplicationMst.setLicNomineeDtlses(nomineeList);
			}

			if(addressList == null || addressList.size() == 0){
				addressList = new ArrayList<LicInsuredAddressMapping>();
				
				LicInsuredAddressMapping licInsuredAddressMapping=new LicInsuredAddressMapping();
				licInsuredAddressMapping.setLicAddressDtls(licAddressDtls);
				licInsuredAddressMapping.setLicInsuredDtls(this.licOblApplicationMst.getLicInsuredDtls());
				licInsuredAddressMapping.setAddressType("I");
				licInsuredAddressMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licInsuredAddressMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licInsuredAddressMapping.setCreatedDate(now);
				licInsuredAddressMapping.setModifiedDate(now);
				licInsuredAddressMapping.setDeleteFlag("N");
				
				addressList.add(licInsuredAddressMapping);
				
				licAddressDtls.setLicInsuredAddressMappings(addressList);
				this.licOblApplicationMst.getLicInsuredDtls().setLicInsuredAddressMappings(addressList);

			}else{
				for(LicInsuredAddressMapping obj : addressList){
					obj.getLicAddressDtls().setDeleteFlag("Y");
					obj.setDeleteFlag("Y");
				}
				
				LicInsuredAddressMapping licInsuredAddressMapping = new LicInsuredAddressMapping();
				licInsuredAddressMapping.setLicAddressDtls(licAddressDtls);
				licInsuredAddressMapping.setLicInsuredDtls(licOblApplicationMst.getLicInsuredDtls());
				licInsuredAddressMapping.setAddressType("I");
				licInsuredAddressMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licInsuredAddressMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licInsuredAddressMapping.setCreatedDate(now);
				licInsuredAddressMapping.setModifiedDate(now);
				licInsuredAddressMapping.setDeleteFlag("N");

				addressList.add(licInsuredAddressMapping);
				licAddressDtls.setLicInsuredAddressMappings(addressList);
				this.licOblApplicationMst.getLicInsuredDtls().setLicInsuredAddressMappings(addressList);
			}		
			
			if(!(licOblApplicationMst.getLicInsuredBankDtls().getAcctNo() == null || licOblApplicationMst.getLicInsuredBankDtls().getAcctNo().equals(""))){
				Boolean accountValidation = BankValidation.bankAccountValidation(licOblApplicationMst.getLicInsuredBankDtls().getBankName(), licOblApplicationMst.getLicInsuredBankDtls().getAcctNo());
				
				if(accountValidation == false){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error : ", "Account Number Not Valid"));
					return;
				}
			}
			
			licOblApplicationMst.setSecondaryEntryFlag("Y");

			Boolean status = licOblApplicationMstService.insertDataForSecondaryDataEntry(licOblApplicationMst,licPrintRcptDtls);

			if (status) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Save Successful : ", "Data Saved Successfully"));
				renderedDetailForm = false;
				renderedsearchPanel = true;
				refresh();
				search();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Save UnSuccessful : ", "Data Not Saved"));
			}
		}catch(Exception e){
			log.info("LicSecondaryDataEntryAction process Error : ", e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error Occured : ", "Please Try Again"));
		}
	}

	
	/* GETTER SETTER */
	public List<LicOblApplicationMst> getLicOblApplicationMstList() {
		return licOblApplicationMstList;
	}
	public void setLicOblApplicationMstList(
			List<LicOblApplicationMst> licOblApplicationMstList) {
		this.licOblApplicationMstList = licOblApplicationMstList;
	}

	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public LicOblApplicationMst getLicOblApplicationMst() {
		return licOblApplicationMst;
	}
	public void setLicOblApplicationMst(LicOblApplicationMst licOblApplicationMst) {
		this.licOblApplicationMst = licOblApplicationMst;
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

//	public List<LicProofMst> getAgeProofList() {
//		return ageProofList;
//	}
//
//	public void setAgeProofList(List<LicProofMst> ageProofList) {
//		this.ageProofList = ageProofList;
//	}

	
	public List<LicProofMst> getAddrProofList() {
		return addrProofList;
	}
	public void setAddrProofList(List<LicProofMst> addrProofList) {
		this.addrProofList = addrProofList;
	}

	
	public List<LicProofMst> getIdentityProofList() {
		return identityProofList;
	}
	public void setIdentityProofList(List<LicProofMst> identityProofList) {
		this.identityProofList = identityProofList;
	}

	
	public List<LicProofMst> getIncomeProofList() {
		return incomeProofList;
	}
	public void setIncomeProofList(List<LicProofMst> incomeProofList) {
		this.incomeProofList = incomeProofList;
	}

	
	public List<StateMst> getStateMsts() {
		return stateMsts;
	}
	public void setStateMsts(List<StateMst> stateMsts) {
		this.stateMsts = stateMsts;
	}

	
	public LicPrintRcptDtls getLicPrintRcptDtls() {
		return licPrintRcptDtls;
	}
	public void setLicPrintRcptDtls(LicPrintRcptDtls licPrintRcptDtls) {
		this.licPrintRcptDtls = licPrintRcptDtls;
	}

	
	public List<String> getPrefixs() {
		return prefixs;
	}
	public void setPrefixs(List<String> prefixs) {
		this.prefixs = prefixs;
	}

	
	public Boolean getRequiredApointee() {
		return requiredApointee;
	}
	public void setRequiredApointee(Boolean requiredApointee) {
		this.requiredApointee = requiredApointee;
	}

	
	public Boolean getInsuredNomineeAddressSameFlag() {
		return insuredNomineeAddressSameFlag;
	}
	public void setInsuredNomineeAddressSameFlag(
			Boolean insuredNomineeAddressSameFlag) {
		this.insuredNomineeAddressSameFlag = insuredNomineeAddressSameFlag;
	}

	public Boolean getRequiredHusband() {
		return requiredHusband;
	}
	public void setRequiredHusband(Boolean requiredHusband) {
		this.requiredHusband = requiredHusband;
	}


	public Boolean getRequiredAccountNo() {
		return requiredAccountNo;
	}
	public void setRequiredAccountNo(Boolean requiredAccountNo) {
		this.requiredAccountNo = requiredAccountNo;
	}

}
