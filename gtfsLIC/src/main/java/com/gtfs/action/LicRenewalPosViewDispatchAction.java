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
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicBrnhHubPicMapDtlsService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;
import com.gtfs.service.interfaces.LicRequirementDtlsService;

@Component
@Scope("session")
public class LicRenewalPosViewDispatchAction implements Serializable{
	private Logger log = Logger.getLogger(LicRenewalPosViewDispatchAction.class);
	
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	@Autowired
	private LicRequirementDtlsService licRequirementDtlsService;
	@Autowired
	private LicBrnhHubPicMapDtlsService licBrnhHubPicMapDtlsService;
	
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPolicyDtlsDto> selectedList = new ArrayList<LicPolicyDtlsDto>();
	
	public void refresh(){
		if(licPolicyDtlsDtoList!=null){
			licPolicyDtlsDtoList.clear();
		}
		
		if(selectedList != null){
			selectedList.clear();
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchRenewalActivity/licRenewalPosViewDispatch.xhtml";
	}
	
	public void onSearch(){
		try{
			if(licPolicyDtlsDtoList != null){
				licPolicyDtlsDtoList.clear();
			}
			
			if(selectedList != null){
				selectedList.clear();
			}
			
			List<Object> list = licPolicyDtlsService.findPolicyDtlsForPosViewDispatch(loginAction.getUserList().get(0).getBranchMst().getBranchId());
			
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
				licPolicyDtlsDto.setHubName((String) objects[11]);
				licPolicyDtlsDtoList.add(licPolicyDtlsDto);
			}
		}catch(Exception e){
			log.info("LicRenewalPosViewDispatchAction onSearch Error : ", e);
		}
	}
	
	public void onSave(){
		try{
			if(selectedList == null || selectedList.size() == 0 || selectedList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Please Select a Policy"));
				return;
			}

			Date now = new Date();		
			List<LicRequirementDtls> licRequirementDtlsToSave = new ArrayList<LicRequirementDtls>();

			LicBrnhHubPicMapDtls licBrnhHubPicMapDtls = new LicBrnhHubPicMapDtls();
			licBrnhHubPicMapDtls.setBranchHubPicFlag("B2H");
			licBrnhHubPicMapDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licBrnhHubPicMapDtls.setCreatedDate(now);
			licBrnhHubPicMapDtls.setModifiedDate(now);
			licBrnhHubPicMapDtls.setDeleteFlag("N");

			for(LicPolicyDtlsDto obj : selectedList){
				List<LicPolicyDtls> policyList = licPolicyDtlsService.findPolicyDtlsByPolicyNoForPos(obj.getPolicyNo());

				Iterator<LicPolicyDtls> iterator = policyList.iterator();
				while(iterator.hasNext()){
					LicPolicyDtls policyDtls = iterator.next();
					LicRequirementDtls licRequirementDtls = licRequirementDtlsService.findReqDtlsByPolicyDtlsId(policyDtls).get(0);

					licRequirementDtls.setDispatchReadyFlag("Y");
					licRequirementDtls.setLicBrnhHubPicMapDtls(licBrnhHubPicMapDtls);
					licRequirementDtlsToSave.add(licRequirementDtls);					
				}
			}
			
			for(LicRequirementDtls obj1 : licRequirementDtlsToSave){
				obj1.setLicBrnhHubPicMapDtls(licBrnhHubPicMapDtls);
			}

			licBrnhHubPicMapDtls.setLicRequirementDtlses(licRequirementDtlsToSave);
			
			Boolean status  = licBrnhHubPicMapDtlsService.savePosViewDispatchList(licBrnhHubPicMapDtls);

			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Success : ", "POS View Dispatch List is saved"));
				refresh();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "POS View Dispatch List is not saved"));
			}
			
		}catch(Exception e){
			log.info("LicRenewalPosViewDispatchAction onSave Error : ", e);
		}
	}

	
	/* GETTER SETTER */
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
