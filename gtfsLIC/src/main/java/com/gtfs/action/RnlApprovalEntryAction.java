package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPolicyMst;
import com.gtfs.service.interfaces.LicPolicyDtlsService;

@Component
@Scope("session")
public class RnlApprovalEntryAction implements Serializable{
	private Logger log = Logger.getLogger(RnlApprovalEntryAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	
	private String policyNo;
	private Boolean statusUpdate;
	private Boolean rprUpdate;
	private Boolean renderedList;
	private Boolean renderedApprovalList;
	private LicPolicyDtls licPolicyDtls;
	private List<LicPolicyDtls> licPolicyDtlsList = new ArrayList<LicPolicyDtls>();
	private List<LicPolicyDtls> approvalLicPolicyDtlsList = new ArrayList<LicPolicyDtls>();
	
	
	public void refresh(){
		policyNo = null;
		renderedList = false;
		renderedApprovalList = false;
		
		if(licPolicyDtlsList != null){
			licPolicyDtlsList.clear();
		}
		
		if(approvalLicPolicyDtlsList != null){
			approvalLicPolicyDtlsList.clear();
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubRenewalActivity/rnlApprovalEntry.xhtml";
	}
	
	public void statusChange(){
		if(statusUpdate == false){
			rprUpdate = false;
		}
	}
	
	public void rprChange(){
		if(rprUpdate == true){
			statusUpdate = true;
		}
	}
	
	public void onSearch(){
		try{
			licPolicyDtlsList.clear();
			approvalLicPolicyDtlsList.clear();
			
			if(statusUpdate || rprUpdate){
				licPolicyDtlsList = licPolicyDtlsService.findPolicyDtlsByPolicyNoForRpr(policyNo, loginAction.getUserList().get(0).getBranchMst().getBranchId());
				renderedList = true;
				renderedApprovalList = false;
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select at least One Checkbox"));
				return;
			}			
		}catch(Exception e){
			log.info("RnlApprovalEntryAction Search Error : ", e);
		}
	}
	
	public void select(LicPolicyDtls licPolicyDtls){
		try{
			approvalLicPolicyDtlsList.add(licPolicyDtls);
			
			if(rprUpdate != true){
				if(licPolicyDtls.getRprFlag().equals("N")){
					licPolicyDtls.setRprFlag("N");
				}else{
					licPolicyDtls.setRprFlag("Y");
				}
			}else{
				licPolicyDtls.setRprFlag("Y");
			}
			
			this.licPolicyDtls = licPolicyDtls;
			renderedList = false;
			renderedApprovalList = true;
		}catch(Exception e){
			log.info("RnlApprovalEntryAction select Error : ", e);
		}
	}

	public void save(){
		try{
			List<LicPolicyDtls> list = new ArrayList<LicPolicyDtls>();
			
			for(LicPolicyDtls obj : approvalLicPolicyDtlsList){
				obj.setRenewalStatus(licPolicyDtls.getRenewalStatus());
				obj.setRprFlag(licPolicyDtls.getRprFlag());
				obj.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				obj.setModifiedDate(new Date());
				list.add(obj);
			}
			
			Boolean status = licPolicyDtlsService.updatePolicyDtlsRenewalRprStatus(list);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Success : ", "Renewal Policy Updatation Successful"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Renewal Policy Updatation Unsuccessful"));
			}
		}catch(Exception e){
			log.info("RnlApprovalEntryAction Save Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Boolean getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Boolean statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

	public Boolean getRprUpdate() {
		return rprUpdate;
	}

	public void setRprUpdate(Boolean rprUpdate) {
		this.rprUpdate = rprUpdate;
	}

	public Boolean getRenderedList() {
		return renderedList;
	}

	public void setRenderedList(Boolean renderedList) {
		this.renderedList = renderedList;
	}

	public Boolean getRenderedApprovalList() {
		return renderedApprovalList;
	}

	public void setRenderedApprovalList(Boolean renderedApprovalList) {
		this.renderedApprovalList = renderedApprovalList;
	}

	public List<LicPolicyDtls> getLicPolicyDtlsList() {
		return licPolicyDtlsList;
	}

	public void setLicPolicyDtlsList(List<LicPolicyDtls> licPolicyDtlsList) {
		this.licPolicyDtlsList = licPolicyDtlsList;
	}

	public List<LicPolicyDtls> getApprovalLicPolicyDtlsList() {
		return approvalLicPolicyDtlsList;
	}

	public void setApprovalLicPolicyDtlsList(
			List<LicPolicyDtls> approvalLicPolicyDtlsList) {
		this.approvalLicPolicyDtlsList = approvalLicPolicyDtlsList;
	}

	public LicPolicyDtls getLicPolicyDtls() {
		return licPolicyDtls;
	}

	public void setLicPolicyDtls(LicPolicyDtls licPolicyDtls) {
		this.licPolicyDtls = licPolicyDtls;
	}
	
}
