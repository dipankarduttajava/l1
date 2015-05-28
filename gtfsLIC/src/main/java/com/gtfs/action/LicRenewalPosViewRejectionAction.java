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

import com.gtfs.bean.BranchMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.BranchMstService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.LicRequirementDtlsService;

@Component
@Scope("session")
public class LicRenewalPosViewRejectionAction implements Serializable{
	private Logger log = Logger.getLogger(LicRenewalPosViewRejectionAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private LicRequirementDtlsService licRequirementDtlsService;
	@Autowired
	private BranchMstService branchMstService;
	
	private Date policyFromDate;
	private Date policyToDate;
	private Long branchId;
	private Boolean renderedListPanel;
	private Boolean renderedSave;
	
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> selectedList =  new ArrayList<LicPolicyDtlsDto>();
	private List<BranchMst> branchMstList =  new ArrayList<BranchMst>();
	
	public void refresh(){
		try{
			policyFromDate = null;
			policyToDate = null;
			renderedListPanel = false;
			renderedSave = false;
			
			if (licPolicyDtlsDtoList != null) {
				licPolicyDtlsDtoList.clear();
			}
			
			branchMstList = branchMstService.findAll();
		}catch(Exception e){
			log.info("LicRenewalPosViewRejectionAction refresh Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchRenewalActivity/licRenewalPosViewRejection.xhtml";
	}
	
	public void onSearch() {
		try {
			if (licPolicyDtlsDtoList != null) {
				licPolicyDtlsDtoList.clear();
			}

			List<Object> list = licPolicyDtlsService.findPosViewRejectionByDateForRenewal(policyFromDate, policyToDate, branchId);

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
			log.info("LicRenewalPosViewRejectionAction onSearch Error : ", e);
		}
	}
	
	public void onSave(){
		try{
			Date now = new Date();
			List<LicRequirementDtls> licRequirementDtlsToUpdate = new ArrayList<LicRequirementDtls>();
			
			for(LicPolicyDtlsDto obj : selectedList){
				List<LicPolicyDtls> policyList = licPolicyDtlsService.findPolicyDtlsByPolicyNoAndDueDateRange(obj.getPolicyNo(), obj.getFromDueDate(), obj.getToDueDate());
				
				Iterator<LicPolicyDtls> iterator = policyList.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls policyDtls = iterator.next();
					LicRequirementDtls licRequirementDtls = licRequirementDtlsService.findReqDtlsByPolicyDtlsId(policyDtls).get(0);
					
					licRequirementDtls.setColBy(loginAction.getUserList().get(0).getUserid());
					licRequirementDtls.setColDate(now);
					licRequirementDtls.setColFlag("Y");
					licRequirementDtls.setBranchRcvFlag("Y");
					licRequirementDtls.setLicHubMst(policyDtls.getProcessHubMst());
					licRequirementDtlsToUpdate.add(licRequirementDtls);
					
				}
			}
			
			Boolean status = licRequirementDtlsService.updateForPosViewRejectionForRenewal(licRequirementDtlsToUpdate);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "POS View Rejection Successfully Completed."));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "POS View Rejection Unsuccessful"));
			}
		}catch(Exception e){
			log.info("LicRenewalPosViewRejectionAction save Error : ", e);
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

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtoList() {
		return licPolicyDtlsDtoList;
	}

	public void setLicPolicyDtlsDtoList(List<LicPolicyDtlsDto> licPolicyDtlsDtoList) {
		this.licPolicyDtlsDtoList = licPolicyDtlsDtoList;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public Boolean getRenderedSave() {
		return renderedSave;
	}

	public void setRenderedSave(Boolean renderedSave) {
		this.renderedSave = renderedSave;
	}

	public List<LicPolicyDtlsDto> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicPolicyDtlsDto> selectedList) {
		this.selectedList = selectedList;
	}
	
	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public List<BranchMst> getBranchMstList() {
		return branchMstList;
	}

	public void setBranchMstList(List<BranchMst> branchMstList) {
		this.branchMstList = branchMstList;
	}

}
