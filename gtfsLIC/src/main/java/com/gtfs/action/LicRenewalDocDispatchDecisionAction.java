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
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;


@Component
@Scope("session")
public class LicRenewalDocDispatchDecisionAction implements Serializable{
	private Logger log = Logger.getLogger(LicRenewalDocDispatchDecisionAction.class);
	
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private LicBranchHubPicMappingService licBranchHubPicMappingService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicHubMstService licHubMstService;
	
	private Date payDateFrom;
	private Date payDateTo;
	
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> selectedList = new ArrayList<LicPolicyDtlsDto>();
	
	public void refresh(){
		payDateFrom = null;
		payDateTo = null;
		
		if(licPolicyDtlsDtoList!=null){
			licPolicyDtlsDtoList.clear();
		}
		
		if(selectedList!=null){
			selectedList.clear();
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubRenewalActivity/licRenewalDocDispatchDecision.xhtml";
	}

	
	public void search(){
		try{
			if(licPolicyDtlsDtoList != null){
				licPolicyDtlsDtoList.clear();
			}
			
			if(selectedList != null){
				selectedList.clear();
			}
			
			List<Object> list = licPolicyDtlsService.findPolicyDtlsForDispatchDecision(payDateFrom, payDateTo, loginAction.getUserList().get(0).getBranchMst().getBranchId());
			
			if(list == null || list.size()==0 || list.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Nothing Left To take Decision"));
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
				licPolicyDtlsDto.setBranchName((String) objects[11]);				
				licPolicyDtlsDtoList.add(licPolicyDtlsDto);
			}
		}catch(Exception e){
			log.info("LicRenewalDocDispatchDecisionAction search Error : ", e);
		}
	}
	
	
	public void save(){
		try{
			List<LicPolicyDtls> licPolicyDtlsToUpdate = new ArrayList<LicPolicyDtls>();
			
			for(LicPolicyDtlsDto obj : selectedList){				
				LicHubMst hubMst = null;
				
				List<LicPolicyDtls> dtls = licPolicyDtlsService.findPolicyDtlsByPolicyNoAndDueDateRange(obj.getPolicyNo(), obj.getFromDueDate(), obj.getToDueDate());
				
				if(obj.getSendingHub().equals("CRH")){
					hubMst = dtls.get(0).getOblHubMst();
				}else if(obj.getSendingHub().equals("PPH")){
					hubMst = licHubMstService.findById(13l); // hard coded 
				}else if(obj.getSendingHub().equals("PDH")){
					hubMst = dtls.get(0).getLicPolicyMst().getSentHub();
				}
				
				Iterator<LicPolicyDtls> iterator = dtls.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls policyDtls = iterator.next();
					policyDtls.setBrnhHubDespFlag("Y");
					policyDtls.setProcessHubMst(hubMst);
					licPolicyDtlsToUpdate.add(policyDtls);
				}
			}
			
			Boolean status  = licPolicyDtlsService.saveBrnhHubDispatchList(licPolicyDtlsToUpdate);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Success : ", "Dispatch List is saved"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Dispatch List is not saved"));
			}
		}catch(Exception e){
			log.info("LicRenewalDocDispatchDecisionAction save Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public Date getPayDateFrom() {
		return payDateFrom;
	}

	public void setPayDateFrom(Date payDateFrom) {
		this.payDateFrom = payDateFrom;
	}

	public Date getPayDateTo() {
		return payDateTo;
	}

	public void setPayDateTo(Date payDateTo) {
		this.payDateTo = payDateTo;
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
