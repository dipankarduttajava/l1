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

import com.gtfs.bean.LicCmsDenominationDtls;
import com.gtfs.bean.LicCmsMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPisMst;
import com.gtfs.dto.CashCmsSlipDto;
import com.gtfs.dto.LicCmsMstDto;
import com.gtfs.dto.LicPisDto;
import com.gtfs.service.interfaces.LicOblApplicationMstService;
import com.gtfs.service.interfaces.LicPisMstService;

@Component
@Scope("session")
public class LicCmsSlipAction implements Serializable{
	private Logger log = Logger.getLogger(LicCmsSlipAction.class);
	
	@Autowired
	private LicPisMstService licPisMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicOblApplicationMstService licOblApplicationMstService;
	
	private Boolean renderedListPanel;
	private Boolean renderedGeneratePanel;
	private Double totalAmount;
	private Double totalCash;
	private Double totalDDChqTieUpCo;
	private Double totalDDChqInsCo;
	private Date systemDate = new Date();
		
	private CashCmsSlipDto cashCmsSlipDto;
	private LicCmsMstDto cmsMstDto;
	
	private List<LicOblApplicationMst> selectedList =  new ArrayList<LicOblApplicationMst>();
	private List<LicPisDto> licPisDtos = new ArrayList<LicPisDto>();
	private List<LicCmsMstDto> licCmsMstDtoList = new ArrayList<LicCmsMstDto>();
	private Set<LicCmsMstDto> licCmsMstDtoset = new HashSet<LicCmsMstDto>();
	
	
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
			
			for(LicPisDto obj:licPisDtos){
				LicOblApplicationMst applicationMaster = licOblApplicationMstService.findById(obj.getApplicationMstId());
				applicationMaster.setLicPisMst(licPisMst);
				licPisMst.getLicOblApplicationMsts().add(applicationMaster);			
			}
			
