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
import com.gtfs.bean.LicHubMst;
import com.gtfs.bean.LicOblApplicationMst;
import com.gtfs.bean.LicPisMst;
import com.gtfs.bean.LicRequirementDtls;
import com.gtfs.dto.CashCmsSlipDto;
import com.gtfs.dto.LicCmsMstDto;
import com.gtfs.dto.LicPisDto;
import com.gtfs.service.interfaces.LicBranchHubPicMappingService;
import com.gtfs.service.interfaces.LicHubMstService;
import com.gtfs.service.interfaces.LicPisMstService;
import com.gtfs.service.interfaces.LicRequirementDtlsService;

@Component
@Scope("session")
public class LicCmsSlipForReqAction implements Serializable{
	private Logger log = Logger.getLogger(LicCmsSlipForReqAction.class);
	
	@Autowired
	private LicPisMstService licPisMstService;
	@Autowired
	private LoginAction loginAction;
	@Autowired
	private LicRequirementDtlsService licRequirementDtlsService;
	@Autowired
	private LicBranchHubPicMappingService licBranchHubPicMappingService;
	@Autowired
	private LicHubMstService licHubMstService;
	
	private Date systemDate = new Date();
	private CashCmsSlipDto cashCmsSlipDto;
	private Boolean renderedListPanel;
	private Double totalAmount;
	private Double totalCash;
	private Double totalDDChqTieUpCo;
	private Double totalDDChqInsCo;
	private LicCmsMstDto cmsMstDto;
	
	private List<LicOblApplicationMst> selectedList =  new ArrayList<LicOblApplicationMst>();
	private List<LicPisDto> licPisDtos = new ArrayList<LicPisDto>();
	private List<LicCmsMstDto> licCmsMstDtoList = new ArrayList<LicCmsMstDto>();
	private Set<LicCmsMstDto> licCmsMstDtoset = new HashSet<LicCmsMstDto>();
	
	public void refresh(){
		systemDate = new Date();
		cashCmsSlipDto = new CashCmsSlipDto();
		cmsMstDto = new LicCmsMstDto();
		cashCmsSlipDto.setTotal(0.0);
		renderedListPanel = false;
		
		if(licPisDtos!=null){
			licPisDtos.clear();
		}
		
		if(licCmsMstDtoList!=null){
			licCmsMstDtoList.clear();
		}
	}
	
	
	public String onLoad(){
		refresh();
		return "/licBranchActivity/licCmsSlipForReq.xhtml";
	}
	
	public void save(){
		try{
			Date now = new Date();
			List<Long> destinationlist = licBranchHubPicMappingService.findHubIdForBranchIdByProcessName(loginAction.getUserList().get(0).getBranchMst().getBranchId(), "POS");
			
			if(destinationlist == null || destinationlist.size() == 0 || destinationlist.contains(null)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error ", "Branch Hub Mapping Not Defined"));
				return;
			}
			
			LicHubMst licHubMst = licHubMstService.findById(destinationlist.get(0));
			
			Double total = 0.0;
			
			LicPisMst licPisMst = new LicPisMst();
			licPisMst.setCreatedBy(loginAction.getUserList().get(0).getUserid());
			licPisMst.setModifiedBy(loginAction.getUserList().get(0).getUserid());
			licPisMst.setCreatedDate(now);
			licPisMst.setModifiedDate(now);
			licPisMst.setDeleteFlag("N");
			
			for(LicPisDto obj:licPisDtos){
				LicRequirementDtls licRequirementDtls = licRequirementDtlsService.findById(obj.getRequirementId());
				licRequirementDtls.setLicHubMst(licHubMst);
				licRequirementDtls.setLicPisMst(licPisMst);
				licRequirementDtls.setDispatchReadyFlag("Y");
				licPisMst.getLicRequirementDtlses().add(licRequirementDtls);
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
			log.info("LicCmsSlipForReqAction save Error : ", e);
		}
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
			licPisDtos.clear();
			
			List<Object> objectList = licPisMstService.findApplicationforPisForRequirement(loginAction.getUserList().get(0).getBranchMst());
			
			for(Object objList:objectList){
				Object[] objs = (Object[]) objList;
				LicPisDto licPisDto = new LicPisDto();
				licPisDto.setApplicationNo((String) objs[0]);
				licPisDto.setBusinessDate((Date) objs[1]);
				licPisDto.setCashAmt((Double) objs[2]);
				licPisDto.setDraftChqInsAmt(0.0);
				licPisDto.setDraftChqTieAmt(0.0);
				licPisDto.setTotalAmt((Double) objs[2]);
				licPisDto.setApplicationMstId((Long) objs[3]);
				licPisDto.setRequirementId((Long) objs[4]);
				licPisDtos.add(licPisDto);
				totalCash = totalCash + licPisDto.getCashAmt();
				totalDDChqInsCo = totalDDChqInsCo + licPisDto.getDraftChqInsAmt();
				totalDDChqTieUpCo = totalDDChqTieUpCo + licPisDto.getDraftChqTieAmt();
				totalAmount = totalAmount + licPisDto.getTotalAmt();
			}
			
			if(licPisDtos.size()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error : ", "No Records Found"));
			}else{
				renderedListPanel = true;
			}
		}catch(Exception e){
			log.info("LicCmsSlipForReqAction generateReport Error : ", e);
		}
	}
	
	public void addCmsSlip(){
		if(cmsMstDto.getPayMode().equals("C")){
			if(cmsMstDto.getAmount().equals(cashCmsSlipDto.getTotal()) && cmsMstDto.getAmount().equals(totalCash)){
				licCmsMstDtoset.add(cmsMstDto);
				licCmsMstDtoList.clear();
				licCmsMstDtoList.addAll(licCmsMstDtoset);
				cmsMstDto = new LicCmsMstDto();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Error: ", "Please Add Cash Amount Equivalent To Total Cash Amount."));
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
			}
		}		
	}
	
	public void deleteCms(LicCmsMstDto licCmsMstDto){
		licCmsMstDtoList.remove(licCmsMstDto);
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
	
	
	/* GETTER SETTER */
	public Date getSystemDate() {
		return systemDate;
	}

	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}

	public CashCmsSlipDto getCashCmsSlipDto() {
		return cashCmsSlipDto;
	}

	public void setCashCmsSlipDto(CashCmsSlipDto cashCmsSlipDto) {
		this.cashCmsSlipDto = cashCmsSlipDto;
	}

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

	public List<LicOblApplicationMst> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<LicOblApplicationMst> selectedList) {
		this.selectedList = selectedList;
	}

	public List<LicPisDto> getLicPisDtos() {
		return licPisDtos;
	}

	public void setLicPisDtos(List<LicPisDto> licPisDtos) {
		this.licPisDtos = licPisDtos;
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

	public Set<LicCmsMstDto> getLicCmsMstDtoset() {
		return licCmsMstDtoset;
	}

	public void setLicCmsMstDtoset(Set<LicCmsMstDto> licCmsMstDtoset) {
		this.licCmsMstDtoset = licCmsMstDtoset;
	}
}
