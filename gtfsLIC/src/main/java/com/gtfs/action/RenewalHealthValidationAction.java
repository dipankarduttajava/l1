package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicPolicyDtlsService;

@Component
@Scope("session")
public class RenewalHealthValidationAction implements Serializable {
	private Logger log = Logger.getLogger(RenewalHealthValidationAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;

	private Date policyFromDate;
	private Date policyToDate;
	private Boolean renderedListPanel;
	private Boolean renderedSave;

	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();

	public void refresh() {
		policyFromDate = null;
		policyToDate = null;
		renderedListPanel = false;
		renderedSave = false;

		if (licPolicyDtlsDtoList != null) {
			licPolicyDtlsDtoList.clear();
		}
	}

	public String onLoad() {
		refresh();
		return "/licHubRenewalActivity/renewalHealthValidation.xhtml";
	}

	public void search() {
		try {
			if (licPolicyDtlsDtoList != null) {
				licPolicyDtlsDtoList.clear();
			}

			List<Object> list = licPolicyDtlsService.findHealthValidationDtlsByDateForRenewal(policyFromDate, policyToDate, loginAction.getUserList().get(0).getBranchMst().getBranchId());

			if (list == null || list.size() == 0 || list.contains(null)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error : ", "No Record(s) Found"));
				return;
			}

			Iterator<Object> listIterator = list.iterator();
			while (listIterator.hasNext()) {
				Object[] objects = (Object[]) listIterator.next();
				LicPolicyDtlsDto licPolicyDtlsDto = new LicPolicyDtlsDto();
				licPolicyDtlsDto.setPolicyNo((String) objects[0]);
				licPolicyDtlsDto.setPayDate((Date) objects[1]);
				licPolicyDtlsDto.setInsuredName((String) objects[2]);
				licPolicyDtlsDto.setProposerName((String) objects[3]);
				licPolicyDtlsDto.setProduct((String) objects[4]);
				licPolicyDtlsDto.setHealthFlag((String) objects[5]);
				licPolicyDtlsDto.setPayMode((String) objects[6]);
				licPolicyDtlsDto.setDueYears((Long) objects[7]);
				licPolicyDtlsDto.setFromDueDate((Date) objects[8]);
				licPolicyDtlsDto.setToDueDate((Date) objects[9]);
				licPolicyDtlsDto.setPayNature((String) objects[10]);
				licPolicyDtlsDtoList.add(licPolicyDtlsDto);
			}
			renderedListPanel = true;
			renderedSave = true;
		} catch (Exception e) {
			log.info("RenewalHealthValidationAction search Error : ", e);
		}
	}

	public void onSave() {
		try {
			Date now = new Date();
			List<LicPolicyDtls> licPolicyDtlsToUpdate = new ArrayList<LicPolicyDtls>();
			for (LicPolicyDtlsDto obj : licPolicyDtlsDtoList) {
				List<LicPolicyDtls> dtls = licPolicyDtlsService.findPolicyDtlsByPolicyNoDueDateRangeForHealthValidation(obj.getPolicyNo(), obj.getFromDueDate(), obj.getToDueDate());
				
				Iterator<LicPolicyDtls> iterator = dtls.iterator();
				while (iterator.hasNext()) {
					LicPolicyDtls policyDtls = iterator.next();
					policyDtls.setHealthValidated(obj.getHealthValidated());
					
					if(obj.getHealthValidated().equals("N")){
						LicRequirementDtls licRequirementDtls = new LicRequirementDtls();
						licRequirementDtls.setActionBy(loginAction.getUserList().get(0).getUserid());
						licRequirementDtls.setActionDate(now);
						licRequirementDtls.setActionType("CFSL");
						licRequirementDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
						licRequirementDtls.setCreatedDate(now);
						licRequirementDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
						licRequirementDtls.setModifiedDate(now);
						licRequirementDtls.setDeleteFlag("N");
						licRequirementDtls.setDocument("Document Required");
						licRequirementDtls.setProcessType("POS");
						licRequirementDtls.setReqType("D");
						licRequirementDtls.setLicPolicyDtls(policyDtls);
						
						List<LicRequirementDtls> licRequirementDtlses = new ArrayList<LicRequirementDtls>();
						licRequirementDtlses.add(licRequirementDtls);
						
						policyDtls.setLicRequirementDtlses(licRequirementDtlses);
					}
					
					licPolicyDtlsToUpdate.add(policyDtls);
				}
			}

			Boolean status  = licPolicyDtlsService.saveHealthValidationForRenewal(licPolicyDtlsToUpdate);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Success : ", "Health Validated Successfully"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Health Validatation UnSuccessfull"));
			}
		} catch (Exception e) {
			log.info("RenewalHealthValidationAction save Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public Date getPolicyFromDate() {
		return policyFromDate;
	}

	public void setPolicyFromDate(Date policyFromDate) {
		this.policyFromDate = policyFromDate;
	}

	public Date getPolicyToDate() {
		return policyToDate;
	}

	public void setPolicyToDate(Date policyToDate) {
		this.policyToDate = policyToDate;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtoList() {
		return licPolicyDtlsDtoList;
	}

	public void setLicPolicyDtlsDtoList(
			List<LicPolicyDtlsDto> licPolicyDtlsDtoList) {
		this.licPolicyDtlsDtoList = licPolicyDtlsDtoList;
	}

	public Boolean getRenderedSave() {
		return renderedSave;
	}

	public void setRenderedSave(Boolean renderedSave) {
		this.renderedSave = renderedSave;
	}

}
