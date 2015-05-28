package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicPremApplMapping;
import com.gtfs.bean.LicPremPolicyMapping;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.bean.PicBranchMst;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.LicPremiumListService;
import com.gtfs.service.interfaces.PicBranchMstService;
@Component
@Scope("session")
public class PremiumListNormal implements Serializable{
	private Logger log = Logger.getLogger(PremiumListNormal.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private PicBranchMstService picBranchMstService;
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	
	private List<PicBranchMst> picBranchMsts = new ArrayList<PicBranchMst>();
	private Long picBranchId;
	private String payMode;
	private String healthReq;
	private Boolean renderedPremiumList;
	private Date payFromDate;
	private Date payToDate;
	
	private List<LicPolicyDtlsDto> licPolicyDtlsDtos = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> selectedList = new ArrayList<LicPolicyDtlsDto>();
	
	@PostConstruct
	public void loadingPicForHub(){
		List<Long> licHubMsts = new ArrayList<Long>();
		for(LicHubMst hub:loginAction.findHubForProcess("RNL")){
			licHubMsts.add(hub.getId());
		}
		
		List<Long> pics = picBranchMstService.findPicIdsForHubsByProcessName(licHubMsts, "RNL");		
		picBranchMsts = picBranchMstService.findPicsByPicIds(pics);
		
	}
	
	public void refresh(){
		renderedPremiumList = false;
		picBranchId = null;
		payMode = null;
		healthReq = null;
		
		if(licPolicyDtlsDtos!=null){
			licPolicyDtlsDtos.clear();
		}
		if(selectedList!=null){
			selectedList.clear();
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubRenewalActivity/premiumListNormal.xhtml";
	}


	public void search(){
		try{
			if(licPolicyDtlsDtos!=null){
				licPolicyDtlsDtos.clear();
			}
			if(selectedList!=null){
				selectedList.clear();
			}
			renderedPremiumList = true;
			List<Object> list = licPremiumListService.findPolicyDtlsForPremList(loginAction.findHubForProcess("RNL"), payMode, healthReq,payFromDate,payToDate);


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
				licPolicyDtlsDtos.add(licPolicyDtlsDto);
			}
		}catch(Exception e){
			log.info("PremiumListNormal search Error : ", e);
		}
	}

	public void save(){
		try{
			if(selectedList == null || selectedList.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select a Policy to Save"));
				return;
			}
			
			Date now = new Date();
			PicBranchMst picBranchMst = picBranchMstService.findbyId(picBranchId);
			LicPremiumListDtls licPremiumListDtls =new LicPremiumListDtls();
			licPremiumListDtls.setPremiumListDate(now);
			licPremiumListDtls.setPremuumType("R");
			licPremiumListDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licPremiumListDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPremiumListDtls.setCreatedDate(now);
			licPremiumListDtls.setModifiedDate(now);
			licPremiumListDtls.setDeleteFlag("N");		
			
			List<LicPremPolicyMapping> licPremPolicyMappings = new ArrayList<LicPremPolicyMapping>();
			
			for(LicPolicyDtlsDto obj:selectedList){
				List<LicPolicyDtls> dtls = licPolicyDtlsService.findPolicyDtlsByPolicyNoAndDueDateRangeForPremium(obj.getPolicyNo(),obj.getFromDueDate(),obj.getToDueDate());
				Iterator<LicPolicyDtls> iterator = dtls.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls policyDtls = iterator.next();

					policyDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
					policyDtls.setModifiedDate(now);
					policyDtls.setProcessPicBranchMst(picBranchMst);
					policyDtls.setProcessHubMst(loginAction.findHubForProcess("RNL").get(0));
					
					
					LicPremPolicyMapping licPremPolicyMapping = new LicPremPolicyMapping();
					licPremPolicyMapping.setCreatedBy(loginAction.getUserList().get(0).getUserid());
					licPremPolicyMapping.setModifiedBy(loginAction.getUserList().get(0).getUserid());
					licPremPolicyMapping.setCreatedDate(now);
					licPremPolicyMapping.setModifiedDate(now);
					licPremPolicyMapping.setDeleteFlag("N");
					licPremPolicyMapping.setLicPolicyDtls(policyDtls);
					licPremPolicyMapping.setLicPremiumListDtls(licPremiumListDtls);
					licPremPolicyMappings.add(licPremPolicyMapping);
				}
			}
			
			Boolean status = licPremiumListService.savePremiumListForRnl(licPremPolicyMappings);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Premium List Generated Successfully"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Premium List Generation UnSuccessful"));
			}
		}catch(Exception e){
			log.info("PremiumListNormal save Error : ", e);
		}
	}
	
	/* GETTER SETTER */
	public List<PicBranchMst> getPicBranchMsts() {
		return picBranchMsts;
	}
	public void setPicBranchMsts(List<PicBranchMst> picBranchMsts) {
		this.picBranchMsts = picBranchMsts;
	}
	public Long getPicBranchId() {
		return picBranchId;
	}
	public void setPicBranchId(Long picBranchId) {
		this.picBranchId = picBranchId;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public Boolean getRenderedPremiumList() {
		return renderedPremiumList;
	}
	public void setRenderedPremiumList(Boolean renderedPremiumList) {
		this.renderedPremiumList = renderedPremiumList;
	}
	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtos() {
		return licPolicyDtlsDtos;
	}
	public void setLicPolicyDtlsDtos(List<LicPolicyDtlsDto> licPolicyDtlsDtos) {
		this.licPolicyDtlsDtos = licPolicyDtlsDtos;
	}
	public List<LicPolicyDtlsDto> getSelectedList() {
		return selectedList;
	}
	public void setSelectedList(List<LicPolicyDtlsDto> selectedList) {
		this.selectedList = selectedList;
	}
	public String getHealthReq() {
		return healthReq;
	}
	public void setHealthReq(String healthReq) {
		this.healthReq = healthReq;
	}
	public Date getPayFromDate() {
		return payFromDate;
	}
	public void setPayFromDate(Date payFromDate) {
		this.payFromDate = payFromDate;
	}
	public Date getPayToDate() {
		return payToDate;
	}
	public void setPayToDate(Date payToDate) {
		this.payToDate = payToDate;
	}
	
}
