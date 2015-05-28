package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicBranchReqRcvMst;
import com.gtfs.bean.LicPaymentDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.LicBranchReqRcvMstService;

@Component
@Scope("session")
public class LicBranchReqRcvMstAction implements Serializable{
	private Logger log = Logger.getLogger(LicBranchReqRcvMstAction.class);
	
	@Autowired
	private LicBranchReqRcvMstService licBranchReqRcvMstService;
	@Autowired
	private BranchMstService branchMstService;
	@Autowired
	private LoginAction loginAction;
	
	private Date businessFromDate;
	private Date businessToDate;
	private Long branchId;
	private Boolean renderedlistForm;
	private Boolean renderedSaveListForm;
	
	private List<BranchMst> branchMsts = new ArrayList<BranchMst>();
	private List<LicRequirementDtls> licRequirementDtlses = new ArrayList<LicRequirementDtls>();
	private List<LicRequirementDtls> licRequirementDtlsList = new ArrayList<LicRequirementDtls>();	
	
	@PostConstruct
	public void init(){
		branchMsts = branchMstService.findAll();
	}
	
	public void refresh(){
		if(licRequirementDtlses != null){
			licRequirementDtlses.clear();
		}
		
		if(licRequirementDtlsList != null){
			licRequirementDtlsList.clear();
		}
		
		renderedlistForm = false;
		renderedSaveListForm = false;
		businessFromDate = null;
		businessToDate = null;
	}
	
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/licBranchReqRcv.xhtml";
	}

	
	public void search(){
		try{
			if(licRequirementDtlses != null){
				licRequirementDtlses.clear();
			}
			
			renderedlistForm = true;
			renderedSaveListForm = false;
			licRequirementDtlses = licBranchReqRcvMstService.findPrendingRequrementAtBranch(businessFromDate, businessToDate, branchId);
			
			if(licRequirementDtlses == null || licRequirementDtlses.size() == 0 || licRequirementDtlses.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
		}catch(Exception e){
			log.info("LicBranchReqRcvMstAction Search Error : ", e);
		}
	}
	
	public void indvSave(LicRequirementDtls licRequirementDtls) {
		licRequirementDtlsList.clear();
		renderedlistForm = false;
		renderedSaveListForm = true;
		licRequirementDtlsList.add(licRequirementDtls);
	}
	
	public void save(){
		try{
			Date now = new Date();
			Long userId = loginAction.getUserList().get(0).getUserid();
			LicBranchReqRcvMst licBranchReqRcvMst = new LicBranchReqRcvMst();
			LicRequirementDtls licRequirementDtls = new LicRequirementDtls();

			if(licRequirementDtlsList.get(0).getReqType().equals("D")){
				// LIC_BRANCH_REQ_REV_MST Save
				licBranchReqRcvMst.setCreatedBy(userId);
				licBranchReqRcvMst.setCreatedDate(now);
				licBranchReqRcvMst.setModifiedBy(userId);
				licBranchReqRcvMst.setModifiedDate(now);
				licBranchReqRcvMst.setDeleteFlag("N");
				
				// LIC_REQUIRMENT_DTLS Save
				licRequirementDtls = licRequirementDtlsList.get(0);
				licRequirementDtls.setBranchRcvFlag("Y");
				licRequirementDtls.setModifiedBy(userId);
				licRequirementDtls.setModifiedDate(now);
				licBranchReqRcvMst.setLicRequirementDtls(licRequirementDtls);
				
			}else if(licRequirementDtlsList.get(0).getReqType().equals("S")){
				// LIC_BRANCH_REQ_REV_MST Save
				licBranchReqRcvMst.setCreatedBy(userId);
				licBranchReqRcvMst.setCreatedDate(now);
				licBranchReqRcvMst.setModifiedBy(userId);
				licBranchReqRcvMst.setModifiedDate(now);
				licBranchReqRcvMst.setDeleteFlag("N");
				
				// LIC_REQUIRMENT_DTLS Save
				licRequirementDtls = licRequirementDtlsList.get(0);
				licRequirementDtls.setBranchRcvFlag("Y");
				licRequirementDtls.setModifiedBy(userId);
				licRequirementDtls.setModifiedDate(now);
				licBranchReqRcvMst.setLicRequirementDtls(licRequirementDtls);
								
				// LIC_BUSINESS_TXN Save
				licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().setBusinessValue(licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getBusinessValue() + licRequirementDtls.getAmount());
				licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().setReceivable(licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getReceivable() + licRequirementDtls.getAmount());
				licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().setReceived(licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getReceived() + licRequirementDtls.getAmount());
								
				// LIC_PAYMENT_MST Save
				licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setTotalReceivable(licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().getTotalReceivable() + licRequirementDtls.getAmount());
				licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setTotalReceived(licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().getTotalReceived() + licRequirementDtls.getAmount());
								
				// LIC_PRODUCT_VALUE_MST Save
				licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicProductValueMst().setTotalAmt(licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicProductValueMst().getTotalAmt() + licRequirementDtls.getAmount());
				
				// LIC_PAYMENT_DTLS Save
				List<LicPaymentDtls> list = new ArrayList<LicPaymentDtls>();
				LicPaymentDtls licPaymentDtls = new LicPaymentDtls();
				licPaymentDtls.setPayMode("C");
				licPaymentDtls.setShortPremFlag("Y");
				licPaymentDtls.setAmount(licRequirementDtls.getAmount());
				licPaymentDtls.setLicRequirementDtls(licRequirementDtls);
				licPaymentDtls.setCreatedBy(userId);
				licPaymentDtls.setCreatedDate(now);
				licPaymentDtls.setModifiedBy(userId);
				licPaymentDtls.setModifiedDate(now);
				licPaymentDtls.setDeleteFlag("N");
				licPaymentDtls.setLicPaymentMst(licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst());
				list.add(licPaymentDtls);
				
				licBranchReqRcvMst.getLicRequirementDtls().getLicOblApplicationMst().getLicBusinessTxn().getLicPaymentMst().setLicPaymentDtlses(list);
				
			}
			
			Boolean status = licBranchReqRcvMstService.save(licBranchReqRcvMst);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Success : ", "Branch Request Saved Successful"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Branch Request Save Unsuccessful"));
			}
		}catch(Exception e){
			log.info("LicBranchReqRcvMstAction Save Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public Date getBusinessFromDate() {
		return businessFromDate;
	}

	public void setBusinessFromDate(Date businessFromDate) {
		this.businessFromDate = businessFromDate;
	}

	public Date getBusinessToDate() {
		return businessToDate;
	}

	public void setBusinessToDate(Date businessToDate) {
		this.businessToDate = businessToDate;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	
	public List<BranchMst> getBranchMsts() {
		return branchMsts;
	}

	public void setBranchMsts(List<BranchMst> branchMsts) {
		this.branchMsts = branchMsts;
	}

	public List<LicRequirementDtls> getLicRequirementDtlses() {
		return licRequirementDtlses;
	}

	public void setLicRequirementDtlses(
			List<LicRequirementDtls> licRequirementDtlses) {
		this.licRequirementDtlses = licRequirementDtlses;
	}

	public List<LicRequirementDtls> getLicRequirementDtlsList() {
		return licRequirementDtlsList;
	}

	public void setLicRequirementDtlsList(
			List<LicRequirementDtls> licRequirementDtlsList) {
		this.licRequirementDtlsList = licRequirementDtlsList;
	}

	public Boolean getRenderedlistForm() {
		return renderedlistForm;
	}

	public void setRenderedlistForm(Boolean renderedlistForm) {
		this.renderedlistForm = renderedlistForm;
	}

	public Boolean getRenderedSaveListForm() {
		return renderedSaveListForm;
	}

	public void setRenderedSaveListForm(Boolean renderedSaveListForm) {
		this.renderedSaveListForm = renderedSaveListForm;
	}

}
