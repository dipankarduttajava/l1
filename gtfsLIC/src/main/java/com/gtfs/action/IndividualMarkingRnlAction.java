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

import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicMarkingToQcDtlsService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;

@Component
@Scope("session")
public class IndividualMarkingRnlAction implements Serializable{
	private Logger log = Logger.getLogger(IndividualMarkingRnlAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private LicMarkingToQcDtlsService licMarkingToQcDtlsService;
	
	
	private Date policyFromDate;
	private Date policyToDate;
	private Boolean renderedListPanel;
	
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> selectedList =  new ArrayList<LicPolicyDtlsDto>();
	
	public void refresh(){
		renderedListPanel = false;
		policyFromDate = null;
		policyToDate = null;
		
		if(licPolicyDtlsDtoList != null){
			licPolicyDtlsDtoList.clear();
		}
		
		if(selectedList != null){
			selectedList.clear();
		}
	}

	public String onLoad(){
		refresh();
		return "/licHubRenewalActivity/licRnlIndividualMarking.xhtml";
	}
	
	public void search(){
		try{
			if(licPolicyDtlsDtoList != null){
				licPolicyDtlsDtoList.clear();
			}
			
			List<Object> list = licPolicyDtlsService.findIndividualMarkingDtlsByDateForRenewal(policyFromDate, policyToDate, loginAction.getUserList().get(0).getBranchMst().getBranchId());

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
			log.info("IndividualMarkingRnlAction search Error : ", e);
		}	
	}
	
	public void onSave(){
		try{
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select a Policy to Save"));
				return;
			}
			
			Date now = new Date();
			List<LicPolicyDtls> licPolicyDtlsToSave = new ArrayList<LicPolicyDtls>();
			
			for(LicPolicyDtlsDto obj : selectedList){
				List<LicPolicyDtls> dtls = licPolicyDtlsService.findPolicyDtlsByPolicyNoAndDueDateRange(obj.getPolicyNo(), obj.getFromDueDate(), obj.getToDueDate());

				Iterator<LicPolicyDtls> iterator = dtls.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls licPolicyDtls = iterator.next();
					licPolicyDtls.getLicMarkingToQcDtls().setIndMrkBy(loginAction.getUserList().get(0).getUserid());
					licPolicyDtls.getLicMarkingToQcDtls().setIndMrkDate(now);
					licPolicyDtls.getLicMarkingToQcDtls().setIndMrkFlag("Y");
					licPolicyDtls.getLicMarkingToQcDtls().setModifiedBy(loginAction.getUserList().get(0).getUserid());
					licPolicyDtls.getLicMarkingToQcDtls().setModifiedDate(now);
					licPolicyDtls.setPremListReadyFlag("Y");
					licPolicyDtlsToSave.add(licPolicyDtls);
				}
			}

			Boolean status = licMarkingToQcDtlsService.updateForIndividualMarkingForRenewal(licPolicyDtlsToSave);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "POD Tagging Successfully Completed."));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "POD Tagging Unsuccessful"));
			}
		}catch(Exception e){
			log.info("IndividualMarkingRnlAction save Error : ", e);
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
	public List<LicPolicyDtlsDto> getSelectedList() {
		return selectedList;
	}
	public void setSelectedList(List<LicPolicyDtlsDto> selectedList) {
		this.selectedList = selectedList;
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
}
