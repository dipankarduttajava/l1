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
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicBrnhHubPicMapDtlsService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.LicPremiumListService;

@Component
@Scope("session")
public class RnlPicDeliveryListAction implements Serializable{
	private Logger log = Logger.getLogger(RnlPicDeliveryListAction.class);
	
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private LicBrnhHubPicMapDtlsService licBrnhHubPicMapDtlsService;
	
	private List<Long> premList = new ArrayList<Long>();
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> searchLicPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>(); 
	private List<LicPolicyDtlsDto> addedLicPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>(); 
	
	private Boolean premiumSearchRendered;
	private Long premListNo;
	private Boolean searchRendered;
	
	public void refresh(){
		try{
			searchRendered = false;
			premiumSearchRendered = true;
			//licOblApplicationMsts.clear();
			//searchLicOblApplicationMsts.clear();
			//addedLicOblApplicationMsts.clear();
			premListNo = null;
			//applNo = null;
			premList = licPremiumListService.findPremiumListForRenewalPicDelivery(loginAction.findHubForProcess("RNL"));
		}catch(Exception e){
			log.info("RnlPicDeliveryListAction refresh Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubRenewalActivity/rnlPicDeliveryList.xhtml";
	}

	
	public void save(){
		try{
			Date now = new Date();
			LicBrnhHubPicMapDtls licBrnhHubPicMapDtls =new LicBrnhHubPicMapDtls();
			licBrnhHubPicMapDtls.setBranchHubPicFlag("H2P");
			licBrnhHubPicMapDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setCreatedDate(now);
			licBrnhHubPicMapDtls.setModifiedDate(now);
			licBrnhHubPicMapDtls.setDeleteFlag("N");
			
			List<LicPolicyDtls> licPolicyDtlsToSave = new ArrayList<LicPolicyDtls>();
			
			for(LicPolicyDtlsDto obj : addedLicPolicyDtlsDtoList){
				List<LicPolicyDtls> dtls = licPolicyDtlsService.findPolicyDtlsByPolicyNoAndDueDateRange(obj.getPolicyNo(), obj.getFromDueDate(), obj.getToDueDate());

				Iterator<LicPolicyDtls> iterator = dtls.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls licPolicyDtls = iterator.next();
					licPolicyDtls.setHubPicMapDtls(licBrnhHubPicMapDtls);
					licPolicyDtls.getLicMarkingToQcDtls().setModifiedBy(loginAction.getUserList().get(0).getUserid());
					licPolicyDtls.getLicMarkingToQcDtls().setModifiedDate(now);
					licPolicyDtlsToSave.add(licPolicyDtls);
				}
			}		

			for(LicPolicyDtls obj : licPolicyDtlsToSave){
				obj.setHubPicMapDtls(licBrnhHubPicMapDtls);
			}
			
			licBrnhHubPicMapDtls.setHubPicLicPolicyDtlses(licPolicyDtlsToSave);
			
			Long id = licBrnhHubPicMapDtlsService.saveForHubPicDispatchList(licBrnhHubPicMapDtls);
			
			if(id > 0){
				refresh();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "PIC List Generated Successfully"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "PIC List Generation UnSuccessful"));
			}
		}catch(Exception e){
			log.info("RnlPicDeliveryListAction save Error : ", e);
		}
	}
	
	public void search(){
		try{
			if(licPolicyDtlsDtoList != null){
				licPolicyDtlsDtoList.clear();
			}
			
			List<Object> list = licPremiumListService.findPolicyForDeliveryListOfPic(premListNo);
			
			if(list == null || list.size() == 0 || list.contains(null)){
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
			searchLicPolicyDtlsDtoList.addAll(licPolicyDtlsDtoList);
			searchRendered = true;
			premiumSearchRendered = false;
		}catch(Exception e){
			log.info("RnlPicDeliveryListAction search Error : ", e);
		}
	}
	
	public void add(LicPolicyDtlsDto licPolicyDtlsDto){
		addedLicPolicyDtlsDtoList.add(licPolicyDtlsDto);
		searchLicPolicyDtlsDtoList.remove(licPolicyDtlsDto);
		licPolicyDtlsDtoList.remove(licPolicyDtlsDto);
	}
	
	
	/* GETTER SETTER */
	public List<Long> getPremList() {
		return premList;
	}

	public void setPremList(List<Long> premList) {
		this.premList = premList;
	}

	public Boolean getPremiumSearchRendered() {
		return premiumSearchRendered;
	}

	public void setPremiumSearchRendered(Boolean premiumSearchRendered) {
		this.premiumSearchRendered = premiumSearchRendered;
	}

	public Long getPremListNo() {
		return premListNo;
	}

	public void setPremListNo(Long premListNo) {
		this.premListNo = premListNo;
	}

	public Boolean getSearchRendered() {
		return searchRendered;
	}

	public void setSearchRendered(Boolean searchRendered) {
		this.searchRendered = searchRendered;
	}

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtoList() {
		return licPolicyDtlsDtoList;
	}

	public void setLicPolicyDtlsDtoList(List<LicPolicyDtlsDto> licPolicyDtlsDtoList) {
		this.licPolicyDtlsDtoList = licPolicyDtlsDtoList;
	}

	public List<LicPolicyDtlsDto> getSearchLicPolicyDtlsDtoList() {
		return searchLicPolicyDtlsDtoList;
	}

	public void setSearchLicPolicyDtlsDtoList(
			List<LicPolicyDtlsDto> searchLicPolicyDtlsDtoList) {
		this.searchLicPolicyDtlsDtoList = searchLicPolicyDtlsDtoList;
	}

	public List<LicPolicyDtlsDto> getAddedLicPolicyDtlsDtoList() {
		return addedLicPolicyDtlsDtoList;
	}

	public void setAddedLicPolicyDtlsDtoList(
			List<LicPolicyDtlsDto> addedLicPolicyDtlsDtoList) {
		this.addedLicPolicyDtlsDtoList = addedLicPolicyDtlsDtoList;
	}
	
	
}
