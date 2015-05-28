package com.gtfs.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtfs.bean.LicCmsMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPisMst;
import com.gtfs.bean.LicPolicyDtls;
import com.gtfs.dto.CashCmsSlipDto;
import com.gtfs.dto.LicCmsMstDto;
import com.gtfs.dto.LicPisDto;
import com.gtfs.dto.LicRnlPisDto;
import com.gtfs.service.interfaces.LicPisMstService;
import com.gtfs.service.interfaces.LicPolicyDtlsService;

@Component
@Scope("session")
public class LicRnlCmsSlipAction implements Serializable{
	private Logger log = Logger.getLogger(LicRnlCmsSlipAction.class);
	
	@Autowired
	private LicPisMstService licPisMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicPolicyDtlsService licPolicyDtlsService;
	
	private Boolean renderedListPanel;
	private Double totalAmount;
	private Double totalCash;
	private Double totalDDChqTieUpCo;
	private Double totalDDChqInsCo;
	private Date systemDate;
	
	private CashCmsSlipDto cashCmsSlipDto;
	private LicCmsMstDto cmsMstDto;
	private List<LicCmsMstDto> licRnlCmsMstDtoList = new ArrayList<LicCmsMstDto>();
	private Set<LicCmsMstDto> licRnlCmsMstDtoSet = new HashSet<LicCmsMstDto>();
	private List<LicRnlPisDto> licRnlPisDtos = new ArrayList<LicRnlPisDto>();
	private List<LicRnlPisDto> licRnlPisDtosToSave = new ArrayList<LicRnlPisDto>();

	public void refresh(){
		systemDate = new Date();
		renderedListPanel = false;
		cashCmsSlipDto = new CashCmsSlipDto();
		cmsMstDto = new LicCmsMstDto();
		cashCmsSlipDto.setTotal(0.0);
		
		if(licRnlPisDtos!=null){
			licRnlPisDtos.clear();
		}
		
		if(licRnlPisDtosToSave!=null){
			licRnlPisDtosToSave.clear();
		}
		
		if(licRnlCmsMstDtoList != null){
			licRnlCmsMstDtoList.clear();
		}
		
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchRenewalActivity/licRnlCmsSlip.xhtml";
	}
	
	public void generateReport(){
		try{
			cashCmsSlipDto.setFiftyAmt(0.0);
			cashCmsSlipDto.setFiveAmt(0.0);
			cashCmsSlipDto.setFiveHundredAmt(0.0);
			cashCmsSlipDto.setHundredAmt(0.0);
			cashCmsSlipDto.setOneAmt(0.0);
			cashCmsSlipDto.setTenAmt(0.0);
			cashCmsSlipDto.setThousandAmt(0.0);
			cashCmsSlipDto.setTwentyAmt(0.0);
			cashCmsSlipDto.setTwoAmt(0.0);
			cashCmsSlipDto.setTotal(0.0);
			totalCash = 0.0;
			totalDDChqInsCo = 0.0;
			totalDDChqTieUpCo = 0.0;
			totalAmount = 0.0;
			
			List<Object> objectList = licPisMstService.findPolicyDtlsforPis(loginAction.getUserList().get(0).getBranchMst());
			
			if(objectList.size() == 0 || objectList == null || objectList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Records Found"));
				return;
			}
			
			Long tempPayId = -1l;
			Long payId = -1l;
			LicRnlPisDto licRnlPisDto = null;
			
			for(Object objList:objectList){
				Object[] objs = (Object[]) objList;			
				payId = (Long) objs[6];
				
				if(!payId.equals(tempPayId)){
					licRnlPisDto = new LicRnlPisDto();
					licRnlPisDto.setPolicyNo((String) objs[0]);
					licRnlPisDto.setPayDate((Date) objs[1]);
					licRnlPisDto.setCashAmt((Double) objs[2]);
					licRnlPisDto.setDraftChqInsAmt((Double) objs[3]);
					licRnlPisDto.setDraftChqTieAmt((Double) objs[4]);
					licRnlPisDto.setTotalAmt((Double) objs[2] + (Double) objs[3] + (Double) objs[4]);
					licRnlPisDto.setPolicyDtlsId((Long) objs[5]);
					licRnlPisDto.setPaymentMstId((Long) objs[6]);
					licRnlPisDtos.add(licRnlPisDto);
					totalCash = totalCash + licRnlPisDto.getCashAmt();
					totalDDChqInsCo = totalDDChqInsCo + licRnlPisDto.getDraftChqInsAmt();
					totalDDChqTieUpCo = totalDDChqTieUpCo + licRnlPisDto.getDraftChqTieAmt();
					totalAmount = totalAmount + licRnlPisDto.getTotalAmt();
				}			
				tempPayId = (Long) objs[6];
				
				// to save			
				LicRnlPisDto licRnlPisDtoToSave = new LicRnlPisDto();
				licRnlPisDtoToSave.setPolicyNo((String) objs[0]);
				licRnlPisDtoToSave.setPayDate((Date) objs[1]);
				licRnlPisDtoToSave.setCashAmt((Double) objs[2]);
				licRnlPisDtoToSave.setDraftChqInsAmt((Double) objs[3]);
				licRnlPisDtoToSave.setDraftChqTieAmt((Double) objs[4]);
				licRnlPisDtoToSave.setTotalAmt((Double) objs[2] + (Double) objs[3] + (Double) objs[4]);
				licRnlPisDtoToSave.setPolicyDtlsId((Long) objs[5]);
				licRnlPisDtoToSave.setPaymentMstId((Long) objs[6]);
				
				licRnlPisDtosToSave.add(licRnlPisDtoToSave);
			}
			
			if(licRnlPisDtos.size() == 0 || licRnlPisDtos == null || licRnlPisDtos.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Records Found"));
				return;
			}else{
				renderedListPanel = true;
			}
		}catch(Exception e){
			log.info("LicRnlCmsSlipAction generateReport Error : ", e);
		}
	}
	
