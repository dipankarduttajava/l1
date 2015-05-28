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

import com.gtfs.bean.LicMarkingToQcDtls;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.ConsolidatedMarkingDto;
import com.gtfs.service.interfaces.LicBrnhHubPicPodDtlsService;
import com.gtfs.service.interfaces.LicMarkingToQcDtlsService;

@Component
@Scope("session")
public class ConsolidatedMarkingForReqAction implements Serializable{
	private Logger log = Logger.getLogger(ConsolidatedMarkingForReqAction.class);
	
	@Autowired
	private LicBrnhHubPicPodDtlsService licBrnhHubPicPodDtlsService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicMarkingToQcDtlsService licMarkingToQcDtlsService;
	
	private Long podId;
	private Date receivedDate;
	private List<ConsolidatedMarkingDto> consolidatedMarkingDtoList = new ArrayList<ConsolidatedMarkingDto>();
	
	
	public void markAsReceived(){
		try{
			Date now = new Date();
			List<LicRequirementDtls> list = licBrnhHubPicPodDtlsService.findApplicationByPodIdForReq(podId);
			
			LicMarkingToQcDtls licMarkingToQcDtls = new LicMarkingToQcDtls();
			licMarkingToQcDtls.setConsldMrkFlag("Y");
			licMarkingToQcDtls.setConsldDate(receivedDate);
			licMarkingToQcDtls.setConsldBy(loginAction.getUserList().get(0).getUserid());
			licMarkingToQcDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licMarkingToQcDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licMarkingToQcDtls.setCreatedDate(now);
			licMarkingToQcDtls.setModifiedDate(now);
			licMarkingToQcDtls.setDeleteFlag("N");
			licMarkingToQcDtls.setLicRequirementDtls(list);
			
			for(LicRequirementDtls obj:list){
				obj.setLicMarkingToQcDtls(licMarkingToQcDtls);
			}
			
			Boolean status = licMarkingToQcDtlsService.saveForConsolidateMarking(licMarkingToQcDtls);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Mark As Received Successfully"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Mark As Received Unsuccessful"));
			}
		}catch(Exception e){
			log.info("ConsolidatedMarkingForReqAction markAsReceived Error : ", e);
		}
	}
	
	public String onLoad(){
		try{
			if(consolidatedMarkingDtoList!=null){
				consolidatedMarkingDtoList.clear();
			}
			
			receivedDate = null;
			List<Object> list = licBrnhHubPicPodDtlsService.findDistinctPodDtlsConsldMarkingForReq(loginAction.findHubForProcess("POS"));

			if(!(list == null || list.size() == 0)){
				for(Object ob:list){
					Object[] arr = (Object[]) ob;
					ConsolidatedMarkingDto consolidatedMarkingDto = new ConsolidatedMarkingDto();
					consolidatedMarkingDto.setPodNo((String) arr[0]);
					consolidatedMarkingDto.setPodDate((Date) arr[1]);
					consolidatedMarkingDto.setUserName((String) arr[2]);
					consolidatedMarkingDto.setUserid( (Long) arr[3]);
					consolidatedMarkingDto.setId((Long) arr[4]);
					consolidatedMarkingDto.setCourierName((String) arr[5]);
					
					if((Long) arr[3] != null){
						consolidatedMarkingDto.setDropdownString("Pod No: " + (String) arr[0] + " Dated: " +(Date) arr[1] + " Through Employee No: "+ (Long) arr[3]);
					}else{
						consolidatedMarkingDto.setDropdownString("Pod No: " + (String) arr[0] + " Dated: " +(Date) arr[1] + " Through Courier: "+ (String) arr[5]);
					}
					
					consolidatedMarkingDtoList.add(consolidatedMarkingDto);
					System.out.println("consolidatedMarkingDtoList : " + consolidatedMarkingDtoList);
				}
			}
			return "/licHubActivity/consolidatedMarkingForReq.xhtml";
		}catch(Exception e){
			log.info("ConsolidatedMarkingForReqAction onLoad Error : ", e);
			return null;
		}
	}

	
	/* GETTER SETTER */
	public Long getPodId() {
		return podId;
	}

	public void setPodId(Long podId) {
		this.podId = podId;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public List<ConsolidatedMarkingDto> getConsolidatedMarkingDtoList() {
		return consolidatedMarkingDtoList;
	}

	public void setConsolidatedMarkingDtoList(
			List<ConsolidatedMarkingDto> consolidatedMarkingDtoList) {
		this.consolidatedMarkingDtoList = consolidatedMarkingDtoList;
	}
	
}
