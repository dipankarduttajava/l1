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
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicOblApplicationMstDto;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.LicRequirementDtlsService;

@Component
@Scope("session")
public class LicPosPrintReceiptAction implements Serializable{
	private Logger log = Logger.getLogger(LicPosPrintReceiptAction.class);
	@Autowired
	private LicRequirementDtlsService licRequirementDtlsService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private BranchMstService branchMstService;
	
	private Date businessDate;
	private String applicationNo;
	private Long branchId;
	private Boolean renderedListPanel;
	
	private List<BranchMst> branchMsts = new ArrayList<BranchMst>();
	
	private List<LicRequirementDtls> licRequirementDtlsList = new ArrayList<LicRequirementDtls>();
	private LicRequirementDtls licRequirementDtls;
	
	@PostConstruct
	public void init(){
		branchMsts = branchMstService.findAll();
	}
	
	public void refresh(){
		businessDate = null;
		applicationNo = null;
		renderedListPanel = false;
		if(licRequirementDtlsList !=null){
			licRequirementDtlsList.clear();
		}
		
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/licPosPrintReceipt.xhtml";
	}

	public void search(){
		try{
			if(businessDate != null){
				licRequirementDtlsList = licRequirementDtlsService.findRequirementForPrintReceiptByDate(businessDate, branchId);
				renderedListPanel = true;
				
			}else if(applicationNo != null && applicationNo != ""){
				licRequirementDtlsList = licRequirementDtlsService.findRequirementForPrintReceiptByApplicationNo(applicationNo, branchId);
				renderedListPanel = true;
				
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_ERROR,"Error :", "Please Enter Date or Application Number"));
				renderedListPanel = false;
			}
		}catch(Exception e){
			log.info("LicPrintReceiptAction search Error : ", e);
		}
	}
	
	
	public String printPage(LicRequirementDtls licRequirementDtls){
		try{
			this.licRequirementDtls = licRequirementDtls;
			if(licRequirementDtls.getPrintFlag().equals("N")){
				Boolean status = licRequirementDtlsService.updatePrintReceiptFlagInLicRequirementDtls(licRequirementDtls.getId());
				
				if(status == false){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error :", "Please Try Again"));
					return null;
				}
			}
			return "/licBranchActivity/licPosProvitionalPrintReceipt.xhtml?faces-redirect=true";
		}catch(Exception e){
			log.info("LicPosPrintReceiptAction printPage Error : ", e);
			return null;
		}
	}
	
	
	/* GETTER SETTER */
	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}
	public List<LicRequirementDtls> getLicRequirementDtlsList() {
		return licRequirementDtlsList;
	}
	public void setLicRequirementDtlsList(
			List<LicRequirementDtls> licRequirementDtlsList) {
		this.licRequirementDtlsList = licRequirementDtlsList;
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
	public LicRequirementDtls getLicRequirementDtls() {
		return licRequirementDtls;
	}
	public void setLicRequirementDtls(LicRequirementDtls licRequirementDtls) {
		this.licRequirementDtls = licRequirementDtls;
	}
	
	
	
}
