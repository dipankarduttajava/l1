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

import com.gtfs.bean.LicBrnhHubPicMapDtls;
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;

@Component
@Scope("session")
public class LicRnlBranchHubDispatchAction implements Serializable{
	private Logger log = Logger.getLogger(LicRnlBranchHubDispatchAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicHubMstService licHubMstService;
	@Autowired
	private LicBranchHubPicMappingService licBranchHubPicMappingService;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	
	private Date payDateFrom;
	private Date payDateTo;
	private Long hubId;
	private Boolean renderedListPanel;
	
	private List<LicHubMst> hubMstList = new ArrayList<LicHubMst>();
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> selectedList = new ArrayList<LicPolicyDtlsDto>();
	
	
	public void refresh(){
		try{
			if(hubMstList!=null){
				hubMstList.clear();
			}
			
			payDateFrom = null;
			payDateTo = null;
			List<Long> destinationlist = licBranchHubPicMappingService.findHubIdForRnlDespatch(loginAction.getUserList().get(0).getBranchMst().getBranchId());
		
			for(LicHubMst obj:licHubMstService.findActiveHubMst()){
				if(destinationlist.contains(obj.getId())){
					hubMstList.add(obj);
				}		
			}
			
			if(licPolicyDtlsDtoList!=null){
				licPolicyDtlsDtoList.clear();
			}
			
			if(selectedList!=null){
				selectedList.clear();
			}
		}catch(Exception e){
			log.info("LicRnlBranchHubDispatchAction refresh Error : ", e);
		}
	}
	
	public void searchForDispatch(){
		try{
			List<Object> list = licPolicyDtlsService.findPolicyDtlsByHubIdAndBranchForDispatch(payDateFrom, payDateTo, hubId, loginAction.getUserList().get(0).getBranchMst().getBranchId());
			
			if(list == null || list.size()==0 || list.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Nothing to Dispatch"));
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
		}catch(Exception e){
			log.info("LicRnlBranchHubDispatchAction searchForDispatch Error : ", e);
		}
	}
	
	public void onSave(){
		try{
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select an Application to Save"));
				return;
			}
			
			Date now = new Date();		
			List<LicPolicyDtls> licPolicyDtlsToSave = new ArrayList<LicPolicyDtls>();
			
			LicBrnhHubPicMapDtls licBrnhHubPicMapDtls = new LicBrnhHubPicMapDtls();
			licBrnhHubPicMapDtls.setBranchHubPicFlag("B2H");
			licBrnhHubPicMapDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setCreatedDate(now);
			licBrnhHubPicMapDtls.setModifiedDate(now);
			licBrnhHubPicMapDtls.setDeleteFlag("N");
			
			
			for(LicPolicyDtlsDto obj : selectedList){
				List<LicPolicyDtls> dtls = licPolicyDtlsService.findPolicyDtlsByPolicyNoAndDueDateRange(obj.getPolicyNo(), obj.getFromDueDate(), obj.getToDueDate());
				
				Iterator<LicPolicyDtls> iterator = dtls.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls policyDtls = iterator.next();
					policyDtls.setBrnhHubMapDtls(licBrnhHubPicMapDtls);
					licPolicyDtlsToSave.add(policyDtls);
				}
			}
			Boolean status  = licPolicyDtlsService.saveBrnhHubDispatchList(licPolicyDtlsToSave);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Success : ", "Dispatch List is saved"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Dispatch List is not saved"));
			}
		}catch(Exception e){
			log.info("LicRnlBranchHubDispatchAction save Error : ", e);
		}
	}
	
	public String onLoad(){		
		refresh();
		return "/licBranchRenewalActivity/licRnlBranchHubDispatch.xhtml";
	}
	
	
	/* GETTER SETTER */
	public Long getHubId() {
		return hubId;
	}

	public void setHubId(Long hubId) {
		this.hubId = hubId;
	}

	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public List<LicHubMst> getHubMstList() {
		return hubMstList;
	}

	public void setHubMstList(List<LicHubMst> hubMstList) {
		this.hubMstList = hubMstList;
	}

	

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtoList() {
		return licPolicyDtlsDtoList;
	}

	public void setLicPolicyDtlsDtoList(List<LicPolicyDtlsDto> licPolicyDtlsDtoList) {
		this.licPolicyDtlsDtoList = licPolicyDtlsDtoList;
	}

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

	public List<LicPolicyDtlsDto> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicPolicyDtlsDto> selectedList) {
		this.selectedList = selectedList;
	}

}
