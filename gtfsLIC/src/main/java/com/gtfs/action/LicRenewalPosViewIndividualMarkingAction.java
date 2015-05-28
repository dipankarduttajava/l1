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

import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicMarkingToQcDtlsService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.LicRequirementDtlsService;

@Component
@Scope("session")
public class LicRenewalPosViewIndividualMarkingAction implements Serializable{
	private Logger log = Logger.getLogger(LicRenewalPosViewIndividualMarkingAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private LicMarkingToQcDtlsService licMarkingToQcDtlsService;
	@Autowired
	private LicRequirementDtlsService licRequirementDtlsService;
	
	
	private Boolean renderedListPanel;
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> selectedList =  new ArrayList<LicPolicyDtlsDto>();
	
	public String onLoad(){
		if(licPolicyDtlsDtoList!=null){
			licPolicyDtlsDtoList.clear();
		}
		
		if(selectedList!=null){
			selectedList.clear();
		}
		
		return "/licHubRenewalActivity/licRenewalPosViewIndividualMarking.xhtml";
	}
	
	public void onSearch(){
		try{
			if(licPolicyDtlsDtoList != null){
				licPolicyDtlsDtoList.clear();
			}
			
			List<Object> list = licPolicyDtlsService.findIndividualMarkingDtlsForPos(loginAction.getUserList().get(0).getBranchMst().getBranchId());

			if(list == null || list.size()==0 || list.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
			
			Iterator<Object> listIterator = list.iterator();
			while(listIterator.hasNext()){
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
		}catch(Exception e){
			log.info("LicRenewalPosViewIndividualMarkingAction onSearch Error : ", e);
		}	
	}

	public void onSave(){
		try{
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unsuccessful : ", "Please Select a Policy"));
				return;
			}

			Date now = new Date();
			List<LicRequirementDtls> licRequirementDtlsToSave = new ArrayList<LicRequirementDtls>();

			for(LicPolicyDtlsDto obj : selectedList){
				List<LicPolicyDtls> policyList = licPolicyDtlsService.findPolicyDtlsByPolicyNoAndDueDateRangeForPos(obj.getPolicyNo(), obj.getFromDueDate(), obj.getToDueDate());

				Iterator<LicPolicyDtls> iterator = policyList.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls policyDtls = iterator.next();
					LicRequirementDtls licRequirementDtls = licRequirementDtlsService.findReqDtlsByPolicyDtlsId(policyDtls).get(0);
					
					licRequirementDtls.getLicMarkingToQcDtls().setIndMrkBy(loginAction.getUserList().get(0).getUserid());
					licRequirementDtls.getLicMarkingToQcDtls().setIndMrkDate(now);
					licRequirementDtls.getLicMarkingToQcDtls().setIndMrkFlag("Y");
					licRequirementDtls.getLicMarkingToQcDtls().setModifiedBy(loginAction.getUserList().get(0).getUserid());
					licRequirementDtls.getLicMarkingToQcDtls().setModifiedDate(now);
					licRequirementDtlsToSave.add(licRequirementDtls);
				}
			}
			
			Boolean status = licMarkingToQcDtlsService.updateForIndividualMarkingForPos(licRequirementDtlsToSave);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "POS Individual Marking Successfully Completed."));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "POS Individual Marking Unsuccessful"));
			}
		}catch(Exception e){
			log.info("LicRenewalPosViewIndividualMarkingAction onSave Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtoList() {
		return licPolicyDtlsDtoList;
	}

	public void setLicPolicyDtlsDtoList(List<LicPolicyDtlsDto> licPolicyDtlsDtoList) {
		this.licPolicyDtlsDtoList = licPolicyDtlsDtoList;
	}

	public List<LicPolicyDtlsDto> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicPolicyDtlsDto> selectedList) {
		this.selectedList = selectedList;
	}
}