			for(LicCmsMstDto obj:licCmsMstDtoList){
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
				
				if(obj.getPayMode().equals("C")){
					LicCmsDenominationDtls licCmsDenominationDtls = new LicCmsDenominationDtls();
					licCmsDenominationDtls.setInr1(cashCmsSlipDto.getOne());
					licCmsDenominationDtls.setInr2(cashCmsSlipDto.getTwo());
					licCmsDenominationDtls.setInr5(cashCmsSlipDto.getFive());
					licCmsDenominationDtls.setInr10(cashCmsSlipDto.getTen());
					licCmsDenominationDtls.setInr20(cashCmsSlipDto.getTwenty());
					licCmsDenominationDtls.setInr50(cashCmsSlipDto.getFifty());
					licCmsDenominationDtls.setInr100(cashCmsSlipDto.getHundred());
					licCmsDenominationDtls.setInr500(cashCmsSlipDto.getFiveHundred());
					licCmsDenominationDtls.setInr1000(cashCmsSlipDto.getThousand());
					licCmsDenominationDtls.setLicCmsMst(licCmsMst);
					licCmsMst.setLicCmsDenominationDtls(licCmsDenominationDtls);				
					licCmsDenominationDtls.setCreatedBy(loginAction.getUserList().get(0).getUserid());
					licCmsDenominationDtls.setModifiedBy(loginAction.getUserList().get(0).getUserid());
					licCmsDenominationDtls.setCreatedDate(now);
					licCmsDenominationDtls.setModifiedDate(now);
					licCmsDenominationDtls.setDeleteFlag("N");
					
				}
				
				
				
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
			log.info("LicCmsSlipAction Search Error : ", e);
		}
	}
	
	public void refresh(){
		systemDate = new Date();
		cashCmsSlipDto = new CashCmsSlipDto();
		cmsMstDto = new LicCmsMstDto();
		cashCmsSlipDto.setTotal(0.0);
		renderedListPanel = false;
		
		if(licPisDtos != null ){
			licPisDtos.clear();
		}
		
		if(licCmsMstDtoList != null){
			licCmsMstDtoList.clear();
		}
		
		if(licCmsMstDtoset != null){
			licCmsMstDtoset.clear();
		}
		renderedGeneratePanel = true;
	}
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/licCmsSlip.xhtml";
	}
	
	public void generateReport(){
		try{
			if(licCmsMstDtoList != null){
				licCmsMstDtoList.clear();
			}
			
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
			licPisDtos.clear();
			
			List<Object> objectList = licPisMstService.findApplicationforPis(loginAction.getUserList().get(0).getBranchMst());
			
			if(objectList.size() == 0 || objectList == null || objectList.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}
			
			for(Object objList : objectList){
				Object[] objs = (Object[]) objList;
				LicPisDto licPisDto = new LicPisDto();
				licPisDto.setApplicationNo((String) objs[0]);
				licPisDto.setBusinessDate((Date) objs[1]);
				licPisDto.setCashAmt((Double) objs[2]);
				licPisDto.setDraftChqInsAmt((Double) objs[3]);
				licPisDto.setDraftChqTieAmt((Double) objs[4]);
				licPisDto.setTotalAmt((Double) objs[2] + (Double) objs[3] + (Double) objs[4]);
				licPisDto.setApplicationMstId((Long) objs[5]);
				licPisDtos.add(licPisDto);
				
				totalCash = totalCash + licPisDto.getCashAmt();
				totalDDChqInsCo = totalDDChqInsCo + licPisDto.getDraftChqInsAmt();
				totalDDChqTieUpCo = totalDDChqTieUpCo + licPisDto.getDraftChqTieAmt();
				totalAmount = totalAmount + licPisDto.getTotalAmt();
			}
			
			if(licPisDtos.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Record(s) Found"));
				return;
			}else{
				renderedListPanel = true;
				renderedGeneratePanel = false;
			}
		}catch(Exception e){
			log.info("LicCmsSlipAction Search Error : ", e);
		}
	}
	
	public void calcDinominationListener(){
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
	}
	
	public void addCmsSlip(){
		try{			
			if(cmsMstDto.getPayMode() == null || cmsMstDto.getCmsNo() == null || cmsMstDto.getAmount() == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "Unable to Add CMS"));
				return;
			}
			if(cmsMstDto.getPayMode().equals("C")){
				if(cmsMstDto.getAmount().equals(cashCmsSlipDto.getTotal()) && cmsMstDto.getAmount().equals(totalCash)){
					licCmsMstDtoset.add(cmsMstDto);
					licCmsMstDtoList.clear();
					licCmsMstDtoList.addAll(licCmsMstDtoset);
					cmsMstDto = new LicCmsMstDto();
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error: ", "Please Add Cash Amount Equivalent To Total Cash Amount."));
					return;
				}
			}else if(cmsMstDto.getPayMode().equals("P")){
				if(cmsMstDto.getAmount().equals(totalDDChqInsCo)){
					licCmsMstDtoset.add(cmsMstDto);
					licCmsMstDtoList.clear();
					licCmsMstDtoList.addAll(licCmsMstDtoset);
					cmsMstDto = new LicCmsMstDto();
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error: ", "Please Add Parent Company DD/Cheque Amount Equivalent To Total DD/Cheque Amount of Parent Company"));
					return;
				}			
			}else if(cmsMstDto.getPayMode().equals("T")){
				if(cmsMstDto.getAmount().equals(totalDDChqTieUpCo)){
					licCmsMstDtoset.add(cmsMstDto);
					licCmsMstDtoList.clear();
					licCmsMstDtoList.addAll(licCmsMstDtoset);
					cmsMstDto = new LicCmsMstDto();
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                    "Error: ", "Please Add Tie Up DD/Cheque Amount Equivalent To Total DD/Cheque Amount Of Tie Up Company"));
					return;
				}
			}
		}catch(Exception e){
			log.info("LicCmsSlipAction addCmsSlip Error : ", e);
		}
	}
	
	public void deleteCms(LicCmsMstDto licCmsMstDto){
		licCmsMstDtoList.remove(licCmsMstDto);
	}
	

	/* GETTER SETTER */
	public Boolean getRenderedListPanel() {
		return renderedListPanel;
	}

	public void setRenderedListPanel(Boolean renderedListPanel) {
		this.renderedListPanel = renderedListPanel;
	}

	public List<LicPisDto> getLicPisDtos() {
		return licPisDtos;
	}

	public void setLicPisDtos(List<LicPisDto> licPisDtos) {
		this.licPisDtos = licPisDtos;
	}

	public List<LicOblApplicationMst> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicOblApplicationMst> selectedList) {
		this.selectedList = selectedList;
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

	public List<LicCmsMstDto> getLicCmsMstDtoList() {
		return licCmsMstDtoList;
	}

	public void setLicCmsMstDtoList(List<LicCmsMstDto> licCmsMstDtoList) {
		this.licCmsMstDtoList = licCmsMstDtoList;
	}

	public Date getSystemDate() {
		return systemDate;
	}

	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}

	public Set<LicCmsMstDto> getLicCmsMstDtoset() {
		return licCmsMstDtoset;
	}

	public void setLicCmsMstDtoset(Set<LicCmsMstDto> licCmsMstDtoset) {
		this.licCmsMstDtoset = licCmsMstDtoset;
	}

	public Boolean getRenderedGeneratePanel() {
		return renderedGeneratePanel;
	}

	public void setRenderedGeneratePanel(Boolean renderedGeneratePanel) {
		this.renderedGeneratePanel = renderedGeneratePanel;
	}
}