	public void calcDinominationListener(){
		try{
			cashCmsSlipDto.setFiftyAmt(0.0);
			cashCmsSlipDto.setFiveAmt(0.0);
			cashCmsSlipDto.setFiveHundredAmt(0.0);
			cashCmsSlipDto.setHundredAmt(0.0);
			cashCmsSlipDto.setOneAmt(0.0);
			cashCmsSlipDto.setTenAmt(0.0);
			cashCmsSlipDto.setThousandAmt(0.0);
			cashCmsSlipDto.setTwentyAmt(0.0);
			cashCmsSlipDto.setTwoAmt(0.0);
			cashCmsSlipDto.setTotal(0.0);
			
			if(cashCmsSlipDto.getThousand() != null){
				cashCmsSlipDto.setThousandAmt(cashCmsSlipDto.getThousand() * 1000.0);
			}
			if(cashCmsSlipDto.getFiveHundred() != null){
				cashCmsSlipDto.setFiveHundredAmt(cashCmsSlipDto.getFiveHundred() * 500.0);
			}
			if(cashCmsSlipDto.getHundred() != null){
				cashCmsSlipDto.setHundredAmt(cashCmsSlipDto.getHundred() * 100.0);
			}
			if(cashCmsSlipDto.getFifty() != null){
				cashCmsSlipDto.setFiftyAmt(cashCmsSlipDto.getFifty() * 50.0);
			}
			if(cashCmsSlipDto.getTwenty() != null){
				cashCmsSlipDto.setTwentyAmt(cashCmsSlipDto.getTwenty() * 20.0);
			}
			if(cashCmsSlipDto.getTen() != null){
				cashCmsSlipDto.setTenAmt(cashCmsSlipDto.getTen() * 10.0);
			}
			if(cashCmsSlipDto.getFive() != null){
				cashCmsSlipDto.setFiveAmt(cashCmsSlipDto.getFive() * 5.0);
			}
			if(cashCmsSlipDto.getTwo() != null){
				cashCmsSlipDto.setTwoAmt(cashCmsSlipDto.getTwo() * 2.0);
			}
			if(cashCmsSlipDto.getOne() != null){
				cashCmsSlipDto.setOneAmt(cashCmsSlipDto.getOne() * 1.0);
			}
			cashCmsSlipDto.setTotal(cashCmsSlipDto.getThousandAmt() + cashCmsSlipDto.getFiveHundredAmt() + cashCmsSlipDto.getHundredAmt() + cashCmsSlipDto.getFiftyAmt() + cashCmsSlipDto.getTwentyAmt() + cashCmsSlipDto.getTenAmt() + cashCmsSlipDto.getFiveAmt() + cashCmsSlipDto.getTwoAmt() + cashCmsSlipDto.getOneAmt());
		
		}catch(Exception e){
			log.info("LicRnlCmsSlipAction calcDinominationListener Error : ", e);
		}
		
	}
	
	public void addCmsSlip(){		
			if(cmsMstDto.getPayMode().equals("C")){
				if(cmsMstDto.getAmount().equals(cashCmsSlipDto.getTotal()) && cmsMstDto.getAmount().equals(totalCash)){
					licRnlCmsMstDtoSet.add(cmsMstDto);
					licRnlCmsMstDtoList.clear();
					licRnlCmsMstDtoList.addAll(licRnlCmsMstDtoSet);
					cmsMstDto = new LicCmsMstDto();
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error: ", "Please Add Cash Amount Equivalent To Total Cash Amount."));
				}
			}else if(cmsMstDto.getPayMode().equals("P")){
				if(cmsMstDto.getAmount().equals(totalDDChqInsCo)){
					licRnlCmsMstDtoSet.add(cmsMstDto);
					licRnlCmsMstDtoList.clear();
					licRnlCmsMstDtoList.addAll(licRnlCmsMstDtoSet);
					cmsMstDto = new LicCmsMstDto();
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error: ", "Please Add Parent Company DD/Cheque Amount Equivalent To Total DD/Cheque Amount of Parent Company"));
				}			
			}else if(cmsMstDto.getPayMode().equals("T")){
				if(cmsMstDto.getAmount().equals(totalDDChqTieUpCo)){
					licRnlCmsMstDtoSet.add(cmsMstDto);
					licRnlCmsMstDtoList.clear();
					licRnlCmsMstDtoList.addAll(licRnlCmsMstDtoSet);
					cmsMstDto = new LicCmsMstDto();
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error: ", "Please Add Tie Up DD/Cheque Amount Equivalent To Total DD/Cheque Amount Of Tie Up Company"));
				}
			}		
		
	}
	
