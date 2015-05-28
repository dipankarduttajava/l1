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

import com.gtfs.bean.LicPremPaymentDtls;
import com.gtfs.bean.LicPremiumListDtls;
import com.gtfs.dao.interfaces.LicPolicyDtlsDao;
import com.gtfs.dto.LicPolicyDtlsDto;
import com.gtfs.service.interfaces.LicPremiumListService;

@Component
@Scope("session")
public class LicRnlPremListDetailDataEntryAction implements Serializable{
	private Logger log = Logger.getLogger(LicRnlPremListDetailDataEntryAction.class);
	
	@Autowired
	private LicPremiumListService licPremiumListService;
	@Autowired
	private LoginAction loginAction;

	private Double totalAmt;
	private List<LicPremiumListDtls> premList = new ArrayList<LicPremiumListDtls>();
	private List<LicPolicyDtlsDao> licPolicyDtlsDaos = new ArrayList<LicPolicyDtlsDao>();
	private List<LicPolicyDtlsDto> licPolicyDtlsDtoList = new ArrayList<LicPolicyDtlsDto>();
	private List<LicPremPaymentDtls> licPremPaymentDtlses = new ArrayList<LicPremPaymentDtls>();
	
	private Long premiumListNo;
	private Boolean renderedPanel;
	
	public void refresh(){
		try{
			if(licPolicyDtlsDtoList != null){
				licPolicyDtlsDtoList.clear();
			}
			
			if(premList != null){
				premList.clear();
			}
			
			if(licPolicyDtlsDaos != null){
				licPolicyDtlsDaos.clear();
			}
			
			if(licPremPaymentDtlses != null){
				licPremPaymentDtlses.clear();
			}
			
			totalAmt = null;
			renderedPanel = false;
			
			premList = licPremiumListService.findPremiumListForDetailEntryForRenewal(loginAction.findHubForProcess("RNL"));
		}catch(Exception e){
			log.info("LicRnlPremListDetailDataEntryAction refresh Error : ", e);
		}
	}
	
	public String onLoad(){
		refresh();
		return "/licHubRenewalActivity/licRnlPremListDetailDataEntry.xhtml";
	}

	public void search(){
		try{
			if(licPolicyDtlsDtoList != null){
				licPolicyDtlsDtoList.clear();
			}
			
			List<Object> list = licPremiumListService.findPolicyDtlsByPremListNoForRenewal(premiumListNo);
			
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
				licPolicyDtlsDto.setLicRnlPayId((Long) objects[11]);
				
				Double cashAmt = licPremiumListService.findCashAmtByRnlPayId((Long) objects[11]).get(0);
				Double chqDDAmt = licPremiumListService.findChqDDAmtByRnlPayId((Long) objects[11]).get(0);

				licPolicyDtlsDto.setCashAmt(cashAmt);
				licPolicyDtlsDto.setChqDDAmt(chqDDAmt);
				licPolicyDtlsDto.setTotalAmt(licPolicyDtlsDto.getChqDDAmt() + licPolicyDtlsDto.getCashAmt());
				
				licPolicyDtlsDtoList.add(licPolicyDtlsDto);
				totalAmt = licPolicyDtlsDto.getTotalAmt();
				renderedPanel = true;
			}
		}catch(Exception e){
			log.info("LicRnlPremListDetailDataEntryAction search Error : ", e);
		}
	}
	
	public void addChqDD(){
		Date now = new Date();
		LicPremPaymentDtls licPremPaymentDtls= new LicPremPaymentDtls();
		licPremPaymentDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
		licPremPaymentDtls.setCreatedDate(now);
		licPremPaymentDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
		licPremPaymentDtls.setModifiedDate(now);
		licPremPaymentDtls.setDeleteFlag("N");
		licPremPaymentDtlses.add(licPremPaymentDtls);
	}
	
	public void onSave(){
		try{
			Double totalDraftAmount = 0.0;
			
			for(LicPremPaymentDtls obj : licPremPaymentDtlses){
				totalDraftAmount = totalDraftAmount + obj.getSentPayAmount();
			}
			
			if(!totalAmt.equals(totalDraftAmount)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Total Cheque/DD amount is not equal to Total Premium Amount"));
				return;
			}
			
			LicPremiumListDtls licPremiumListDtls = licPremiumListService.findById(premiumListNo);
			
			for(LicPremPaymentDtls obj : licPremPaymentDtlses){
				obj.setLicPremiumListDtls(licPremiumListDtls);
			}
			licPremiumListDtls.setLicPremPaymentDtlses(licPremPaymentDtlses);
			
			Boolean status = licPremiumListService.saveForPremiumDetailDataEntryList(licPremiumListDtls);
			
			if(status){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Save Successful : ", "Renewal Premium Detailed Entry Successfully Completed"));
				onLoad();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save UnSuccessful : ", "Renewal Premium Detailed Entry UnSuccessful"));
			}
			
		}catch(Exception e){
			log.info("LicRnlPremListDetailDataEntryAction onSave Error : ", e);
		}
	}
	
	
	/* GETTER SETTER */
	public List<LicPremiumListDtls> getPremList() {
		return premList;
	}

	public void setPremList(List<LicPremiumListDtls> premList) {
		this.premList = premList;
	}

	public List<LicPolicyDtlsDao> getLicPolicyDtlsDaos() {
		return licPolicyDtlsDaos;
	}

	public void setLicPolicyDtlsDaos(List<LicPolicyDtlsDao> licPolicyDtlsDaos) {
		this.licPolicyDtlsDaos = licPolicyDtlsDaos;
	}

	public Long getPremiumListNo() {
		return premiumListNo;
	}

	public void setPremiumListNo(Long premiumListNo) {
		this.premiumListNo = premiumListNo;
	}

	public List<LicPolicyDtlsDto> getLicPolicyDtlsDtoList() {
		return licPolicyDtlsDtoList;
	}

	public void setLicPolicyDtlsDtoList(List<LicPolicyDtlsDto> licPolicyDtlsDtoList) {
		this.licPolicyDtlsDtoList = licPolicyDtlsDtoList;
	}

	

	public List<LicPremPaymentDtls> getLicPremPaymentDtlses() {
		return licPremPaymentDtlses;
	}

	public void setLicPremPaymentDtlses(
			List<LicPremPaymentDtls> licPremPaymentDtlses) {
		this.licPremPaymentDtlses = licPremPaymentDtlses;
	}

	public Boolean getRenderedPanel() {
		return renderedPanel;
	}

	public void setRenderedPanel(Boolean renderedPanel) {
		this.renderedPanel = renderedPanel;
	}
	
	
}