	public void deleteCms(LicCmsMstDto licCmsMstDto){
		licRnlCmsMstDtoList.remove(licCmsMstDto);
	}
	
	public void save(){
		try{
			Date now = new Date();			
			Double total = 0.0;			
			LicPisMst licPisMst = new LicPisMst();
			licPisMst.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licPisMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPisMst.setCreatedDate(now);
			licPisMst.setModifiedDate(now);
			licPisMst.setDeleteFlag("N");
			
			for(LicRnlPisDto obj:licRnlPisDtosToSave){
				LicPolicyDtls licPolicyDtls = licPolicyDtlsService.findById(obj.getPolicyDtlsId());
				licPolicyDtls.setLicPisMst(licPisMst);
				licPisMst.getLicPolicyDtlses().add(licPolicyDtls);			
			}
			
			for(LicCmsMstDto obj:licRnlCmsMstDtoList){
				LicCmsMst licCmsMst = new LicCmsMst();
				licCmsMst.setCmsNo(obj.getCmsNo());
				licCmsMst.setPayMode(obj.getPayMode());
				licCmsMst.setAmount(obj.getAmount());
				licCmsMst.setLicPisMst(licPisMst);
				licCmsMst.setCreatedBy(loginAction.getUserList().get(0).getUserid());
				licCmsMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
				licCmsMst.setCreatedDate(now);
				licCmsMst.setModifiedDate(now);
				licCmsMst.setDeleteFlag("N");
				licPisMst.getLicCmsMsts().add(licCmsMst);
				total = total + obj.getAmount();
			}
			
			
			if(!total.equals(totalAmount)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Save Unuccessful: ", "Total Amount is not matched with CMS Total Amount "));
				return;
			}
			
			
			Boolean status = licPisMstService.save(licPisMst);
			if(status){
				refresh();
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		                    "Save Successful: ", "PIS Entry Saved Successfully for PIS No :"+licPisMst.getId()));
			}else{
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Save Unsuccessful: ", "PIS Entry Unsuccessful"));
			}
		}catch(Exception e){
			log.info("LicRnlCmsSlipAction save Error : ", e);
		}
	}

	
	/* GETTER SETTER */
	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(Double totalCash) {
		this.totalCash = totalCash;
	}

	public Double getTotalDDChqTieUpCo() {
		return totalDDChqTieUpCo;
	}

	public void setTotalDDChqTieUpCo(Double totalDDChqTieUpCo) {
		this.totalDDChqTieUpCo = totalDDChqTieUpCo;
	}

	public Double getTotalDDChqInsCo() {
		return totalDDChqInsCo;
	}

	public void setTotalDDChqInsCo(Double totalDDChqInsCo) {
		this.totalDDChqInsCo = totalDDChqInsCo;
	}

	public Date getSystemDate() {
		return systemDate;
	}

	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}

	public List<LicCmsMstDto> getLicRnlCmsMstDtoList() {
		return licRnlCmsMstDtoList;
	}

	public void setLicRnlCmsMstDtoList(List<LicCmsMstDto> licRnlCmsMstDtoList) {
		this.licRnlCmsMstDtoList = licRnlCmsMstDtoList;
	}

	public List<LicRnlPisDto> getLicRnlPisDtos() {
		return licRnlPisDtos;
	}

	public void setLicRnlPisDtos(List<LicRnlPisDto> licRnlPisDtos) {
		this.licRnlPisDtos = licRnlPisDtos;
	}

	public CashCmsSlipDto getCashCmsSlipDto() {
		return cashCmsSlipDto;
	}

	public void setCashCmsSlipDto(CashCmsSlipDto cashCmsSlipDto) {
		this.cashCmsSlipDto = cashCmsSlipDto;
	}

	public LicCmsMstDto getCmsMstDto() {
		return cmsMstDto;
	}

	public void setCmsMstDto(LicCmsMstDto cmsMstDto) {
		this.cmsMstDto = cmsMstDto;
	}

	public List<LicRnlPisDto> getLicRnlPisDtosToSave() {
		return licRnlPisDtosToSave;
	}

	public void setLicRnlPisDtosToSave(List<LicRnlPisDto> licRnlPisDtosToSave) {
		this.licRnlPisDtosToSave = licRnlPisDtosToSave;
	}
	
	
	
	
}
